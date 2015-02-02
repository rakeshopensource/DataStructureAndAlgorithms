package rakeshopensource.algorithms.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Graph3<T> {

  public T[] vertexList = null;
  public Map<T, List<T>> adjList = null;
  public Map<T, Boolean> visited = null;

  public Graph3(T[] vertex) {
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

  }

  public Graph3<T> getTranspose() {
    Graph3<T> transposedGraph = new Graph3<T>(vertexList);
    for (T vertex : adjList.keySet()) {
      List<T> edgeList = getEdge(vertex);
      for (T entry : edgeList) {
        List<T> list = transposedGraph.adjList.get(entry);
        list.add(vertex);
        transposedGraph.adjList.put(entry, transposedGraph.adjList.get(entry));
      }
    }
    return transposedGraph;

  }

  /* Returns the List containing the vertex joining the source vertex */
  public List<T> getEdge(T source) {
    if (!adjList.containsKey(source)) {
      System.out.println("the vertex entered is not present");
      return null;
    }
    return adjList.get(source);
  }

  public void dfs(T source) {

    // System.out.print(source + " ");

    visited.put(source, true);
    List<T> list = adjList.get(source);
    if (list != null) {
      for (T vertex : list) {
        if (!visited.get(vertex)) {
          dfs(vertex);

        }
      }
    }
  }

  public boolean isStronglyConnected(T source) {
    boolean isConnected = true;
    dfs(source);
    Graph3<T> g1 = getTranspose();
    System.out.println("Adj. List of Transpose Graph");
    g1.printAdjList();
    g1.dfs(source);

    for (T vertex : vertexList) {
      isConnected = isConnected & visited.get(vertex);
    }

    for (T vertex : g1.vertexList) {
      isConnected = isConnected & g1.visited.get(vertex);
    }

    return isConnected;
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

public class StronglyConnectedGraph {

  public static void main(String[] args) {

    Graph3<Integer> graphA = new Graph3<Integer>(new Integer[] { 0, 1, 2, 3, 4 });
    graphA.addEdge(0, 1);
    graphA.addEdge(1, 2);
    graphA.addEdge(2, 4);
    graphA.addEdge(4, 2);
    graphA.addEdge(2, 3);
    graphA.addEdge(3, 0);

    graphA.printAdjList();

    System.out.println("Graph is Strongly Connected : " + graphA.isStronglyConnected(0));

    Graph3<Integer> graphB = new Graph3<Integer>(new Integer[] { 0, 1, 2, 3, 4 });
    graphB.addEdge(0, 1);
    graphB.addEdge(1, 2);
    graphB.addEdge(2, 3);
    graphB.addEdge(3, 4);

    graphB.printAdjList();

    System.out.println("Graph is Strongly Connected : " + graphB.isStronglyConnected(0));

  }
}
