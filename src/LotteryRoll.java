import java.util.Scanner;

/**
 * 
 */

/**
 * @author Yusuf Ali
 * Date: April 1, 2023
 * Description: Simple lottery program, user buys a lottery ticket, program generates the winning lottery ticket numbers,
 * 				program then checks if the user has matching numbers with the winning ticket, user wins the prize pool
 *
 */
public class LotteryRoll {

	/**
	 * 
	 */
	public LotteryRoll() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This function cuztimizies the usersAnswers condition
	 */
	
	public static boolean userAnswerCondition(String userInput, String condition1, String condition2) {
		
		if (userInput.equalsIgnoreCase(condition1) || userInput.equalsIgnoreCase(condition2)) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * This function randomly generates a 2 digit number pair from 10 - 99
	 */
	
	public static int generateNumberPair() {
		
		int lotteryNumberPair = 0; // Declare and initalize lottery number
		int num_min = 10, num_max = 99; // min and max of lottery number pair
		
		lotteryNumberPair = (int)Math.floor(Math.random() * (num_max - num_min) + num_min); // Generate number from 10 - 99
		// System.out.println(lotteryNumberPair);
		
		return lotteryNumberPair;
	}
	
	/**
	 * This function uses the generateNumberPair function to generate 6 (2 digit) pair numbers
	 */
	
	public static String generateNumbers(int pairsLimit) {
		
		// 11 15 45 54 55 58 - 6 pairs of 2 digit numbers
		String lotteryNumber = "";
		int numberPair = 0, length;
		
		for (int i = 0; i < pairsLimit; i++) {
			numberPair = generateNumberPair();
			
			String lotteryNumberHolder = Integer.toString(numberPair);
			lotteryNumber = lotteryNumber + lotteryNumberHolder + "-";		
		}
		
		length = lotteryNumber.length(); // Get length of lotteryNumber
		
		lotteryNumber = lotteryNumber.substring(0, length-1); // Removes the last dash at the end
		// System.out.println(lotteryNumber); // Display number - for testing
		
		return lotteryNumber;
	}
	
	/**
	 * This function compares the users lottery number with the winning lottery number
	 */
	
	public static String determineIfWinner(String userNum, String winningNum, String userName) {
		
		String output;
		
		if (userNum.equalsIgnoreCase(winningNum)) {
			output = "You won!";
			return output;
		}
		else {
			String loseOutput = String.format("Sorry %s, you didn't win the jackpot.\nWinning number was %s\nYour number was    %s", userName, winningNum, userNum); // formatted string	
			return loseOutput;
		}
		
	}
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// Declare variables for user name, user answer, usernumber, winning number, and initalize int value of numOfPairs
		String userName = "", userAnswer = "";
		String userNumber = "", winningNumber;
		int numOfPairs = 0;
		
		// Scanner text input
		Scanner textInput = new Scanner(System.in); 
			
		// Display welcome message
		System.out.println("Welcome to the lottery program!");
		
		// Ask user if they want to participate in the lottery
		System.out.println("Would you like to partcipate in the lottery? (Y or y)");
		userAnswer = textInput.nextLine(); // User answer input
			
		// Check if the user enters 'Y' or 'y'
		if (userAnswerCondition(userAnswer, "Y", "y")) {
			
			System.out.println("Okay, enter the number of pairs in the lottery winning number: ");
			numOfPairs = textInput.nextInt(); // User answer input
			winningNumber = generateNumbers(numOfPairs); // Generate winning lottery numbers
			
			textInput.nextLine();
			
			System.out.println("Enter your name?");
			userName = textInput.nextLine(); // User answer input
			
			System.out.println("Would you like to pick your own number or randomly generate your number? (PK or RND)");	
			userAnswer = textInput.nextLine(); // user picks their choic
			
			if (userAnswerCondition(userAnswer, "PK", "pk")) {
				
				String userCreatedNumber = "";
				
				for (int i = 0; i < numOfPairs; i++) {
					System.out.println("Enter a number from 10 - 99");
					userNumber = textInput.nextLine();
					userCreatedNumber = userCreatedNumber + userNumber + "-";
				}
				userCreatedNumber = userCreatedNumber.substring(0, 17); // Removes the last dash at the end
				System.out.println(userCreatedNumber); // Display the users created lottery number
				
				System.out.println("Would you like to find out if you won? (Y or y)");
				userAnswer = textInput.nextLine(); // User answer input
				
				// Check if the user enters 'Y' or 'y'
				if (userAnswerCondition(userAnswer, "Y", "y")) {
					
					System.out.println(determineIfWinner(userCreatedNumber, winningNumber, userName)); // Call the determineIfWinner function, if the user won the jackpot
				}
			}
			else if (userAnswerCondition(userAnswer, "RND", "rnd")) {
				System.out.println("\nHere is your randomly generated ticket number.");
				
				userNumber = generateNumbers(numOfPairs); // generate users lottery numbers
				
				System.out.println(userNumber); // Display the users generated lottery number
						
				System.out.println("Would you like to find out if you won? (Y or y)"); // Ask user if they want to see if they won the jackpot
				userAnswer = textInput.nextLine(); // Users input
				
				// Check if the user enters 'Y' or 'y'
				if (userAnswerCondition(userAnswer, "Y", "y")) {
					
					System.out.println(determineIfWinner(userNumber, winningNumber, userName)); // Call the determineIfWinner function, if the user won the jackpot
				}
				
			}	
		}
	}

}
