package def;

import java.util.*;
import java.util.LinkedList;

// Essa classe representa um grafo nao direcionado, usando representacao de lista de adjacencia
class Grafo
{
 private int V;   // Variavel respresentante do numero de vertices

 // Array  of lists for Adjacency List Representation
 private LinkedList<Integer> adjacente[];

 // Constructor
 Grafo(int v)
 {
     V = v;
     adjacente = new LinkedList[v];
     for (int i=0; i<v; ++i)
         adjacente[i] = new LinkedList();
 }

 //Function to add an edge into the graph
 void adicionaAresta(int v, int w)
 {
     adjacente[v].add(w);// Add w to v's list.
     adjacente[w].add(v); //The graph is undirected
 }

 // A function used by DFS
 void buscaEmProfundidade(int v,boolean visitado[])
 {
     // Mark the current node as visited
     visitado[v] = true;
     System.out.println(v+1);
     // Recur for all the vertices adjacent to this vertex
     Iterator<Integer> i = adjacente[v].listIterator();
     while (i.hasNext())
     {
         int n = i.next();
         if (!visitado[n])
             buscaEmProfundidade(n, visitado);
     }
 }

 // Method to check if all non-zero degree vertices are
 // connected. It mainly does DFS traversal starting from
 boolean ehConexo()
 {
     // Mark all the vertices as not visited
     boolean visitado[] = new boolean[V];
     int i;
     for (i = 0; i < V; i++)
         visitado[i] = false;

     // Find a vertex with non-zero degree
     for (i = 0; i < V; i++)
         if (adjacente[i].size() != 0)
             break;

     // If there are no edges in the graph, return true
     if (i == V)
         return true;

     // Start DFS traversal from a vertex with non-zero degree
     buscaEmProfundidade(i, visitado);

     // Check if all non-zero degree vertices are visited
     for (i = 0; i < V; i++)
        if (visitado[i] == false && adjacente[i].size() > 0)
             return false;

     return true;
 }

 /* The function returns one of the following values
    0 --> If grpah is not Eulerian
    1 --> If graph has an Euler path (Semi-Eulerian)
    2 --> If graph has an Euler Circuit (Eulerian)  */
 int ehEuleriano()
 {
     // Check if all non-zero degree vertices are connected
     if (ehConexo() == false)
         return 0;

     // Count vertices with odd degree
     int impar = 0;
     for (int i = 0; i < V; i++)
         if (adjacente[i].size()%2!=0)
             impar++;

     // If count is more than 2, then graph is not Eulerian
     if (impar > 2)
         return 0;

     // If odd count is 2, then semi-eulerian.
     // If odd count is 0, then eulerian
     // Note that odd count can never be 1 for undirected graph
     return (impar==2)? 1 : 2;
 }

 // Function to run test cases
 void teste()
 {
	 System.out.println("Numero de vertices V: " + V);
     int res = ehEuleriano();
     if (res == 0)
         System.out.println("O grafo não é euleriano");
     else if (res == 1)
         System.out.println("O grafo tem um caminho euleriano");
     else
        System.out.println("O grafo tem um circuito euleriano");
 }

 
}

