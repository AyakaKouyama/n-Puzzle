import java.util.Comparator;

public class ManhatanNodeCompare implements Comparator<Node>
{
	@Override
	public int compare(Node first, Node second) 
	{
		if(first.manhatanScore() > second.manhatanScore())
		{
			return 1;
		}
		if(first.manhatanScore() < second.manhatanScore())
		{
			return -1;
		}
		
		return 0;
	}
}
