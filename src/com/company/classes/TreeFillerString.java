package com.company.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class TreeFillerString {
    private Set<String> tree = null;
    private BufferedReader in = null;
    public TreeFillerString(Set<String> tree, BufferedReader in){
        this.tree = tree;
        this.in = in;
    }

    public void fill() throws IOException {
        String line = in.readLine();
        while (line != null) {
            tree.add(line);
            // считываем остальные строки в цикле
            line = in.readLine();
        }
    }


}