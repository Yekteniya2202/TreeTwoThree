package com.company.classes;
import com.company.interfaces.ITree;
import com.company.interfaces.IValidTree;
public class TreeTwoThreeChecker<T extends  Comparable<T>> implements IValidTree<T> {

    @Override
    public boolean isValid(ITree<T> tree) {
        return isValid((TreeTwoThree<T>)tree);
    }

    public boolean isValid(TreeTwoThree<T> tree){
        if (tree.root.keys.size() == 0 || tree.root.keys.size() == 1) return true;
        return isValid(tree.root);
    }

    private boolean isValid(NodeTwoThree<T> node) {
        if (node == null) return true;
        if (node.isLeaf()) return isValidLeaf(node);
        if (isTwoNode(node)) return isValidNode(node) && isValid(node.first) && isValid(node.second);
        if (isThreeNode(node)) return isValidNode(node) && isValid(node.first) && isValid(node.second) && isValid(node.third);
        return true;
    }

    private boolean isTwoNode(NodeTwoThree<T> node){
        if (node.isLeaf() && node.keys.size() == 1) return true;
        return node.keys.size() == 1 && node.first != null && node.second != null && node.third == null;
    }

    private boolean isThreeNode(NodeTwoThree<T> node){
        if (node.isLeaf() && node.keys.size() == 2) return true;
        return node.keys.size() == 2 && node.first != null && node.second != null && node.third != null;
    }

    private boolean isValidNode(NodeTwoThree<T> node){
        if (isTwoNode(node)){
            return isValidLeft_Two(node) && isValidRight_Two(node);
        }
        else if (isThreeNode(node)){
            return isValidLeft_Three(node) && isValidMiddle_Three(node) && isValidRight_Three(node);
        }
        else if (node.isLeaf()){
            return isValidLeaf(node);
        }
        return false;
    }

    private boolean isValidLeaf(NodeTwoThree<T> node) {
        return node.first == null && node.second == null && node.third == null;
    }

    private boolean isValidLeft_Three(NodeTwoThree<T> node) {
        T nodeElementFirst = node.keys.get(0);
        T nodeElementSecond = node.keys.get(1);
        boolean isLeftValid = false;
        if (isTwoNode(node.first)){
            if (node.first.keys.get(0).compareTo(nodeElementFirst) <= 0 && node.first.keys.get(0).compareTo(nodeElementSecond) <= 0){
                isLeftValid = true;
            }
        }
        else if (isThreeNode(node.first)) {
            if (node.first.keys.get(0).compareTo(nodeElementFirst) <= 0 && node.first.keys.get(0).compareTo(nodeElementSecond) <= 0 &&
                node.first.keys.get(1).compareTo(nodeElementFirst) <= 0 && node.first.keys.get(1).compareTo(nodeElementSecond) <= 0){
                isLeftValid = true;
            }
        }
        return isLeftValid;
    }
    private boolean isValidMiddle_Three(NodeTwoThree<T> node) {
        T nodeElementFirst = node.keys.get(0);
        T nodeElementSecond = node.keys.get(1);
        boolean isMiddleValid = false;
        if (isTwoNode(node.second)){
            if (node.second.keys.get(0).compareTo(nodeElementFirst) >= 0 && node.second.keys.get(0).compareTo(nodeElementSecond) <= 0){
                isMiddleValid = true;
            }
        }
        else if (isThreeNode(node.second)) {
            if (node.second.keys.get(0).compareTo(nodeElementFirst) >= 0 && node.second.keys.get(0).compareTo(nodeElementSecond) <= 0 &&
                node.second.keys.get(1).compareTo(nodeElementFirst) >= 0 && node.second.keys.get(1).compareTo(nodeElementSecond) <= 0){
                isMiddleValid = true;
            }
        }
        return isMiddleValid;
    }
    private boolean isValidRight_Three(NodeTwoThree<T> node) {
        T nodeElementFirst = node.keys.get(0);
        T nodeElementSecond = node.keys.get(1);
        boolean isRightValid = false;
        if (isTwoNode(node.third)){
            if (node.third.keys.get(0).compareTo(nodeElementFirst) >= 0 && node.third.keys.get(0).compareTo(nodeElementSecond) >= 0){
                isRightValid = true;
            }
        }
        else if (isThreeNode(node.third)) {
            if (node.third.keys.get(0).compareTo(nodeElementFirst) >= 0 && node.third.keys.get(0).compareTo(nodeElementSecond) >= 0 &&
                node.third.keys.get(1).compareTo(nodeElementFirst) >= 0 && node.third.keys.get(1).compareTo(nodeElementSecond) >= 0){
                isRightValid = true;
            }
        }
        return isRightValid;
    }

    private boolean isValidLeft_Two(NodeTwoThree<T> node) {
        boolean isLeftValid = false;
        T nodeElement = node.keys.get(0);
        if (isTwoNode(node.first)) {
            if (node.first.keys.get(0).compareTo(nodeElement) <= 0) {
                isLeftValid = true;
            }
        }
        else if (isThreeNode(node.first)) {
            if (node.first.keys.get(0).compareTo(nodeElement) <= 0 && node.first.keys.get(1).compareTo(nodeElement) <= 0) {
                isLeftValid = true;
            }
        }
        return isLeftValid;
    }

    private boolean isValidRight_Two(NodeTwoThree<T> node) {
        boolean isRightValid = false;
        T nodeElement = node.keys.get(0);
        if (isTwoNode(node.second)){
            if (node.second.keys.get(0).compareTo(nodeElement) >= 0){
                isRightValid = true;
            }
        }
        else if (isThreeNode(node.second)) {
            if (node.second.keys.get(0).compareTo(nodeElement) >= 0 && node.second.keys.get(1).compareTo(nodeElement) >= 0){
                isRightValid = true;
            }
        }
        return isRightValid;
    }


}
