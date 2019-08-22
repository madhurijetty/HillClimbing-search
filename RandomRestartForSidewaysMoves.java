
/* Team members
 * Aarti Nimhan - 801098198
 * Uma Sai Madhuri Jetty - 801101049
 * Sahithi Priya Gutta - 801098589
 * 
 * This class implements Hill Climbing Random Restart with Sideway Moves.
 */
import java.util.ArrayList;
import java.util.List;

public class RandomRestartForSidewaysMoves {

	public int numberOfQueens = 0;
	public CommonUtils commonUtils = new CommonUtils();
	public boolean isSuccessful = false;
	public boolean isFail = false;
	List<Node> possibleSuccessors = new ArrayList<Node>();
	Node bestSuccessor = new Node();
	int regularMoves = 0;
	int totalSuccessfulMoves = 0;
	int totalFailMoves = 0;
	int totalSuccessfulIterations = 0;
	int totalFailedIterations = 0;
	int totalNumberOfRestarts = 0;
	int[] result = new int[6];
	int sideWalkStepsLimit = 100;
	int sideStepCount = 0;
	boolean updateCurrentNode = false;
	boolean wasRestartRequired = false;
	int restartUsedCount = 0;

	/**
	 * This method implements Hill Climbing Random Restart with Sideway Moves.
	 * 
	 * @param numberOfQueens the total number of queens on the board.
	 * @param printOutput    True if board states are to be printed. False if no
	 *                       board states are printed.
	 * @return returns an integer array with moves required for success and failure
	 *         also includes success and failure iterations count. The array also
	 *         includes number of restarts required.
	 */
	public int[] process(int numberOfQueens, boolean printOutput) {
		this.numberOfQueens = numberOfQueens;

		// Generate a random state with one queen in every column
		Node currentNode = commonUtils.generateRandomState(numberOfQueens);

		// calculate heuristic
		commonUtils.calculateHeuristic(currentNode);
		if (printOutput) {
			System.out.println("----Initial State-----");
			commonUtils.printNode(currentNode);
		}
		while (!isSuccessful && !isFail) {
			updateCurrentNode = false;
			// get possible successors
			possibleSuccessors = commonUtils.findPossibleSuccessors(currentNode);
			// calculate heuristic of possible successors
			for (int i = 0; i < possibleSuccessors.size(); i++) {
				commonUtils.calculateHeuristic(possibleSuccessors.get(i));
			}

			// select best successor if hcost is less than current hcost
			bestSuccessor = commonUtils.bestSuccessor(possibleSuccessors);
			if (bestSuccessor.gethCost() == currentNode.gethCost()) {
				bestSuccessor = commonUtils.bestSuccessor(possibleSuccessors);
			}
			if (currentNode.gethCost() == 0) {
				totalSuccessfulMoves = +regularMoves;
				totalSuccessfulIterations++;
				isSuccessful = true;
				if (printOutput) {
					System.out.println("----Goal State-----");
					commonUtils.printNode(currentNode);
				}
			} else if (bestSuccessor.gethCost() < currentNode.gethCost()) {
				// if yes update current Node with best Successor
				sideStepCount = 0;
				regularMoves++;
				updateCurrentNode = true;
			} else if (bestSuccessor.gethCost() == currentNode.gethCost() && sideStepCount < sideWalkStepsLimit) { // move
																													// sideways
																													// updateCurrentNode
																													// =true;
				sideStepCount++;
				regularMoves++;
				updateCurrentNode = true;
			} else {
				// restart with a new state
				currentNode = commonUtils.generateRandomState(numberOfQueens);
				commonUtils.calculateHeuristic(currentNode);
				totalNumberOfRestarts++;
				wasRestartRequired = true;
			}
			if (updateCurrentNode) {
				currentNode = bestSuccessor;
				// sideStepCount=0;
			}
		}

		if (wasRestartRequired) {
			restartUsedCount++;
		}

		result[0] = totalSuccessfulMoves;
		result[1] = totalSuccessfulIterations;
		result[2] = totalFailMoves;
		result[3] = totalFailedIterations;
		result[4] = totalNumberOfRestarts;
		result[5] = restartUsedCount;

		return result;

	}

}
