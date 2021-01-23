package com.rd.graph.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


public class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode(null, new HashMap<>(), Boolean.FALSE);
    }

    public void add(String word) {
        Map<Character, TrieNode> children = root.children;
        for (int i = 0; i < word.length(); i++) {
            Character character = word.charAt(i);

            if (!children.containsKey(character)) {
                Boolean isLeaf = (i == word.length() - 1);
                children.put(character, new TrieNode(character, new HashMap<>(), isLeaf));
            }
            children = children.get(character).getChildren();
        }

    }

    public boolean search(String word) {
        TrieNode node = root;
        Character character;
        for (int i = 0; i < word.length(); i++) {
            character = word.charAt(i);
            if (node.getChildren().containsKey(character)) {
                node = node.getChildren().get(character);
            } else {
                return false;
            }
        }
        return node.getIsWord();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (int i = 0; i < prefix.length(); i++) {
            Character character = prefix.charAt(i);

            if (node.getChildren().containsKey(character)) {
                node = node.getChildren().get(character);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("aac");
        System.out.println(trie.search("abcd"));
        System.out.println(trie.search("aba"));
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("aac"));

        System.out.println();
        System.out.println(trie.startsWith("b"));
        System.out.println(trie.startsWith("ac"));

        System.out.println(trie.startsWith("a"));
        System.out.println(trie.startsWith("ab"));
        System.out.println(trie.startsWith("abc"));
        System.out.println(trie.startsWith("aac"));
    }
}

@Data
@AllArgsConstructor
class TrieNode {
    Character character;
    Map<Character, TrieNode> children;
    Boolean isWord;
}
