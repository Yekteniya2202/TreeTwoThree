package com.company.classes;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.company.interfaces.ITree;

public class TreeTwoThree<T extends Comparable<T>> implements Iterable<T>, ITree<T>{




    NodeTwoThree<T> root;

    public TreeTwoThree() {
        root = new NodeTwoThree<>();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final ArrayList<T> elements = getSortedElements(new ArrayList<>());
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.size();
            }

            @Override
            public T next() {
                return elements.get(i++);
            }
        };
    }

    public ArrayList<T> getLessThen(T value){
        ArrayList<T> elements = getSortedElements(new ArrayList<>());
        elements.removeIf(elem -> elem.compareTo(value) >= 0);
        return elements;
    }

    public ArrayList<T> getMoreThen(T value){
        ArrayList<T> elements = getSortedElements(new ArrayList<>());
        elements.removeIf(elem -> elem.compareTo(value) <= 0);
        return elements;
    }

    public ArrayList<T> getRange(T start, T end){
        ArrayList<T> elements = getSortedElements(new ArrayList<>());
        elements.removeIf(elem -> elem.compareTo(start) < 0 || elem.compareTo(end) > 0);
        return elements;
    }
    public T getMin() {
        return getMin(root);
    }

    public T getMax() {
        return getMax(root);
    }

    public ArrayList<T> getEquals(T value){
        ArrayList<T> elements = getSortedElements(new ArrayList<>());
        elements.removeIf(elem -> !elem.equals(value));
        return elements;
    }

    private T getMin(NodeTwoThree<T> node) {
        if (node.isLeaf())
            return node.keys.get(0);
        return getMin(node.first);
    }

    private T getMax(NodeTwoThree<T> node) {

        if (node.getSize() == 1){
            if (node.isLeaf()){
                return node.keys.get(0);
            }
            return getMax(node.second);

        }
        if (node.isLeaf()){
            return node.keys.get(1);
        }
        return getMax(node.third);
    }

    private ArrayList<T> getElements(NodeTwoThree<T> node, ArrayList<T> targetArray){
        return getSortedElements(node, targetArray);
    }


    public ArrayList<T> getSortedElements(ArrayList<T> targetArray){
        return getSortedElements(root, targetArray);
    }

    private ArrayList<T> getSortedElements(NodeTwoThree<T> node, ArrayList<T> targetArray){
        if (node == null || node.getSize() == 0) return targetArray;

        getSortedElements(node.first, targetArray);
        targetArray.add(node.keys.get(0));
        getSortedElements(node.second, targetArray);
        if (node.getSize() == 2){
            targetArray.add(node.keys.get(1));
            getSortedElements(node.third, targetArray);
        }
        return targetArray;
    }
    public void insert(T value) {
        insert(root, value);
    }

    public boolean contains(T value){

        return contains(root, value) != null;
    }

    private NodeTwoThree<T> contains(NodeTwoThree<T> targetNode, T value) {
        if (targetNode == null)
            return null;

        if (targetNode.getSize() == 0)
            return null;
        if(targetNode.contains(value)) return targetNode;
        else if (value.compareTo(targetNode.keys.get(0)) < 0) return contains(targetNode.first, value);
        else if (targetNode.getSize() == 1 && value.compareTo(targetNode.keys.get(0)) > 0) return contains(targetNode.second, value);
        else if (targetNode.getSize() == 2 && value.compareTo(targetNode.keys.get(1)) < 0) return contains(targetNode.second, value);
        else return contains(targetNode.third, value);

    }

    public void delete(T value){
        delete(root, value);
    }

    private void delete(NodeTwoThree<T> targetNode, T value) {
        if (contains(value) == false) throw new NoSuchElementException("Element " + value + " is not presented in the TreeTwoThree");

        NodeTwoThree<T> item = contains(targetNode, value);

        NodeTwoThree<T> min;

        if (item.keys.get(0).equals(value)) min = findMin(item.second);
        else min = findMin(item.third);

        if (min != null){
            T z = item.keys.get(0).equals(value) ? item.keys.get(0) : item.keys.get(1);

            item.keys.set(item.keys.indexOf(z), min.keys.get(0));
            min.keys.set(0, z);


            item = min;
        }
        item.remove(value);
        fix(item);
        if (root.getSize() == 0 && root.isLeaf() == false){
            if (root.first != null) root = root.first;
            else root = root.second;
        }
    }

    private void fix(NodeTwoThree<T> leaf) {
        if (leaf.getSize() == 0 && leaf.parent == null){
               root = new NodeTwoThree<>();
               return;
        }

        if (leaf.getSize() != 0){
            if (leaf.parent != null) fix(leaf.parent);
            else return;
        }
        else {
            var parent = leaf.parent;
            if (parent.first.getSize() == 2 || parent.second.getSize() == 2 || parent.getSize() == 2)
                leaf = redistribute(leaf);
            else if (parent.getSize() == 2 && parent.third.getSize() == 2) leaf = redistribute(leaf);
            else leaf = merge(leaf);

            fix(leaf);
        }
    }
    public String toString()
    {
        StringBuilder buffer = new StringBuilder(1000);
        root.print(buffer, "", "");
        return buffer.toString();
        //recDisplayTree(root, 0, 0);
    }

    private NodeTwoThree<T> redistribute(NodeTwoThree<T> leaf) {
        var parent = leaf.parent;
        var first = parent.first;
        var second = parent.second;
        var third = parent.third;

        if (parent.getSize() == 2 && first.getSize() < 2 && second.getSize() < 2 && third.getSize() < 2){
            if (first.equals(leaf)){
                parent.first = parent.second;
                parent.second = parent.third;
                parent.third = null;

                parent.first.insert(parent.keys.get(0));
                parent.first.third = parent.first.second;
                parent.first.second = parent.first.first;

                if (leaf.first != null) parent.first.first = leaf.first;
                else if (leaf.second != null) parent.first.first = leaf.second;

                if (parent.first.first != null) parent.first.first.parent = parent.first;

                parent.remove(parent.keys.get(0));

            }
            else if (second.equals(leaf)){
                first.insert(parent.keys.get(0));
                parent.remove(parent.keys.get(0));
                if(leaf.first != null) first.third = leaf.first;
                else if (leaf.second != null) first.third = leaf.second;

                if(first.third != null) first.third.parent = first;

                parent.second = parent.third;
                parent.third = null;

            }
            else if (third.equals(leaf)){
                second.insert(parent.keys.get(1));
                parent.third = null;
                parent.remove(parent.keys.get(1));

                if(leaf.first != null) second.third = leaf.first;
                else if (leaf.second != null) second.third = leaf.second;

                if (second.third != null) second.third.parent = second;

            }
        }
        else if (parent.getSize() == 2 && (first.getSize() == 2 || second.getSize() == 2 || third.getSize() == 2)){

            if (third.equals(leaf)){
                if (leaf.first != null){
                    leaf.second = leaf.first;
                    leaf.first = null;
                }

                leaf.insert(parent.keys.get(1));

                if (second.getSize() == 2) {
                    parent.keys.set(1, second.keys.get(1));
                    second.remove(second.keys.get(1));
                    leaf.first = second.third;
                    second.third = null;
                    if (leaf.first != null) leaf.first.parent = leaf;
                }
                else if (first.getSize() == 2) {
                    parent.keys.set(1, second.keys.get(0));
                    leaf.first = second.second;
                    second.second = second.first;
                    if (leaf.first != null) leaf.first.parent = leaf;

                    second.keys.set(0, parent.keys.get(0));
                    parent.keys.set(0, first.keys.get(1));
                    first.remove(first.keys.get(1));
                    second.first = first.third;
                    if (second.first != null) second.first.parent = second;
                    first.third = null;

                }

            }
            else if (second.equals(leaf)){
                if (first.getSize() == 2) {
                    if (leaf.second == null){
                        leaf.second = leaf.first;
                        leaf.first = null;
                    }
                    second.insert(parent.keys.get(0));
                    parent.keys.set(0, first.keys.get(1));
                    first.remove(first.keys.get(1));
                    second.first = first.third;
                    if (second.first != null) second.first.parent = second;

                    first.third = null;

                }
                else if (third.getSize() == 2) {

                    if (leaf.first == null){
                        leaf.first = leaf.second;
                        leaf.second = null;
                    }
                    second.insert(parent.keys.get(1));
                    parent.keys.set(1, third.keys.get(0));
                    third.remove(third.keys.get(0));
                    second.second = third.first;
                    if (second.second != null) second.second.parent = second;
                    third.first  = third.second;
                    third.second = third.third;
                    third.third = null;
                }
            }
            else if (first.equals(leaf)){
                if (leaf.first == null){
                    leaf.first = leaf.second;
                    leaf.second = null;
                }
                first.insert(parent.keys.get(0));
                if (second.getSize() == 2) {
                    parent.keys.set(0, second.keys.get(0));
                    second.remove(second.keys.get(0));
                    first.second = second.first;
                    if (first.second != null) first.second.parent = first;
                    second.first = second.second;
                    second.second = second.third;
                    second.third = null;
                }
                else if (third.getSize() == 2) {
                    parent.keys.set(0, second.keys.get(0));
                    second.keys.set(0, parent.keys.get(1));
                    parent.keys.set(1, third.keys.get(0));
                    third.remove(third.keys.get(0));

                    first.second = second.first;
                    if (first.second != null) first.second.parent = first;
                    second.first = second.second;
                    second.second = third.first;

                    if (second.second != null) second.second.parent = second;
                    third.first = third.second;
                    third.second = third.third;
                    third.third = null;
                }

            }
        }
        else if (parent.getSize() == 1){
            leaf.insert(parent.keys.get(0));
            if (first.equals(leaf) && second.getSize() == 2){
                parent.keys.set(0, second.keys.get(0));
                second.remove(second.keys.get(0));
                if (leaf.first == null) leaf.first = leaf.second;

                leaf.second = second.first;
                second.first = second.second;
                second.second = second.third;
                second.third = null;
                if (leaf.second != null) leaf.second.parent = leaf;
            }
            else if (second.equals(leaf) && first.getSize() == 2){
                parent.keys.set(0, first.keys.get(1));
                first.remove(first.keys.get(1));

                if(leaf.second == null) leaf.second = leaf.first;

                leaf.first = first.third;
                first.third = null;
                if (leaf.first != null) leaf.first.parent = leaf;

            }
        }
        return parent;
    }

    private NodeTwoThree<T> merge(NodeTwoThree<T> leaf) {
        var parent = leaf.parent;

        if (parent.first.equals(leaf)){
            parent.second.insert(parent.keys.get(0));
            parent.second.third = parent.second.second;
            parent.second.second = parent.second.first;

            if (leaf.first != null) parent.second.first = leaf.first;
            else if (leaf.second != null) parent.second.first = leaf.second;

            if (parent.second.first != null) parent.second.first.parent = parent.second;

            parent.remove(parent.keys.get(0));
            parent.first = null;
        }
        else if (parent.second.equals(leaf)){
            parent.first.insert(parent.keys.get(0));
            if (leaf.first != null) parent.first.third = leaf.first;
            if (leaf.second != null) parent.first.third = leaf.second;

            if(parent.first.third != null) parent.first.third.parent = parent.first;

            parent.remove(parent.keys.get(0));
            parent.second = null;
        }

        if (parent.parent == null){
            NodeTwoThree<T> tmp;

            if (parent.first != null) tmp = parent.first;
            else tmp = parent.second;
            assert tmp != null;
            tmp.parent = null;
            return tmp;
        }
        return parent;
    }

    private NodeTwoThree<T> findMin(NodeTwoThree<T> targetNode){
        if (targetNode == null || targetNode.isLeaf()) return targetNode;
        return findMin(targetNode.first);
    }


    private void insert(NodeTwoThree<T> targetNode, T value){
        //if (contains(value)) throw new Exception("Such element is already presented");
        if (targetNode.getSize() == 0 && targetNode.parent == null) {
            targetNode.insert(value);
            return;
        }
        if (targetNode.isLeaf()) targetNode.insert(value);
        else if (value.compareTo(targetNode.keys.get(0)) < 0) insert(targetNode.first, value);
        //2-вершина
        else if (targetNode.getSize() == 1){
            insert(targetNode.second, value);
        }
        //3-вершина
        else {
            if (value.compareTo(targetNode.keys.get(1)) <= 0) insert(targetNode.second, value);
            else insert(targetNode.third, value);
        }

        split(targetNode);
    }


    private void split(NodeTwoThree<T> targetNode) {
        if(targetNode.getSize() < 3) return;

        NodeTwoThree<T> left = new NodeTwoThree<>(targetNode.keys.get(0), targetNode.first, targetNode.second, null, null, targetNode.parent);
        NodeTwoThree<T> right = new NodeTwoThree<>(targetNode.keys.get(2), targetNode.third, targetNode.fourth, null, null, targetNode.parent);

        //указываем родителей для новых вершин
        if (left.first != null) left.first.parent = left;
        if (left.second != null) left.second.parent = left;
        if (right.first != null) right.first.parent = right;
        if (right.second != null) right.second.parent = right;

        var parent = targetNode.parent;
        if (parent != null){
            parent.insert(targetNode.keys.get(1));

            //отвязываем старую от родителя
            if (parent.first.equals(targetNode)) parent.first = null;
            else if (parent.second.equals(targetNode)) parent.second = null;
            else if (parent.third.equals(targetNode)) parent.third = null;

            //нет детей
            if(parent.first == null){
                parent.fourth = parent.third;
                parent.third = parent.second;
                parent.second = right;
                parent.first = left;
            }
            //есть один ребёнок
            else if(parent.second == null){
                parent.fourth = parent.third;
                parent.third = right;
                parent.second = left;
            }
            //есть двое детей
            else {
                parent.fourth = right;
                parent.third = left;

            }
        }
        else {
            parent = new NodeTwoThree<>(targetNode.keys.get(1), left, right, null, null, null);
            left.parent = parent;
            right.parent = parent;
            root = parent;
        }
    }

    private NodeTwoThree<T> findMin(NodeTwoThree<T> targetNode, T value) {
        if (targetNode == null) return null;
        if (targetNode.first == null) return targetNode;
        else return findMin(targetNode.first, value);
    }

}
