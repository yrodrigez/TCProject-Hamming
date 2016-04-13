import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Principal {

	public static void menu(Matriz mGeneradora, Matriz mParidad){
		int o = 0;
		System.out.println("Opciones:");
		System.out.println("0. Salir.");
		System.out.println("1. Codificar archivo de texto.");
		System.out.println("2. Decodificar archivo de texto.");
		System.out.println("¿Qué quieres hacer?: ");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			o = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.err.println("Error leyendo la opción...");
			System.err.println("No es un número");
			o = 65536;
		}
		switch (o){
			case 1:
				System.out.print("Introduce la ruta del archivo a cofificar: ");
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String ruta = (br.readLine());
					System.out.println("Codificando...");
					Hamming.codificar(getTextoBinario(ruta), mGeneradora);
					System.out.println("Codificado. Nombre de archivo \"codificado.txt\"");
				} catch (Exception e) {
					System.err.println("Error leyendo archivo a codificar...");
					System.err.println("No existe el fichero introducido...");
					if(e.getClass() == NullPointerException.class){
						break;
					}
				}

				break;
			case 2:
				System.out.print("Introduce la ruta del archivo a decofificar: ");
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String ruta = (br.readLine());
					System.out.println("Decodificando...");
					Hamming.decodificar(ficheroToMatriz(ruta), mParidad);
					System.out.println("Decodificado. Nombre de archivo \"decodificado.txt\"");
				} catch (Exception e) {
					System.err.println("Error leyendo archivo a codificar...");
					System.err.println("No existe el fichero introducido");
					if(e.getClass() == NullPointerException.class){
						break;
					}
				}
				break;
			case 0:
				System.out.println("Programa finalizado...");
				System.exit(0);
				break;
			case 65536:
				// caso cuando se produce una excepción escribiendo algo que no sea un número.
				break;
			default:
				System.err.println("Opción \""+o+"\" no definida");
				break;
		}
	}

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

	public static void imprimirMensaje(String[] mensaje) throws Exception {
		StringBuilder sbMensaje = new StringBuilder();
		for(String palabraBinario : mensaje){
			int palabraDecimal = Integer.parseInt(palabraBinario, 2);
			sbMensaje.append((char)palabraDecimal);
		}
		System.out.println(sbMensaje.toString());
	}

	public static void main(String[] args) throws Exception {

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

		System.out.println("*******************************************");
		System.out.println("**         Codificador - Decodificador   **");
		System.out.println("**            Hamming Binario            **");
		System.out.println("*******************************************");
		System.out.println("*******************************************");
		System.out.println("**  Autores: José Meilán Maldonado       **");
		System.out.println("**           Yago Rodríguez Lorenzo      **");
		System.out.println("*******************************************");

		while (true){
			Principal.menu(mGeneradora, mParidad);
		}

	}

}
