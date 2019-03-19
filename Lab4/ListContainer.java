package com.havryshkiv;

import java.util.Iterator;

public class ListContainer<T> implements Iterable<T> {
    private Node first;
    private int size = 0;
    private Object arr[];
    public void add(T value){
        Node t = new Node();
        t.setValue(value);
        if(first != null){
            Node temp = first;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(t);
        }
        else{
            first = t;
        }
        size++;
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        if(size <= 0){
            return null;
        }
        Node temp = first;
        while(temp != null){
            str.append(temp.getValue());
            temp = temp.getNext();
        }
        return new String(str);
    }
    public boolean remove(T value){
        if(size <= 0){
            return false;
        }
        Node prev = null;
        Node temp = first;
        while(temp != null){
            if(temp.getValue().equals(value)){
                if(prev == null) first = temp.getNext();
                else if(temp.getNext() == null) prev.setNext(null);
                else prev.setNext(temp.getNext());
                size--;
                return true;
            }
            prev = temp;
            temp = temp.getNext();
        }
        return false;
    }
    public boolean remove(int id){
        if(size <= 0){
            return false;
        }
        int counter = 0;
        Node prev = null;
        Node temp = first;
        while(temp != null){
            if(counter == id){
                if(prev == null) first = temp.getNext();
                else if(temp.getNext() == null) prev.setNext(null);
                else prev.setNext(temp.getNext());
                size--;
                return true;
            }
            prev = temp;
            temp = temp.getNext();
        }
        return false;
    }
    public T get(int index){
        int counter = 0;
        Node temp = first;
        while(temp != null){
            if(counter++ == index){
                return (T) temp.getValue();
            }
            temp = temp.getNext();
        }
        return null;
    }

    public int size(){
        return size;
    }
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = get(i);
        }
        return arr;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter;

            {
                counter = 0;
            }

            @Override

            public boolean hasNext() {
                return counter < size();
            }

            @Override
            public T next() {
                return get(counter++);
            }
        };
    }
}
