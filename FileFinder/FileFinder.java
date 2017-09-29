import java.io.IOException;
import java.nio.file.Path;

public class FileFinder {

	public static void main(String[] args) throws IOException  {
		
		// Valida parametros passados por linha de comando
		if(args.length < 2) {
			System.out.println("Erro nos parâmetros \n Usage: NomeArquivoBuscado DiretorioIncial");
			return;
		}
		
		String nomeArquivo = args[0];
		String dirIncial = args[1];

		// Instancia objeto responsável pela busca
		FileSearch fs = new FileSearch();
		
		fs.search(nomeArquivo,dirIncial);

		try {
			// Realiza a busca e retorna a referência com o caminho do arquivo
			Path p = fs.search(nomeArquivo, dirIncial);
			
			if(p != null) 
				System.out.println("Arquivo encontado em: "+ p);
			else 
				System.out.println("Arquivo '"+ nomeArquivo+"' não encontrado...");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	

	}
}