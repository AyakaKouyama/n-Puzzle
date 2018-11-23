
public class Node 
{
	private Node previous;
	private Node []children;
	
	private int [][]numbers;
	private int [][]goal;
	
	private String order;
	private char prevMove;
	
	private int rows;
	private int columns;
	private int depth;
	private int hammingDistance;
	private int manhatanDistance;
	
	public Node(Node previous, int[][] numbers, int rows, int columns, String order, char prevMove, int depth)
	{
		this.previous = previous;
		this.numbers = numbers;
		this.rows = rows;
		this.columns = columns;
		this.depth = depth;
		this.prevMove = prevMove;
		
		order = order.toUpperCase();
		this.order = order;
		
		this.children = new Node[4];
		this.goal = new int[rows][columns];
		goalState();
	}
	
	
	//----------------------------------------
	public int getDepth()
	{
		return depth;
	}
	
	public char getPrevMove()
	{
		return prevMove;
	}
	
	public Node getPrevious()
	{
		return previous;
	}
	
	public int[][] getNumbers()
	{
		return numbers;
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getCoulums()
	{
		return columns;
	}
	
	public Node[] getChildren()
	{
		return children;
	}
	
	public int getColumn(int value)
	{
		return (value - 1) % columns;
	}
	
	public int getRow(int value)
	{
		return (value - 1) / rows;
	}
	
	public int manhatanScore()
	{
		return depth + manhatan();
	}
	
	public int hammingScore()
	{
		return depth + hamming();
	}
	//---------------------------------------
	
	public void setOrder(String order)
	{
		this.order = order;
	}
	
	public void goalState()
	{
		int k = 0;
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j<columns; j++)
			{
				k++;
				goal[i][j] = k;
			}
		}
		
		goal[rows - 1][columns - 1]  = 0;	
	}
	
	public boolean stopCondition()
	{
		for(int i = 0; i< rows; i++)
		{
			for(int j = 0; j<columns; j++)
			{
				if(goal[i][j] != numbers[i][j])
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public int getChilderNumber()
	{
		int counter = 0;
		if(canGoUp() && prevMove != 'D')
			counter++;
		if(canGoLeft() && prevMove != 'R')
			counter++;
		if(canGoDown()&& prevMove != 'U')
			counter++;
		if(canGoRight() && prevMove != 'L')
			counter++;
		
		return counter;	
	}
	
	
	public int[][] copy()
	{
		int [][]array = new int[rows][columns];
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j< columns; j++)
			{
				array[i][j] = getNumbers()[i][j];
			}
		}
		
		return array;
	}
	
	public void setChildern()
	{
		int index = 0;
		for(int i = 0; i<4; i++)
		{
			if(order.charAt(i) == 'L')
			{
				if(canGoLeft() && prevMove != 'R')
				{
					children[index] = new Node(this, copy(), rows, columns, order, 'L', depth + 1);
					children[index].goLeft();
					index++;
				}
			}
			
			if(order.charAt(i) == 'R')
			{
				if(canGoRight() && prevMove != 'L')
				{
					children[index] = new Node(this, copy(), rows, columns, order, 'R', depth + 1);
					children[index].goRight();
					index++;
				}
			}
			if(order.charAt(i) == 'U')
			{
				if(canGoUp() && prevMove != 'D')
				{
					children[index] = new Node(this, copy(), rows, columns, order, 'U', depth + 1);
					children[index].goUp();
					index++;
				}
			}
			if(order.charAt(i) == 'D')
			{
				if(canGoDown() && prevMove != 'U')
				{
					children[index] = new Node(this, copy(), rows, columns, order, 'D', depth + 1);
					children[index].goDown();
					index++;
				}
			}
			
		}	
	}

	public int[] indexOfBlank()
	{
		int index[] = new int[2];
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j< columns; j++)
			{
				if(numbers[i][j] == 0)
				{
					index[0] = i;
					index[1] = j;
					
					return index;
				}
			}
		}
		
		return null;
	}
	
	public void printPuzzle()
	{
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j< columns; j++)
			{
				System.out.print(numbers[i][j]);
			}
			System.out.println();
		}
	}
	
	public int goUp()
	{
		int posX = indexOfBlank()[0];
		int posY = indexOfBlank()[1];
		int temp;
		
		if(canGoUp())
		{
			temp = numbers[posX][posY];
			numbers[posX][posY] = numbers[posX - 1][posY];
			numbers[posX - 1][posY] = temp;
			return 1;
		}
		
		return -1;
	}
	
	public int goLeft()
	{
		int posX = indexOfBlank()[0];
		int posY = indexOfBlank()[1];
		int temp;
		
		if(canGoLeft())
		{
			temp = numbers[posX][posY];
			numbers[posX][posY] = numbers[posX][posY - 1];
			numbers[posX][posY - 1] = temp;
			
			return 1;
		}
		
		return -1;
	}
	
	public int goRight()
	{
		int posX = indexOfBlank()[0];
		int posY = indexOfBlank()[1];
		int temp;
		
		if(canGoRight())
		{
			temp = numbers[posX][posY];
			numbers[posX][posY] = numbers[posX][posY + 1];
			numbers[posX][posY + 1] = temp;
			
			return 1;
		}
		
		return -1;
	}
	
	public int goDown()
	{
		int posX = indexOfBlank()[0];
		int posY = indexOfBlank()[1];
		int temp;
		
		if(canGoDown())
		{
			temp = numbers[posX][posY];
			numbers[posX][posY] = numbers[posX + 1][posY];
			numbers[posX + 1][posY] = temp;
			return 1;
		}
		
		return -1;
	}
	
	public boolean canGoLeft()
	{
		int posY = indexOfBlank()[1];
		
		if(posY - 1 >= 0)
		{
			return true;
		}
		
		return false;	
	}
	
	public boolean canGoRight()
	{
		int posY = indexOfBlank()[1];
		
		if(posY + 1 < columns)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canGoUp()
	{
		int posX = indexOfBlank()[0];

		if(posX - 1 >= 0)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canGoDown()
	{
		int posX = indexOfBlank()[0];

		if(posX + 1 < rows)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean isSame(Node node)
	{
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j<columns; j++)
			{
				if(node.getNumbers()[i][j] != this.numbers[i][j])
					return false;
			}
		}
		
		return true;
	}
	
	public int hamming()
	{
		int expected = 1;
		hammingDistance = 0;
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j< columns; j++)
			{
				if(i == rows - 1 && j == columns - 1)
				{
					expected = 0;
				}
				
				if(numbers[i][j] != expected && numbers[i][j] != 0)
				{
					hammingDistance++;
				}
				
				expected++;
			}
		}
		return hammingDistance;
	}
	
	public int manhatan()
	{
		manhatanDistance = 0;
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j<columns; j++)
			{
				int value = numbers[i][j]; 
	            if (value != 0) 
	            { 
	                manhatanDistance += Math.abs(i - getRow(value)) + Math.abs(j - getColumn(value)); 
	            }
			}
		}
		return manhatanDistance;
	}
	
	
}
