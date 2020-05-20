class Edge{
	
	private int distance;
	private Vertex destination;
	
	public Edge(Vertex destiny, int kilometers){
		distance = kilometers;
		destination = destiny;
	}
	
	public int getDistance(){
		return distance;
	}
	
	public Vertex getDestination(){
		return destination;
	}
	
}