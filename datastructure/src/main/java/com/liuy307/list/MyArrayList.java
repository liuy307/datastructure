package com.liuy307.list;

import java.util.Arrays;

public class MyArrayList<E> {
    private static final Object[] EMPTY = {};
    private static final Object[] DEFAULT_CAPCITY_EMPTY = {};
    private static final int DEFAULT_CAPCITY = 20;

    private Object[] elementData;
    int size;

    public MyArrayList() {
        this.elementData = DEFAULT_CAPCITY_EMPTY;
    }

    public MyArrayList(int initCapcity) {
        if (initCapcity == 0)
            this.elementData = EMPTY;
        else
            this.elementData = new Object[initCapcity];
    }

    public boolean add(E e) {
        ensureCapcityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }



    private void ensureCapcityInternal(int minCapcity) {
        minCapcity = reviseCapcity(minCapcity);
        if (minCapcity > elementData.length) //重要,判断需不需要扩容
            grow(minCapcity);
    }

    private int reviseCapcity(int minCapcity) {
        if (this.elementData == DEFAULT_CAPCITY_EMPTY)
            return DEFAULT_CAPCITY;
        return minCapcity;
    }

    private void grow(int minCapcity) {
        int oldCapcity = elementData.length;
        int newCapcity = oldCapcity + (oldCapcity >> 1); //(oldCapcity>>1)要用括号，运算优先级
        if (newCapcity < minCapcity)
            newCapcity = minCapcity;
        this.elementData = Arrays.copyOf(elementData, newCapcity);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    public boolean add(E e, int index) {
        rangeCheckForAdd(index);
        ensureCapcityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
        return true;
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    public E remove(int index) {
        rangeCheck(index);
        E oldValue = elementData(index);
        int moveNum =  size-index-1;

        if(moveNum > 0)
            System.arraycopy(elementData, index+1, elementData, index, size-index-1);
        elementData[--size] = null; //记得回收资源

        return oldValue;


    }

    public void removeRange(int fromIndex, int toIndex) {
        int moveNum =  size - toIndex;
        if (moveNum > 0)
            System.arraycopy(elementData, toIndex+1, elementData, fromIndex, moveNum);

        int newSize = size - (toIndex - fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    public int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
    }

}
