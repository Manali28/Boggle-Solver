package com.game.boggle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class WordSearchTest {
	@Test
	public void iterateBoard_boggleBoard_returnsWordList() {
		// Arrange
		List<String> expectedResult = new ArrayList<>();
		expectedResult.add("can");
		expectedResult.add("cap");
		Collections.sort(expectedResult);
		
		TrieNode trie = new TrieNode();
		trie.children[2] = new TrieNode();
		trie.children[2].children[0] = new TrieNode();
		trie.children[2].children[0].children[13] = new TrieNode();
		trie.children[2].children[0].children[13].endWord = "can";
		trie.children[2].children[0].children[15] = new TrieNode();
		trie.children[2].children[0].children[15].endWord = "cap";

		char[][] board = { { 'c', 'a', 'n', 'p' }, { 'a', 'p', 'w', 'i' }, { 't', 'h', 'r', 'u' },
				{ 'j', 'r', 'g', 'k' } };
		
		//Act
		List<String> actualResult = WordSearch.iterateBoard(board, trie);
		Collections.sort(actualResult);
		
		//Assert
		assertEquals(expectedResult,actualResult);
		

	}
	
	@Test
	public void iterateBoard_nullBoggleBoard_returnsEmptyList() {
		// Arrange
		TrieNode trie = new TrieNode();

		//Act
		List<String> actualResult = WordSearch.iterateBoard(null, trie);
		
		//Assert
		assertNotNull(actualResult);
		assertEquals(0,actualResult.size());
	}
	
	@Test
	public void iterateBoard_nullTrieNode_returnsEmptyList() {
		// Arrange
		char[][] board = { { 'c', 'a', 'n', 'p' }, { 'a', 'p', 'w', 'i' }, { 't', 'h', 'r', 'u' },
				{ 'j', 'r', 'g', 'k' } };
		//Act
		List<String> actualResult = WordSearch.iterateBoard(board, null);
		
		//Assert
		assertNotNull(actualResult);
		assertEquals(0,actualResult.size());
	}
	
	@Test
	public void iterateBoard_emptyBoggleBoard_returnsEmptyList() {
		// Arrange
		TrieNode trie = new TrieNode();
		char[][] board = new char[0][0];
		//Act
		List<String> actualResult = WordSearch.iterateBoard(board, trie);
		
		//Assert
		assertNotNull(actualResult);
		assertEquals(0,actualResult.size());
	}

}
