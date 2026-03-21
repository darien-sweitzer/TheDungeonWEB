package thedungeon;

import java.io.File;
import java.util.*;

public class Main {

    public static Scanner userInput = new Scanner(System.in);
    public static Random randomizer = new Random();
    public static int rounds = 1;
    public static int level = 1;
    public static int displayTotal = 0;
    public static Fighter theBoss;
    public static FileYanker fileYanker = new FileYanker();
    public static AbilityCaster abilityCaster = new AbilityCaster();

    public static void main(String[] args) {

        // Call in Files
        File damageFile = new File("DamageNumbers.txt");
        File abilityFile = new File("AbilityNames.txt");
        File abilityDescFile = new File("AbilityDesc.txt");
        File roleDescFile = new File("roleDesc.txt");

        // Opening Text
        System.out.println("-------------\nWelcome to the Dungeon\nYou must defeat 5 bosses to escape. They will get increasingly harder to defeat.\n-------------");

        // User Input Pick Class
        System.out.println("Choose Your Class:\n-------------\n"
                + "1: Warrior\n2: Wizard\n3: Assassin\n4: Alchemist\n5: Necromancer\n-------------");

        int classQ = 0;
        String classR;
        String classN;

        while (classQ<1 || classQ>5) {
            try {
                classQ = userInput.nextInt();
            } catch (InputMismatchException ex) {
                userInput.next();
            }
            if (classQ<1 || classQ>5) {
                System.out.println("I know, so many choices. Choose wisely.");
            }
        } // close validation

        switch (classQ) {
            case 1:
                classR = "Warrior";
                classN = "Ragnar";
                break;
            case 2:
                classR = "Wizard";
                classN = "Blaze";
                break;
            case 3:
                classR = "Assassin";
                classN = "Shadow";
                break;
            case 4:
                classR = "Alchemist";
                classN = "One-Eye";
                break;
            case 5:
                classR = "Necromancer";
                classN = "Rot";
                break;
            default:
                classR = "Invalid";
                classN = "Invalid";
                break;
        } // Close Switch
        // close pick class

        // Declare myFighter
        // Requires Role, Name, and Health
        Fighter myFighter = new Fighter(classR, classN, 5000);
        fileYanker.getDamage(damageFile, classR, myFighter);
        fileYanker.getAbName(abilityFile, classR, myFighter);
        fileYanker.getAbDesc(abilityDescFile, classR, myFighter);
        fileYanker.getRoleDesc(roleDescFile, classR, myFighter);

        //start game question
        System.out.println("-------------\nYou Have Chosen:\n-------------\n" + myFighter.toString() + "\n-------------\n1: Descend into Dungeon\n-------------");

        int startGame = 0;
        while(startGame != 1) {
            try {
                startGame = userInput.nextInt();
            } catch (InputMismatchException ex) {
                userInput.next();
            }
            if (startGame != 1) {
                System.out.println("Don't be scared. Descend Traveler.");
            }
        } // close validation
// close initialization
//-----------------------------------------------------------------------------------------------------
        // Start Game
        while (true) {

            // Start Round
            while (startGame == 1) {

                int AI = 1;
                int notAI = 2;
                int endGame = 0;

                // create boss for each new level
                if (level == 1 && rounds == 1) {
                    theBoss = new Fighter("Level 1", "The Orc King", 1000);
                    fileYanker.getDamage(damageFile, "boss1", theBoss);
                    fileYanker.getAbName(abilityFile, "boss1", theBoss);
                    fileYanker.getAbDesc(abilityDescFile, "boss1", theBoss);
                    fileYanker.getRoleDesc(roleDescFile, "boss1", theBoss);
                } else if (level == 2 && rounds == 1) {
                    theBoss = new Fighter("Level 2", "High Priest Genji", 1250);
                    fileYanker.getDamage(damageFile, "boss2", theBoss);
                    fileYanker.getAbName(abilityFile, "boss2", theBoss);
                    fileYanker.getAbDesc(abilityDescFile, "boss2", theBoss);
                    fileYanker.getRoleDesc(roleDescFile, "boss2", theBoss);
                } else if (level == 3 && rounds == 1) {
                    theBoss = new Fighter("Level 3", "Reptilian", 1500);
                    fileYanker.getDamage(damageFile, "boss3", theBoss);
                    fileYanker.getAbName(abilityFile, "boss3", theBoss);
                    fileYanker.getAbDesc(abilityDescFile, "boss3", theBoss);
                    fileYanker.getRoleDesc(roleDescFile, "boss3", theBoss);
                } else if (level == 4 && rounds == 1) {
                    theBoss = new Fighter("Level 4", "Kill-A-Tron 3000", 1750);
                    fileYanker.getDamage(damageFile, "boss4", theBoss);
                    fileYanker.getAbName(abilityFile, "boss4", theBoss);
                    fileYanker.getAbDesc(abilityDescFile, "boss4", theBoss);
                    fileYanker.getRoleDesc(roleDescFile, "boss4", theBoss);
                    System.out.println("YOU HAVE FOUND A HEALTH BOOST. HEALTH CAP INCREASED AND RESTORED TO MAXIMUM.");
                    myFighter.health = 6000;
                } else if (level == 5 && rounds == 1) {
                    theBoss = new Fighter("Level 5", "Death", 2500);
                    fileYanker.getDamage(damageFile, "boss5", theBoss);
                    fileYanker.getAbName(abilityFile, "boss5", theBoss);
                    fileYanker.getAbDesc(abilityDescFile, "boss5", theBoss);
                    fileYanker.getRoleDesc(roleDescFile, "boss5", theBoss);
                } // close boss initializer

                // display boss description if it's the first round
                if (rounds == 1) {
                    System.out.println("-------------\n" + theBoss.toString() + "\n-------------\n1: Fight\n-------------");
                    userInput.nextInt();
                } // first round checker

                // health display
                System.out.println("-------------\nRound: " + rounds + "\n" + myFighter.name + ": " + myFighter.health + " HP" + "\n"
                        + theBoss.name + ": " + theBoss.health + " HP\n-------------\n" + myFighter.role + "'s Turn");

                // user casts ability then checks if anyone died
                theBoss.health = abilityCaster.castAbility(notAI, myFighter, theBoss);
                endGame = abilityCaster.death(myFighter, theBoss);

                if(endGame == 1){
                    break;
                } // end fight checker

                System.out.println("----- Start Enemy Turn: Enter 1 -----");
                int opponentStart = userInput.nextInt();

                System.out.println("-------------\n" + theBoss.name + "'s Turn");

                // boss casts random ability then checks if anyone died
                myFighter.health = abilityCaster.castBossAbility(AI, myFighter, theBoss);
                endGame = abilityCaster.death(myFighter, theBoss);

                if(endGame == 1){
                    break;
                } // end fight checker

                System.out.println("----- End Round: Enter 1 -----");
                userInput.nextInt();

                rounds++;

            } // close round

            // death checker
            if(myFighter.health <= 0){
                System.out.println("The weary traveler succumbs to his wounds, you have died. GAME OVER.");
                break;
            } else {
                System.out.println("You have slain Boss " + level + "/5!\n\nEnter 1 to Descend Deeper.");
                userInput.next();
            } // close death checker

            rounds = 1;
            level++;

            if (level > 5) {
                System.out.println("Congrats mighty traveler, you have defeated every monster in this dungeon. Enjoy your spoils of war, time for a feast!");
                break;
            } //  end game after final boss
        } // close game
    } // close main
} // close class