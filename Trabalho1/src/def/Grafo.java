package def;

import java.util.*;
import java.util.LinkedList;

// Essa classe representa um grafo nao direcionado, usando representacao de lista de adjacencia
class Grafo
{
	 private int V;   // Variavel respresentante do numero de vertices
	
	 // Array de listas para representação da lista de adjacencias
	 private LinkedList<Integer> lista_de_adjacencia[];
	
	 // Construtor
	 Grafo(int v)
	 {
	     V = v;
	     lista_de_adjacencia = new LinkedList[v];
	     for (int i=0; i<v; ++i)
	         lista_de_adjacencia[i] = new LinkedList();
	 }
	
	 // Funcao para adicionar aresta no grafo
	 void adicionaAresta(int v, int w)
	 {
	     lista_de_adjacencia[v].add(w);	// Adiciona vertice w na lista do vertice v
	 }
	
	 // Funcao usada para busca em profundidade
	 void buscaEmProfundidade(int v,boolean visitado[])
	 {
	 // Marca vertice como visitado
	 visitado[v] = true;
	 System.out.println(v+1);
	 
	 // Recorre para todos os vertices adjacentes a esse vertice 
	     Iterator<Integer> i = lista_de_adjacencia[v].listIterator();
	     while (i.hasNext())
	     {
	         int n = i.next();
	         if (!visitado[n])
	             buscaEmProfundidade(n, visitado);
	     }
	 }
	 /*
	  *	Metodo para checar se todos os vertices com grau diferente de zero sao conectados. Basicamente faz uma busca em profundidade.
	  */
	 boolean ehConexo()
	 {
		 // Marca todos os vertices como nao-visitados
		 boolean visitado[] = new boolean[V];
		 int i;
		 for (i = 0; i < V; i++)
		     visitado[i] = false;
		
		 // Acha um vertice com grau diferente de zero
		 for (i = 0; i < V; i++)
		     if (lista_de_adjacencia[i].size() != 0)
		         break;
		
		 // Se nao tem arestas no grafo, retorna true
		 if (i == V) {
		     return true;
		 }
		 // Faz uma busca em profundidade com vertice de grau diferente de zero
		 buscaEmProfundidade(i, visitado);
		 
		// Checa se todos os vertices de grau diferente de zero sao visitados
	 	for (i = 0; i < V; i++) {
	 		if (visitado[i] == false && lista_de_adjacencia[i].size() > 0) {
	 			return false;
	 		}
	 	}
	 	return true;
	 }

	 /* 
	  * A funcao retorna 0 caso :
	  *		- O grafo nao seja conexo
	  *		- O numero de vertices de grau impar seja maior que 2
	  *	A funcao retorna 1 caso :
	  *		- Número de vértices ímpares for igual a 2
	  *	A funcao retorna 2 caso:
	  *		- Numero de vertices de grau impar for zero
	  */
	 int ehEuleriano()
	    {
		 	// Checa se todos os vertices de grau diferente de zero sao conexos, caso nao sejam, o grafo nao eh euleriano
	        if (ehConexo() == false) {
	            return 0;
	        }
	        // Conta o numero de vertices de grau impar
	        int impar= 0;
	        for (int i = 0; i < V; i++) {
	            if (lista_de_adjacencia[i].size()%2!=0)
	                impar++;
	        }
	        // Aqui eh mostrado o numero de vertices impares
	        System.out.println("Quantidade de vertices de grau impar: " + impar);
	        
	        // Caso o número de vertices de grau impar for maior que 3 o grafo nao eh euleriano
	        if (impar > 2) {
	            return 0;
	        }
	        // Caso o numero de vertices impares for igual a dois, existe um caminho euleriano, e 
	        // caso o numero de vertices impares for igual a zero, existe um circuito euleriano
	        return (impar==2)? 1 : 2;
	    }
	  
	 	// Função para rodar os casos de teste
	    void teste()
	    {
	        int resposta = ehEuleriano();
	        if (resposta == 0)
	            System.out.println("O grafo não é euleriano");
	        else if (resposta == 1)
	            System.out.println("O grafo tem um caminho euleriano, mas nao um circuito");
	        else
	           System.out.println("O grafo tem um circuito euleriano");
	    }
	 
}

