import java.io.*;
import java.util.Scanner;

public class Files
{
	public String fileName;
	
	
	public Files(String filename)
	{
		this.fileName = filename;
	}
	

	public void Save(String data)
	{
		
	}
	
	public String ReadSize() 
	{
		
		try
		{
			FileReader file = new FileReader("input.txt");
			BufferedReader buffer = new BufferedReader(file);
			String txtLine = buffer.readLine();
			buffer.close();
			return txtLine;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
			return null;	
	}
	
	public int GetRows()
	{
		return ReadSize().charAt(0) - '0';
	}
	
	public int GetColumns()
	{
		return ReadSize().charAt(2) - '0';
	}
	
	public int[][] ReadPuzzle()
	{
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader("input.txt")));)
		{
			String txtLine = sc.nextLine();
			
			int rows = txtLine.charAt(0) - '0';
			int col = txtLine.charAt(2) - '0';
			int array[][] = new int[rows][col];
			
			 for (int i=0; i<rows; i++) 
			 {
		            String[] line = sc.nextLine().trim().split(" ");
		            for (int j=0; j<col; j++)
		            {
		               array[i][j] = Integer.parseInt(line[j]);
		            }
			 }
			 
			 return array;
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
			return null;	
	}
	
}