package com.liuy307.list;

import java.util.*;
import java.util.function.Consumer;

public class MyArrayList<E> {
    private static final Object[] EMPTY = {};
    private static final Object[] DEFAULT_Capacity_EMPTY = {};
    private static final int DEFAULT_Capacity = 20;

    private Object[] elementData;
    int size;

    public MyArrayList() {
        this.elementData = DEFAULT_Capacity_EMPTY;
    }

    public MyArrayList(int initCapacity) {
        if (initCapacity == 0)
            this.elementData = EMPTY;
        else
            this.elementData = new Object[initCapacity];
    }

    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }



    private void ensureCapacityInternal(int minCapacity) {
        minCapacity = reviseCapacity(minCapacity);
        if (minCapacity > elementData.length) //重要,判断需不需要扩容
            grow(minCapacity);
    }

    private int reviseCapacity(int minCapacity) {
        if (this.elementData == DEFAULT_Capacity_EMPTY)
            return DEFAULT_Capacity;
        return minCapacity;
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); //(oldCapacity>>1)要用括号，运算优先级
        if (newCapacity < minCapacity)
            newCapacity = minCapacity;
        this.elementData = Arrays.copyOf(elementData, newCapacity);
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
        ensureCapacityInternal(size + 1);
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

    public Object[] toArray() {
//        return Arrays.copyOf(elementData, elementData.length);
        return Arrays.copyOf(elementData, size); //返回非空元素的数组
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        Object[] a = c.toArray();
        int len = a.length;
        ensureCapacityInternal(size+len);
        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + len, size-index);
        System.arraycopy(a, 0, elementData, index, len);
        size += len; //记得加size
    }

    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            if(cursor >= size)
                throw new NoSuchElementException();
            E e = (E) MyArrayList.this.elementData[cursor];
            lastRet = cursor;
            cursor ++;
            return e;
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }
//    int expectedModCount = modCount;
    }

}


