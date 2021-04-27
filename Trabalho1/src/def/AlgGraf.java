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
			System.out.println("Só é possivel entrar com o caminho do arquivo");
		System.exit(-1) ;
	}else {
		filename = args[0];
	}
	Grafo grafo;
	try {
		File file = new File(filename);   			 	//creates a new file instance  
		FileReader fr = new FileReader(file);  		 	//reads the file  
		BufferedReader br = new BufferedReader(fr); 	//creates a buffering character input stream  
		String line; 
		try {
			BufferedReader contador = new BufferedReader(new FileReader(filename));
			int n = 0;
			while((line = contador.readLine()) != null) n++;
			grafo = new Grafo(n);
			contador.close();
			System.out.println("n : "+n );
			while((line = br.readLine()) != null) {
				String[] lineList= line.split(" = ");
				int v = Integer.parseInt( (lineList[0].replaceAll(" ", "")) );
				String[] adjList= lineList[1].split(" ");
				for(String wstr : adjList) {
					int w = Integer.parseInt(wstr);
					grafo.adicionaAresta(v-1, w-1); 
					System.out.println("v: "+ v + " w: "+ w);
				}
			}
			br.close();
			grafo.teste();	
		} catch (IOException e) {
			System.out.println("ERRO--IOException");
			e.printStackTrace();
		}	
	} catch (FileNotFoundException e) {
		System.out.println("ERRO--FileNotFound");
		e.printStackTrace();
    }	
			
	}
	
}
