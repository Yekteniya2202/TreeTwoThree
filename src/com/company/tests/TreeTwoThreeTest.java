package com.company.tests;

import com.company.classes.TreeTwoThree;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TreeTwoThreeTest {

    @org.junit.jupiter.api.Test
    void add() {
        Set<Integer> tree = new TreeTwoThree<Integer>();
        for(int i = -1000; i <= 1000; i++){

            Assert.assertTrue(tree.add(i));
        }


        for(int i = -100; i <= 100; i++){

            Assert.assertFalse(tree.add(i));
        }
        for(int i = -1000; i <= 1000; i++){
            Assert.assertTrue(tree.contains(i));
        }
    }

    @org.junit.jupiter.api.Test
    void addDuplicate() {
        Set<String> tree = new TreeTwoThree<String>();
        Set<String> all = new HashSet<>();
        for(int i = -1000; i <= 1000; i++){
            String str = getRandomString(random, "qwerty", 5);
            if (all.add(str)){
                Assert.assertTrue(tree.add(str));
            }
            else{
                Assert.assertFalse(tree.add(str));
            }

        }

    }

    @org.junit.jupiter.api.Test
    void contains() {

        Set<Integer> tree = new TreeTwoThree<Integer>();
        for(int i = -1000; i <= 1000; i++){

            Assert.assertTrue(tree.add(i));
        }

        for(int i = -1000; i <= 1000; i++){
            Assert.assertTrue(tree.contains(i));
        }
    }

    @org.junit.jupiter.api.Test
    void remove() {
        Set<Integer> tree = new TreeTwoThree<Integer>();
        for(int i = -1000; i <= 1000; i++){

            Assert.assertTrue(tree.add(i));
        }

        for(int i = 0; i <= 1000; i++){

            Assert.assertTrue(tree.remove(i));
        }


        for(int i = 100; i <= 500; i++){

            Assert.assertFalse(tree.remove(i));
        }
    }

    @org.junit.jupiter.api.Test
    void containsAll() {

        Set<Integer> tree = new TreeTwoThree<Integer>();
        Collection<Integer> added = new ArrayList<Integer>();
        Collection<Integer> notAddedFully = new ArrayList<Integer>();
        for(int i = -1000; i <= 1000; i++){

            Assert.assertTrue(tree.add(i));
        }
        for(int i = -100; i <= 100; i++){

            added.add(i);
        }

        for(int i = -1500; i <= -500; i++){

            notAddedFully.add(i);
        }

        Assert.assertTrue(tree.containsAll(added));
        Assert.assertFalse(tree.containsAll(notAddedFully));
    }

    @org.junit.jupiter.api.Test
    void addAll() {

        Set<Integer> tree = new TreeTwoThree<Integer>();
        Collection<Integer> added = new ArrayList<Integer>();
        for(int i = -1000; i <= 1000; i++){

            added.add(i);
        }

        Assert.assertTrue(tree.addAll(added));
        for(int i = -1000; i <= 1000; i++){

            added.add(i);
        }
        Assert.assertFalse(tree.addAll(added));
    }

    @org.junit.jupiter.api.Test
    void retainAll() {
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
    }

    Random random = new Random();
    private static String getRandomString(Random rng, String characters, int size){
        char[] text = new char[size];
        for (int i = 0; i < size; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}