package thedungeon;

public class AbilityCaster {

    public int castAbility(int AI, Fighter myChar, Fighter daBoss) {
        int modifiedHealth = 0;
        if(myChar.name.equals("Ragnar")) {
            modifiedHealth = myChar.castWarriorAbility(AI, myChar, daBoss);
        } else if (myChar.name.equals("Blaze")) {
            modifiedHealth = myChar.castWizardAbility(AI, myChar, daBoss);
        }else if (myChar.name.equals("Shadow")) {
            modifiedHealth = myChar.castAssassinAbility(AI, myChar, daBoss);
        } else if (myChar.name.equals("One-Eye")) {
            modifiedHealth = myChar.castAlchemistAbility(AI, myChar, daBoss);
        } else {
            modifiedHealth = myChar.castNecroAbility(AI, myChar, daBoss);
        } // close if
        return modifiedHealth;
    } // close cast ability

    public int castBossAbility(int AI, Fighter myChar, Fighter daBoss) {
        int modifiedHealth = 0;
        if(daBoss.name.equals("The Orc King")) {
            modifiedHealth = daBoss.castOrcAbility(AI, myChar, daBoss);
        } else if (daBoss.name.equals("High Priest Genji")) {
            modifiedHealth = daBoss.castPriestAbility(AI, myChar, daBoss);
        }else if (daBoss.name.equals("Reptilian")) {
            modifiedHealth = daBoss.castReptileAbility(AI, myChar, daBoss);
        } else if (daBoss.name.equals("Kill-A-Tron 3000")) {
            modifiedHealth = daBoss.castMechAbility(AI, myChar, daBoss);
        } else {
            modifiedHealth = daBoss.castDeathAbility(AI, myChar, daBoss);
        } // close if
        return modifiedHealth;
    } // close cast boss ability

    public int death(Fighter me, Fighter daBoss) {
        int death = 0;
        if (me.health <= death) {
            System.out.println("\n- " + me.name + " has died! " + daBoss.name + " wins! -");
            death = 1;
        } // close userDeath
        if (daBoss.health <= death) {
            System.out.println("\n- " + daBoss.name + " has died! " + me.name + " wins! -");
            death = 1;
        } // close bossDeath
        return death;
    } // close death
} // close ability caster
