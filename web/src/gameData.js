export const classes = [
  {
    id: 'warrior',
    role: 'Warrior',
    heroName: 'Ragnar',
    summary: 'Heavy melee fighter built for direct damage and staying power.',
    health: 5000,
    details: [
      'Giant | Armored | Dual-Wielded Longswords | Trained',
      'Inspirational | Extremely Strong | Righteous',
    ],
    abilities: [
      {
        id: 'whirlwind',
        name: 'Whirlwind',
        description: 'Spin Your Axe Around Dealing (75 - 125) Damage 3 Times',
        effect: { type: 'multi', hits: 3, base: 75, spread: 50 },
      },
      {
        id: 'haymaker',
        name: 'Haymaker',
        description:
          'Swing Your Axe with All Your Force Dealing (450 - 600) Damage with a 50% Chance to Miss',
        effect: { type: 'single-miss', base: 450, spread: 150, missChance: 0.5 },
      },
      {
        id: 'catapult',
        name: 'Catapult',
        description:
          'Grab an Object Near You & Hurl it Towards Your Opponenent Dealing (175 - 225) Damage with a 50% Chance to Grab a Heavy Object & Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
    ],
  },
  {
    id: 'wizard',
    role: 'Wizard',
    heroName: 'Blaze',
    summary: 'Spell-focused attacker with elemental pressure and burst turns.',
    health: 5000,
    details: [
      'Elf | Long Staff | Fiery Eyes | Disciplined',
      'Telekenetic | Controls Elements | Can Fly',
    ],
    abilities: [
      {
        id: 'lightning-bolt',
        name: 'Lightning-Bolt',
        description: 'Direct a Blast of Energy Dealing (250 - 350) Damage',
        effect: { type: 'single', base: 250, spread: 100 },
      },
      {
        id: 'meteor-shower',
        name: 'Meteor-Shower',
        description:
          'Rain Fire Down on Your Opponent Dealing (75 - 125) Damage 5 Times with a 33% Chance to Miss Each Shot',
        effect: {
          type: 'multi-miss',
          hits: 5,
          base: 75,
          spread: 50,
          missChance: 1 / 3,
        },
      },
      {
        id: 'conjure-beast',
        name: 'Conjure-Beast',
        description:
          'Summons a Wolf to Attack for you Dealing (175 - 225) Damage with a 50% Chance to Summon a Lion to Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
    ],
  },
  {
    id: 'assassin',
    role: 'Assassin',
    heroName: 'Shadow',
    summary: 'Agile striker that relies on precision, speed, and trickery.',
    health: 5000,
    details: [
      'Unknown ( Species ) | Dual Daggers | Hood | Cant See Face',
      'Teleportation | Disguises | Loki Level Cunningness',
    ],
    abilities: [
      {
        id: 'tricks',
        name: 'Tricks',
        description:
          'Perform 1 of 3 Mind Games To Play with & Terrorize Your Opponent Dealing (250 - 350) Damage',
        effect: {
          type: 'single',
          base: 250,
          spread: 50,
          flavors: ['Whos Ya Daddy!', 'Love At First Sight!', 'Imposter!'],
        },
      },
      {
        id: 'backstab',
        name: 'Backstab',
        description:
          'Teleport Behind Your Opponent & Stab Them Attempting to Hit a Weak-Spot Dealing (175 - 225) Damage, if Weak-Spot is Exposed Deal Double Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
      {
        id: 'shurikens',
        name: 'Shurikens',
        description:
          'Rapid Fire Ninja Stars Towards Your Opponent Dealing (50 - 100) Damage Twice, Three Times in a Row with a 33% Chance to Miss Each Shot',
        effect: {
          type: 'multi-double',
          hits: 3,
          base: 50,
          spread: 50,
          multiplier: 2,
          missChance: 1 / 3,
        },
      },
    ],
  },
  {
    id: 'alchemist',
    role: 'Alchemist',
    heroName: 'One-Eye',
    summary: 'Unpredictable caster mixing strange tools and battlefield control.',
    health: 5000,
    details: [
      'Goblin | Hysterical | Glass Eye | Always Joking Around',
      'Scientist | Potions | Doesnt Care',
    ],
    abilities: [
      {
        id: 'fire-bomb',
        name: 'Fire-Bomb',
        description:
          'Brew a Potion Infused with Fire & Glass Then Launch it at Your Opponent Dealing (250 - 350) Damage',
        effect: { type: 'single', base: 250, spread: 50 },
      },
      {
        id: 'freeze-concoction',
        name: 'Freeze-Concoction',
        description:
          'Brew a Potion Infused with Ice & Wind Then Hurl it at the Ground Dealing (450 - 600) Damage with a 50% Chance to Freeze Yourself & Miss',
        effect: { type: 'single-miss', base: 450, spread: 150, missChance: 0.5 },
      },
      {
        id: 'time-spell',
        name: 'Time-Spell',
        description:
          'Brew a Potion Infused with the Sun & the Moon to Slow Time Down Dealing (175 - 225) Damage with a 50% Chance to Reverse Time & Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
    ],
  },
  {
    id: 'necromancer',
    role: 'Necromancer',
    heroName: 'Rot',
    summary: 'Dark magic specialist with decay, corruption, and summoned force.',
    health: 5000,
    details: [
      'Undead | Corrupted Staff | Withered | Dark',
      'Controls Dead | Dark Magic | Immortal',
    ],
    abilities: [
      {
        id: 'deteriorate',
        name: 'Deteriorate',
        description:
          'Curse Your Opponent with a Degeneration Spell Causing There Body to Fall Apart Dealing (75 - 125) Damage 5 Times with a 33% Chance to Miss Each Shot',
        effect: {
          type: 'multi-miss',
          hits: 5,
          base: 75,
          spread: 50,
          missChance: 1 / 3,
        },
      },
      {
        id: 'possess',
        name: 'Possess',
        description:
          'Take Control of Your Opponents Mind & Make Them Snap Their Own Arm Dealing (175 - 225) Damage with a 50% Chance to Make Them Stab Themselves & Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
      {
        id: 'raise-army',
        name: 'Raise Army',
        description:
          'Resurrect Over a Thousand Corpses From Their Graves to Swarm Your Opponent Dealing (50 - 100) Damage 7 Times with a 33% Chance to Miss Each Shot',
        effect: {
          type: 'multi-miss',
          hits: 7,
          base: 50,
          spread: 50,
          missChance: 1 / 3,
        },
      },
    ],
  },
]

export const bosses = [
  {
    id: 'boss1',
    level: 1,
    title: 'The Orc King',
    role: 'Level 1',
    health: 1000,
    summary: 'The first gatekeeper of the dungeon, brutal and straightforward.',
    details: [
      "Green | 12' Tall | 550 Pounds | Sharp Tusks",
      'Dim-Witted | Savage | Fierce | Angry',
    ],
    abilities: [
      {
        id: 'stomp',
        name: 'Stomp',
        description:
          'Jump High Into The Air & Land on Your Opponent, Crushing Them & Dealing (250 - 350) Damage',
        effect: { type: 'single', base: 250, spread: 100 },
      },
      {
        id: 'double-sided-axe',
        name: 'Double-Sided-Axe',
        description:
          'Swing Your Axe With All Your Body Weight for an Overpowered Attack Dealing (450 - 600) Damage with a 50% Chance to Miss',
        effect: { type: 'single-miss', base: 450, spread: 150, missChance: 0.5 },
      },
      {
        id: 'charge',
        name: 'Charge',
        description:
          'Sprint Directly at Your Opponent & Trample Over Them Dealing (175 - 225) Damage with a 50% Chance to Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
    ],
  },
  {
    id: 'boss2',
    level: 2,
    title: 'High Priest Genji',
    role: 'Level 2',
    health: 1250,
    summary: 'A fanatical caster who mixes holy force with darker power.',
    details: [
      'Elf | Blonde Hair | Blue Eyes | Pointy Ears',
      'Extremely Intelligent | Egotistical | Insane',
    ],
    abilities: [
      {
        id: 'smite',
        name: 'Smite',
        description:
          'Strike Down Your Opponent With Holy Light Dealing (250 - 350) Damage',
        effect: { type: 'single', base: 250, spread: 100 },
      },
      {
        id: 'invocation',
        name: 'Invocation',
        description:
          'Call Upon the Word of God Himself reading a Scripture Dealing (450 - 600) Damage with a 50% Chance to Miss',
        effect: { type: 'single-miss', base: 400, spread: 150, missChance: 0.5 },
      },
      {
        id: 'shadow-bolt',
        name: 'Shadow-Bolt',
        description:
          'Call Upon Evil to Harness Dark Magic & Then Project the Energy Forward Dealing (175 - 225) Damage with a 50% Chance to Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
    ],
  },
  {
    id: 'boss3',
    level: 3,
    title: 'Reptilian',
    role: 'Level 3',
    health: 1500,
    summary: 'A feral speedster that pressures with repeated bleeding attacks.',
    details: [
      'Lizard | Green | Slimey | Scaled',
      'Incredibly Fast | Deranged | Frenzied',
    ],
    abilities: [
      {
        id: 'acid-spit',
        name: 'Acid-Spit',
        description:
          'Shoot a Ball of Acid From Your Stomach at Your Opponent Dealing (250 - 350) Damage',
        effect: { type: 'single', base: 250, spread: 100 },
      },
      {
        id: 'devour',
        name: 'Devour',
        description:
          'Take a Bite Out of Your Opponents Neck Causing Them to Bleed Out Dealing (75 - 125) Damage 4 Times with a 33% Chance to Miss Each',
        effect: {
          type: 'multi-miss',
          hits: 4,
          base: 125,
          spread: 50,
          missChance: 1 / 3,
        },
      },
      {
        id: 'dash',
        name: 'Dash',
        description:
          'Dart to Your Opponent & Slash Their Chest With Your Claws Dealing (175 - 225) Damage with a 50% Chance to Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 175,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
    ],
  },
  {
    id: 'boss4',
    level: 4,
    title: 'Kill-A-Tron 3000',
    role: 'Level 4',
    health: 1750,
    summary: 'A weaponized machine that overwhelms with volume and follow-up hits.',
    details: [
      'Mech | Steel | Robot | Weaponized',
      'Emotionless | Aggressive | Designed to Kill',
    ],
    abilities: [
      {
        id: 'minigun',
        name: 'MiniGun',
        description:
          'Convert Your Arms Into Gatlin Guns and Unload Dealing (75 - 125) Damage 4 Times',
        effect: { type: 'multi', hits: 4, base: 75, spread: 50 },
      },
      {
        id: 'heat-seeking-missles',
        name: 'Heat-Seeking-Missles',
        description:
          'Convert Your Arms Into a Rocket Launcher and Fire Missles Dealing (75 - 125) Damage 6 Times with a 33% Chance to Miss Each',
        effect: {
          type: 'multi-miss',
          hits: 6,
          base: 75,
          spread: 50,
          missChance: 1 / 3,
        },
      },
      {
        id: 'double-katana',
        name: 'Double-Katana',
        description:
          'Convert Your Arms Into 2 Samurai Swords and Slash Your Opponent Dealing (275 - 325) Damage with a 75% Chance to Hit, if Successful Perform again with a 50% Chance',
        effect: {
          type: 'mech-double',
          base: 275,
          spread: 50,
          firstHitChance: 0.75,
          secondHitChance: 0.5,
        },
      },
    ],
  },
  {
    id: 'boss5',
    level: 5,
    title: 'Death',
    role: 'Level 5',
    health: 2500,
    summary: 'The final boss, relentless and punishing enough to end a run fast.',
    details: [
      'Spirit | Dark | Shadow | Reaper',
      'Feels Nothing | Drags People to Underworld | Unstoppable',
    ],
    abilities: [
      {
        id: 'haunt',
        name: 'Haunt',
        description:
          'Terrify Your Opponent by Following Them But Never Confronting Them Dealing (375 - 425) Damage',
        effect: { type: 'single', base: 375, spread: 50 },
      },
      {
        id: 'drain-soul',
        name: 'Drain-Soul',
        description:
          'Drain the Life Force Out of Your Opponent Dealing (50 - 100) Damage 8 Times with a 33% Chance to Miss Each',
        effect: {
          type: 'multi-miss',
          hits: 8,
          base: 50,
          spread: 50,
          missChance: 1 / 3,
        },
      },
      {
        id: 'corrupt',
        name: 'Corrupt',
        description:
          'Rot Your Opponents Body Dealing (225 - 275) Damage with a 50% Chance to Corrupt Internal Organs & Deal Critical Damage',
        effect: {
          type: 'single-crit',
          base: 225,
          spread: 50,
          critChance: 0.5,
          critMultiplier: 2,
        },
      },
    ],
  },
]
