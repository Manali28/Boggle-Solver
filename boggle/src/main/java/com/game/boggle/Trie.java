package com.game.boggle;
import java.util.List;

/**
 * Represents each node of Trie data structure.
 * 
 * @author Manali
 *
 */
class TrieNode {
	TrieNode[] children = new TrieNode[26];
	String endWord = null;
}

/**
 * Helper class to build Trie data structure.
 * 
 * @author Manali
 *
 */
public class Trie {
	/**
	 * Builds Trie data structure based on list of words from dictionary.
	 * 
	 * @param words - List of dictionary words.
	 * @return - Returns root of built Trie data structure.
	 */
	public static TrieNode buildTrie(List<String> words) {
		if(words == null || words.size() <= 0) return null;
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode current = root;
			for (char character : word.toCharArray()) {
				int currChar = character - 'a';
				TrieNode node = current.children[currChar];
				if (node == null) {
					node = new TrieNode();
					current.children[currChar] = node;
				}
				current = node;
			}
			current.endWord = word;
		}
		return root;
	}
}

