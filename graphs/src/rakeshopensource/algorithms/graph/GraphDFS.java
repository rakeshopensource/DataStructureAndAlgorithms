package rakeshopensource.algorithms.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Graph1<T> {

  public T[] vertexList = null;
  public Map<T, List<T>> adjList = null;
  public Map<T, Boolean> visited = null;

  public Graph1(T[] vertex) {
    this.vertexList = vertex;
    adjList = new HashMap<T, List<T>>();
    visited = new HashMap<T, Boolean>();
    for (int i = 0; i < vertex.length; i++) {
      adjList.put(vertex[i], new LinkedList<T>());
      visited.put(vertex[i], false);
    }
  }

  /* Adds nodes in the Adjacency list for the corresponding vertex */
  public void addEdge(T source, T destination) {
    List<T> slist = adjList.get(source);
    slist.add(destination);
    List<T> dlist = adjList.get(destination);
    dlist.add(source);
  }

  /* Returns the List containing the vertex joining the source vertex */
  public List<T> getEdge(T source) {
    if (!adjList.containsKey(source)) {
      System.out.println("the vertex entered is not present");
      return null;
    }
    return adjList.get(source);
  }

  /* Print Adjacency List */
  public void printAdjList() {
    for (int i = 0; i < vertexList.length; i++) {
      System.out.print(vertexList[i] + "->");
      List<T> edgeList = getEdge(vertexList[i]);
      int listSize = edgeList.size();
      for (int j = 0; j < listSize; j++) {
        System.out.print(j == listSize - 1 ? edgeList.get(j) : edgeList.get(j) + "->");
      }
      System.out.println();
    }
  }

}

public class GraphDFS {

  public <T> void dfs(Graph1<T> g, T source) {

    System.out.println(source);
    g.visited.put(source, true);
    List<T> list = g.adjList.get(source);
    if (list != null) {
      for (T vertex : list) {
        if (!g.visited.get(vertex)) {
          dfs(g, vertex);

        }
      }
    }

  }

  public static void main(String[] args) {
    GraphDFS graphBfs = new GraphDFS();
    Graph1<Integer> g = new Graph1<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);
    g.addEdge(3, 5);
    g.addEdge(4, 6);
    g.addEdge(4, 7);
    g.addEdge(5, 8);
    g.addEdge(5, 9);
    g.printAdjList();

    System.out.println("---------DFS-----------------");
    graphBfs.dfs(g, 1);
  }

}
