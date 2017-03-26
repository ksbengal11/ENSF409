import java.util.Scanner;
import java.lang.String;
import java.lang.Character;

public class Multi_Array {

	private int[] SIN;

	// Reverse words in a string
	private static String Reverse_String(String[] input){
		String reverse_words = "";
		for (int i = input.length -1; i >= 0; --i){
			reverse_words += input[i] + " ";
		}
		return reverse_words;
	}

	// Store reversed characters in the sentence array
	public static void Reverse_Chars(String[][] sentence, String input){
		String reverse_string = new StringBuffer(input).reverse().toString();
		sentence[0] = new String [input.length()];
		for(int i = 0; i < reverse_string.length(); i++){
			sentence[0][i] = Character.toString(reverse_string.charAt(i));
		}
	}

	// Store reversed words in the sentence array
	public static void Reverse_Words(String[][] sentence, String input){
		sentence[1] = new String [input.length()];
		String temp [] = input.split(" ");
		String reverse_words = Reverse_String(temp);
		for(int i = 0; i < input.length(); i++){
			sentence[1][i] = Character.toString(reverse_words.charAt(i));
		}
	}

	// Convert every fifth element to uppercase and store in the sentence array
	public static void Upper_Case(String[][] sentence, String input){
		sentence[2] = new String [input.length()];
		for(int i = 0; i < input.length(); i++){
			if(i % 5 == 0)
					sentence[2][i] = (Character.toString(input.charAt(i))).toUpperCase();
			else
				sentence[2][i] = Character.toString(input.charAt(i));
		}
	}

	public static void main(String[] args) {
		// Initialize array
	  String sentences [][] = new String [3][];
		String input;
		Scanner scan = new Scanner(System.in);
		int sentence_counter = 0;

		// Read user input and modify strings
		while (sentence_counter != 3){
			System.out.println("Please enter sentence " + sentence_counter);
			input = scan.nextLine();
			switch (sentence_counter){
				case 0:
					Reverse_Chars(sentences, input);
					sentence_counter++;
					break;
				case 1:
					Reverse_Words(sentences, input);
					sentence_counter++;
					break;
				case 2:
					Upper_Case(sentences, input);
					sentence_counter++;
					break;
			}
		}
		// print output
		for (int i = 0; i < 3; i++){
			System.out.println("Sentence " + i);
			for (int j = 0; j < sentences[i].length; j++){
				System.out.print(sentences[i][j]);
			}
			System.out.println(" ");
		}
	}
}
