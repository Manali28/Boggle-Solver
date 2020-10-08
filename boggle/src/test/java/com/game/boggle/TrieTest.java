package com.game.boggle;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TrieTest {
	@Test
	public void buildTrie_validWords_constructsTrie() {
		//Arrange
		List<String> words = new ArrayList<>();
		words.add("can");
		words.add("cap");
		
//		TrieNode expectedTrie = new TrieNode();
//		expectedTrie.children[2] = new TrieNode();
//		expectedTrie.children[2].children[0] = new TrieNode();
//		expectedTrie.children[2].children[0].children[13] = new TrieNode();
//		expectedTrie.children[2].children[0].children[13].endWord = "can";
//		expectedTrie.children[2].children[0].children[15] = new TrieNode();
//		expectedTrie.children[2].children[0].children[15].endWord = "ca";
		
		//Act
		TrieNode actualTrie = Trie.buildTrie(words);
		
		//Assert
		assertNotNull(actualTrie.children[2]);
		assertNotNull(actualTrie.children[2].children[0]);
		assertNotNull(actualTrie.children[2].children[0].children[13]);
		assertNotNull(actualTrie.children[2].children[0].children[15]);
		assertNotNull(actualTrie.children[2].children[0].children[13].endWord);
		assertNotNull(actualTrie.children[2].children[0].children[15].endWord);
	}
	
	@Test
	public void buildTrie_nullInput_returnsNull() {
		//Arrange
		
		//Act
		TrieNode actualTrie = Trie.buildTrie(null);
		
		//Assert
		assertNull(actualTrie);
		
	}
	
	@Test
	public void buildTrie_emptyList_returnsNull() {
		//Arrange
		
		//Act
		TrieNode actualTrie = Trie.buildTrie(new ArrayList<>());
		
		//Assert
		assertNull(actualTrie);
		
	}
}
