package rakeshopensource.algorithms.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Graph2<T> {

  public T[] vertexList = null;
  public Map<T, List<T>> adjList = null;
  public Map<T, Boolean> visited = null;

  public Graph2(T[] vertex) {
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

public class GraphTopSort {

  public <T> void topologicalSort(Graph2<T> g, T source) {

    System.out.println(source);
    g.visited.put(source, true);
    List<T> list = g.adjList.get(source);
    if (list != null && list.size() > 0) {
      for (T vertex : list) {
        if (!g.visited.get(vertex)) {
          topologicalSort(g, vertex);

        }
      }
    }

  }

  public static void main(String[] args) {
    GraphTopSort graphTopSort = new GraphTopSort();
    Graph2<Integer> g = new Graph2<Integer>(new Integer[] { 5, 1, 2, 3, 4, 0 });
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 1);
    g.addEdge(4, 0);
    g.addEdge(4, 1);

    g.printAdjList();

    for (Integer vertex : g.vertexList) {
      if (!g.visited.get(vertex)) {
        graphTopSort.topologicalSort(g, vertex);
      }
    }

  }
}
