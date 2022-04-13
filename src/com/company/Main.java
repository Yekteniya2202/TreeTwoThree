package com.company;

import com.company.classes.TreeFillerInteger;
import com.company.classes.TreeTwoThree;
import com.company.classes.TreeTwoThreeChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args)

    {
        try {
            long value;
            TreeTwoThree<Integer> theTree = new TreeTwoThree<Integer>();
            String fileDir = "out/production/ TreeTwoThree/com/company/input/";
            String fileSrc = fileDir + "1.txt";
            try(BufferedReader reader = new BufferedReader(new FileReader(fileSrc))){
                TreeFillerInteger treeFillerInteger = new TreeFillerInteger(theTree, reader);
                treeFillerInteger.fill();
            }
            System.out.println(theTree.getLessThen(5));
            System.out.println(theTree.getMoreThen(5));
            System.out.println(theTree.getRange(1, 5));
            System.out.println(theTree.getEquals(1));
            while (true) {
                System.out.print("Enter first letter of ");
                System.out.print("show, insert, or find: ");
                char choice = getChar();
                switch (choice) {
                    case 's':
                        System.out.println(theTree.toString());
                        break;
                    case 'i':
                        System.out.print("Enter value to insert: ");
                        value = getInt();
                        theTree.insert((int) value);
                        break;
                    case 'c':
                        System.out.print("Enter value to find: ");
                        value = getInt();
                        boolean found = theTree.contains((int) value);
                        if (found)
                            System.out.println("Found " + value);
                        else
                            System.out.println("Could not find " + value);
                        break;
                    case 'd':
                        System.out.print("Enter value to delete: ");
                        value = getInt();
                        theTree.delete((int) value);
                        break;
                    default:
                        System.out.print("Invalid entry\n");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException
    {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }

}

