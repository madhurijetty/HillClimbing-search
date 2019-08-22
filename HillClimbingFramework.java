
/* Team members
 * Aarti Nimhan - 801098198
 * Uma Sai Madhuri Jetty - 801101049
 * Sahithi Priya Gutta - 801098589
 * 
 * This is the main driver class for all implementations of hill climbing.
 */
import java.util.Random;
import java.util.Scanner;

public class HillClimbingFramework {

	public static void main(String[] args) {
		float successPercent = 0, avgSuccess = 0, failurePercent = 0, avgFailure = 0;
		float totalSuccessfulMoves = 0.0f, totalSuccessfulIterations = 0.0f, totalFailMoves = 0.0f,
				totalFailedIterations = 0.0f;
		float totalNumberOfRestarts = 0.0f;
		int numberOfIterationsForWhichRestartWasUsed = 0;
		float numberOfRestarts = 0.0f;
		boolean wishToContinue = true;
		int options, noOfQueens;
		Random rand = new Random();
		int iterations = rand.nextInt(400) + 100; // as expected iterations are generated random
		boolean printOutput = false;
		System.out.println("Enter the value for Number of Queens: ");
		Scanner scanner = new Scanner(System.in);
		noOfQueens = scanner.nextInt();
		// validating if the entered number of Queens are greater than 3
		if (noOfQueens < 4) {
			System.out.println("Number of Queens should be greater than 3.");
			System.exit(0);
		}
		System.out.println("Do you wish to print output states alongwith the statistics? (Y/y - Yes And N/n - No)");
		if (scanner.next().equalsIgnoreCase("Y"))
			printOutput = true;

		while (wishToContinue) {
			System.out.println("\n Enter 1 for n-queens problem using Steepest Ascent"
					+ "\n Enter 2 for n-queens problem using Sideway moves"
					+ "\n Enter 3 for n-queens problem using Random restart without sideways moves"
					+ "\n Enter 4 for n-queens problem using Random restart with sideways moves ");
			options = scanner.nextInt();

			switch (options) {
			case 1:// Iterations of Hill Climbing Steepest Ascent.
				for (int i = 0; i < iterations; i++) {
					SteepestAscent steepestAscentObj = new SteepestAscent();
					int[] tempAnswer = steepestAscentObj.process(noOfQueens, printOutput);
					totalSuccessfulMoves = totalSuccessfulMoves + tempAnswer[0];
					totalSuccessfulIterations = totalSuccessfulIterations + tempAnswer[1];
					totalFailMoves = totalFailMoves + tempAnswer[2];
					totalFailedIterations = totalFailedIterations + tempAnswer[3];
				}
				System.out.println("\n----------------------------------------------------------------------------");
				System.out.println("Steepest-Ascent Hill Climbing Algorithm");
				System.out.println("# Of Queens: " + noOfQueens);
				System.out.println("Number of Iterations: " + iterations);
				System.out.println("Success/Fail Analysis");
				// Calculating success and failure percent and average moves.
				if (totalSuccessfulIterations != 0) {
					successPercent = (totalSuccessfulIterations / iterations) * 100;
					avgSuccess = totalSuccessfulMoves / totalSuccessfulIterations;
				}
				if (totalFailedIterations != 0) {
					failurePercent = (totalFailedIterations / iterations) * 100;
					avgFailure = totalFailMoves / totalFailedIterations;
				}
				System.out.println("Success Rate: " + String.format("%.2f", successPercent) + "%");
				System.out.println("Failure Rate: " + String.format("%.2f", failurePercent) + "%");
				System.out.println("Average Number of Steps When It Succeeds: " + String.format("%.2f", avgSuccess));
				System.out.println("Average Number of Steps When It Fails: " + String.format("%.2f", avgFailure));
				break;
			case 2:// Iterations of Hill Climbing with Sideway Moves.
				for (int i = 0; i < iterations; i++) {
					SidewayMoves sidewayMovesObj = new SidewayMoves();
					int[] tempAnswer = sidewayMovesObj.process(noOfQueens, printOutput);
					totalSuccessfulMoves = totalSuccessfulMoves + tempAnswer[0];
					totalSuccessfulIterations = totalSuccessfulIterations + tempAnswer[1];
					totalFailMoves = totalFailMoves + tempAnswer[2];
					totalFailedIterations = totalFailedIterations + tempAnswer[3];
				}
				System.out.println("\n----------------------------------------------------------------------------");
				System.out.println("Sideway moves Hill Climbing Algorithm");
				System.out.println("# Of Queens: " + noOfQueens);
				System.out.println("Number of Iterations: " + iterations);
				System.out.println("Success/Fail Analysis");
				// Calculating success and failure percent and average moves.
				if (totalSuccessfulIterations != 0) {
					successPercent = (totalSuccessfulIterations / iterations) * 100;
					avgSuccess = totalSuccessfulMoves / totalSuccessfulIterations;
				}
				if (totalFailedIterations != 0) {
					failurePercent = (totalFailedIterations / iterations) * 100;
					avgFailure = totalFailMoves / totalFailedIterations;
				}
				System.out.println("Success Rate: " + String.format("%.2f", successPercent) + "%");
				System.out.println("Failure Rate: " + String.format("%.2f", failurePercent) + "%");
				System.out.println("Average Number of Steps When It Succeeds: " + String.format("%.2f", avgSuccess));
				System.out.println("Average Number of Steps When It Fails: " + String.format("%.2f", avgFailure));

				break;
			case 3:// Iterations of Hill Climbing Random Restart without Sideway Moves.
				numberOfIterationsForWhichRestartWasUsed = 0;
				for (int i = 0; i < iterations; i++) {
					RandomRestartForSteepestAscent randomRestartSteepestAscentObj = new RandomRestartForSteepestAscent();
					int[] tempAnswer = randomRestartSteepestAscentObj.process(noOfQueens, printOutput);
					totalSuccessfulMoves = totalSuccessfulMoves + tempAnswer[0];
					totalSuccessfulIterations = totalSuccessfulIterations + tempAnswer[1];
					totalFailMoves = totalFailMoves + tempAnswer[2];
					totalFailedIterations = totalFailedIterations + tempAnswer[3];
					totalNumberOfRestarts = totalNumberOfRestarts + tempAnswer[4];
					numberOfIterationsForWhichRestartWasUsed = numberOfIterationsForWhichRestartWasUsed + tempAnswer[5];

				}
				System.out.println("\n----------------------------------------------------------------------------");
				System.out.println("Random Restart without Sideway Moves Hill Climbing Algorithm");
				System.out.println("# Of Queens: " + noOfQueens);
				System.out.println("Number of Iterations: " + iterations);
				System.out.println("Success/Fail Analysis");
				// Calculating success and failure percent and average moves.
				if (totalSuccessfulIterations != 0) {
					successPercent = (totalSuccessfulIterations / iterations) * 100;
					avgSuccess = totalSuccessfulMoves / totalSuccessfulIterations;
				}
				if (totalFailedIterations != 0) {
					failurePercent = (totalFailedIterations / iterations) * 100;
					avgFailure = totalFailMoves / totalFailedIterations;
				}
				if (numberOfIterationsForWhichRestartWasUsed != 0) {
					numberOfRestarts = (totalNumberOfRestarts / numberOfIterationsForWhichRestartWasUsed);
				}
				System.out.println("Success Rate: " + String.format("%.2f", successPercent) + "%");
				System.out.println("Failure Rate: " + String.format("%.2f", failurePercent) + "%");
				System.out.println("Average Number of Steps When It Succeeds: " + String.format("%.2f", avgSuccess));
				System.out.println("Average Number of Steps When It Fails: " + String.format("%.2f", avgFailure));
				System.out.println("Average Number of Restarts: " + String.format("%.2f", numberOfRestarts));
				break;
			case 4:// Iterations of Hill Climbing Random Restart with Sideway Moves.
				for (int i = 0; i < iterations; i++) {
					RandomRestartForSidewaysMoves randomRestartSidewaysMovesObj = new RandomRestartForSidewaysMoves();
					int[] tempAnswer = randomRestartSidewaysMovesObj.process(noOfQueens, printOutput);
					totalSuccessfulMoves = totalSuccessfulMoves + tempAnswer[0];
					totalSuccessfulIterations = totalSuccessfulIterations + tempAnswer[1];
					totalFailMoves = totalFailMoves + tempAnswer[2];
					totalFailedIterations = totalFailedIterations + tempAnswer[3];
					totalNumberOfRestarts = totalNumberOfRestarts + tempAnswer[4];
					numberOfIterationsForWhichRestartWasUsed = numberOfIterationsForWhichRestartWasUsed + tempAnswer[5];
				}
				System.out.println("\n----------------------------------------------------------------------------");
				System.out.println("Random Restart with Sideway moves Hill Climbing Algorithm");
				System.out.println("# Of Queens: " + noOfQueens);
				System.out.println("Number of Iterations: " + iterations);
				System.out.println("Success/Fail Analysis");
				if (totalSuccessfulIterations != 0) {
					successPercent = (totalSuccessfulIterations / iterations) * 100;
					avgSuccess = totalSuccessfulMoves / totalSuccessfulIterations;
				}
				if (totalFailedIterations != 0) {
					failurePercent = (totalFailedIterations / iterations) * 100;
					avgFailure = totalFailMoves / totalFailedIterations;
				}

				if (numberOfIterationsForWhichRestartWasUsed != 0) {
					numberOfRestarts = (totalNumberOfRestarts / numberOfIterationsForWhichRestartWasUsed);
				}
				System.out.println("Success Rate: " + String.format("%.2f", successPercent) + "%");
				System.out.println("Failure Rate: " + String.format("%.2f", failurePercent) + "%");
				System.out.println("Average Number of Steps When It Succeeds: " + String.format("%.2f", avgSuccess));
				System.out.println("Average Number of Steps When It Fails: " + String.format("%.2f", avgFailure));
				System.out.println("Average Number of Restarts: " + String.format("%.2f", numberOfRestarts));

				break;

			default:
				System.out.println("Entered input is invalid.");
				break;
			}

			System.out.println("Do you wish to continue? (Y/y - Yes And N/n - No)");
			String input = scanner.next();
			if (input.equalsIgnoreCase("Y")) {
				// clear all variables
				totalSuccessfulMoves = 0.0f;
				totalSuccessfulIterations = 0.0f;
				totalFailMoves = 0.0f;
				totalFailedIterations = 0.0f;
				totalNumberOfRestarts = 0.0f;
				numberOfIterationsForWhichRestartWasUsed = 0;
				avgSuccess = 0.0f;
				avgFailure = 0.0f;
				numberOfRestarts = 0.0f;
				successPercent = 0.0f;
				failurePercent = 0.0f;
				wishToContinue = true;
			} else {
				wishToContinue = false;
			}

		}

		scanner.close();
	}
}
