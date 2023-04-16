import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Yusuf Ali
 *
 */
public class LotteryRollGUI extends JFrame implements ActionListener {

	// 
	JLabel programTitleLabel, nameLabel, numOfPairLabel, userLotteryNumLabel, userNumberLabel, winningNumberLabel;
	JTextField nameInput, numOfPairInput, lotteryNumberInput;
	JButton generateLotteryNumberBtn, checkIfWinnerBtn;
	
	int numOfPairs = 0;
	String userLotteryNumber = "";
	String winningLotteryNumber = "";
	String userName = "";
	
	Graphics g;
	
	/**
	 * 
	 */
	public LotteryRollGUI() {
		// Create JFrame
		super("Lottery Roll"); // Sets the frame title name
		
		// Create JLabels
		programTitleLabel = new JLabel("Lottery Program");
		nameLabel = new JLabel("Name");
		numOfPairLabel = new JLabel("# of lottery number pair (1-10)");
		userLotteryNumLabel = new JLabel("Your Number");
		userNumberLabel = new JLabel("");
		winningNumberLabel = new JLabel("Winning Number");
		
		
		// Create JTextFields
		nameInput = new JTextField("");
		numOfPairInput = new JTextField("");
		lotteryNumberInput = new JTextField("");
		
		// Create JButtons
		generateLotteryNumberBtn = new JButton("Generate Lotto Number");
		checkIfWinnerBtn = new JButton("Are you a winner?");
		
		// Place all items in the frame using X and Y cords
		programTitleLabel.setBounds(200, 0, 150, 50);
		nameLabel.setBounds(35, 35, 50, 50);
		numOfPairLabel.setBounds(35, 75, 150, 50);
		userLotteryNumLabel.setBounds(300, 35, 100, 50);
		userNumberLabel.setBounds(300, 50, 200, 50);
		winningNumberLabel.setBounds(250, 125, 200, 100);
		
		nameInput.setBounds(100, 50, 100, 25);
		numOfPairInput.setBounds(175, 90, 100, 25);
		
		generateLotteryNumberBtn.setBounds(35, 130, 175, 35);
		checkIfWinnerBtn.setBounds(35, 175, 175, 35);
			
		// Add the JLabels, JTextFields, and JButtons to the frame
		add (programTitleLabel);
		add (nameLabel);
		add (numOfPairLabel);
		add (userLotteryNumLabel);
		add (userNumberLabel);
		add (winningNumberLabel);
		
		add (nameInput);
		add (numOfPairInput);
		add (lotteryNumberInput);
		
		add (generateLotteryNumberBtn);
		add (checkIfWinnerBtn);
		
		// Add the buttons as listener of events
		checkIfWinnerBtn.addActionListener(this);
		generateLotteryNumberBtn.addActionListener(this);
			
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		setVisible(true);
	}
	
	/**
	 * This function creates the lottery ball
	 */
	
	public void lotteryBall(Graphics g, int positionX, int positionY, String text) {
		
		// Draw the first circle
		g.fillOval(positionX, positionY, 60, 60);
		g.setColor(Color.RED);
		
		// Draw the second circle inside of the first one
		g.fillOval(positionX+5, positionY+5, 50, 50);
		g.setColor(Color.BLACK);
		
		// Draw the number string in the circle
		g.setFont(new Font("Arial", Font.BOLD, 17));
		g.setColor(Color.WHITE);
		g.drawString(text, positionX+20, positionY+35);
		
		g.setColor(Color.BLACK);
		
	}
	
	/**
	 * This function creates the lottery ball row (5 in row)
	 */
	public void createRowOfBalls(Graphics g, int startingXPosition, int startingYPosition, int numOfBalls) {
		
		// max number of balls in row is 5
		int distanceBetweenBalls = 75;
		
		
		if (numOfBalls < 6) {
			
			for (int i = 0; i < numOfBalls; i++) {
				int number = LotteryRoll.generateNumberPair();
				String textNumber = "";
				textNumber = Integer.toString(number);
				lotteryBall(g, startingXPosition, startingYPosition, textNumber); // 75 pixels between each ball
				startingXPosition+=distanceBetweenBalls;
			}		
		}	
	}
	
	/**
	 * This function checks the number of balls, creates the nessescary rows
	 */
	
	public void checkNumberOfBalls(Graphics g, int totalBalls) {
		
		int maxBallsInRow = 5; // max number of balls in a row
		int numOfRows = 0; // number of rows
				
		// Check if the total amount of balls is greater than 5
		if (totalBalls > 5) {
			
			createRowOfBalls(g, 50, 50, maxBallsInRow);
			numOfRows++;
			totalBalls = totalBalls - maxBallsInRow;
			
			// check if the number of rows is greater than 0
			if (numOfRows > 0) {
				
				if (totalBalls < 5) {
					createRowOfBalls(g, 90, 125, totalBalls); // offset the second row balls
				}
				else {
					createRowOfBalls(g, 50, 125, totalBalls); // center the second row balls
				}
			}
			
		}
		else {
			createRowOfBalls(g, 50, 50, totalBalls);
		}
	}
	
	/**
	 * The paint method
	 */
	
	public void paint(Graphics g) {
		
		checkNumberOfBalls(g, 6);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LotteryRollGUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Check what button you pressed
		if (e.getSource() == generateLotteryNumberBtn) {
			
			// throws an error if the user does not enter a valid number
			try {
				// Receive information from numOfPairInput and nameInput
				numOfPairs = Integer.parseInt(numOfPairInput.getText());
				
				userLotteryNumber = LotteryRoll.generateNumbers(numOfPairs); // Call the generateNumber function to generate the users lottery number
				winningLotteryNumber = LotteryRoll.generateNumbers(numOfPairs); // Call the generateNumber function to generate the winning lottery number
				
				userName = nameInput.getText();
				userNumberLabel.setText(userLotteryNumber); // Show the users lottery number
				
			}
			catch (Exception e2) {
				System.out.println("ERROR! INVALID NUMBER ENTERED");
			}
		
		}
		else if (e.getSource() == checkIfWinnerBtn) {
			winningNumberLabel.setText("<html>" + LotteryRoll.determineIfWinner(userLotteryNumber, winningLotteryNumber, userName + "<html>")); // <html> is for word wrapping
		}
	}
}
