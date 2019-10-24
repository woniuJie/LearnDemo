package com.zsj.learndemo.suanfa;

import java.util.HashSet;

/**
 * Created by zhangshijie on 2019/8/21;
 * 判断单链表是否有环
 */
public class A {

    public static boolean a(Node node) {
        HashSet set = new HashSet();
        if (node == null || node.getNext() == null) {
            return false;
        }
        Node a = node;
        while (a != null) {
            if (set.contains(a)) {
                return true;
            }
            set.add(a);
            a = a.getNext();
        }

        return false;

    }


}
