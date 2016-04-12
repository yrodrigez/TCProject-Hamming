import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Principal {

	public static StringBuilder [] getTextoBinario(String path) throws IOException {
		StringBuilder texto = new StringBuilder();
		File archivo = new File (path);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea;

		while((linea=br.readLine())!=null){
			texto.append(linea).append("\n");
		}

		StringBuilder [] palabrasCodigos = new StringBuilder [texto.length()];

		for (int i = 0; i < texto.length(); i++) {
			String letra = Integer.toBinaryString(texto.codePointAt(i));

			while (letra.length() < 11) {
				letra = "0" + letra;
			}

			palabrasCodigos[i] = new StringBuilder();
			palabrasCodigos[i].append(letra);
		}

		return palabrasCodigos;
	}

	public static void matrizToFichero(Matriz mTextoCodificado) {
		try {
			FileWriter fichero = new FileWriter("codificado.txt");
			PrintWriter pw = new PrintWriter(fichero);

			for (int i = 0; i < mTextoCodificado.getX(); i++) {
				for (int j = 0; j < mTextoCodificado.getY(); j++) {
					pw.print(mTextoCodificado.getMatriz()[i][j]);
				}
				pw.println();
			}

			fichero.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public static Matriz ficheroToMatriz(String url) {
		try {
			List<String> todo = new LinkedList<>();
			File archivo = new File(url);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				todo.add(linea);
			}

			if(todo.size() != 0){
				short[][] retorno = new short[todo.size()][todo.get(0).length()];
				int contador=0;

				for(String palabra : todo){
					retorno[contador] = palabraToShort(palabra.split(""));
					contador++;
				}


				return new Matriz(retorno);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public static short [] palabraToShort(String [] palabra){
		short [] toret = new short[palabra.length];

		for (int i = 0; i < palabra.length; i++) {
			toret[i] = Short.parseShort(palabra[i]);
		}

		return toret;
	}

	public static void imprimirMensaje(String[] mensaje){
		StringBuilder sbMensaje = new StringBuilder();
		for(String palabraBinario : mensaje){
			int palabraDecimal = Integer.parseInt(palabraBinario, 2);
			sbMensaje.append((char)palabraDecimal);
		}
		System.out.println(sbMensaje.toString());

	}

	public static void main(String[] args) throws InterruptedException, IOException {

		short generadora[][] = {
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
				{ 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
				{ 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 }
		};

		short paridadTraspuesta[][] = {
				{ 0, 0, 0, 1 },
				{ 0, 0, 1, 0 },
				{ 0, 0, 1, 1 },
				{ 0, 1, 0, 0 },
				{ 0, 1, 0, 1 },
				{ 0, 1, 1, 0 },
				{ 0, 1, 1, 1 },
				{ 1, 0, 0, 0 },
				{ 1, 0, 0, 1 },
				{ 1, 0, 1, 0 },
				{ 1, 0, 1, 1 },
				{ 1, 1, 0, 0 },
				{ 1, 1, 0, 1 },
				{ 1, 1, 1, 0 },
				{ 1, 1, 1, 1 }
		};

		Matriz mParidad = new Matriz(paridadTraspuesta);
		Matriz mGeneradora = new Matriz(generadora);


		Hamming.codificar(getTextoBinario("castellanoPrueba.txt"), mGeneradora);
		Hamming.decodificar(ficheroToMatriz("codificado.txt"), mParidad);
            
	}

}
