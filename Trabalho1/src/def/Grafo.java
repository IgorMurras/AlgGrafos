package def;

import java.util.*;
import java.util.LinkedList;

// Essa classe representa um grafo nao direcionado, usando representacao de lista de adjacencia
class Grafo
{
 private int V;   // Variavel respresentante do numero de vertices

 // Array  of lists for Adjacency List Representation
 private LinkedList<Integer> lista_de_adjacencia[];

 // Constructor
 Grafo(int v)
 {
     V = v;
     lista_de_adjacencia = new LinkedList[v];
     for (int i=0; i<v; ++i)
         lista_de_adjacencia[i] = new LinkedList();
 }

 //Function to add an edge into the graph
 void adicionaAresta(int v, int w)
 {
     lista_de_adjacencia[v].add(w);// Add w to v's list.
 }

 // A function used by DFS
 void buscaEmProfundidade(int v,boolean visitado[])
 {
     // Mark the current node as visited
     visitado[v] = true;
     System.out.println(v+1);
     // Recur for all the vertices adjacent to this vertex
     Iterator<Integer> i = lista_de_adjacencia[v].listIterator();
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
         if (lista_de_adjacencia[i].size() != 0)
             break;

     // If there are no edges in the graph, return true
     if (i == V)
         return true;

     // Start DFS traversal from a vertex with non-zero degree
     buscaEmProfundidade(i, visitado);

     // Check if all non-zero degree vertices are visited
     for (i = 0; i < V; i++)
        if (visitado[i] == false && lista_de_adjacencia[i].size() > 0)
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
     for (int i = 0; i < V; i++) {
    	 System.out.println(lista_de_adjacencia[i]);
         if (lista_de_adjacencia[i].size()%2!=0) {
       
             impar++;
         }
     }
     
     // 
     System.out.println("Quantidade de vertices impares: " + impar);
     if(impar==0 ) {
    	 return 1;
     }
     else 
    	 return 0;
 }

 // Function to run test cases
 void teste()
 {
	 System.out.println("Numero de vertices V: " + V);
     int res = ehEuleriano();
     if (res == 1)
    	 System.out.println("Há circuito euleriano");
     else
    	 System.out.println("Não há circuito euleriano");
   
 }

 
}

