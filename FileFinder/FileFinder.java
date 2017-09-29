import java.io.IOException;
import java.nio.file.Path;

public class FileFinder {

	public static void main(String[] args) throws IOException  {
		
		// Valida parametros passados por linha de comando
		if(args.length < 2) {
			System.out.println("Erro nos par�metros \n Usage: NomeArquivoBuscado DiretorioIncial");
			return;
		}
		
		String nomeArquivo = args[0];
		String dirIncial = args[1];

		// Instancia objeto respons�vel pela busca
		FileSearch fs = new FileSearch();
		
		fs.search(nomeArquivo,dirIncial);

		try {
			// Realiza a busca e retorna a refer�ncia com o caminho do arquivo
			Path p = fs.search(nomeArquivo, dirIncial);
			
			if(p != null) 
				System.out.println("Arquivo encontado em: "+ p);
			else 
				System.out.println("Arquivo '"+ nomeArquivo+"' n�o encontrado...");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	

	}
}