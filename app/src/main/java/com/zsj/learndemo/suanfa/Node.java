package com.zsj.learndemo.suanfa;

/**
 * Created by zhangshijie on 2019/8/21;
 */
public class Node {
    int data;
    Node next;

    public Node(int a) {
        this.data = a;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
