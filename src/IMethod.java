import java.util.ArrayList;

public interface IMethod 
{
	public void solve(Node node, int depth);
	public ArrayList<Node> pathTrace(Node n);
	public ArrayList<Node> getPath();
	public int getMaxRecursionDepth();
	public int getVisitedNodesCounter();
	public int getProcessedNodesCounter();
}
