package com.company.interfaces;

public interface ITree <T>{
    boolean contains(T value);
    void insert(T value);
    void delete(T value);
    String toString();
}
