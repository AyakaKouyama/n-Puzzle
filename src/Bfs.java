import java.util.ArrayList;

public class Bfs
{

	
	public ArrayList<Node> BreathFirstSearch(Node root)
	{
		ArrayList<Node> pathToSolution = new ArrayList<Node>();
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> close = new ArrayList<Node>();
		
		open.add(root);
		boolean found = false;
		int counter = 0;
		
		while(open.size() > 0 && found == false)
		{
			Node currentNode = open.get(0);
			close.add(currentNode);
			open.remove(0);
			
			currentNode.setChildern();

			for(int i = 0; i<currentNode.getChilderNumber(); i++)
			{
				Node child = currentNode.getChildren()[i];
				
				if(child.stopCondition() == true)
				{
					System.out.println("Found");
					pathTrace(pathToSolution, child);
					found = true;
				}
				
				if(contains(open, child) == false && contains(close, child) == false)
				{
					open.add(child);
				}
				
			}
				
		}
		
		return pathToSolution;
	}
	
	
	public void pathTrace(ArrayList<Node> path, Node n)
	{
		Node current = n;
		path.add(current);
		
		while(current.previous != null)
		{
			current = current.previous;
			path.add(current);
			
		}
	}
	public boolean contains(ArrayList<Node> list, Node node)
	{
		for(int i = 0; i<list.size(); i++)
		{
			if(list.get(i).isSame(node))
				return true;
		}
		
		return false;
	}
	


}
