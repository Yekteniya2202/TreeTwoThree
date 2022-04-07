package com.company;

import com.company.classes2.TreeTwoThree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class Main {
    public static void main(String[] args)

    {
        try {
            long value;
            TreeTwoThree<Integer> theTree = new TreeTwoThree<Integer>();

            Random rng = new Random();
            ArrayList<Integer> added = new ArrayList<>();
            for(var i : added){
                i = 2;
            }
            for(int i = 0; i < 100; i++){

                theTree.insert(i);
            }

            theTree.displayTree();
            System.out.println(theTree.getMax());
            System.out.println(theTree.getMin());
            while (true) {
                System.out.print("Enter first letter of ");
                System.out.print("show, insert, or find: ");
                char choice = getChar();
                switch (choice) {
                    case 's':
                        theTree.displayTree();
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
    //--------------------------------------------------------------
    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    //--------------------------------------------------------------
    public static char getChar() throws IOException
    {
        String s = getString();
        return s.charAt(0);
    }
    //-------------------------------------------------------------
    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
//-------------------------------------------------------------
} // Конец класса Tree234App

