
public class Node 
{
	public Node previous;
	Node []children;
	String order;
	int [][]numbers;
	int [][]goal;
	int rows;
	int columns;
	int childrenNumber;	
	int maxDeepth = 10000000;
	public int deepth;
	public String prevMove;
	
	public Node(Node previous, int[][] numbers, int rows, int columns, String order, int deepth, String move)
	{
		this.previous = previous;
		this.numbers = numbers;
		this.rows = rows;
		this.columns = columns;
		this.order = order;
		this.deepth = deepth;
		
		this.childrenNumber = getChilderNumber();
		this.children = new Node[4];
		this.prevMove = move;
		//if(deepth < 2)
		//	setChildern();
		
	//	printPuzzle();
		
		goal = new int[rows][columns];
		goalState();
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
		if(canGoUp() && prevMove != "D")
			counter++;
		if(canGoLeft() && prevMove != "R")
			counter++;
		if(canGoDown()&& prevMove != "U")
			counter++;
		if(canGoRight() && prevMove != "L")
			counter++;
		
		return counter;	
	}
	
	
	public Node[] getChildren()
	{
		return children;
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
		order.toUpperCase();
		if(deepth < maxDeepth)
		{
		if(order == "LRUD")
		{
			int index = 0;
			if(canGoLeft() && prevMove != "R")
			{
				children[index] = new Node(this, copy(), rows, columns, order, deepth + 1, "L");
				children[index].goLeft();
			//	children[index].setChildern();
				index++;
			}
			if(canGoRight() && prevMove != "L")
			{
				children[index] = new Node(this, copy(), rows, columns, order, deepth + 1, "R");
				children[index].goRight();
				//children[index].setChildern();
				index++;
			}
			if(canGoUp() && prevMove != "D")
			{
				children[index] = new Node(this, copy(), rows, columns, order, deepth + 1, "U");
				children[index].goUp();
				//children[index].setChildern();
				index++;
			}
			if(canGoDown() && prevMove != "U")
			{
				children[index] = new Node(this, copy(), rows, columns, order, deepth + 1, "D");
				children[index].goDown();
				//children[index].setChildern();
				index++;
			}

		}
		else if(order == "RLUD")
		{
			
		}
		else if(order == "ULRD")
		{
			
		}
		else if(order == "LURD")
		{
			
		}
		else if(order == "RULD")
		{
			
		}
		else if(order == "URLD")
		{
			
		}
		else if(order == "URDL")
		{
			
		}
		else if(order == "RUDL")
		{
			
		}
		else if(order == "DURL")
		{
			
		}
		else if(order == "UDRL")
		{
			
		}
		else if(order == "RDUL")
		{
			
		}
		else if(order == "DRUL")
		{
			
		}
		else if(order == "LDUR")
		{
			
		}
		else if(order == "UDLR")
		{
			
		}
		else if(order == "DULR")
		{
			
		}
		else if(order == "LUDR")
		{
			
		}
		else if(order == "ULDR")
		{
			
		}
		else if(order == "RLDU")
		{
			
		}
		else if(order == "LRDU")
		{
			
		}
		else if(order == "DRLU")
		{
			
		}
		else if(order == "RDLU")
		{
			
		}
		else if(order == "LDRU")
		{
			
		}
		else if(order == "DLRU")
		{
			
		}
		}
		
		
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
	
	public int  goUp()
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
	
}
