package com.italom.java.data.structures;

import java.util.Arrays;

public class Vector<T> {

    private static final int MIN_SIZE = 100;
    private Object[] elements = new Object[MIN_SIZE];
    private int nextEmptyPosition = 0;

    public void add(T element) {
        expandIfNeeded();
        elements[nextEmptyPosition++] = element;
    }

    public void add(int index, T element) {
        expandIfNeeded();
        validateIndexInsertion(index);
        for (int i = nextEmptyPosition - 1; i >= index; i--)
            elements[i + 1] = elements[i];
        elements[index] = element;
        nextEmptyPosition++;
    }

    private void expandIfNeeded() {
        if (nextEmptyPosition == elements.length) {
            Object newElements[] = new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++)
                newElements[i] = elements[i];
            elements = newElements;
        }
    }

    private void contractIfNeeded() {
        if (nextEmptyPosition < elements.length / 2 && elements.length > MIN_SIZE) {
            Object newElements[] = new Object[elements.length /2 ];
            for (int i = 0; i < newElements.length; i++)
                newElements[i] = elements[i];
            elements = newElements;
        }
    }

    private void validateIndexInsertion(int index) {
        if (index < 0 || index > nextEmptyPosition)
            throw new IndexOutOfBoundsException("Cannot insert at index: " + index);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        validateIndexAccess(index);
        return (T) elements[index];
    }

    private void validateIndexAccess(int index) {
        if (index < 0 || index >= nextEmptyPosition)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }

    public void remove(int index) {
        validateIndexAccess(index);
        contractIfNeeded();
        for (int i = index; i < nextEmptyPosition - 1; i++)
            elements[i] = elements[i + 1];
        elements[nextEmptyPosition-- - 1] = null;
    }

    public boolean contains(T element) {
        for (int i = 0; i < nextEmptyPosition; i++)
            if (element.equals(elements[i])) return true;
        return false;
    }

    public int size() {
        return nextEmptyPosition;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

}
