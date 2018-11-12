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
		long start = 0;
		long stop = 0;
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
		Solver solver = null;
		try
		{
			if(method.equals("BFS") || method.equals("DFS") || method.equals("ASTR"))
			{
				Node root = new Node(null, file.ReadPuzzle(), file.GetRows(), file.GetColumns(), order, '0', 0);
	
				if(method.equals("BFS"))
				{
					solver = new Solver(new Bfs());	
				}
				else if(method.equals("DFS"))
				{
					solver = new Solver(new Dfs());
				}
				else if(method.equals("ASTR"))
				{
					solver = new Solver(new Astar(order));
					root.setOrder("LURD");
				}
				
			
				start = System.nanoTime();
				solver.solve(root, 0);
				stop = System.nanoTime();
	
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
		finally
		{
			visitedNodes = solver.getVisitedNodesCounter();
			processedNodes = solver.getProcessedNodesCounter();
			maxRecursion = solver.getMaxRecursionDepth();
			solutionPath = solver.getPath();
		
			
			StringBuilder str = new StringBuilder();
			if(getMoves(solutionPath) != null)
					str.append(getMoves(solutionPath).length() + System.lineSeparator());
			else
				str.append("-1" + System.lineSeparator());
			
			str.append(visitedNodes + System.lineSeparator());
			str.append(processedNodes + System.lineSeparator());
			str.append(maxRecursion + System.lineSeparator());
			str.append(new DecimalFormat("##.###").format((float)((stop - start)/1e6)) + System.lineSeparator());
			statistics.Save(str.toString());
			
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

	}
}


