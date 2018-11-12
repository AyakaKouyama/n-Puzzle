import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs implements IMethod
{

	private int visited = 0;
	private int processed = 0;
	private int maxDepth = 0;
	private ArrayList<Node> path;
	
	@Override
	public ArrayList<Node> getPath()
	{
		return path;
	}
	@Override
	public int getMaxRecursionDepth()
	{
		return maxDepth;
	}
	
	@Override
	public int getVisitedNodesCounter()
	{
		return visited;
	}
	@Override
	public int getProcessedNodesCounter()
	{
		return processed;
	}
	
	
	@Override
	public void solve(Node root, int depth)
	{
		Queue<Node> open = new LinkedList<Node>();
		Queue<Node> close = new LinkedList<Node>();
		
		open.add(root);
		visited++;
		boolean found = false;
		
		while(open.size() > 0 && found == false)
		{
			Node currentNode = open.remove();
			close.add(currentNode);
			visited++;
			
			
			currentNode.setChildern();
			int size = currentNode.getChilderNumber();
			for(int i = 0; i<size; i++)
			{
				Node child = currentNode.getChildren()[i];
				
				if(child.stopCondition() == true)
				{
					found = true;
					processed = close.size();
					maxDepth = child.getDepth();
					
					//return pathTrace(child);
					path = pathTrace(child);
				}		
				else if(contains(open, child) == false && contains(close, child) == false)
				{
					open.add(child);
					visited++;
				}
			}	
		}
		
		//return null;
	}
	
	
	@Override
	public ArrayList<Node> pathTrace(Node n)
	{
		ArrayList<Node> path = new ArrayList<Node>();
		Node current = n;
		path.add(current);
		
		while(current.getPrevious() != null)
		{
			current = current.getPrevious();
			path.add(current);
		}
		
		return path;
	} 
	
	
	private boolean contains(Queue<Node> list, Node node)
	{
		Queue<Node> n = new LinkedList<Node>(list);
		
		while(n.size() > 0)
		{
			if(n.remove().isSame(node))
			{
				return true;
			}
		}
		return false;
	}

}
