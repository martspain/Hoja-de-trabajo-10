import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

class Controller{
	
	Digraph graph = new Digraph("Digrafo");
	Matrix matt = new Matrix();
	ArrayList<Vertex> list;
	ArrayList<Edge> collection;
	boolean active;
	Scanner screen = new Scanner(System.in);
	
	public void runProgram(){
		active = true;
		this.readFile();
		list = graph.getList();
		
		while(active){
			System.out.println("\nMenu de opciones \n1. Mostrar grafo \n2. Agregar ruta \n3. Buscar ruta \n4. Salir \n");
			int input = screen.nextInt();
			
			if(input == 1){
				this.showDigraph();
			}
			else if(input == 2){
				this.askInfo();
			}
			else if(input == 3){
				this.search();
			}
			else if(input == 4){
				System.out.println("Gracias por usar el programa... \n");
				active = false;
			}
			else{
				System.out.println("\nPorfavor ingrese una opcion valida \n");
			}
			
			list = graph.getList(); //Se actualiza la lista de rutas
		}
	}
	
	private void readFile(){
		File file = new File("guategrafo.txt");
		int lineCounter = 0;
		try{
			Scanner reader = new Scanner(file);
			
			while(reader.hasNext()){
				//Contador para saber en que linea fallo en caso de una excepcion
				lineCounter += 1;
				
				//Se lee la siguiente linea y se separa donde haya espacios
				String line = reader.nextLine();
				String[] words = line.split(" ");
				
				//Se crea el vertice con su respectiva direccion hacia otro vertice
				Vertex newOne = new Vertex(words[0]);
				Vertex destiny = new Vertex(words[1]);
				Edge newLine = new Edge(destiny, Integer.parseInt(words[2]));
				newOne.addEdge(newLine);
				
				//Se agrega el vertice a la matriz
				matt.addNode(newOne);
				matt.addNode(destiny);
				
				//Se agrega el vertice al grafo
				graph.addNode(newOne);
				
			}
		}
		catch(Exception e){
			System.out.println("\n*** Hay un error de sintaxis en el archivo en la linea " + lineCounter + " *** \n");
		}
	}
	
	private void showDigraph(){
		System.out.println("\n-----------------------------------------------------------------------");
		for(int i = 0; i<list.size(); i++){
			
			collection = list.get(i).getCollection();
			
			for(int a = 0; a<collection.size(); a++){
				System.out.println("Origen: " + list.get(i).getName() + " --> Destino: " + collection.get(a).getDestination().getName() +
				" --> La distancia mas corta es: " + matt.getShortestDistance(list.get(i)) + " KM. ");
			}
		}
		System.out.println("----------------------------------------------------------------------- \n");
		
	}
	
	public void askInfo(){
		boolean done = false;
		while(!done){
			try{
				System.out.println("\nIngrese el nombre del origen de la ruta (omita los espacios, por ejemplo: SanJuan)... ");
				String answer = screen.next();
				Vertex origin = new Vertex(answer);
				screen.nextLine(); //Se limpia el buffer
				
				System.out.println("\nIngrese el nombre del destino de la ruta (omita los espacios, por ejemplo: SanJuan)... ");
				answer = screen.next();
				Vertex destiny = new Vertex(answer);
				screen.nextLine(); //Se limpia el buffer
				
				System.out.println("\nIngrese la distancia de la ruta...");
				int distance = screen.nextInt();
				screen.nextLine(); //Se limpia el buffer
				
				//Se crea la ruta
				Edge route = new Edge(destiny, distance);
				origin.addEdge(route);
				
				//Se agregan los vertices a la matriz
				matt.addNode(origin);
				matt.addNode(destiny);
				
				//Se agrega la ruta al grafo
				graph.addNode(origin);
				
				done = true;
			}
			catch(Exception e){
				System.out.println("Error: porfavor ingrese la informacion correctamente...");
				done = true;
			}
		}
	}
	
	public void search(){
		boolean done = false;
		while(!done){
			System.out.println("\nIngrese el nombre del origen de la ruta...");
			String origin = screen.next();
			screen.nextLine();
			
			System.out.println("\nIngrese el nombre del destino de la ruta...");
			String destiny = screen.next();
			screen.nextLine();
			
			String response = "";
			
			Vertex temporal = null;
			boolean existence = false;
			
			for(int i = 0;i<list.size();i++){
				
				if(list.get(i).getName().equalsIgnoreCase(origin)){
					response += "\nEl origen \"" + origin + "\" ";
					collection = list.get(i).getCollection();
					boolean exists = false;
					existence = true;
					
					for(int a =0; a<collection.size(); a++){
						if(collection.get(a).getDestination().getName().equalsIgnoreCase(destiny)){
							response += "va hacia el destino \"" + destiny + "\" a una distancia de " + collection.get(a).getDistance() + "KM \n";
							exists = true;
						}
					}
					
					if(!exists){
						response += "no tiene un ruta existente hacia \"" + destiny + "\" \n";
					}
				}
				
				if(i == (list.size()-1) && !existence){
					response += "\nEl origen \"" + origin + "\" no existe \n";
				}
			}
			
			System.out.println(response);
			done = true;
		}
	}
}