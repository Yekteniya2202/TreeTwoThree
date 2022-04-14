package com.company;

import com.company.classes.TreeFillerInteger;
import com.company.classes.TreeFillerString;
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
            TreeTwoThree<String> theTree = new TreeTwoThree<>();
            String fileDir = "out/production/ TreeTwoThree/com/company/input/";
            String fileSrc = fileDir + "2.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(fileSrc))) {
                TreeFillerString treeFillerInteger = new TreeFillerString(theTree, reader);
                treeFillerInteger.fill();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(theTree.toString());
            /*
            System.out.println(theTree.getLessThen(5));
            System.out.println(theTree.getMoreThen(5));
            System.out.println(theTree.getRange(1, 5));
            System.out.println(theTree.getEquals(1));*/
        }
        catch (Exception e){
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
