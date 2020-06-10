package com.liuy307.list;

import org.junit.Test;

public class MyArrayListTest {
    @Test
    public void MyArrayListTest1() {
        MyArrayList list1 = new MyArrayList(0);
        for (int i = 0; i < 20; i++) {
            list1.add(i);
        }
        list1.add(21);
        MyArrayList list2 = new MyArrayList();
        for (int i = 0; i < 20; i++) {
            list2.add("22");
        }
    }

    @Test
    public void MyArrayListTest2() {
        MyArrayList<Integer> list1 = new MyArrayList();
        System.out.println(list1.isEmpty());
        for (int i = 0; i < 21; i++) {
            list1.add(i);
        }
        list1.add(100, 13);
        System.out.println(list1.isEmpty());
        System.out.println(list1.size());
        list1.remove(13);
        list1.removeRange(5, 15);
        //list1.add("a");
    }
}
