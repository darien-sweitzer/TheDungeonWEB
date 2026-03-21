package thedungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileYanker {

    public void getDamage(File f, String r, Fighter a) {
        int counter = 0;
        try {
            // Read from File
            Scanner myReader = new Scanner(f);
            while(myReader.hasNext()) {
                String c = myReader.next();
                // If role matches assignes damage to array
                if (c.equals(r)) {
                    a.abObj.damage[counter] = Integer.parseInt(myReader.next());
                    counter++;
                } // close if
            } // close loop
        } catch (FileNotFoundException ex) {
            System.out.println("Something went wrong.");
        } // close tryCatch
    } // close getDamage

    public void getAbName(File f, String r, Fighter a) {
        int counter = 0;
        try {
            // Read from File
            Scanner myReader = new Scanner(f);
            while(myReader.hasNext()) {
                String c = myReader.next();
                // If role matches assignes damage to array
                if (c.equals(r)) {
                    a.abObj.abilityName[counter] = myReader.next();
                    counter++;
                } // close if
            } // close loop
        } catch (FileNotFoundException ex) {
            System.out.println("Something went wrong.");
        } // close tryCatch
    } // close getDamage

    public void getAbDesc(File f, String r, Fighter a) {
        int counter = 0;
        try {
            // Read from File
            Scanner myReader = new Scanner(f);
            while(myReader.hasNext()) {
                String c = myReader.next();
                // If role matches assignes damage to array
                if (c.equals(r)) {
                    a.abObj.abilityDesc[counter] = myReader.nextLine();
                    counter++;
                } // close if
            } // close loop
        } catch (FileNotFoundException ex) {
            System.out.println("Something went wrong.");
        } // close tryCatch
    } // close getDamage

    public void getRoleDesc(File f, String r, Fighter a) {
        int counter = 0;
        try {
            // Read from File
            Scanner myReader = new Scanner(f);
            while(myReader.hasNext()) {
                String c = myReader.next();
                // If role matches assignes damage to array
                if (c.equals(r)) {
                    a.roleDesc[counter] = myReader.nextLine();
                    counter++;
                } // close if
            } // close loop
        } catch (FileNotFoundException ex) {
            System.out.println("Something went wrong.");
        } // close tryCatch
    } // close getDamage

} // close FileYanker
