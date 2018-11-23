import java.util.ArrayList;
import java.util.Stack;

public class Dfs implements IMethod
{

	private int maxDepth = 40;
	private int maxRecursionDepth = 0;
	private int processed = 0;
	//private int rows = 0;
	//private int columns = 0;
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
		//rows = node.getRows();
		//columns = node.getCoulums();
		
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
				System.out.println("found");
				found = true;
				path = pathTrace(node);
			}
			else
			{
				node.setChildern();
				int size = node.getChilderNumber();
				for(int i = 0; i<size; i++)
				{
					//if(contains(numbers, node.getChildren()[i].getNumbers(), rows, columns) == false)
					if(numbers.contains(node.getChildren()[i].getNumbers()) == false)
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
	
	public boolean contains(Stack<int[][]> stack, int[][] numbers, int rows, int columns)
	{
		Stack<int[][]> tempStack = (Stack<int[][]>) stack.clone();
		
		while(tempStack.isEmpty() == false)
		{
			int counter = 0;
			int[][] temp = tempStack.pop();
		
			for(int i = 0; i<rows; i++)
			{
				for(int j = 0; j< columns; j++)
				{
					if(temp[i][j] == numbers[i][j])
					{
						counter++;
					}
				}
			}
			
			if(counter == (rows * columns))
			{
				return true;
			}
		} 
		
		return false;
	}

}
