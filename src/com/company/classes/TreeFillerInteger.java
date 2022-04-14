package com.company.classes;

import com.company.interfaces.ITree;

import java.io.BufferedReader;
import java.io.IOException;

public class TreeFillerInteger {
    private ITree<Integer> tree = null;
    private BufferedReader in = null;
    public TreeFillerInteger(ITree<Integer> tree, BufferedReader in){
        this.tree = tree;
        this.in = in;
    }

    public void fill() throws IOException {
        String line = in.readLine();

        while (line != null) {
            tree.insert(Integer.parseInt(line));
            // считываем остальные строки в цикле
            line = in.readLine();
        }
    }


}
