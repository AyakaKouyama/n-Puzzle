import java.text.DecimalFormat;
import java.util.ArrayList;

public class Program 
{
	public static String getMoves(ArrayList<Node> path)
	{
		if(path == null)
			return null;
		
		StringBuilder str = new StringBuilder();
		for(int i = path.size() - 2; i>= 0; i--)
		{
			str.append(path.get(i).getPrevMove());
		}
		
		return str.toString();
	}
	
	public static void main(String[] args) 
	{
		long start;
		long stop;
		ArrayList<Node> solutionPath = null;
		
		if(args.length < 4)
		{
			System.out.println("Pass arguments.");
			return;
		}
		String method = args[0];
		String order = args[1];
		String inputFile = args[2];
		String solutionFile = args[3];
		String statisticFile = args[4];
		
		method = method.toUpperCase();
		order = order.toUpperCase();
		
		Files file = new Files(inputFile);
		Files solution= new Files(solutionFile);
		Files statistics = new Files(statisticFile);
		
		int visitedNodes = 0;
		int processedNodes = 0;
		int maxRecursion = 0;

		try
		{
		if(method.equals("BFS") || method.equals("DFS") || method.equals("ASTR"))
		{
			Node root = new Node(null, file.ReadPuzzle(), file.GetRows(), file.GetColumns(), order, '0', 0);
			
			if(method.equals("BFS"))
			{
				start = System.nanoTime();
				Bfs bfs = new Bfs();
				solutionPath= bfs.breathFirstSearch(root);
				stop = System.nanoTime();
				
				StringBuilder str = new StringBuilder();
				if(getMoves(solutionPath) != null)
						str.append(getMoves(solutionPath).length() + System.lineSeparator());
				else
					str.append("-1" + System.lineSeparator());
				
				str.append(bfs.getVisitedNodesCounter( )+ System.lineSeparator());
				str.append(bfs.getProcessedNodesCounter() + System.lineSeparator());
				str.append(bfs.getMaxRecursionDepth() + System.lineSeparator());
				str.append(new DecimalFormat("##.###").format((float)((stop - start)/1e6)) + System.lineSeparator());
				statistics.Save(str.toString());

				
			}
			else if(method.equals("DFS"))
			{
				start = System.nanoTime();
				Dfs dfs = new Dfs();
				dfs.deepFirstSearch(root, 0);
				solutionPath = dfs.getPath();
				stop = System.nanoTime();
				
				StringBuilder str = new StringBuilder();
				if(getMoves(solutionPath) != null)
						str.append(getMoves(solutionPath).length() + System.lineSeparator());
				else
					str.append("-1" + System.lineSeparator());
				str.append(dfs.getVisitedNodesCounter() + System.lineSeparator());
				str.append(dfs.getProcessedNodesCounter() + System.lineSeparator());
				str.append(dfs.getMaxRecursionDepth() + System.lineSeparator());
				str.append(new DecimalFormat("##.###").format((float)((stop - start)/1e6)) + System.lineSeparator());

				statistics.Save(str.toString());
			}
			else if(method.equals("ASTR"))
			{
				start = System.nanoTime();
				Astar astr = new Astar(order);
				solutionPath = astr.AStar(root);
				stop = System.nanoTime();
				
				StringBuilder str = new StringBuilder();
				if(getMoves(solutionPath) != null)
						str.append(getMoves(solutionPath).length() + System.lineSeparator());
				else
					str.append("-1" + System.lineSeparator());
				str.append(astr.getVisitedNodesCounter( )+ System.lineSeparator());
				str.append(astr.getProcessedNodesCounter() + System.lineSeparator());
				str.append(astr.getMaxRecursionDepth() + System.lineSeparator());
				str.append(new DecimalFormat("##.###").format((float)((stop - start)/1e6)) + System.lineSeparator());
				
				statistics.Save(str.toString());

			}
			
			String solutionTxt;
			if(solutionPath != null)
			{
				solutionTxt = getMoves(solutionPath).length()+ System.lineSeparator() + getMoves(solutionPath);
				solution.Save(solutionTxt);
			}
			else
			{
				solutionTxt = "-1";
			}
			
			solution.Save(solutionTxt);
			System.out.println("DONE");
		}
		else
		{
			System.out.println("Wrong arguments. Available methods are: 'bfs', 'dfs', 'astar'");

		}
		}
		catch(OutOfMemoryError e)
		{
			System.out.println("Out of memory");
			stop = System.nanoTime();
		}

	}
}
