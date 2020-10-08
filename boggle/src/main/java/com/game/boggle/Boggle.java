package com.game.boggle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is the starting of the Boggle game which takes input dice letters from
 * user and delegates the call to various methods to build board and generate
 * list of valid words based on the Boggle board.
 * 
 * @author Manali
 *
 */
public class Boggle {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		List<String> dictionary = parseInputDictionary("words.txt");
		TrieNode root = null;

		if (dictionary != null && dictionary.size() > 0) {
			root = Trie.buildTrie(dictionary);
		}

		if (root == null) {
			throw new IllegalArgumentException(
					"Trie could not be build from given dictionary. It could be that the file is empty");
		} else {
			List<String> diceLetters = Stream.of(DiceLetters.values()).map(Enum::name).collect(Collectors.toList());

			char[][] board = buildBoggleBoard(diceLetters);

			List<String> result = WordSearch.iterateBoard(board, root);

			displayOutput(result);
		}
	}

	/**
	 * Takes the words from the Input file and stores it in a list.
	 * 
	 * @param ditionaryPath - Input file location which has words dictionary.
	 * @return - Return parsed dictionary having list of words.
	 * @throws IOException
	 */
	private static List<String> parseInputDictionary(String dictionaryPath) throws IOException, ClassNotFoundException {
		if (dictionaryPath == null || dictionaryPath.length() == 0) {
			System.out.println("Dictionary file path not passed");
			return null;
		}

		List<String> dictionary = null;
		try {

			ClassLoader classLoader = Class.forName("com.game.boggle.Boggle").getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(dictionaryPath);

			// the stream holding the file content
			if (inputStream == null) {
				throw new IllegalArgumentException("file not found! " + dictionaryPath);
			} else {
				try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
						BufferedReader reader = new BufferedReader(streamReader)) {

					String line;
					List<String> words = new ArrayList<>();
					while ((line = reader.readLine()) != null) {
						words.add(line);
					}

					dictionary = words.stream().map(word -> word.toLowerCase())
							.filter(word -> word.chars().allMatch(Character::isLetter))
							.filter(word -> word.length() > 2 && word.length() <= 17).collect(Collectors.toList());

				} catch (IOException e) {
					System.out.println("Unable to read input file" + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Exception while reading words from dictionary" + e.getMessage());
		}

		return dictionary;
	}

	/**
	 * Takes the letters from user for each dice and builds the Boggle board.
	 * 
	 * @param diceLetters - List of letters on each dice.
	 * @return - returns the Boggle board.
	 */
	private static char[][] buildBoggleBoard(List<String> diceLetters) {

		System.out.println("How would you like to input characters in Boggle Board ?");
		System.out.println("Press 1: Input valid 16 character string for Boggle Board");
		System.out.println("Press 2: Select from dice letter options and build board");
		Scanner scanner = new Scanner(System.in);
		int option = 0;

		option = Integer.parseInt(scanner.next());

		String input = "";
		if (option == 1) {
			input = processOptionOne();
		} else if (option == 2) {
			input = processOptionSecond(diceLetters);
		} else {
			System.out.println("Invalid Entry");
			System.exit(0);
		}

		if (input == null || input.length() <= 0) {
			throw new IllegalArgumentException("Invalid input given for boggle board");
		}

		System.out.println("Your 4X4 Boggle board looks as follows: ");

		char[][] board = new char[4][4];

		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = input.charAt(index);
				System.out.print(board[i][j] + " ");
				index++;
			}
			System.out.println();
		}
		return board;
	}

	/**
	 * This is to process request when user inputs 1
	 * 
	 * @return returns String of length 16
	 */
	private static String processOptionOne() {
		System.out.println("Enter 16 character string to form 4X4 Boggle matrix");
		Scanner scanner = new Scanner(System.in);
		String input = null;
		try {
			input = scanner.next().trim().toLowerCase();
			if (input.length() != 16) {
				throw new IllegalArgumentException("The size of the input is not 16");
			}
			for (char ch : input.toCharArray()) {
				if (!Character.isLetter(ch)) {
					throw new IllegalArgumentException("Invalid input given for boggle board");
				}
			}
		} finally {
			scanner.close();
		}

		return input;
	}

	/**
	 * This is to process request when user inputs 2
	 * 
	 * @param diceLetters - list of string which contains the letters of every dice of boggle board
	 * @return returns String of length 16
	 */
	private static String processOptionSecond(List<String> diceLetters) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		try {
			if (diceLetters == null || diceLetters.size() <= 0) {
				System.out.println("DiceLetters list missing");
				return null;
			}

			// Shuffle the list so that every time we run the program we can get new letter
			// dice on each position of Boggle board.
			Collections.shuffle(diceLetters);

			System.out.println("Enter 16 character one by one from below choices to form 4X4 Boggle matrix");
			StringBuilder boggleBoradStr = new StringBuilder();
			for (String diceLetter : diceLetters) {
				System.out.println("Please enter any one character from this string: " + diceLetter.toLowerCase());
				char character = scanner.next().toLowerCase().charAt(0);
				while (diceLetter.toLowerCase().indexOf(character) == -1) {
					System.out.println(
							"Please enter correct character from the given choice: " + diceLetter.toLowerCase());
					character = scanner.next().charAt(0);
				}
				boggleBoradStr.append(character);
			}
			input = boggleBoradStr.toString();
		} finally {
			scanner.close();
		}
		return input;
	}

	/**
	 * This is a helper method to iterate over and display valid words.
	 * 
	 * @param result - Holds the list of valid words on a given board.
	 * @throws IOException
	 */
	private static void displayOutput(List<String> result) throws IOException {
		try {
			System.out.println("Total valid words found: " + result.size());

			int countOfWords = 1;
			for (String validWord : result) {
				System.out.print(validWord + ", ");
				if (countOfWords == 10) {
					System.out.println();
					countOfWords = 0;
				}
				countOfWords++;
			}

		} catch (Exception e) {
			System.out.println("Exception while printing result" + e.getMessage());
		}

	}

}
