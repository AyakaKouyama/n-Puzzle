import java.util.ArrayList;
import java.util.Stack;

public class Dfs implements IMethod
{

	private int maxDepth = 20;
	private int maxRecursionDepth = 0;
	private int processed = 0;
	private boolean found = false;
	private ArrayList<Node> path;
	private Stack<int[][]> numbers = new Stack<int[][]>();
	
	@Override
	public ArrayList<Node> getPath()
	{
		return path;
	}
	@Override
	public int getMaxRecursionDepth()
	{
		return maxRecursionDepth;
	}
	@Override
	public int getVisitedNodesCounter()
	{
		return numbers.size();
	}
	@Override
	public int getProcessedNodesCounter()
	{
		return processed;
	}
	
	@Override
	public void solve(Node node, int depth)
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
			//	System.out.println(depth);
				found = true;
				path = pathTrace(node);
			}
			else
			{
				node.setChildern();
				for(int i = 0; i<node.getChilderNumber(); i++)
				{
					
					if(numbers.search(node.getChildren()[i]) == -1)
					{
						solve(node.getChildren()[i], depth + 1);
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
	
	
	@Override
	public ArrayList<Node> pathTrace(Node node)
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
