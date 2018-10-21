import java.util.Comparator;

public class HammingNodeCompare implements Comparator<Node>
{
	@Override
	public int compare(Node first, Node second) 
	{
		if(first.hammingScore() > second.hammingScore())
		{
			return 1;
		}
		if(first.hammingScore() < second.hammingScore())
		{
			return -1;
		}
		
		return 0;
	}
	
}
