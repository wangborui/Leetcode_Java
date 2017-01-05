/*
Source: https://leetcode.com/problems/implement-trie-prefix-tree/
********************************************************************************
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
********************************************************************************
 */
package Leetcode_Java.trie_medium;

/**
 *
 * @author Borui Wang
 */
public class ImplementTrie {
    class TrieNode {
    boolean isWord;
    TrieNode [] subtree;
    private final int size = 26;
    // Initialize your data structure here.
    public TrieNode() {
        this.isWord = false;
        this.subtree = new TrieNode[size];
    }
     
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if(!search(word)) {
            TrieNode walker = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(walker.subtree[c - 'a'] == null) {
                    walker.subtree[c - 'a'] = new TrieNode();
                }
                walker = walker.subtree[c - 'a'];
            }
            walker.isWord = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return explore(word, true);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return explore(prefix, false);
    }
    
    private boolean explore(String s, boolean isSearch) {
        TrieNode walker = root;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            TrieNode next = walker.subtree[c - 'a'];
            if(next == null) {
                return false;
            }
            walker = next;
        }
        return isSearch? walker.isWord : true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
}
