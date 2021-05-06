package def;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AlgGraf {
	
	public static String filename; 
	public static void main(String args[]) {
		if(args.length !=1) {
			System.out.println("So eh possivel entrar com o caminho do arquivo");
			System.exit(-1) ;
		}else {
		filename = args[0];
		}
		Grafo grafo;
		try {
			/*
			 *	Nessa parte o arquivo de entrada eh lido e manipulado para a informacao do grafo ser utilizada de forma correta
			 */
			File file = new File(filename);   			 	// cria nova instancia de arquivo
			FileReader fr = new FileReader(file);  		 	// leitura do arquivo
			BufferedReader br = new BufferedReader(fr); 	// cria um buffer de caracteres
			String line; 
			try {
				// Aqui primeiramente eh feito uma contagem do numero de linhas no arquivo, para ser utilizado como referencia depois na criacao grafo utilizado no algoritmo
				BufferedReader contador = new BufferedReader(new FileReader(filename));
				int n = 0;
				while((line = contador.readLine()) != null) n++;
				grafo = new Grafo(n);
				contador.close();
				
				// Nessa parte eh feita a leitura de cada linha do arquivo de entrada
				while((line = br.readLine()) != null) {
					String[] lineList= line.split(" = ");						   	// aqui a entrada eh dividida em duas, no caso utilizando o sinal de '=' como referencia para dividir
					
					int v = Integer.parseInt( (lineList[0].replaceAll(" ", "")) ); 	/* aqui sao eliminados todos os espacos da primeira metade da linha (no caso o que estava antes do '='),
																					 * para obter só o número do vertice e armazena em v */
	
					String[] adjList= lineList[1].split(" ");						/* aqui a segunda parte da entrada (que estava depois de '=') é dividida em varias,
																					 * no caso utilizando os espaços " " como referencia para dividir, pegando o numero de cada vertice */ 
					/*
					 * Aqui cada vertice V (do lado esquerdo do sinal de '=') eh relacionado com seus respectivos vertices W (que estavam do lado direito do sinal de '=')  
					 * Essa associação eh feita por meio do metodo adicionaAresta
					 * Depois da associacao eh feito um print de cada v
					 * */
					for(String wstr : adjList) {
						int w = Integer.parseInt(wstr);
						grafo.adicionaAresta(v-1, w-1); 
					}
				}
				// fecha o leitor
				br.close(); 	
				
				
				// Aqui o teste eh rodado 
				grafo.teste();
				
			} catch (IOException e) {
				System.out.println("ERRO--IOException");	// Erro na formatacao do arquivo
				e.printStackTrace();
			}	
		} catch (FileNotFoundException e) {
			System.out.println("ERRO--FileNotFound");		// Erro caso o arquivo nao seja encontrado
			e.printStackTrace();
		    }		
	}
	
}
