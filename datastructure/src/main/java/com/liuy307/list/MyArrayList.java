package com.liuy307.list;

import java.util.Arrays;

public class MyArrayList<E>{

    private Object[] elementData;

    private int size;

    private static final int DEFAULT_CAPACITY = 8;
    private static final Object[] DEFAULT_CAPACITY_EMPTY = {};
    private static final Object[] EMPTY = {};


    public MyArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY;
    }

    public MyArrayList(int initCapacity) {
        if(initCapacity == 0)
            this.elementData = EMPTY;
        else if(initCapacity > 0)
            this.elementData = new Object[initCapacity];
        else
            throw new IllegalArgumentException("Illegal Capacity: "+ initCapacity);
    }

    public void add(E e) {
        ensureCapacityInternal(size+1);
        this.elementData[size++] = e;
    }


    public void add(int index, E e) {
        addRangCheck(index);
        ensureCapacityInternal(size+1);
        int moveNums = size - index;
        System.arraycopy(this.elementData, index, this.elementData, index+1, moveNums);
        this.elementData[index] = e;
        size++;
    }

    void addRangCheck(int index) {
        if(index < 0  || index > size)
            throw new IllegalArgumentException("Illegal index: "+ index);
    }

    private void ensureCapacityInternal(int minCapacity) {
        minCapacity = reviseMinCapacity(minCapacity);
        if(minCapacity > this.elementData.length) {
            grow(minCapacity);
        }
    }

    private int reviseMinCapacity(int minCapacity) {
        if(this.elementData == DEFAULT_CAPACITY_EMPTY) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);//注意
        }
        return minCapacity;
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity>>1);
        if(newCapacity < minCapacity) //要确认需要容量和扩充后容量
            newCapacity = minCapacity;
        this.elementData = Arrays.copyOf(this.elementData, newCapacity);
        //this.size = newCapacity; 不能在这加
    }

    public E remove(int index) {
        removeRangeCheck(index);
        E oldData = (E)this.elementData[index];
        int moveNums = size - index - 1;
        System.arraycopy(this.elementData, index+1, this.elementData, index, moveNums);
        this.elementData[--size] = null;
        return oldData;
    }
    private void removeRangeCheck(int index) {
        if(index < 0 || index >=size)
            throw new IllegalArgumentException("Illegal index: "+ index);
    }
}
