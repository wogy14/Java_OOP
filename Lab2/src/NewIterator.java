package com.tasks5.command;


import java.util.Iterator;

class NewIterator implements Iterator<String> {
    private MyString str;
    private int current = 0;
    private int last;

    public NewIterator(MyString str, int length) {
        this.str = str;
        last = length - 1;
    }
    public String next() {
        return (String) str.get(current++);
    }
    public boolean hasNext() {
        return (current <= last);
    }
    public void remove() {
        str.remove(--current);
    }
}