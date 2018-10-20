import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Dfs
{

	int maxDepth = 25;
	int currentDepth = 0;
	boolean found = false;
	int solution = 0;
	Stack<Node> path = new Stack<Node>();
	ArrayList<Node> foundPath = null;
	

	
	public ArrayList<Node> test(Node node, int depth)
	{
		Stack<Node> visited = new Stack<Node>();
		if(depth < maxDepth)
		{
			visited.add(node);
			if(node.stopCondition() == true && found == false)
			{
				found = true;
				solution = depth;
				foundPath = tracePath(node);
				return foundPath;
			}
			
			node.setChildern();
			for(int i = 0; i<node.getChilderNumber(); i++)
			{
				if(contain(visited, node.getChildren()[i]) == false)
					test(node.getChildren()[i], depth + 1);
			}
			
		}
		
		return foundPath;
	}
	
	public ArrayList<Node> tracePath(Node node)
	{
		ArrayList<Node> path = new ArrayList<Node>();

		Node current = node;
		path.add(current);
		
		while(current.previous != null)
		{
			current = current.previous;
			path.add(current);
			
		}
		
		return path;
	}
	
	public Stack<Node> depthFirstSearch(Node node) // some suspicious recursive method
	{
		Stack<Node> open = new Stack<Node>();
		Stack<Node> close = new Stack<Node>();
		
		open.push(node);
		
		currentDepth = 0;
		
			while(open.empty() == false)
			{	
				Node current = open.pop();
				
				if(open.size() > maxDepth)
				{
					int counter = 0;
					//for(int i = 0; i< open.size(); i++)
					//{
					//	Node toClose = open.pop();
					//	close.push(toClose);
					//}				
					//open.push(node);
				//	while(counter != 0)
					//{
					//	current = open.pop();
					//	for(int i = 0; i<current.getChilderNumber(); i++)
					//	{
					//		if(contain(close, current.getChildren()[i]) == false)
					//			counter++;
					//	}
						
				//	}
				//	open.push(current);
				}
				
				close.push(current);
				//open.pop();
				
				if(current.stopCondition() == true)
				{
					current.printPuzzle();
					System.out.println("Stop");
					return path;
				}
				
				current.setChildern();
				for(int i = 0; i<current.getChilderNumber(); i++)
				{
					if(contain(close, current.getChildren()[i]) == false)
					open.push(current.getChildren()[i]);
				}
				System.out.println(open.size());
			}
		
		return null;
	}
	
	
	public void getPath(Node node)
	{
		Node current = node;
		path.add(current);
		
		while(current.previous != null)
		{
			current = current.previous;
			path.add(current);
			
		}
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
	
	/*
	 * public void solve()
	 * {
	 * if(node == null)
	 * return;
	 * 
	 * System.out.println(node.getNumbers[0][0]);
	 * solve(node.left);
	 * solve(node.right);
	 */

}
