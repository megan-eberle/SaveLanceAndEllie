package hangman;

import java.util.Random;
import java.util.Scanner;
import java.lang.Character;



public class Hangman {
	
	
	public static void main(String[] args) {
		boolean wordGuessed = false;
		boolean[] allLettersGuessed = new boolean[26];
		int numTurns = 0;
		
		System.out.println("lancelot and elaine are being kidnapped by the local zoo!\n"
				+ "act quick to guess the padlock's combo while the zookeeper is chasing an escaped gorilla!\n\n"
				+ "guess one letter at a time OR guess the whole word!\n"
				+ "(Hint: the zookeeper is a huge cyclone fan)\n");
		String word = getWord();
		//System.out.println(word);
		//System.out.println("\n\n\n\n\n");
		//word = "code camp";
		Scanner scan = new Scanner(System.in);
		boolean[] lettersGuessed = new boolean[word.length()];
		int numLettersToWin = word.length();
		
		for(int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ') {
				lettersGuessed[i] = true;
				numLettersToWin--;
			}
			else {
				lettersGuessed[i] = false;
			}
		}
		
		for(int i = 0; i < allLettersGuessed.length; i++) {
			allLettersGuessed[i] = false;
		}
		
		
		while(!wordGuessed) {
			System.out.println("\n\n\n\nguess a letter!");

			for(int i = 0; i < word.length(); i++) {
				
				if(lettersGuessed[i] == true) {
					System.out.print(word.charAt(i) + " ");
				}
				else {
					System.out.print("_ ");
				}
			}
			
			System.out.println("\n");
			
			String wordGuess = scan.nextLine();
			
			wordGuess = wordGuess.toLowerCase();
			
			//if the user guesses the whole word, they win.
			if(wordGuess.length() > 1) {
			if (wordGuess.equals(word)) {
				System.out.println("\n\nthe combination was: " + word);
				System.out.println("you saved Lancelot and Elaine!");
				break;
			}
			else {
				System.out.println(wordGuess + " was not the password :( try again!\n");
				numTurns ++;
				continue;
			}
			
			}
			
			char guess = wordGuess.charAt(0);
			
			if(guess - 97 < 0 || guess - 97 > 25) {
				System.out.println("there are only letters in this combo!");
				System.out.println(8 - numTurns + " trys left!!");
				continue;
			}
			
			//if it's already been guessed, continue
			if (inAllLettersGuesssed(guess, allLettersGuessed)) {
				System.out.println("you've already guessed '" + guess + "'!\n");
				System.out.println(8 - numTurns + " trys left!!");
				continue;
			}
			
			//otherwise, add to guessed list and check if it's in the word
			else {
				//add to list
				allLettersGuessed[guess - 97] = true;
				
				//check if in word, alter lettersGuessed array
				boolean[] guessedRight = isInWord(guess, word);
				
				for(int i = 0; i < word.length(); i++) {
					if(guessedRight[i] == true) {
						lettersGuessed[i] = true;
						numLettersToWin--;
					}
				}
				
				System.out.print("\n" + guess + " is ");
				
				if(guessedRight[guessedRight.length - 1] == false) {
					System.out.print("NOT ");
					numTurns++;
				}
				
				System.out.println("in the combination!");
				
				
				if(numLettersToWin == 0) {
					System.out.println("\n\nthe combination was: " + word);
					System.out.println("you saved Lancelot and Elaine!");
					break;
				}
				
				else {
					if(8 - numTurns == 0) {
						System.out.println("you didn't guess it in time :( lancelot and elaine are gone forever.");
						System.out.println("the combination was:   " + word);
						break;
					}
					System.out.println(8 - numTurns + " incorrect guesses until the zookeeper is back!!");
				}
				
			}
			
			
		
		}
		
		
		scan.close();
	}



	private static String getWord() {
		String[] wordBank = {"lancelot",
							"elaine",
							"cyclone",
							"jack trice",
							"campanielle",
							"campameal",
							"cy",
							"hilton coliseum",
							"cardinal",
							"gold"
							};
	
		
		Random rand = new Random();
		int wordIndex = rand.nextInt(wordBank.length);
		
		return wordBank[wordIndex];
	}
	
	
	private static boolean inAllLettersGuesssed(char c, boolean[] allGuessed) {
		for (int i = 0; i < 26; i++){
			if(allGuessed[c - 97]) {
				return true;
			}
		}
		return false;	
	}
	
	private static boolean[] isInWord(char c, String word) {
		//last index is whether or not the letter is in the array.
		//rest are true if letter is at that index
		boolean[] inWord = new boolean[word.length() + 1];
		
		for(int i = 0; i < word.length() + 1; i++) {
			inWord[i] = false;
		}
		
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == c) {
				inWord[i] = true;
				inWord[word.length()] = true;
			}
		}
		
		return inWord;
	}
	
	
	
	
}
