import java.util.ArrayList;
import java.util.Date;

public class Program {

	public static void main(String[] args) 
	{
		Files file = new Files("input.txt");
		Node startNode = new Node(null, file.ReadPuzzle(), file.GetRows(), file.GetColumns(), "LURD", '0', 0);
		
		Date a1 = new Date();
		Astar astr = new Astar("MANH");
		astr.AStar(startNode);
		Date a2 = new Date();
		
		System.out.println("MANH Time " + (a2.getTime() - a1.getTime()) + "ms");
		
		
		Date a11 = new Date();
		Astar astr2 = new Astar("HAMM");
		astr2.AStar(startNode);
		Date a22 = new Date();
		
		System.out.println("HAMM Time " + (a22.getTime() - a11.getTime()) + "ms");
		
		Bfs bfs = new Bfs();
		Date s = new Date();
		ArrayList<Node> path = bfs.breathFirstSearch(startNode);
		Date stop = new Date();
		System.out.println("BFS Time: " + (stop.getTime() - s.getTime()) + "ms");
		System.out.println("PATH: " + (path.size() - 1));
		
		Dfs dfs = new Dfs();
		Date dfs1 = new Date();
		dfs.deepFirstSearch(startNode, 0);
		Date dfs2 = new Date();
		System.out.println("DFS Time: " + (dfs2.getTime() - dfs1.getTime()) + "ms");
		
		/*
		System.out.println();
		for(int i = path.size() - 1; i>=0; i--)
		{
			path.get(i).printPuzzle();
			System.out.println();
		}
		
		System.out.println("Path length: " + path.size());
		System.out.println("Time: " + (stop.getTime() - s.getTime()) + "ms");
		
		for(int i = path.size() - 2; i>= 0 ; i--)
		{
			System.out.print(path.get(i).prevMove + " ");
		}
		
		System.out.println("Path length: " + (path.size() - 1));
		System.out.println();
		System.out.println("____________________________");
		
		
		//-----------------------------------------------------------//
		Dfs dfs = new Dfs();
		Date dfs1 = new Date();
		dfs.deepFirstSearch(startNode, 0);
		Date dfs2 = new Date();
		
		ArrayList<Node> path2 = dfs.foundPath;
		if(path2 != null)
		{
			for(int i = path2.size() - 1; i>=0; i--)
			{
				path2.get(i).printPuzzle();
				System.out.println();
				
			}
			
			System.out.println("Path length: " + path2.size());
			System.out.println("Time: " + (dfs2.getTime() - dfs1.getTime()) + "ms");
			
			for(int i = path2.size() - 2; i>= 0 ; i--)
			{
				System.out.print(path2.get(i).prevMove + " ");
			}
			
			System.out.println("Path length: " + (path2.size() - 1));
			System.out.println();
			System.out.println("____________________________");
			
		}
		else
		{
			System.out.println("Solution not found");
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
