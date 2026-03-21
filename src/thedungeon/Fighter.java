package thedungeon;

import static thedungeon.Main.displayTotal;
import static thedungeon.Main.randomizer;
import static thedungeon.Main.userInput;

public class Fighter {

    Abilities abObj = new Abilities();
    public String role;
    public String name;
    public int health;
    public String[] roleDesc = new String[2];
    // private String roleDesc;

    public Fighter() {
        role = "N/A";
        name = "N/A";
        health = 1000;
    } // close defaultConstructor

    public Fighter(String loadedRole, String loadedName, int loadedHealth) {
        role = loadedRole;
        name = loadedName;
        health = loadedHealth;
        // roleDesc = loadedRoleDesc;
    } // close overloadedConstructor


    public String toString() {
        return role + ": " + name + "\n" + roleDesc[0] + "\n" + roleDesc[1]
                + "\nAbilities:\n " + abObj.abilityName[0] + " |" + abObj.abilityDesc[0]
                + "\n " + abObj.abilityName[1] + " |" + abObj.abilityDesc[1]
                + "\n " + abObj.abilityName[2] + " |" + abObj.abilityDesc[2];
    };

    // methods

    public int castWarriorAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {
            int choose = 3;
            castAbility = randomizer.nextInt(choose);
        } else {
            System.out.println("Cast Ability: \n1: " + myChar.abObj.abilityName[0] + ", 2: " + myChar.abObj.abilityName[1] + ", 3: " + myChar.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;
        } // close AI

        if (castAbility == 0) {

            System.out.println("- " + myChar.abObj.abilityName[0] + "! -");
            int loop = 0;
            while (loop < 3) {
                int max = 50;
                int moreDamage = randomizer.nextInt(max);
                myChar.abObj.damage[0] = myChar.abObj.damage[0] + moreDamage;
                System.out.println("*" +  myChar.abObj.damage[0] + " Damage");
                loop = loop + 1;
                daBoss.health = daBoss.health - myChar.abObj.damage[0];
                displayTotal = displayTotal + myChar.abObj.damage[0];
                myChar.abObj.damage[0] = 75;
            } // close loop
            System.out.println("----------");
            System.out.println("*" + displayTotal + " Total");
            displayTotal = 0;
            // close ability1
        } else if (castAbility == 1) {

            int miss = 2;
            int hitTarget = randomizer.nextInt(miss);

            if (hitTarget != 1) {
                int max = 150;
                int moreDamage = randomizer.nextInt(max);
                myChar.abObj.damage[1] = myChar.abObj.damage[1] + moreDamage;
                System.out.println("- " + myChar.abObj.abilityName[1] + "! -");
                System.out.println("*" + myChar.abObj.damage[1] + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[1]);
            } else {
                System.out.println("" + myChar.name + " Misses!");
                System.out.println("*0 Damage");
            } // close hitTarget
            myChar.abObj.damage[1] = 450;
            //close ability2
        } else {

            int chance = 2;
            int critical = randomizer.nextInt(chance);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[2] = myChar.abObj.damage[2] + moreDamage;

            System.out.println("- " + myChar.abObj.abilityName[2] + "! -");

            if (critical == 1) {
                System.out.println("*" + myChar.abObj.damage[2] + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[2]);
            } else {
                System.out.println("Critical Hit!");
                System.out.println("*" + (myChar.abObj.damage[2] * 2) + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[2] * 2);
            } // close ability3
            myChar.abObj.damage[2] = 175;
        } // close abilities
        return daBoss.health;
    } // close warrior

    public int castWizardAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + myChar.abObj.abilityName[0] + ", 2: " + myChar.abObj.abilityName[1] + ", 3: " + myChar.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {
            int max = 100;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[0] = myChar.abObj.damage[0] + moreDamage;

            System.out.println("- " + myChar.abObj.abilityName[0] + "! -");
            System.out.println("*" + myChar.abObj.damage[0] + " Damage");

            daBoss.health = daBoss.health - (myChar.abObj.damage[0]);
            myChar.abObj.damage[0] = 250;
        } else if (castAbility == 1) {
            int start = 0;
            int total = 5;

            System.out.println("- " + myChar.abObj.abilityName[1] + "!- ");

            while (start < total) {

                int chance = 3;
                int hitTarget = randomizer.nextInt(chance);

                if (hitTarget == 2) {

                    System.out.println("Miss!");

                } else {

                    myChar.abObj.damage[1] = 75;
                    int max = 50;
                    int moreDamage = randomizer.nextInt(max);
                    myChar.abObj.damage[1] = myChar.abObj.damage[1] + moreDamage;
                    System.out.println("Hit! *" + myChar.abObj.damage[1]);
                    daBoss.health = daBoss.health - myChar.abObj.damage[1];
                    displayTotal = displayTotal + myChar.abObj.damage[1];

                } // close hit or miss
                start = start + 1;
            } // close while

            System.out.println("----------");
            System.out.println("*" + displayTotal + " Total");
            displayTotal = 0;
            myChar.abObj.damage[1] = 0;
        } else {
            System.out.println("- " + myChar.abObj.abilityName[2] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[2] = myChar.abObj.damage[2] + moreDamage;

            if (animal == 0) {

                System.out.println("*" + myChar.abObj.damage[2] + " Damage");
                daBoss.health = daBoss.health - myChar.abObj.damage[2];

            } else {

                System.out.println("Critical Hit!");
                System.out.println("*" + (myChar.abObj.damage[2] * 2) + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[2] * 2);

            } // close chooseAnimal
            myChar.abObj.damage[2] = 175;
        } // close abilities

        return daBoss.health;
    } // close wizard ability

    public int castAssassinAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + myChar.abObj.abilityName[0] + ", 2: " + myChar.abObj.abilityName[1] + ", 3: " + myChar.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            int whichGame = 3;
            int mindGames = randomizer.nextInt(whichGame);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[0] = myChar.abObj.damage[0] + moreDamage;

            System.out.println("- " + myChar.abObj.abilityName[0] + "! -");

            if (mindGames == 0) {
                System.out.println("- Whos Ya Daddy! -");
            } // whosYaDaddy
            if (mindGames == 1) {
                System.out.println("- Love At First Sight! -");
            } // firstSightLove
            if (mindGames == 2) {
                System.out.println("- Imposter! -");
            } // imposter

            System.out.println("*" + myChar.abObj.damage[0] + " Damage");

            daBoss.health = daBoss.health - (myChar.abObj.damage[0]);

            myChar.abObj.damage[0] = 250;

        } else if (castAbility == 1) {

            System.out.println("- " + myChar.abObj.abilityName[1] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[1] = myChar.abObj.damage[1] + moreDamage;

            if (animal == 0) {

                System.out.println("*" + myChar.abObj.damage[1] + " Damage");
                daBoss.health = daBoss.health - myChar.abObj.damage[1];

            } else {

                System.out.println("Critical Hit!");
                System.out.println("*" + (myChar.abObj.damage[1] * 2) + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[1] * 2);

            } // close chooseAnimal

            myChar.abObj.damage[1] = 175;

        } else {

            int start = 0;
            int total = 3;

            System.out.println("- " + myChar.abObj.abilityName[2] + "!- ");

            while (start < total) {

                int chance = 3;
                int hitTarget = randomizer.nextInt(chance);

                if (hitTarget == 2) {

                    System.out.println("- Miss!");

                } else {

                    myChar.abObj.damage[2] = 50;
                    int max = 50;
                    int moreDamage = randomizer.nextInt(max);
                    myChar.abObj.damage[2] = myChar.abObj.damage[2] + moreDamage;
                    System.out.println("- Hit!\n*" + myChar.abObj.damage[2]);
                    System.out.println("*" + myChar.abObj.damage[2]);
                    daBoss.health = daBoss.health - (myChar.abObj.damage[2] * 2);
                    displayTotal = displayTotal + (myChar.abObj.damage[2] * 2);

                } // close hit or miss

                start = start + 1;

            } // close while

            System.out.println("----------");
            System.out.println("*" + displayTotal + " Damage");
            displayTotal = 0;

            myChar.abObj.damage[2] = 50;

        }

        return daBoss.health;
    } // close assassin ability

    public int castAlchemistAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + myChar.abObj.abilityName[0] + ", 2: " + myChar.abObj.abilityName[1] + ", 3: " + myChar.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[0] = myChar.abObj.damage[0] + moreDamage;

            System.out.println("- " + myChar.abObj.abilityName[0] + "! -");
            System.out.println("*" + myChar.abObj.damage[0] + " Damage");

            daBoss.health = daBoss.health - (myChar.abObj.damage[0]);

            myChar.abObj.damage[0] = 250;

        } else if (castAbility == 1) {

            int miss = 2;
            int hitTarget = randomizer.nextInt(miss);

            if (hitTarget != 1) {

                int max = 150;
                int moreDamage = randomizer.nextInt(max);
                myChar.abObj.damage[1] = myChar.abObj.damage[1] + moreDamage;
                System.out.println("- " + myChar.abObj.abilityName[1] + "! -");
                System.out.println("*" + myChar.abObj.damage[1] + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[1]);

            } else {

                System.out.println("" + myChar.name + " Misses!");
                System.out.println("*0 Damage");

            } // close hitTarget

            myChar.abObj.damage[1] = 450;

        } else {

            System.out.println("- " + myChar.abObj.abilityName[2] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[2] = myChar.abObj.damage[2] + moreDamage;

            if (animal == 0) {

                System.out.println("*" + myChar.abObj.damage[2] + " Damage");
                daBoss.health = daBoss.health - myChar.abObj.damage[2];

            } else {

                System.out.println("Critical Hit!");
                System.out.println("*" + (myChar.abObj.damage[2] * 2) + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[2] * 2);

            } // close chooseAnimal

            myChar.abObj.damage[2] = 175;

        }

        return daBoss.health;
    } // close alchemist ability

    public int castNecroAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + myChar.abObj.abilityName[0] + ", 2: " + myChar.abObj.abilityName[1] + ", 3: " + myChar.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            int start = 0;
            int total = 5;

            System.out.println("- " + myChar.abObj.abilityName[0] + "! -");

            while (start < total) {

                int chance = 3;
                int hitTarget = randomizer.nextInt(chance);

                if (hitTarget == 2) {

                    System.out.println("Miss!");

                } else {

                    myChar.abObj.damage[0] = 75;
                    int max = 50;
                    int moreDamage = randomizer.nextInt(max);
                    myChar.abObj.damage[0] = myChar.abObj.damage[0] + moreDamage;
                    System.out.println("Hit! *" + myChar.abObj.damage[0]);
                    daBoss.health = daBoss.health - myChar.abObj.damage[0];
                    displayTotal = displayTotal + myChar.abObj.damage[0];

                } // close hit or miss

                start = start + 1;

            } // close while

            System.out.println("----------");
            System.out.println("*" + displayTotal + " Total");
            displayTotal = 0;

            myChar.abObj.damage[0] = 75;

        } else if (castAbility == 1) {

            System.out.println("- " + myChar.abObj.abilityName[1] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            myChar.abObj.damage[1] = myChar.abObj.damage[1] + moreDamage;

            if (animal == 0) {

                System.out.println("*" + myChar.abObj.damage[1] + " Damage");
                daBoss.health = daBoss.health - myChar.abObj.damage[1];

            } else {

                System.out.println("Critical Hit!");
                System.out.println("*" + (myChar.abObj.damage[1] * 2) + " Damage");
                daBoss.health = daBoss.health - (myChar.abObj.damage[1] * 2);

            } // close chooseAnimal

            myChar.abObj.damage[1] = 175;

        } else {

            int start = 0;
            int total = 7;

            System.out.println("- " + myChar.abObj.abilityName[2] + "! -");

            while (start < total) {

                int chance = 3;
                int hitTarget = randomizer.nextInt(chance);

                if (hitTarget == 2) {

                    System.out.println("Miss!");

                } else {

                    myChar.abObj.damage[2] = 50;
                    int max = 50;
                    int moreDamage = randomizer.nextInt(max);
                    myChar.abObj.damage[2] = myChar.abObj.damage[2] + moreDamage;
                    System.out.println("Hit! *" + myChar.abObj.damage[2]);
                    daBoss.health = daBoss.health - myChar.abObj.damage[2];
                    displayTotal = displayTotal + myChar.abObj.damage[2];

                } // close hit or miss

                start = start + 1;

            } // close while

            System.out.println("----------");
            System.out.println("*" + displayTotal + " Total");
            displayTotal = 0;

            myChar.abObj.damage[2] = 50;

        }

        return daBoss.health;
    } // close necro ability
    //--------------------------------------------------------------------------------------------

    public int castOrcAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + daBoss.abObj.abilityName[0] + ", 2: " + daBoss.abObj.abilityName[1] + ", 3: " + daBoss.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            int max = 100;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[0] = daBoss.abObj.damage[0] + moreDamage;

            System.out.println("- " + daBoss.abObj.abilityName[0] + "! -");
            System.out.println("*" + daBoss.abObj.damage[0]  + " Damage");

            myChar.health = myChar.health  - (daBoss.abObj.damage[0]);

            daBoss.abObj.damage[0] = 250;

        } else if (castAbility == 1) {

            System.out.println("- " + daBoss.abObj.abilityName[1] + "! -");
            int miss = 2;
            int hitTarget = randomizer.nextInt(miss);

            if (hitTarget != 1) {

                int max = 150;
                int moreDamage = randomizer.nextInt(max);
                daBoss.abObj.damage[1] = daBoss.abObj.damage[1] + moreDamage;

                System.out.println("*" + daBoss.abObj.damage[1] + " Damage");
                myChar.health = myChar.health - (daBoss.abObj.damage[1]);

            } else {
                System.out.println("" + daBoss.name + " Misses!");
                System.out.println("*0 Damage");
            } // close hitTarget
            daBoss.abObj.damage[1] = 450;
        } else {

            System.out.println("- " + daBoss.abObj.abilityName[2] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[2] = daBoss.abObj.damage[2] + moreDamage;

            if (animal == 0) {
                System.out.println("*" + daBoss.abObj.damage[2] + " Damage");
                myChar.health = myChar.health - daBoss.abObj.damage[2];
            } else {
                System.out.println("Critical Hit!");
                System.out.println("*" + (daBoss.abObj.damage[2] * 2) + " Damage");
                myChar.health = myChar.health - (daBoss.abObj.damage[2] * 2);
            } // close chooseAnimal

            daBoss.abObj.damage[2] = 175;
        } // close abilities
        return myChar.health;
    } // close wizard ability

    public int castPriestAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + daBoss.abObj.abilityName[0] + ", 2: " + daBoss.abObj.abilityName[1] + ", 3: " + daBoss.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            int max = 100;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[0] = daBoss.abObj.damage[0]  + moreDamage;

            System.out.println("- " + daBoss.abObj.abilityName[0] + "! -");
            System.out.println("*" + daBoss.abObj.damage[0] + " Damage");

            myChar.health = myChar.health - (daBoss.abObj.damage[0]);

            daBoss.abObj.damage[0] = 250;

        } else if (castAbility == 1) {

            System.out.println("- " + daBoss.abObj.abilityName[1] + "! -");
            int miss = 2;
            int hitTarget = randomizer.nextInt(miss);

            if (hitTarget != 1) {

                int max = 150;
                int moreDamage = randomizer.nextInt(max);
                daBoss.abObj.damage[1] = daBoss.abObj.damage[1] + moreDamage;

                System.out.println("*" + daBoss.abObj.damage[1] + " Damage");
                myChar.health = myChar.health - (daBoss.abObj.damage[1]);

            } else {

                System.out.println("" + daBoss.name + " Misses!");
                System.out.println("*0 Damage");

            } // close hitTarget

            daBoss.abObj.damage[1] = 400;

        } else {

            System.out.println("- " + daBoss.abObj.abilityName[2] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[2] = daBoss.abObj.damage[2] + moreDamage;

            if (animal == 0) {

                System.out.println("*" + daBoss.abObj.damage[2] + " Damage");
                myChar.health = myChar.health - daBoss.abObj.damage[2];

            } else {

                System.out.println("Critical Hit!");
                System.out.println("*" + (daBoss.abObj.damage[2] * 2) + " Damage");
                myChar.health = myChar.health - (daBoss.abObj.damage[2] * 2);

            } // close chooseAnimal

            daBoss.abObj.damage[2] = 175;

        } // close abilities

        return myChar.health;
    } // close wizard ability

    public int castReptileAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + daBoss.abObj.abilityName[0] + ", 2: " + daBoss.abObj.abilityName[1] + ", 3: " + daBoss.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            int max = 100;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[0] = daBoss.abObj.damage[0] + moreDamage;

            System.out.println("- " + daBoss.abObj.abilityName[0] + "! -");
            System.out.println("*" + daBoss.abObj.damage[0] + " Damage");

            myChar.health = myChar.health - (daBoss.abObj.damage[0]);

            daBoss.abObj.damage[0] = 250;

        } else if (castAbility == 1) {

            int start = 0;
            int total = 4;

            System.out.println("- " + daBoss.abObj.abilityName[1] + "!- ");

            while (start < total) {

                int chance = 3;
                int hitTarget = randomizer.nextInt(chance);

                if (hitTarget == 2) {

                    System.out.println("Miss!");

                } else {

                    daBoss.abObj.damage[1] = 125;
                    int max = 50;
                    int moreDamage = randomizer.nextInt(max);
                    daBoss.abObj.damage[1] = daBoss.abObj.damage[1] + moreDamage;
                    System.out.println("Hit! *" + daBoss.abObj.damage[1]);
                    myChar.health = myChar.health - daBoss.abObj.damage[1];
                    displayTotal = displayTotal + daBoss.abObj.damage[1];

                } // close hit or miss

                start = start + 1;

            } // close while

            System.out.println("*" + displayTotal + " Damage");
            displayTotal = 0;

            daBoss.abObj.damage[1] = 125;

        } else {

            System.out.println("- " + daBoss.abObj.abilityName[2] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[2] = daBoss.abObj.damage[2] + moreDamage;

            if (animal == 0) {

                System.out.println("*" + daBoss.abObj.damage[2] + " Damage");
                myChar.health = myChar.health - daBoss.abObj.damage[2];

            } else {

                System.out.println("Critical Hit!");
                System.out.println("*" + (daBoss.abObj.damage[2] * 2) + " Damage");
                myChar.health = myChar.health - (daBoss.abObj.damage[2] * 2);

            } // close chooseAnimal

            daBoss.abObj.damage[2] = 175;

        } // close abilities

        return myChar.health;
    } // close wizard ability

    public int castMechAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + daBoss.abObj.abilityName[0] + ", 2: " + daBoss.abObj.abilityName[1] + ", 3: " + daBoss.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            System.out.println("- " + daBoss.abObj.abilityName[0] + "! -");
            int loop = 0;
            while (loop < 4) {
                int max = 50;
                int moreDamage = randomizer.nextInt(max);
                daBoss.abObj.damage[0] = daBoss.abObj.damage[0] + moreDamage;
                System.out.println("*" + daBoss.abObj.damage[0] + " Damage");
                loop = loop + 1;
                myChar.health = myChar.health - daBoss.abObj.damage[0];
                displayTotal = displayTotal + daBoss.abObj.damage[0];
                daBoss.abObj.damage[0] = 75;
            }

            System.out.println("----------");
            System.out.println("*" + displayTotal + " Total");
            displayTotal = 0;

            daBoss.abObj.damage[0] = 75;

        } else if (castAbility == 1) {

            int start = 0;
            int total = 6;

            System.out.println("- " + daBoss.abObj.abilityName[1] + "!- ");

            while (start < total) {

                int chance = 3;
                int hitTarget = randomizer.nextInt(chance);

                if (hitTarget == 2) {

                    System.out.println("Miss!");

                } else {

                    daBoss.abObj.damage[1] = 75;
                    int max = 50;
                    int moreDamage = randomizer.nextInt(max);
                    daBoss.abObj.damage[1] = daBoss.abObj.damage[1] + moreDamage;
                    System.out.println("Hit! *" + daBoss.abObj.damage[1]);
                    myChar.health = myChar.health - daBoss.abObj.damage[1];
                    displayTotal = displayTotal + daBoss.abObj.damage[1];

                } // close hit or miss

                start = start + 1;

            } // close while

            System.out.println("*" + displayTotal + " Damage");
            displayTotal = 0;

            daBoss.abObj.damage[1] = 75;

        } else {

            System.out.println("- " + daBoss.abObj.abilityName[2] + "! -");
            int which = 4;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[2] = daBoss.abObj.damage[2] + moreDamage;

            if (animal != 0) {

                System.out.println("*" + daBoss.abObj.damage[2] + " Damage");
                myChar.health = myChar.health - daBoss.abObj.damage[2];
                displayTotal = displayTotal + daBoss.abObj.damage[2];

                daBoss.abObj.damage[2] = 275;

                int hello = 2;
                int hope = randomizer.nextInt(hello);

                int again = 50;
                int againDamage = randomizer.nextInt(again);
                daBoss.abObj.damage[2] = daBoss.abObj.damage[2] + againDamage;

                if (hope != 0) {

                    System.out.println("*" + daBoss.abObj.damage[2] + " Damage");
                    myChar.health = myChar.health - daBoss.abObj.damage[2];
                    displayTotal = displayTotal + daBoss.abObj.damage[2];

                } else {

                    System.out.println("Miss!");

                }

            } else {

                System.out.println("Miss!");

            } // close chooseAnimal

            System.out.println("---------\n*" + displayTotal + " Total");
            displayTotal = 0;

            daBoss.abObj.damage[2] = 275;

        } // close abilities

        return myChar.health;
    } // close wizard ability

    public int castDeathAbility(int AI, Fighter myChar, Fighter daBoss) {

        int castAbility = 0;

        if (AI == 1) {

            int choose = 3;
            castAbility = randomizer.nextInt(choose);

        } else {

            System.out.println("Cast Ability: \n1: " + daBoss.abObj.abilityName[0] + ", 2: " + daBoss.abObj.abilityName[1] + ", 3: " + daBoss.abObj.abilityName[2] + "\n-------------");
            castAbility = userInput.nextInt();
            castAbility = castAbility - 1;

        }

        if (castAbility == 0) {

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[0] = daBoss.abObj.damage[0] + moreDamage;

            System.out.println("- " + daBoss.abObj.abilityName[0] + "! -");
            System.out.println("*" + daBoss.abObj.damage[0] + " Damage");

            myChar.health = myChar.health - (daBoss.abObj.damage[0]);

            daBoss.abObj.damage[0] = 375;

        } else if (castAbility == 1) {

            int start = 0;
            int total = 8;

            System.out.println("- " + daBoss.abObj.abilityName[1] + "!- ");

            while (start < total) {

                int chance = 3;
                int hitTarget = randomizer.nextInt(chance);

                if (hitTarget == 2) {

                    System.out.println("Miss!");

                } else {

                    daBoss.abObj.damage[1] = 50;
                    int max = 50;
                    int moreDamage = randomizer.nextInt(max);
                    daBoss.abObj.damage[1] = daBoss.abObj.damage[1] + moreDamage;
                    System.out.println("Hit! *" + daBoss.abObj.damage[1]);
                    myChar.health = myChar.health - daBoss.abObj.damage[1];
                    displayTotal = displayTotal + daBoss.abObj.damage[1];

                } // close hit or miss

                start = start + 1;

            } // close while

            System.out.println("*" + displayTotal + " Damage");
            displayTotal = 0;

            daBoss.abObj.damage[1] = 50;

        } else {

            System.out.println("- " + daBoss.abObj.abilityName[2] + "! -");
            int which = 2;
            int animal = randomizer.nextInt(which);

            int max = 50;
            int moreDamage = randomizer.nextInt(max);
            daBoss.abObj.damage[2] = daBoss.abObj.damage[2] + moreDamage;

            if (animal == 0) {

                System.out.println("*" + daBoss.abObj.damage[2] + " Damage");
                myChar.health = myChar.health - daBoss.abObj.damage[2];

            } else {

                System.out.println("Critical Hit!");
                System.out.println("*" + (daBoss.abObj.damage[2] * 2) + " Damage");
                myChar.health = myChar.health - (daBoss.abObj.damage[2] * 2);

            } // close chooseAnimal

            daBoss.abObj.damage[2] = 225;

        } // close abilities

        return myChar.health;
    } // close wizard ability
    //--------------------------------------------------------------------------------

} // close class