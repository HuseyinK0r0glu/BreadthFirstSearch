import java.util.*;

public class BreadthFirstSearch <T>{

    private final Vertex<T> startVertex;

    public BreadthFirstSearch(Vertex<T> startVertex) {
        this.startVertex = startVertex;
    }

    public List<T> findShortestPath(Vertex<T> endVertex){

        if(startVertex == null){
            return Collections.emptyList();
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, Vertex<T>> parentMap = new HashMap<>();  // Track parents for path reconstruction

        startVertex.setVisited(true);
        queue.add(startVertex);
        parentMap.put(startVertex,null);

        while(!queue.isEmpty()){

            Vertex<T> current = queue.poll();

            if(current.equals(endVertex)){
                return reconstructPath(parentMap,endVertex);
            }

            for(Vertex<T> neighbor : current.getNeighbours()){
                if(!neighbor.isVisited()){
                    neighbor.setVisited(true);
                    parentMap.put(neighbor,current);
                    queue.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<T> reconstructPath(Map<Vertex<T>, Vertex<T>> parentMap,Vertex<T> endVertex){
        List<T> path = new LinkedList<>();
        for(Vertex<T> at = endVertex;at!=null;at=parentMap.get(at)){
            path.add(at.getData());
        }

        Collections.reverse(path);
        return path;
    }
}
