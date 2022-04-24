package com.company.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class TreeFillerInteger {
    private Set<Integer> tree = null;
    private BufferedReader in = null;
    public TreeFillerInteger(Set<Integer> tree, BufferedReader in){
        this.tree = tree;
        this.in = in;
    }

    public void fill() throws IOException {
        String line = in.readLine();

        while (line != null) {
            tree.add(Integer.parseInt(line));

            // считываем остальные строки в цикле
            line = in.readLine();
        }
    }


}
