package com.zsj.learndemo.suanfa;

/**
 * Created by zhangshijie on 2019/8/19;
 */
public class AUtil {

    /**
     * String 转成int 算法
     *
     * @param str
     * @param radix
     * @return
     */
    public static int parseInt(String str, int radix) throws NumberFormatException {
        if (str == null) {
            throw new NumberFormatException("");
        }
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("");
        }
        int result = 0;
        boolean negative = false;
        int i = 0, len = str.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;
        if (len > 0) {
            char firstChar = str.charAt(0);
            if (firstChar < '0') {
                if (firstChar == '-') {
                    negative = true;
                } else if (firstChar != '+') {
                    throw new NumberFormatException("");
                }

                if (len == 1) {
                    throw new NumberFormatException("");
                }
                i++;
            }

            //如果max =-32 radix = 10   multmin = -3
            multmin = limit / radix;

            while (i < len) {
                //4
                digit = Character.digit(str.charAt(i), radix);

                if (digit < 0) {
                    throw new NumberFormatException("");
                }
                if (result < multmin) {
                    throw new NumberFormatException("");
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException("");
                }
                result -= digit;
            }

        } else {
            throw new NumberFormatException("");
        }

        return negative ? result : -result;
    }

    /**
     * 数字反转  123  --- 321
     *
     * @param a
     * @return
     */
    public static int translate(int a) {
        int result = 0;

        while (a != 0) {
            result = result * 10 + a % 10;
            a = a / 10;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }

        return result;
    }

    /**
     * 快速排序
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    //6,1,2,7,9,3,4,5,10,8
    //6,1,2,4,5,3,8,7,10,8
    public static int[] quicksort(int a[], int left, int right) {
        int i, j, t, temp;
        if (left > right)
            return a;

        temp = a[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while (i != j) {
            //顺序很重要，要先从右边开始找
            while (a[j] >= temp && i < j)
                j--;
            //再找左边的
            while (a[i] <= temp && i < j)
                i++;
            //交换两个数在数组中的位置
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //最终将基准数归位
        a[left] = a[i];
        a[i] = temp;

        quicksort(a, left, i - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(a, i + 1, right);//继续处理右边的 ，这里是一个递归的过程

        return a;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode root = new ListNode(0); // 头结点
        ListNode r = root;
        root.next = l1;

        int carry = 0; // 初始进位
        int sum;
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + carry;
            p1.val = sum % 10; // 本位的结果
            carry = sum / 10; // 本次进位

            r.next = p1;
            r = p1; // 指向最后一个相加的结点
            p1 = p1.next;
            p2 = p2.next;

        }

        if (p1 == null) {
            r.next = p2;
        } else {
            r.next = p1;
        }

        // 最后一次相加还有进位
        if (carry == 1) {
            // 开始时r.next是第一个要相加的结点
            while (r.next != null) {
                sum = r.next.val + carry;
                r.next.val = sum % 10;
                carry = sum / 10;
                r = r.next;
            }

            // 都加完了还有进位，就要创建一个新的结点
            if (carry == 1) {
                r.next = new ListNode(1);
            }
        }

        return root.next;
    }


    /**
     * 二分查找  递归
     *
     * @param a
     * @param i
     * @param low
     * @param high
     * @return
     */
    public static int find(int[] a, int i, int low, int high) {
        if (i < a[low] || i > a[high] || low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (a[mid] > i) {
            return find(a, i, low, mid - 1);
        } else if (a[mid] < i) {
            return find(a, i, mid + 1, high);
        } else {
            return mid;
        }
    }

    //0,1,2,3,4,5,6,7,8
    public static int find1(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        if(key < a[low] || key > a[high] || low > high){
            return -1;
        }

        while (low <= high) {
            mid = (low + high) / 2;
            if(a[mid]<key){
                low = mid+1;
            }else if(a[mid]>key){
                high = mid-1;
            }else {
                return mid;
            }
        }
        return -1;

    }


}
