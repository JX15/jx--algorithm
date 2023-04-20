package com.jiangxin.demo.algorithm.basic.class08;


/**
 * @author: xin.jiang.cn
 * @date: 2023年04月20日 17:03:05
 * @description: 前缀树
 * 1.主要是针对字符串(纯字母)
 * 2.insert时，从头节点开始，将字符串的每个字符加入到前缀树中，经过的节点pass++，结束的节点pass++，end++
 * 3.delete时，先判断字符串是否在前缀树中，如果在，依次遍历pass--，如果存在pass=0的节点，则直接断开该节点
 */
public class Trie {

    public static class Node{
        /**
         * 通过该节点的数量
         */
        private int pass;
        /**
         * 以该节点结尾的数量
         */
        private int end;
        /**
         * 存储该节点的后继节点
         */
        private Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[52];
        }
    }

    /**
     * 头节点
     */
    private Node root;

    public Trie() {
       root = new Node();
    }

    public void insert(String word){
        if (word == null){
            return;
        }
        char[] str = word.toCharArray();
        Node node = root;
        node.pass++;
        int path = 0;
        for (int i = 0; i < str.length; i++) {
            path = getPath(str[i]);
            if (node.nexts[path] == null){
                node.nexts[path] = new Node();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.end++;
    }

    public boolean search(String word){
        if (word == null){
            return false;
        }
        char[] str = word.toCharArray();
        Node node = root;
        int path = 0;
        for (int i = 0; i < str.length; i++) {
            path = getPath(str[i]);
            if (node.nexts[path] == null){
                return false;
            }
            node = node.nexts[path];
        }
        return true;
    }

    public int countPrefixWords(String prefix){
        if (prefix == null){
            return  0;
        }
        char[] str = prefix.toCharArray();
        Node node = root;
        int path = 0;
        for (int i = 0; i < str.length; i++) {
            path = getPath(str[i]);
            if (node.nexts[path] == null){
                return 0;
            }
            node = node.nexts[path];
        }
        return node.pass;
    }

    public void delete(String word){
        if (word == null){
            return;
        }
        if (search(word)){
            char[] str = word.toCharArray();
            Node node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = getPath(str[i]);
                /*
                等价于
                    node.nexts[path].pass--
                    if(node.nexts[path].pass == 0)...
                 */
                if (--node.nexts[path].pass == 0){
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
            }
            node.end--;
        }
    }

    private int getPath(char cha){
        if (Character.isLowerCase(cha)){
            return cha - 'a';
        }else {
            return cha - 'A' + 26;
        }
    }

    private static String[] getRandomStrArray(int maxStrLength,int arrayLength){
        String[] strArray = new String[arrayLength];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayLength; i++) {
            int strLength = 0;
            while (strLength == 0){
                strLength = (int) (Math.random() * maxStrLength);
            }
            for (int j = 0; j < maxStrLength; j++) {

                if (Math.random() > 0.5f){
                    builder.append((char) (Math.random() * 26 + 'A'));
                }else {
                    builder.append((char) (Math.random() * 26 + 'a'));
                }
            }
            strArray[i] = builder.toString();
            builder.delete(0, builder.length());
        }
        return strArray;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] randomStrArray = getRandomStrArray(10, 10);
        for (String str : randomStrArray) {
            trie.insert(str);
        }
        for (String str : randomStrArray) {
            boolean res = trie.search(str);
            System.out.println("res = " + res);
        }

    }
}
