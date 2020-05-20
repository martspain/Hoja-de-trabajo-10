import java.util.ArrayList;

class Digraph{
	
	String name;
	ArrayList<Vertex> nodes = new ArrayList<Vertex>();
	
	public Digraph(String id){
		name = id;
	}
	
	public void addNode(Vertex point){
		nodes.add(point);
	}
	
	public Vertex getNode(String point){
		Vertex result = null;
		for(int i = 0; i<nodes.size(); i++){
			if(point.equalsIgnoreCase(nodes.get(i).getName())){
				result = nodes.get(i);
			}
		}
		return result;
	}
	
	public boolean deleteNode(String point){
		boolean completed = false;
		for(int i = 0; i<nodes.size(); i++){
			if(point.equalsIgnoreCase(nodes.get(i).getName())){
				nodes.remove(i);
				completed = true;
			}
		}
		return completed;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Vertex> getList(){
		return nodes;
	}
	
}