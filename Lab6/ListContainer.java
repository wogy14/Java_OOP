package com.havryshkiv;

import java.util.Iterator;

public class ListContainer implements Iterable {
    public class Node {
        private double value;
        private Node next;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    private Node first;
    private int size = 0;
    private Object arr[];
    public void add(double value){
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
    public boolean remove(double value){
        if(size <= 0){
            return false;
        }
        Node prev = null;
        Node temp = first;
        while(temp != null){
            if(temp.getValue() == value){
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
    public double get(int index){
        int counter = 0;
        Node temp = first;
        while(temp != null){
            if(counter++ == index){
                return (double) temp.getValue();
            }
            temp = temp.getNext();
        }
        return 0;
    }

    public int size(){
        return size;
    }
    public double[] toArray(){
        @SuppressWarnings("unchecked")
        double[] arr = (double[]) new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = get(i);
        }
        return arr;
    }
    public void RandomAdd(int length){
        for (int i = 0; i < length; i++) {
            double a = Math.random()*100;
            this.add(a);
        }
    }
    public double Average(){
        int count = 0;
        double sum = 0;
        Node temp = first;
        while(temp != null){
            sum += temp.getValue();
            count++;
            temp = temp.getNext();
        }
        return (sum/count);
    }
    public double[] minMax(){
        double min = first.getValue();
        double max = first.getValue();
        Node temp = first;
        while(temp != null){
            if(min  > temp.getValue()){
                min = temp.getValue();
            }
            if(max  < temp.getValue()){
                max = temp.getValue();
            }
            temp = temp.getNext();
        }
        double arr[] = new double[2];
        arr[0] = min;
        arr[1] = max;
        return arr;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int counter;

            {
                counter = 0;
            }

            @Override

            public boolean hasNext() {
                return counter < size();
            }

            @Override
            public Double next() {
                return get(counter++);
            }
        };
    }
}
