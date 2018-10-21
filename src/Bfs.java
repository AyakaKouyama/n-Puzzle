import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs
{

	public ArrayList<Node> breathFirstSearch(Node root)
	{
		Queue<Node> open = new LinkedList<Node>();
		Queue<Node> close = new LinkedList<Node>();
		
		open.add(root);
		boolean found = false;
		
		while(open.size() > 0 && found == false)
		{
			Node currentNode = open.remove();
			close.add(currentNode);
			
			
			currentNode.setChildern();
			int size = currentNode.getChilderNumber();
			for(int i = 0; i<size; i++)
			{
				Node child = currentNode.getChildren()[i];
				
				if(child.stopCondition() == true)
				{
					found = true;
					return pathTrace(child);
				}		
				else if(contains(open, child) == false && contains(close, child) == false)
				{
					open.add(child);
				}
			}	
		}
		
		return null;
	}
	
	
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
	
	
	public boolean contains(Queue<Node> list, Node node)
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
