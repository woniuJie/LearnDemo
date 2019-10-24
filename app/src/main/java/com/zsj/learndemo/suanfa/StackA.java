package com.zsj.learndemo.suanfa;

/**
 * Created by zhangshijie on 2019/8/21;
 */
public class StackA {
    private int[] data;
    private int top;
    private int size;

    public StackA(int size) {
        this.size = size;
        data = new int[size];
        top = -1;
    }

    /**
     * 压栈
     *
     * @param a
     */
    public void push(int a) throws Exception {
        if (isFull()) {
            throw new Exception("满了");
        }
        top++;
        data[top] = a;
    }

    public int getTop() {
        return top;
    }

    public int getSize() {
        return size;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("没了");
        }
        return this.data[top--];
    }

    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("没了");
        }
        return this.data[top];
    }

    public boolean isFull() {
        return (top + 1 == size);
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
