import java.util.Scanner;


public class SinValidator {

private int[] SIN;

private int sumDigit(int x)
{
	int result =0;

	while(x > 0){
		result += x % 10;
		x = x /10;
	}

	return result;
}

	public SinValidator(String sin) {

		SIN = new int[9];
		int i =0;
		int counter =0;
		while(i < sin.length()){


			if(Character.isDigit(sin.charAt(i))){
				if(counter < 9)
					SIN[counter] =(int) sin.charAt(i) - 48;
				counter++;
			}
			else{
				System.err.println("Error: Invalid input by the user");
				return;
			}
			i++;
		}

		if(counter != 9){
			System.err.println("Error: SIN must be 9 digits...");
			return;
		}
	}

	public int product_sum(int num){
		int number_one = 0, number_two = 0;
		int product = num * 2;
		if(product > 9){
			number_one = product / 10;
			number_two = product % 10;
			int sum = number_one + number_two;
			return (sum);
		}
		else {
			return product;
		}
	}

	public boolean validateSin(String sin)
	{

		// THIS METHOD DOESN'T WORK. STUDENTS ARE EXPECTED TO COMPLTETE THIS METHOD
		// CORRECTED - KARAN 
		int [] numbers = new int [sin.length()]; int i = 0;

		for (i = 0; i < numbers.length; i++){
			numbers[i] = Character.getNumericValue(sin.charAt(i));
		}

		int sum_of_digits = 0; i = 0;
		while (i <= 6){
			sum_of_digits += numbers[i];
			i += 2;
		}

		int sum_of_products = 0; i = 1;
		while (i <= 7){
			sum_of_products += product_sum(numbers[i]);
			i += 2;
		}

		int calculated_digit_nine = 10 - ((sum_of_digits + sum_of_products) % 10);
		int expected_digit_nine = numbers[8];

		if(calculated_digit_nine == expected_digit_nine)
			return true;
		return false;

	}

	public static void main(String[] args) {
		// Read user input

	  String sin;
		Scanner scan = new Scanner(System.in);
		while (true)
		{
			System.out.println("Please enter your 9 digit social insuracne number"
					+ " or enter quit to terminate the program: ");
			sin = scan.nextLine();
			if(sin.toUpperCase().equals("QUIT"))
				break;
			SinValidator sv = new SinValidator(sin);
			if(sv.validateSin(sin))
				System.out.println("Yes this is a valid SIN\n");
			else
				System.out.println("No this is NOT a valid SIN\n");

		}
	}

}
