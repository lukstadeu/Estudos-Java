import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

// Classe que implementa os mecanismos para busca

public class FileSearch {

	private Path arquivoEncontrado;

	public Path search(String nomeArquivo, String dirInicial) throws IOException  {


		// Cria um objeto path com base no diretório inicial
		Path dir = Paths.get(dirInicial);

		// Verifica se o diretório existe, se não existir lança exceção
		if(!Files.exists(dir)) {
			throw new IOException("Erro: Diretório '" + dir + "' não existe!");
		}

		// Instancia o objeto do tipo FileVisitor que contem a lógica para a busca
		Search search = new Search(nomeArquivo);


		// Inicia a busca
		Files.walkFileTree(dir, search);



		// Retorna o arquivo encontrado
		return arquivoEncontrado;
	}

	// Classe FileVistor que é neessária para realizar a busca

	private  class Search implements FileVisitor<Path> {


		private String filename;


		public Search() {


		}

		public Search(String nomeArquivo) {

			this.filename = nomeArquivo;
		}



		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

			// Continua o processo
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

			// Antes de visitar o diretório, exibe o nome do diretório em qual o arquivo irá ser procurado
			System.out.println("Procurando no diretório: " + dir);

			// Continua o processo
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {



			// Verifica se  o arquivo visitado é o arquivo que está sendo buscado
			if(file.getFileName().toString().equalsIgnoreCase(filename)){

				// Caso o arquivo seja encontrado salva a referenfia em 'arquivoEncontado' e encerra o processo 
				arquivoEncontrado = file; 

				return FileVisitResult.TERMINATE;
			}

			// Caso o arquivo não tenha sido encontrado, continua o processo de busca
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

			// Apresenta exceção lançada ao tentar acessar arquivo
			System.out.println("Erro ao acessar arquivo :" + file);
			System.out.println(exc);

			// Continua o processo
			return FileVisitResult.CONTINUE;
		}

	}

}
