package com.company.classes;

import com.company.interfaces.ITree;

import java.io.BufferedReader;
import java.io.IOException;

public class TreeFillerString {
    private ITree<String> tree = null;
    private BufferedReader in = null;
    public TreeFillerString(ITree<String> tree, BufferedReader in){
        this.tree = tree;
        this.in = in;
    }

    public void fill() throws IOException {
        String line = in.readLine();
        while (line != null) {
            tree.insert(line);
            // считываем остальные строки в цикле
            line = in.readLine();
        }
    }


}