const MAX_LOG_ENTRIES = 12

function randomInt(max) {
  return Math.floor(Math.random() * max)
}

function randomDamage(base, spread) {
  return base + randomInt(spread)
}

function clampHealth(value) {
  return Math.max(0, value)
}

function keepRecentLogs(log) {
  return log.slice(-MAX_LOG_ENTRIES)
}

function summarizeHits(values) {
  return values.join(', ')
}

function formatAbilityName(name) {
  return name.toUpperCase()
}

function resolveAbility(actor, target, ability) {
  const { effect } = ability
  const logs = []
  const abilityLabel = formatAbilityName(ability.name)
  let damage = 0

  switch (effect.type) {
    case 'single': {
      damage = randomDamage(effect.base, effect.spread)
      logs.push(`${abilityLabel}: ${damage} Damage`)
      break
    }
    case 'single-miss': {
      if (Math.random() < effect.missChance) {
        logs.push(`${abilityLabel}: MISS!`)
      } else {
        damage = randomDamage(effect.base, effect.spread)
        logs.push(`${abilityLabel}: ${damage} Damage`)
      }
      break
    }
    case 'single-crit': {
      const rolledDamage = randomDamage(effect.base, effect.spread)
      const isCrit = Math.random() < effect.critChance
      damage = isCrit ? rolledDamage * effect.critMultiplier : rolledDamage
      logs.push(isCrit ? `${abilityLabel}: CRITICAL! ${damage} Damage` : `${abilityLabel}: ${damage} Damage`)
      break
    }
    case 'multi': {
      const hits = Array.from({ length: effect.hits }, () =>
        randomDamage(effect.base, effect.spread),
      )
      damage = hits.reduce((total, value) => total + value, 0)
      logs.push(`${abilityLabel}: ${summarizeHits(hits)} - (${damage}) Damage`)
      break
    }
    case 'multi-miss': {
      const hits = []
      let misses = 0

      for (let index = 0; index < effect.hits; index += 1) {
        if (Math.random() < effect.missChance) {
          misses += 1
        } else {
          hits.push(randomDamage(effect.base, effect.spread))
        }
      }

      damage = hits.reduce((total, value) => total + value, 0)

      if (hits.length === 0) {
        logs.push(`${abilityLabel}: MISS!`)
      } else {
        logs.push(`${abilityLabel}: ${summarizeHits(hits)} - (${damage}) Damage`)
      }
      break
    }
    case 'multi-double': {
      const hits = []
      let misses = 0

      for (let index = 0; index < effect.hits; index += 1) {
        if (Math.random() < effect.missChance) {
          misses += 1
        } else {
          hits.push(randomDamage(effect.base, effect.spread) * effect.multiplier)
        }
      }

      damage = hits.reduce((total, value) => total + value, 0)

      if (hits.length === 0) {
        logs.push(`${abilityLabel}: MISS!`)
      } else {
        logs.push(`${abilityLabel}: ${summarizeHits(hits)} - (${damage}) Damage`)
      }
      break
    }
    case 'mech-double': {
      const hits = []

      if (Math.random() < effect.firstHitChance) {
        hits.push(randomDamage(effect.base, effect.spread))

        if (Math.random() < effect.secondHitChance) {
          hits.push(randomDamage(effect.base, effect.spread))
        }
      }

      damage = hits.reduce((total, value) => total + value, 0)

      if (hits.length === 0) {
        logs.push(`${abilityLabel}: MISS!`)
      } else if (hits.length === 1) {
        logs.push(`${abilityLabel}: ${damage} Damage`)
      } else {
        logs.push(`${abilityLabel}: ${summarizeHits(hits)} - (${damage}) Damage`)
      }
      break
    }
    default: {
      logs.push('MISS!')
    }
  }

  return {
    damage,
    logs,
    nextTargetHp: clampHealth(target.hp - damage),
  }
}

export function createBattleState(playerTemplate, bossTemplate, playerState = {}) {
  const playerMaxHp = playerState.maxHp ?? playerTemplate.health
  const playerHp = playerState.hp ?? playerMaxHp

  return {
    round: 1,
    status: 'active',
    player: {
      id: playerTemplate.id,
      role: playerTemplate.role,
      name: playerTemplate.heroName,
      summary: playerTemplate.summary,
      maxHp: playerMaxHp,
      hp: playerHp,
      abilities: playerTemplate.abilities,
    },
    boss: {
      id: bossTemplate.id,
      role: bossTemplate.role,
      name: bossTemplate.title,
      summary: bossTemplate.summary,
      level: bossTemplate.level,
      maxHp: bossTemplate.health,
      hp: bossTemplate.health,
      abilities: bossTemplate.abilities,
    },
    log: keepRecentLogs([
      `ROUND 1: ${playerTemplate.heroName.toUpperCase()} VS ${bossTemplate.title.toUpperCase()}`,
      'Choose one of your three abilities.',
    ]),
  }
}

export function resolvePlayerTurn(currentState, playerAbilityIndex) {
  if (!currentState || currentState.status !== 'active') {
    return currentState
  }

  const playerAbility = currentState.player.abilities[playerAbilityIndex]

  if (!playerAbility) {
    return currentState
  }

  const playerResult = resolveAbility(currentState.player, currentState.boss, playerAbility)
  let updatedState = {
    ...currentState,
    boss: {
      ...currentState.boss,
      hp: playerResult.nextTargetHp,
    },
    log: keepRecentLogs([...currentState.log, ...playerResult.logs]),
  }

  if (playerResult.nextTargetHp <= 0) {
    return {
      ...updatedState,
      status: 'victory',
      log: keepRecentLogs([
        ...updatedState.log,
        `${updatedState.boss.name.toUpperCase()} DEFEATED!`,
      ]),
    }
  }

  return {
    ...updatedState,
    status: 'waiting',
  }
}

export function resolveBossTurn(currentState) {
  if (!currentState || currentState.status !== 'waiting') {
    return currentState
  }

  const bossAbility = currentState.boss.abilities[randomInt(currentState.boss.abilities.length)]
  const bossResult = resolveAbility(currentState.boss, currentState.player, bossAbility)

  const updatedState = {
    ...currentState,
    round: currentState.round + 1,
    status: 'active',
    player: {
      ...currentState.player,
      hp: bossResult.nextTargetHp,
    },
    log: keepRecentLogs([...currentState.log, ...bossResult.logs, '-----------']),
  }

  if (bossResult.nextTargetHp <= 0) {
    return {
      ...updatedState,
      status: 'defeat',
      log: keepRecentLogs([
        ...updatedState.log,
        `${updatedState.player.name.toUpperCase()} HAS FALLEN!`,
      ]),
    }
  }

  return updatedState
}
