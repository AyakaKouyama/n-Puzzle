import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

public class Program {

	public static void main(String[] args) 
	{
		Files file = new Files("input.txt");
		
		file.ReadPuzzle();
		int [][]start =	{{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};
		Node startNode = new Node(null, start, 3, 3, "LRUD", 0, null);
		
		
		Bfs bfs = new Bfs();
		Date s = new Date();
		ArrayList<Node> path = bfs.BreathFirstSearch(startNode);
		Date stop = new Date();
		
		System.out.println();
		for(int i = path.size() - 1; i>=0; i--)
		{
			path.get(i).printPuzzle();
			System.out.println();
		}
		
		System.out.println("Path length: " + path.size());
		System.out.println("Time: " + (stop.getTime() - s.getTime())); 
		
		
		Dfs dfs = new Dfs();
		ArrayList<Node> path2 = dfs.test(startNode, 0);
		System.out.println();
		for(int i = path.size() - 1; i>=0; i--)
		{
			path2.get(i).printPuzzle();
			System.out.println();
		}
		
		System.out.println("Path length: " + path.size());
		System.out.println(path2.size());
		
		//Stack<Node> path = dfs.depthFirstSearch(startNode);
		/*System.out.println();
		if(path != null)
		{
		for(int i = path.size() - 1; i>=0; i--)		
		{
			path.get(i).printPuzzle();
			System.out.println();
		}

		
		System.out.println("Path length: " + path.size());
		} 
		
		*/

//	//	if(args[0].equals("bfs"))
//		{
//			//new Bfs.solve();
//		}
//		//else if(args[0].equals("dfs"))
//		{
//			//new Dfs.solve();
//		}
//		//else if(args[0].equals("astr"))
//		{
//			//new Astar.solve();
//		}

	}

}
