
/* Team members
 * Aarti Nimhan - 801098198
 * Uma Sai Madhuri Jetty - 801101049
 * Sahithi Priya Gutta - 801098589
 * 
 * This class contains utilities which can be used by all variants of hill climbing.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class CommonUtils {

	private static int QUEEN = 1;
	private static int NO_QUEEN = 0;

	/**
	 * This method finds possible successors to a given state. Each successor is a
	 * result of moving one queen from its current position to any position in its
	 * own column.
	 * 
	 * @param currentNode This is the node for which successors are to be generated.
	 * @return this method returns a list of nodes which are successors to the input
	 *         node.
	 */
	public List<Node> findPossibleSuccessors(Node currentNode) {

		List<Node> possibleSuccessors = new ArrayList<Node>();

		int currentQueenColumn, currentQueenRow, newQueenRow, newQueenColumn;
		for (int column = 0; column < currentNode.getboard().length; column++) { // For each column
			currentQueenRow = Integer.MAX_VALUE;
			currentQueenColumn = column;
			newQueenColumn = column;
			// find the current queen row position in the current column
			for (int i = 0; i < currentNode.getboard().length; i++) {
				if (currentNode.getboard()[i][currentQueenColumn] == QUEEN) {
					currentQueenRow = i;
					break;
				}
			}
			// for each row position swap with current row and create a new possible
			// positions
			for (int row = 0; row < currentNode.getboard().length; row++) {
				if (row != currentQueenRow && currentQueenRow != Integer.MAX_VALUE) {
					int[][] currentState = new int[currentNode.getboard().length][currentNode.getboard().length];
					copyState(currentNode.getboard(), currentState);
					newQueenRow = row;
					// swap
					currentState[currentQueenRow][currentQueenColumn] = NO_QUEEN;
					currentState[newQueenRow][newQueenColumn] = QUEEN;
					Node newNode = new Node();
					newNode.setboard(currentState);
					possibleSuccessors.add(newNode);
				}
			}
		}
		return possibleSuccessors;
	}

	/**
	 * This method calculates the heuristic value for the input node. This value is
	 * calculated by adding all possible attacks of each queen on the board. This
	 * heuristic value is updated in the hcost attribute of the input node.
	 * 
	 * @param currentNode the input Node for which heuristic is to be calculated.
	 * 
	 */
	public void calculateHeuristic(Node currentNode) {

		int[][] currentState = new int[currentNode.getboard().length][currentNode.getboard().length];
		copyState(currentNode.getboard(), currentState);
		// Find queen positions
		int[] rowIndex = new int[currentNode.getboard().length];
		int[] colIndex = new int[currentNode.getboard().length];
		int queenIndex = 0;
		for (int col = 0; col < currentNode.getboard().length; col++) {
			for (int row = 0; row < currentNode.getboard().length; row++) {
				if (currentState[row][col] == QUEEN) {
					rowIndex[queenIndex] = row;
					colIndex[queenIndex] = col;
					queenIndex++;
				}
			}
		}

		int heuristic = 0;
		boolean heuristicNotCheckedFlag = true;
		// From positions for each queen check attack with other
		for (int i = 0; i < rowIndex.length; i++) {
			// First Queen position is rowIndex[i] colIndex[i]
			for (int j = i + 1; j < rowIndex.length; j++) {
				// Second Queen position is rowIndex[j] colIndex[j]
				heuristicNotCheckedFlag = true;
				// if First and second queen are in same rows then increment heuristic cost
				if (rowIndex[i] == rowIndex[j]) {
					heuristic++;
				}

				// if First and second queen are in same rows then increment heuristic cost
				if (colIndex[i] == colIndex[j]) {
					heuristic++;
				}

				// if First and second queen are in diagonal then increment heuristic cost
				if (Math.abs(rowIndex[i] - rowIndex[j]) == Math.abs(colIndex[i] - colIndex[j])) {
					heuristic++;
				}

			}
		}
		if (!heuristicNotCheckedFlag)
			heuristic = Integer.MAX_VALUE;
		currentNode.sethCost(heuristic);
	}

	/**
	 * This is a utility method to copy one state to another
	 * 
	 * @param sourceNodeState      state to be copied.
	 * @param destinationNodeState copied state.
	 */
	public void copyState(int[][] sourceNodeState, int[][] destinationNodeState) {
		for (int i = 0; i < sourceNodeState.length; i++)
			for (int j = 0; j < sourceNodeState.length; j++)
				destinationNodeState[i][j] = sourceNodeState[i][j];

	}

	/**
	 * This method returns the successor with the least hcost value.
	 * 
	 * @param possibleSuccessors this is the list of possible successors to a state.
	 * @return will return a Node with the least hcost value amongst the successors.
	 */
	public Node bestSuccessor(List<Node> possibleSuccessors) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>(possibleSuccessors.size(), new HCostComparator());
		for (int i = 0; i < possibleSuccessors.size(); i++) {
			pq.add(possibleSuccessors.get(i));
		}
		List<Node> bestSuccessorsList = new ArrayList<Node>();
		Node bestSuccessor = new Node();
		bestSuccessor = pq.poll();
		int hcost = bestSuccessor.gethCost();
		bestSuccessorsList.add(bestSuccessor);
		while (pq.peek().gethCost() == hcost) {
			bestSuccessorsList.add(pq.poll());
		}
		Random rand = new Random();
		int index = rand.nextInt(bestSuccessorsList.size());
		bestSuccessor = bestSuccessorsList.get(index);
		pq.clear();
		return bestSuccessor;

	}

	/**
	 * This method generates a new Node with a random state. This random state has
	 * one queen per column. All the queens are placed at random rows in their
	 * respective columns.
	 * 
	 * @param numberOfQueens the total number of queens on the board.
	 * @return returns a node created with randomly placed queens.
	 */
	public Node generateRandomState(int numberOfQueens) {
		Random rand = new Random();
		int[][] newState = new int[numberOfQueens][numberOfQueens];
		Node newNode = new Node();
		for (int i = 0; i < numberOfQueens; i++) {
			int randomNumber = rand.nextInt(numberOfQueens - 1);
			for (int j = 0; j < numberOfQueens; j++) {
				if (j == randomNumber) {
					newState[j][i] = QUEEN;
				} else {
					newState[j][i] = NO_QUEEN;
				}
			}
		}
		newNode.setboard(newState);
		return newNode;
	}

	/**
	 * This method prints the state of the input node.
	 * 
	 * @param node
	 */
	public void printNode(Node node) {
		for (int i = 0; i < node.getboard().length; i++) {
			for (int j = 0; j < node.getboard().length; j++) {
				String printValue = "_";
				if ((node.getboard())[i][j] == 1) {
					printValue = "Q";
				}
				System.out.print("\t" + printValue);
			}
			System.out.println();
		}
		System.out.println("------------------");
	}

}
