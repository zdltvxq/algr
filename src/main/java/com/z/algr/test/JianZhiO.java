package com.z.algr.test;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1 2 3 4
 * 12 13 14 5
 * 11 16 15 6
 * 10 9 8 7
 * <p>
 * 00 01 02 12 22 21 20 10 11
 */
public class JianZhiO {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }

    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }else{
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left,right)+1;
        }
    }


    public static void main5(String[] args) {
        JianZhiO j = new JianZhiO();
        j.toRebuild();
    }

    int[] preArray = {1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15};
    int[] midArray = {8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15};
    Map<Integer, Integer> midMap = new HashMap<>();

    public void toRebuild() {
        for (int i = 0; i < midArray.length; i++) {
            midMap.put(midArray[i], i);
        }

        TreeNode tn = this.building(0, 0, midArray.length - 1);
        System.out.println(tn);
    }

    private TreeNode building(int preRootIdx, int midLeftIdx, int midRightIdx) {
        if (midLeftIdx > midRightIdx) {
            return null;
        }
        int midRootIdx = midMap.get(preArray[preRootIdx]);
        TreeNode rt = new TreeNode(preArray[preRootIdx]);
        rt.left = building(preRootIdx + 1, midLeftIdx, midRootIdx - 1);
        rt.right = building(preRootIdx + midRootIdx - midLeftIdx + 1, midRootIdx + 1, midRightIdx);
        return rt;
    }


    public static void main4(String[] args) {
        int[][] m = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}/*,{10,9,8,7}*/};
        System.out.println(Arrays.toString(clockwise(m)));
    }

    public static int[] clockwise(int[][] m) {
        if (m == null || m.length == 0) {
            return new int[0];
        }
        int left = 0, right = m[0].length - 1, top = 0, idx = 0, rst[] = new int[m.length * m[0].length], bottom = m.length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                rst[idx++] = m[top][i];
            }
            if (++top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                rst[idx++] = m[i][right];
            }
            if (left > --right) {
                break;
            }
            for (int i = right; i >= left; i--) {
                rst[idx++] = m[bottom][i];
            }
            if (top > --bottom) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                rst[idx++] = m[i][left];
            }
            if (++left > right) {
                break;
            }
        }
        return rst;
    }

    public int[] spiralOrder4(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int l = 0, r = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, x = 0;
        int[] result = new int[(r + 1) * (bottom + 1)];
        while (true) {
            for (int i = l; i <= r; i++) {
                result[x++] = matrix[top][i];
            } // left to right.
            if (++top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                result[x++] = matrix[i][r];
            } // top to bottom.
            if (l > --r) {
                break;
            }
            for (int i = r; i >= l; i--) {
                result[x++] = matrix[bottom][i];
            } // right to left.
            if (top > --bottom) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                result[x++] = matrix[i][l];
            } // bottom to top.
            if (++l > r) {
                break;
            }
        }
        return result;
    }


    public int[] spiralOrder3(int[][] matrix) {
        //特判
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        //初始化
        int left = 0, top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        int[] res = new int[(right + 1) * (bottom + 1)];
        int k = 0;

        //循环打印
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) { //从左到右
                res[k++] = matrix[top][i];
            }
            top++;
            for (int i = top; i <= bottom; i++) { //从上到下
                res[k++] = matrix[i][right];
            }
            right--;
            for (int i = right; i >= left && top <= bottom; i--) {    //从右到左
                res[k++] = matrix[bottom][i];
            }
            bottom--;
            for (int i = bottom; i >= top && left <= right; i--) {    //从下到上
                res[k++] = matrix[i][left];
            }
            left++;
        }
        return res;
    }


    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int[] order = new int[total];
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order[i] = matrix[row][column];
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    public static String reorder(String in) {
        if (StringUtils.isEmpty(in)) {
            return in;
        }
        Map<Character, Integer> m = new HashMap<>();
        for (char c : in.toCharArray()) {
            if (m.containsKey(c)) {
                m.put(c, m.get(c) + 1);
            } else {
                m.put(c, 1);
            }
        }
        List<MyEntry> lst = new ArrayList<>();
        m.forEach((k, v) -> {
            lst.add(new MyEntry(k, v));
        });
        Collections.sort(lst);


//        List<Map.Entry<Character, Integer>> collect =
//                m.entrySet()
//                        .stream()
//                        .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                        .collect(Collectors.toList());
//        collect.forEach(e -> sortm.put(e.getKey(), e.getValue()));
//        System.out.println(sortm);
        StringBuilder sb = new StringBuilder();
        for (MyEntry myEntry : lst) {
            for (int i = 0; i < myEntry.b; i++) {
                sb.append(myEntry.a);
            }
        }
        return sb.toString();
    }

    static class MyEntry implements Comparable<MyEntry> {
        char a;
        int b;

        MyEntry(char a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(MyEntry o) {
            return b > o.b ? 1 : b == o.b ? 0 : -1;
        }

    }

    public static void main3(String[] args) {
        System.out.println(reorder("abbbccdef"));
    }

    public static void main2(String[] args) {
        JianZhiO j = new JianZhiO();
        j.test();
    }

    /**
     * 1
     * 2          3
     * 4    5     6     7
     * 8 9 10 11 12 13 14 15
     */
    int[] preorder;
    HashMap<Integer, Integer> inOrderMap = new HashMap<>();

    public void test() {
        int[] a = {1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15};
        int[] b = {8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15};

        // 前序遍历 preorder: 根 -- 左 -- 右   第一个肯定是根节点
        // 中序遍历 inorder: 左 -- 根 -- 右
        this.preorder = a;
        for (int i = 0; i < b.length; i++) {
            inOrderMap.put(b[i], i);
        }
        TreeNode treeNode = this.rebuild(0, 0, b.length - 1);
        System.out.println(treeNode);
    }

    // pre_root_index : 根节点 在 前序遍历中的下标    in_left_index: 该节点在中序遍历中的左边界   in_right_index: 该节点在中序遍历中的右边界
    public TreeNode rebuild(int pre_root_index, int in_left_index, int in_right_index) {
        if (in_left_index > in_right_index) return null;

        int currentRoot = preorder[pre_root_index];

        // 根节点在中序遍历中的位置：in_root_index
        int in_root_index = inOrderMap.get(currentRoot);

        // 创建一个根节点(每次递归进来都新创建一个当前根吗？)
        TreeNode node = new TreeNode(currentRoot);

        // 寻找node的左节点:
        // 在前序遍历中的位置就是  根节点的下标 + 1（右边一个单位）
        // 在中序遍历中的位置就是： 1. 左边界不变，2. 右边界就是根节点的左边一个单位 in_root_index - 1
        // 左子树的根，左子树的左边界，左子树的右边界
        node.left = rebuild(pre_root_index + 1, in_left_index, in_root_index - 1);

        // 寻找node的右节点:
        // 右子树根 在前序遍历中的位置就是  根节点的下标 + 左子树长度 + 1，右子树的根
        // 在中序遍历中的位置就是： 1. 左边界在根节点的右边一个单位  in_root_index + 1, 2. 右边界不变
        node.right = rebuild(pre_root_index + in_root_index - in_left_index + 1, in_root_index + 1, in_right_index);

        return node;
    }


    public static void main0(String[] args) {

//        System.out.println(lengthOfLongestSubstring("abcabefgh"));
        System.out.println(replaceSpace(" ab ca bef gh "));
    }

    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder,
                inorder,
                preorder_left + 1,
                preorder_left + size_left_subtree,
                inorder_left,
                inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder,
                inorder,
                preorder_left + size_left_subtree + 1,
                preorder_right,
                inorder_root + 1,
                inorder_right);
        return root;
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    class TreeNode {
        int i;
        TreeNode left;
        TreeNode right;

        TreeNode(int i) {
            this.i = i;
        }
    }

    public static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        String no = path;
        process1(str, index + 1, ans, no);
        String yes = path + String.valueOf(str[index]);
        process1(str, index + 1, ans, yes);
    }

    public static int lengthOfLongestSubstring(String inputString) {
        if (inputString.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        String str = "";
        for (int i = 0; i < inputString.length(); i++) {
            if (map.containsKey(inputString.charAt(i))) {
                left = Math.max(left, map.get(inputString.charAt(i)) + 1);
            }
            map.put(inputString.charAt(i), i);
            if (i - left + 1 > max) {
                str = inputString.substring(left, i + 1);
            }
            max = Math.max(max, i - left + 1);
        }
        System.out.println(str);
        return max;

    }

    public static String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        char[] newchars = new char[chars.length * 3];
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (' ' == chars[i]) {
                newchars[j++] = '%';
                newchars[j++] = '2';
                newchars[j++] = '0';
            } else {
                newchars[j++] = chars[i];
            }
        }

        return new String(newchars, 0, j);
    }

    public static void main1(String[] args) {
        int[] nums = {3, 1, 0, 4, 2};
        int repeatNumber = findRepeatNumber(nums);
        System.out.println(repeatNumber);
        System.out.println(nums);
    }

    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                tmp = nums[i];
                nums[i] = nums[nums[i]];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
