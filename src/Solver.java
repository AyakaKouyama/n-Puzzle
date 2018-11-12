import java.util.ArrayList;

public class Solver 
{
	IMethod method;
	
	Solver(IMethod method)
	{
		this.method = method;
	}
	
	public void solve(Node root, int depth)
	{
		method.solve(root, depth);
	}
	
	public int getMaxRecursionDepth()
	{
		return method.getMaxRecursionDepth();
	}
	public int getVisitedNodesCounter()
	{
		return method.getProcessedNodesCounter();
	}
	public int getProcessedNodesCounter()
	{
		return method.getVisitedNodesCounter();
	}
	
	public ArrayList<Node> getPath()
	{
		return method.getPath();
	}
}
