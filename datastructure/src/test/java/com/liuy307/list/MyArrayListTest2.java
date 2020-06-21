package com.liuy307.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyArrayListTest2 {

    @Test
    public void add() {
        MyArrayList<Integer> list1 = new MyArrayList(0);
        for (int i = 0; i < 20; i++) {
            list1.add(i);
        }

        MyArrayList<Integer> list2 = new MyArrayList();
        for (int i = 0; i < 20; i++) {
            list2.add(i);
        }
        list2.add(21, 100);
//        list2.add(21, 100);

        List<Integer> list3 = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list3.add(i);
        }
    }
}