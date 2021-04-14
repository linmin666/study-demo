package com.linfafa.datastructure.tree;

/**
 * 前缀树是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * 字典树是一颗有根树，其每个节点包含以下字段：
 * （1）指向子节点的指针数组children，对本题而言，数组长度为26，即小写英文字母的数量，children[0]对应小写字母a，[1]children[1] 对应小写字母b,…,children[25] 对应小写字母z。
 * （2）布尔字段isEnd，表示该节点是否为字符串结尾。
 * <p>
 * 插入字符串
 * 我们从字典树的根开始，插入字符串。对于当前字符串对应的子节点，有两种情况：
 * （1）子节点存在。沿着指针移动到子节点，继续插入下一个字符。
 * （2）子节点不存在。创建一个新的子节点，记录在children数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
 * 重复以上步骤，直到处理完字符串的最后一个字符，然后将当前节点标记为字符串结尾。
 * <p>
 * 查找前缀
 * 我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
 * （1）子节点存在。沿着指针移动到子节点，继续搜索下一个字符串。
 * （2）子节点不存在。说明字典树中不包含该前缀，返回空指针。
 * 重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。
 * <p>
 * 若搜索到了前缀的末尾，就说明字典树中存在该前缀。此外，若前缀末尾对应节点的isEnd为真，则说明字典树中存在该字符串。
 */
public class Trie {
    private Trie[] children;
    private boolean isEnd;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie node = this;//指针
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {//子节点不存在
                node.children[index] = new Trie();
            }
            node = node.children[index];//指针后移
        }
        node.isEnd = true;//末尾字符，isEnd置为true
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null)
                return null;
            node = node.children[index];
        }
        return node;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("insert apple!");
        System.out.println("search apple: " + trie.search("apple"));
        System.out.println("search app: " + trie.search("app"));
        System.out.println("start with app: " + trie.startsWith("app"));
        System.out.println("insert app!");
        trie.insert("app");
        System.out.println("search app: " + trie.search("app"));
    }
}
