import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Astar implements IMethod
{
	private String heuristic;
	private int visited = 0;
	private int processed = 0;
	private int maxDepth = 0;
	private ArrayList<Node> path;
	
	public Astar(String heuristic)
	{
		heuristic = heuristic.toUpperCase();
		this.heuristic = heuristic;
	}
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
	public void solve(Node node, int depth)
	{
		try
		{
			PriorityQueue<Node> queue = null;
			Queue<Node> close = new LinkedList<Node>();
			boolean found = false;
			if(heuristic.equals("HAMM"))
			{
				queue = new PriorityQueue<Node>(new HammingNodeCompare());
			}
			else if (heuristic.equals("MANH"))
			{
				queue = new PriorityQueue<Node>(new ManhatanNodeCompare());
			}
			
			queue.add(node);
			visited++;
			
			while(queue.size() > 0 && found == false)
			{
				Node currentNode = queue.remove();
				close.add(currentNode);
				visited++;
				currentNode.setChildern();
				int size = currentNode.getChilderNumber();
				
				for(int i = 0; i<size; i++)
				{
					Node child = currentNode.getChildren()[i];
					//System.out.println(currentNode.getChildren()[i]);
					if(child.stopCondition() == true)
					{
						found = true;
						processed = close.size();
						maxDepth = child.getDepth();
						
						//return pathTrace(child);
						path = pathTrace(child);
					}
					else if(contains(queue, child) == false && contains(close, child) == false)
					{
						queue.add(child);
						visited++;
					}
				}	
			}
			
			//return null;
			
		}
		catch(OutOfMemoryError e)
		{
			System.out.println("Out of memory");
			//return null;
		}
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



}
