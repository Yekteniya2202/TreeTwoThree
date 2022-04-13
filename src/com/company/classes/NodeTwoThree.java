package com.company.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class NodeTwoThree<T extends Comparable<T>> {
    public ArrayList<T> keys = new ArrayList<>();
    private Comparator<T> comparator = (o1, o2) -> o1.compareTo(o2);
    NodeTwoThree<T> first, second, third, fourth;
    NodeTwoThree<T> parent;

    public NodeTwoThree() {}
    public NodeTwoThree(T value){
        insert(value);
    }

    public NodeTwoThree(T value, NodeTwoThree<T> first, NodeTwoThree<T> second, NodeTwoThree<T> third, NodeTwoThree<T> fourth, NodeTwoThree<T> parent){
        insert(value);
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.parent = parent;
    }


    public int getSize(){
        return keys.size();
    }

    public boolean contains(T value){
        return keys.contains(value);

    }

    public void insert(T value){
        keys.add(value);
        keys.sort(comparator);
    }

    public void remove(T value){
        keys.remove(value);
        keys.sort(comparator);
    }

    public boolean isLeaf(){
        return first == null && second == null && third == null;
    }



    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(toString());
        buffer.append('\n');
        if (isLeaf() == false) {
            if (getSize() == 1) {
                first.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                second.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            } else {
                first.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                second.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                third.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(T elem : keys){
            sb.append("/" + elem);
        }
        sb.append("/");
        return sb.toString();
    }
}