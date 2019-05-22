// Source : https://leetcode.com/problems/add-and-search-word-data-structure-design/
// Date   : 01/30/2017

/********************************************************************************** 
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addWord(word)
 * bool search(word)
 * 
 * search(word) can search a literal word or a regular expression string containing only letters `a-z` or `.`
 * A `.` means it can represent any one letter.
 * 
 * For example:
 * 
 *   addWord("bad")
 *   addWord("dad")
 *   addWord("mad")
 *   search("pad") -> false
 *   search("bad") -> true
 *   search(".ad") -> true
 *   search("b..") -> true
 * 
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * 
 * click to show hint.
 * 
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
 * 
 *               
 **********************************************************************************/
package Leetcode_Java.trie_medium;

/**
 *
 * @author Borui Wang
 */
public class WordDictionary {
    private class TrieNode {
        private boolean isWord;
        private TrieNode[] subTree;
        public TrieNode() {
            this.isWord = false;
            this.subTree = new TrieNode[26];
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode walker = root;
        for(char c : word.toCharArray()) {
            if(walker.subTree[c - 'a'] == null) {
                walker.subTree[c - 'a'] = new TrieNode();
            }
            walker = walker.subTree[c - 'a'];
        }
        walker.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word.toCharArray(), root, 0);
    }
    private boolean search(char[] word, TrieNode root, int k) {
        if(k == word.length) {
            return root.isWord;
        }
        //if the current character is "." search all subtree of the trie node 
        if(word[k] == '.') {
            for(int i = 0; i < 26; i++) {
                if(root.subTree[i] != null) {
                    if(search(word, root.subTree[i], k + 1)) {
                        return true;
                    }
                }
            }
        } else {
            if(root.subTree[word[k] - 'a'] == null) {
                return false;
            } else {
                root =  root.subTree[word[k] - 'a'];
                return search(word, root, k + 1);
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
