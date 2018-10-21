import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Astar 
{
	private String heuristic;
	
	public Astar(String heuristic)
	{
		heuristic = heuristic.toUpperCase();
		this.heuristic = heuristic;
	}

	public ArrayList<Node> AStar(Node node)
	{
		try
		{
			PriorityQueue<Node> queue = null;
			Queue<Node> close = new LinkedList<Node>();
			boolean found = false;
			
			if(heuristic == "HAMM")
			{
				queue = new PriorityQueue<Node>(new HammingNodeCompare());
			}
			else if (heuristic == "MANH")
			{
				queue = new PriorityQueue<Node>(new ManhatanNodeCompare());
			}
			
			queue.add(node);
			
			while(queue.size() > 0 && found == false)
			{
				Node currentNode = queue.remove();
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
					else if(contains(queue, child) == false && contains(close, child) == false)
					{
						queue.add(child);
					}
				}	
			}
			
			return null;
			
		}
		catch(OutOfMemoryError e)
		{
			System.out.println("Out of memory");
			return null;
		}
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
	

}
