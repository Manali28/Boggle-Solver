package com.game.boggle;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class that has methods to iterate board and find valid words.
 * 
 * @author Manali
 *
 */
public class WordSearch {
	
	private final static char VISITED = '#';
	
	/**
	 * This method returns the valid words of boggle board
	 * 
	 * @param board - The Boggle board that needs to be iterated to find valid words.
	 * @param root - Root node of the dictionary created through Trie data structure.
	 * @return
	 */
	public static List<String> iterateBoard(char[][] board, TrieNode root) {
		
		if(board == null || board.length == 0 || board[0].length == 0 || root == null) {
			return new ArrayList<>();
		}
		
		List<String> result = new ArrayList<>();
		
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				int currChar = board[row][column] - 'a';
				if (root.children[currChar] != null) {
					findWords(board, row, column, result, root);
				}
			}
		}
		return result;
	}
	
	/**
	 * This method iterates the board using DFS graph traversal to find valid list of word.
	 *  
	 * @param board - The Boggle board in which we want to find words.
	 * @param row - Represents row of the board.
	 * @param column - Represents column of the board.
	 * @param validWords - Result list that holds all valid word found on the board.
	 * @param root - Root node of the dictionary created through Trie data structure.
	 */
	private static void findWords(char[][] board, int row, int column, List<String> validWords, TrieNode root) {
		// Return when board is not iterable.
		if (row < 0 || column < 0 || row >= board.length || column >= board[0].length)
			return;

		char currChar = board[row][column];

		// Return when letter is already visited or if that letter does not have any children in Trie data structure.
		if (currChar == VISITED || root.children[currChar - 'a'] == null) {
			return;
		}
		root = root.children[currChar - 'a'];

		// if endWord in Trie is not null, we have found a valid word, add it in our return list of valid words.
		if (root.endWord != null) {
			validWords.add(root.endWord);
			root.endWord = null; // To make sure we don't have duplicate words in final result and also to make sure it can go further if longer word exists.
		}

		board[row][column] = VISITED;

		// Move vertically up and down
		findWords(board, row + 1, column, validWords, root);
		findWords(board, row - 1, column, validWords, root);

		// Move horizontally right and left
		findWords(board, row, column + 1, validWords, root);
		findWords(board, row, column - 1, validWords, root);

		// Move diagonally down
		findWords(board, row + 1, column + 1, validWords, root);
		findWords(board, row + 1, column - 1, validWords, root);

		// Move diagonally up
		findWords(board, row - 1, column + 1, validWords, root);
		findWords(board, row - 1, column - 1, validWords, root);

		board[row][column] = currChar;
	}

	
}
