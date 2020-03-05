/* Assignment 3 by: Immanuel Prince and Prishni Lamichhane 
 * Immanuel Prince - Student #: 251033914
 * Prishni Lamichhane - Student #: 251034179 
 */
import java.util.*; 

public class Graph {

    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<>();
        tmp.add(b);
        adjacencyMap.put(a,tmp);
    }

    public void addEdge(Node source, Node destination) {

        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);
        addEdgeHelper(destination, source);
        
    }
    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }
    
    public void resetNodesVisited()
    {
    	 for (Node n : adjacencyMap.keySet()) {
             n.unvisit();
         }
    
    }
    
    void BFS(Node node) {
    	//implement the BFS code
    	Queue<Node> q  = new LinkedList<Node>();
    	q.add(node);
    	node.visit();
    	System.out.print(node.name + " ");
    	while (q.isEmpty() == false) { 
    		Node v = q.remove();
    		for (int i = 0; i < adjacencyMap.get(v).size(); i++) {
    		Node w = adjacencyMap.get(v).get(i);
    		if(w.isVisited() == false) {
    			q.add(w);
    			w.visit();
    			System.out.print(w.name + " ");
    		}
    	}
    }
    	System.out.println();
}
    	
   public void DFS(Node node) {
     //Implement DFS
		   if(node.isVisited() == false) {
	   			node.visit();
	   			System.out.print(node.name + " ");
	   			}
	   
		   for(int i = 0; i < adjacencyMap.get(node).size(); i++) {
			   Node w = adjacencyMap.get(node).get(i);
			   if (w.isVisited() == false) {
				   DFS(w);
			   }
   		}
   }
   
   public void printEdges() {
	 //implement printEdges
	   for (Node node : adjacencyMap.keySet()) {
				System.out.print("The " + node.name + " has an edge towards: ");
				for (int j = 0; j < adjacencyMap.get(node).size(); j++) {
					Node neighbour = adjacencyMap.get(node).get(j);
					System.out.print(neighbour.name + " ");
				}
				System.out.println();
			}
		}
   
}