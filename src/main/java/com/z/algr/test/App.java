package com.z.algr.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhoudongliang
 * @description used for [algorithm]
 * @date 2020/10/5
 */
public class App {
    public static void main(String[] args) {
        Long l1 = new Long(1L);
        Long l2 = new Long(1L);
        Integer i1 = -129;
        Integer i2 = -129;
        Integer ii1 = new Integer(1);
        int ii2 = 1;
        System.out.println(ii1==ii2);
    }
    public static void main4(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        ListNode listNode = reverseKGroup(root, 4);
        System.out.println(listNode);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode rst = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return rst;
//        ListNode pre = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode next = curr.next;
//            curr.next = pre;
//            pre = curr;
//            curr = next;
//        }
//        return pre;
    }

    /**
     * 测试主函数
     */
    public static void main1(String[] args) {
        /**
         * 创建一个二叉树
         *    0
         *  1   2
         * 3 4 5 6
         */
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        // 选两个测试节点用例
        TreeNode nodeA = root.right.left;
        TreeNode nodeB = root.right.right;

        // 查找
        TreeNode closestCommonAncestor = findClosestCommonAncestor(root, nodeA, nodeB);

        // 打印结果
        System.out.println(null == closestCommonAncestor ? "空" : "公共祖先的值为：" + closestCommonAncestor.val);
    }

    /**
     * 查找二叉树里节点A和B最近的公共祖先
     *
     * @param root  二叉树
     * @param nodeA 节点A
     * @param nodeB 节点B
     * @return 祖先
     */
    public static TreeNode findClosestCommonAncestor(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        // 按前序遍历为例
        // 非法数据判断：如果传入的树为空，则直接返回空
        if (root == null) {
            return null;
        }
        // 边界数据判断：如果传入的两节点是根，则直接返回根
        if (root == nodeA || root == nodeB) {
            return root;
        }
        // 左子树查找
        TreeNode left = findClosestCommonAncestor(root.left, nodeA, nodeB);
        // 右子树查找
        TreeNode right = findClosestCommonAncestor(root.right, nodeA, nodeB);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            // 左右子树都不为空
            return root;
        }
    }


    /**
     * 二叉树定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public void zeroCopy() {
        try {
            File f = new File("txt");
            RandomAccessFile raf = new RandomAccessFile(f, "r");
            FileChannel fc = raf.getChannel();
            Socket socket = new Socket("127.0.0.1", 1);
            fc.transferTo(0, raf.length(), socket.getChannel());
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Created by chengxiao on 2016/12/17.
     * 堆排序demo
     */
//    public static void main(String[] args) {
//        System.out.println(6-4/2);
//        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        sort(arr);
//        System.out.println(Arrays.toString(arr));
//    }
    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void 零拷贝() {
        try {
            File file = new File("demo.zip");
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = raf.getChannel();
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("", 1234));
// 直接使用了transferTo()进行通道间的数据传输
            fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        int a = 1;
//        for (int i = 0; i < 16; i++) {
//            a*=2;
//        }
//        System.out.println(a);
////        Integer a=1;
////        Integer b=2;
////        Integer c=3;
////        Long d=3L;
////        System.out.println(d==(a+b));
//    }

    /**
     * 1 2 3
     * 1 2 3
     * 1 2 3
     */
//    public static void main(String[] args) {
//        int[][] grid = new int[3][3];
//        grid[0][0] = 1;
//        grid[0][1] = 2;
//        grid[0][2] = 3;
//        grid[1][0] = 1;
//        grid[1][1] = 2;
//        grid[1][2] = 3;
//        grid[2][0] = 1;
//        grid[2][1] = 2;
//        grid[2][2] = 3;
//        System.out.println(矩阵最短路径(grid));
//    }
    public static int 矩阵最短路径(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else {
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    public static void main2(String[] args) {
        System.out.println(最长子串("aaabcdefgggg"));
    }

    public static int 最长子串(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;

    }

    //    public static void main(String[] args) {
//        System.out.println(最长回文子串("cabaaab"));
//    }

    public String 最长回文子串(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

//    public static void main(String[] args) {
//        char[][] board=new char[2][2];
//        System.out.println(exist(board,""));
//    }


    public static boolean 矩阵中是否有单词(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    static boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        char tmp = board[i][j];
        board[i][j] = '/';
        boolean res = dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) ||
                dfs(board, word, i, j - 1, k + 1);
        board[i][j] = tmp;
        return res;
    }

    public static void main3(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        ListNode swap = swaptwo(root);
        System.out.println(swap.toString());
    }

    public static ListNode swaptwo(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        ListNode temp = root.next;
        root.next = swaptwo(temp.next);
        temp.next = root;
        return temp;
    }

    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode rtn = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return rtn;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);//递归下一个
        head.next.next = head;//那么当前节点就相当于下一个的上一个节点
        head.next = null;//截断原来顺序的下一个节点
        return p;
    }

//    public static void main(String[] args) {
//        int arr[] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//        quick_sort(arr, 0, 9);
//        System.out.println(arr);
//    }

    public static void quick_sort(int s[], int l, int r) {
        if (l < r) {
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) {
                    j--;
                }
                if (i < j) {
                    s[i++] = s[j];
                }
                while (i < j && s[i] < x) {
                    i++;
                }
                if (i < j) {
                    s[j--] = s[i];
                }
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }

//    public static void main(String[] args) {
//        int[] array={1,1,2,3};
//        int[] ints = removeDuplicates(array);
//        System.out.println(ints);
//    }

    public static int[] removeDuplicates(int[] array) {
        int index = 0;
        int[] newArray = new int[array.length];
        Map<Integer, Boolean> maps = new LinkedHashMap<Integer, Boolean>();
        for (int num : array) {
            if (!maps.containsKey(num)) {
                newArray[index++] = num;
                maps.put(num, true);
            }
        }

        return newArray;
    }

//    public static void main(String[] args) {
//        ListNode l1=new ListNode(1);
//        l1.next=new ListNode(2);
//        l1.next.next=new ListNode(3);
//        ListNode l2=new ListNode(7);
//        l2.next=new ListNode(8);
//        l2.next.next=new ListNode(9);
//        l2.next.next.next=new ListNode(1);
//        l2.next.next.next.next=new ListNode(1);
//        ListNode l = addTwo(l1,l2);
//        System.out.println(l);
//    }

    public static ListNode 两数相加(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int carry = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.val = sum % 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (l1 != null || l2 != null) {
                cur.next = new ListNode(0);
                cur = cur.next;
            }
        }
        return dummy;
    }

    boolean init() {
        Node node4 = new Node(5);
        Node node3 = new Node(4);
        Node node2 = new Node(3);
        Node node1 = new Node(2);
        Node head = new Node(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return 链表有环(head);
    }

    boolean 链表有环集合(Node head) {
        Set set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }

    boolean 链表有环(Node head) {
        Node n1 = head;
        Node n2 = head.next;
        while (n1 != null && n1.next != null) {
            if (n1 == n2) {
                return true;
            } else {
                n1 = n1.next;
                n2 = n2.next.next;
            }
        }
        return false;
    }

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}

