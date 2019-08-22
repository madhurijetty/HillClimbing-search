
/* Team members
 * Aarti Nimhan - 801098198
 * Uma Sai Madhuri Jetty - 801101049
 * Sahithi Priya Gutta - 801098589
 * 
 * This is a comparator created which is used in the Priority Queue to decide the ordering based on the hCost. 
 * The priority queue is ordered in ascending order of hcost.
 */
import java.util.Comparator;

public class HCostComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		if (o1.gethCost() < o2.gethCost())
			return -1;
		if (o1.gethCost() > o2.gethCost())
			return 1;
		return 0;
	}

}
