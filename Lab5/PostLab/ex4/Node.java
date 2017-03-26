package ex4;

class Node <T>
{
	public Integer keyM;
	public T itemM;
	Node <T> nextM;
	
	public Node()
	{
		keyM = null;
		itemM=  null; 
		nextM  = null;
	
	}
	public Node (T itemA, Integer keyA, Node <T> nextA)
	
	{
		itemM= itemA ;
		keyM = keyA;
		nextM = nextA;
	}

}
