import java.util.ArrayList;
import java.util.Stack;

public class Dfs
{

	private int maxDepth = 20;
	private boolean found = false;
	private ArrayList<Node> foundPath;
	private Stack<Node> visited = new Stack<Node>();
	
	Dfs()
	{
		foundPath = new ArrayList<Node>();
	}

	public ArrayList<Node> getPath()
	{
		return foundPath;
	}
	
	
	public void deepFirstSearch(Node node, int depth)
	{
		try
		{
			if(depth > maxDepth)
			{
				return;
			}
			
			visited.add(node);
			if(node.stopCondition() == true && found == false)
			{
				found = true;
				foundPath = tracePath(node);
				return;
			}
			else
			{
				node.setChildern();
				for(int i = 0; i<node.getChilderNumber(); i++)
				{
					if(contain(visited, node.getChildren()[i]) == false)
					{
						deepFirstSearch(node.getChildren()[i], depth + 1);
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
	

	public boolean contain(Stack<Node> open, Node n)
	{
		for(int i = 0; i<open.size(); i++)
		{
			if(open.get(i).isSame(n))
			{
				return true;
			}
		}
		
		return false;
	}

}
