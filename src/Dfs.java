import java.util.ArrayList;
import java.util.Stack;

public class Dfs
{

	private int maxDepth = 20;
	private int maxRecursionDepth = 0;
	private int processed = 0;
	private boolean found = false;
	private ArrayList<Node> foundPath;
	private Stack<int[][]> numbers = new Stack<int[][]>();
	
	public ArrayList<Node> getPath()
	{
		return foundPath;
	}
	

	public int getMaxRecursionDepth()
	{
		return maxRecursionDepth;
	}
	
	public int getVisitedNodesCounter()
	{
		return numbers.size();
	}
	
	public int getProcessedNodesCounter()
	{
		return processed;
	}
	public 
	void deepFirstSearch(Node node, int depth)
	{

		try
		{
			if(found == true)
			{
				return;
			}
			
			if(depth > maxDepth)
			{
				return;
			}
			
			if(depth > maxRecursionDepth)
			{
				maxRecursionDepth = depth;
			}
			
			numbers.push(node.getNumbers());
			processed++;
			
			if(node.stopCondition() == true)
			{
				System.out.println(depth);
				found = true;
				foundPath = tracePath(node);
			}
			else
			{
				node.setChildern();
				for(int i = 0; i<node.getChilderNumber(); i++)
				{
					
					if(numbers.search(node.getChildren()[i]) == -1)
					{
						deepFirstSearch(node.getChildren()[i], depth + 1);
						processed++;
					}

				}
			}
		}
		catch(OutOfMemoryError exc)
		{
			System.out.println("Out of memory");
		}

		
	}
	
	public ArrayList<Node> tracePath(Node node)
	{
		ArrayList<Node> path = new ArrayList<Node>();

		Node current = node;
		path.add(current);
		
		while(current.getPrevious()!= null)
		{
			current = current.getPrevious();
			path.add(current);
			
		}
		
		return path;
	}
	

}
