import { useEffect, useState } from 'react'
import './App.css'
import { bosses, classes } from './gameData'
import { createBattleState, resolveBossTurn, resolvePlayerTurn } from './gameLogic'

function getHealthPercent(current, max) {
  return Math.max(0, Math.min(100, (current / max) * 100))
}

function getBoostedRunState(nextBoss, runState) {
  if (nextBoss.level === 4) {
    return {
      hp: 6000,
      maxHp: 6000,
    }
  }

  return runState
}

function App() {
  const [screen, setScreen] = useState('landing')
  const [selectedClassId, setSelectedClassId] = useState(null)
  const [currentBossIndex, setCurrentBossIndex] = useState(0)
  const [runState, setRunState] = useState(null)
  const [battleState, setBattleState] = useState(null)

  const currentClass = classes.find((fighterClass) => fighterClass.id === selectedClassId)
  const currentBoss = bosses[currentBossIndex]

  useEffect(() => {
    if (screen !== 'battle' || battleState?.status !== 'waiting') {
      return undefined
    }

    const timeoutId = window.setTimeout(() => {
      setBattleState((currentBattleState) => {
        const nextBattleState = resolveBossTurn(currentBattleState)

        if (nextBattleState.status === 'defeat') {
          setScreen('defeat')
        }

        return nextBattleState
      })
    }, 1400)

    return () => window.clearTimeout(timeoutId)
  }, [battleState, screen])

  function beginRun() {
    if (!currentClass) {
      return
    }

    setCurrentBossIndex(0)
    setRunState({
      hp: currentClass.health,
      maxHp: currentClass.health,
    })
    setBattleState(null)
    setScreen('matchup')
  }

  function startEncounter(playerState = runState) {
    if (!currentClass || !currentBoss) {
      return
    }

    setBattleState(createBattleState(currentClass, currentBoss, playerState))
    setScreen('battle')
  }

  function handleAbilityChoice(abilityIndex) {
    setBattleState((currentBattleState) => {
      const nextBattleState = resolvePlayerTurn(currentBattleState, abilityIndex)

      if (nextBattleState.status === 'victory') {
        setRunState({
          hp: nextBattleState.player.hp,
          maxHp: nextBattleState.player.maxHp,
        })

        if (currentBossIndex === bosses.length - 1) {
          setScreen('final-victory')
        } else {
          setScreen('victory')
        }
      } else if (nextBattleState.status === 'defeat') {
        setScreen('defeat')
      }

      return nextBattleState
    })
  }

  function retryCurrentEncounter() {
    if (!currentClass || !currentBoss) {
      return
    }

    const retryState =
      battleState?.player
        ? {
            hp: battleState.player.maxHp,
            maxHp: battleState.player.maxHp,
          }
        : runState

    setBattleState(createBattleState(currentClass, currentBoss, retryState))
    setScreen('battle')
  }

  function moveToNextBoss() {
    if (!currentClass || !battleState || currentBossIndex >= bosses.length - 1) {
      return
    }

    const nextBossIndex = currentBossIndex + 1
    const nextBoss = bosses[nextBossIndex]
    const baseRunState = {
      hp: battleState.player.hp,
      maxHp: battleState.player.maxHp,
    }
    const nextRunState = getBoostedRunState(nextBoss, baseRunState)

    setCurrentBossIndex(nextBossIndex)
    setRunState(nextRunState)
    setBattleState(null)
    setScreen('matchup')
  }

  function returnToClassSelect() {
    setScreen('role-select')
    setCurrentBossIndex(0)
    setRunState(null)
    setBattleState(null)
  }

  return (
    <main className="app-shell">
      {screen === 'landing' ? (
        <section className="landing-panel">
          <h1>The Dungeon</h1>
          <p className="lead">
            Escape the dungeon by surviving five boss fights. Choose a class,
            learn its abilities, and battle one monster at a time.
          </p>

          <div className="feature-list" aria-label="Game goals">
            <p>5 playable classes</p>
            <p>5 escalating boss battles</p>
            <p>3 abilities per fighter</p>
          </div>

          <button
            className="start-button"
            type="button"
            onClick={() => setScreen('role-select')}
          >
            Start Adventure
          </button>
        </section>
      ) : screen === 'role-select' ? (
        <section className="selection-panel">
          <div className="selection-header">
            <div>
              <h2>Choose Your Class</h2>
            </div>
            <button
              className="ghost-button"
              type="button"
              onClick={() => setScreen('landing')}
            >
              Back
            </button>
          </div>

          <p className="selection-copy">
            Pick the hero you want to carry through all five dungeon fights.
          </p>

          <div className="class-grid">
            {classes.map((fighterClass) => {
              const isSelected = fighterClass.id === selectedClassId

              return (
                <button
                  key={fighterClass.id}
                  className={`class-card${isSelected ? ' selected' : ''}`}
                  type="button"
                  onClick={() => setSelectedClassId(fighterClass.id)}
                >
                  <span className="class-role">{fighterClass.role}</span>
                  <strong>{fighterClass.heroName}</strong>
                  <span className="class-summary">{fighterClass.summary}</span>
                </button>
              )
            })}
          </div>

          <section className="class-preview" aria-live="polite">
            {currentClass ? (
              <>
                <p className="eyebrow">Selected Hero</p>
                <h3>
                  {currentClass.role}: {currentClass.heroName}
                </h3>
                <p className="selection-copy">{currentClass.summary}</p>
                <div className="preview-details">
                  {currentClass.details.map((detail) => (
                    <p key={detail}>{detail}</p>
                  ))}
                </div>
                <button className="start-button" type="button" onClick={beginRun}>
                  Continue
                </button>
              </>
            ) : (
              <>
                <p className="eyebrow">Selected Hero</p>
                <h3>No class selected yet</h3>
                <p className="selection-copy">
                  Choose one of the five classes above to preview your fighter.
                </p>
              </>
            )}
          </section>
        </section>
      ) : screen === 'matchup' ? (
        <section className="selection-panel matchup-panel">
          {currentBoss.level === 4 ? (
            <div className="end-highlight success-highlight matchup-boost">
              <p>Health Boost Found</p>
              <p>Your hero is fully restored and the max health rises to 6000 HP.</p>
            </div>
          ) : null}

          <div className="matchup-grid">
            <article className="combatant-card hero-card">
              <p className="eyebrow">Your Hero</p>
              <h3>
                {currentClass.role}: {currentClass.heroName}
              </h3>
              <p className="combatant-health">
                Health: {runState?.hp ?? currentClass.health} /{' '}
                {runState?.maxHp ?? currentClass.health} HP
              </p>
              <div className="preview-details">
                {currentClass.details.map((detail) => (
                  <p key={detail}>{detail}</p>
                ))}
              </div>
              <div className="abilities-panel">
                <p className="eyebrow">Abilities</p>
                {currentClass.abilities.map((ability) => (
                  <div className="ability-row" key={ability.id}>
                    <strong>{ability.name}</strong>
                    <span>{ability.description}</span>
                  </div>
                ))}
              </div>
            </article>

            <article className="combatant-card boss-card">
              <p className="eyebrow">Boss {currentBoss.level}</p>
              <h3>{currentBoss.title}</h3>
              <p className="combatant-health">Health: {currentBoss.health} HP</p>
              <div className="preview-details">
                {currentBoss.details.map((detail) => (
                  <p key={detail}>{detail}</p>
                ))}
              </div>
              <div className="abilities-panel">
                <p className="eyebrow">Abilities</p>
                {currentBoss.abilities.map((ability) => (
                  <div className="ability-row" key={ability.id}>
                    <strong>{ability.name}</strong>
                    <span>{ability.description}</span>
                  </div>
                ))}
              </div>
            </article>
          </div>

          <div className="actions-row">
            <button className="ghost-button" type="button" onClick={returnToClassSelect}>
              Change Class
            </button>
            <button className="start-button" type="button" onClick={() => startEncounter()}>
              Enter The Dungeon
            </button>
          </div>
        </section>
      ) : screen === 'battle' ? (
        <section className="battle-panel">
          <div className="selection-header">
            <div>
              <h2>FIGHT!</h2>
            </div>
            <button
              className="ghost-button"
              type="button"
              onClick={() => setScreen('matchup')}
            >
              Back To Info
            </button>
          </div>

          <div className="battle-status-bar">
            <div>
              <p className="status-label">Round</p>
              <strong>{battleState?.round ?? 1}</strong>
            </div>
            <div>
              <p className="status-label">Encounter</p>
              <strong>
                Boss {currentBoss.level} / {bosses.length}
              </strong>
            </div>
          </div>

          {battleState ? (
            <>
              <div className="battle-grid">
                <article className="battle-card player-side">
                  <div className="battle-card-head">
                    <div>
                      <p className="eyebrow">Hero</p>
                      <h3>{battleState.player.name}</h3>
                    </div>
                    <span className="battle-role">{battleState.player.role}</span>
                  </div>

                  <div className="health-block">
                    <div className="health-row">
                      <span>Health</span>
                      <strong>
                        {battleState.player.hp} / {battleState.player.maxHp}
                      </strong>
                    </div>
                    <div className="health-track" aria-hidden="true">
                      <div
                        className="health-fill hero-fill"
                        style={{
                          width: `${getHealthPercent(
                            battleState.player.hp,
                            battleState.player.maxHp,
                          )}%`,
                        }}
                      />
                    </div>
                  </div>

                  <div className="abilities-panel battle-abilities">
                    <p className="eyebrow">Cast Ability</p>
                    {battleState.player.abilities.map((ability, index) => (
                      <button
                        key={ability.id}
                        className="ability-button"
                        type="button"
                        onClick={() => handleAbilityChoice(index)}
                        disabled={battleState.status !== 'active'}
                      >
                        <strong>{ability.name}</strong>
                        <span>{ability.description}</span>
                      </button>
                    ))}
                  </div>
                </article>

                <section className="log-panel battle-log-panel">
                  <div className="selection-header compact-header">
                    <div>
                      <p className="eyebrow">Combat Log</p>
                      <h3>Latest Actions</h3>
                    </div>
                  </div>
                  <div className="log-list">
                    {[...battleState.log].reverse().map((entry, index) =>
                      entry === '-----------' ? (
                        <p className="log-divider" key={`${entry}-${index}`}>
                          -----------
                        </p>
                      ) : (
                        <p className="log-entry" key={`${entry}-${index}`}>
                          {entry}
                        </p>
                      ),
                    )}
                  </div>
                </section>

                <article className="battle-card boss-side">
                  <div className="battle-card-head">
                    <div>
                      <p className="eyebrow">Boss {battleState.boss.level}</p>
                      <h3>{battleState.boss.name}</h3>
                    </div>
                    <span className="battle-role">{battleState.boss.role}</span>
                  </div>

                  <div className="health-block">
                    <div className="health-row">
                      <span>Health</span>
                      <strong>
                        {battleState.boss.hp} / {battleState.boss.maxHp}
                      </strong>
                    </div>
                    <div className="health-track" aria-hidden="true">
                      <div
                        className="health-fill boss-fill"
                        style={{
                          width: `${getHealthPercent(
                            battleState.boss.hp,
                            battleState.boss.maxHp,
                          )}%`,
                        }}
                      />
                    </div>
                  </div>

                  <div className="boss-abilities">
                    <p className="eyebrow">Known Boss Abilities</p>
                    {battleState.boss.abilities.map((ability) => (
                      <div className="ability-row" key={ability.id}>
                        <strong>{ability.name}</strong>
                        <span>{ability.description}</span>
                      </div>
                    ))}
                  </div>
                </article>
              </div>

              <div className="result-actions battle-actions">
                <button className="ghost-button" type="button" onClick={retryCurrentEncounter}>
                  Restart Encounter
                </button>
                <button className="ghost-button" type="button" onClick={returnToClassSelect}>
                  Change Class
                </button>
              </div>
            </>
          ) : null}
        </section>
      ) : screen === 'victory' ? (
        <section className="end-panel victory-panel">
          <p className="eyebrow">Victory</p>
          <h2>{battleState?.boss.name ?? currentBoss.title} Defeated</h2>
          <p className="lead end-copy">
            {battleState?.player.name ?? currentClass?.heroName} survives the duel
            and descends deeper into the dungeon.
          </p>

          <div className="end-highlight success-highlight">
            <p>
              Boss {currentBoss.level} down, {bosses.length - currentBoss.level} to go
            </p>
            <p>
              Carrying forward {battleState?.player.hp ?? runState?.hp} /{' '}
              {battleState?.player.maxHp ?? runState?.maxHp} HP
            </p>
          </div>

          <section className="end-log-panel">
            <p className="eyebrow">Final Moments</p>
            <div className="log-list">
              {[...(battleState?.log ?? [])].slice(-5).reverse().map((entry, index) => (
                <p className="log-entry" key={`${entry}-${index}`}>
                  {entry}
                </p>
              ))}
            </div>
          </section>

          <div className="result-actions centered-actions">
            <button className="start-button" type="button" onClick={moveToNextBoss}>
              Descend Deeper
            </button>
            <button className="ghost-button" type="button" onClick={returnToClassSelect}>
              Choose Another Class
            </button>
          </div>
        </section>
      ) : screen === 'final-victory' ? (
        <section className="end-panel final-victory-panel">
          <p className="eyebrow">Congratulations</p>
          <h2>The Dungeon Is Conquered</h2>
          <p className="lead end-copy">
            {battleState?.player.name ?? currentClass?.heroName} defeats every boss in
            the dungeon and escapes with the full campaign cleared.
          </p>

          <div className="end-highlight success-highlight">
            <p>All 5 bosses defeated</p>
            <p>
              Final HP: {battleState?.player.hp ?? runState?.hp} /{' '}
              {battleState?.player.maxHp ?? runState?.maxHp}
            </p>
          </div>

          <section className="end-log-panel">
            <p className="eyebrow">Final Moments</p>
            <div className="log-list">
              {[...(battleState?.log ?? [])].slice(-5).reverse().map((entry, index) => (
                <p className="log-entry" key={`${entry}-${index}`}>
                  {entry}
                </p>
              ))}
            </div>
          </section>

          <div className="result-actions centered-actions">
            <button
              className="start-button"
              type="button"
              onClick={() => {
                beginRun()
              }}
            >
              Start New Run
            </button>
            <button className="ghost-button" type="button" onClick={returnToClassSelect}>
              Choose Another Class
            </button>
          </div>
        </section>
      ) : (
        <section className="end-panel defeat-panel">
          <p className="eyebrow">Game Over</p>
          <h2>{battleState?.player.name ?? currentClass?.heroName} Has Fallen</h2>
          <p className="lead end-copy">
            {battleState?.boss.name ?? currentBoss.title} holds the line. The run
            ends here, but the dungeon is still waiting.
          </p>

          <div className="end-highlight defeat-highlight">
            <p>
              Defeated by Boss {currentBoss.level}: {battleState?.boss.name ?? currentBoss.title}
            </p>
            <p>
              Boss HP Left: {battleState?.boss.hp ?? currentBoss.health} /{' '}
              {battleState?.boss.maxHp ?? currentBoss.health}
            </p>
          </div>

          <section className="end-log-panel">
            <p className="eyebrow">Final Moments</p>
            <div className="log-list">
              {[...(battleState?.log ?? [])].slice(-5).reverse().map((entry, index) => (
                <p className="log-entry" key={`${entry}-${index}`}>
                  {entry}
                </p>
              ))}
            </div>
          </section>

          <div className="result-actions centered-actions">
            <button className="start-button" type="button" onClick={beginRun}>
              Start New Run
            </button>
            <button className="ghost-button" type="button" onClick={returnToClassSelect}>
              Choose Another Class
            </button>
          </div>
        </section>
      )}
    </main>
  )
}

export default App
