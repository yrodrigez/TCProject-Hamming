import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Util {
    /**
     * Matriz gneradora de la pagina 46
     */
	private static short GENERADORA[][] = {
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

    /**
     * Paridad traspuesta de la pagina ejemplo 3.1.4
     */

	private static short PARIDAD_TRASPUESTA[][] = {
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

    private static final int WORD_LENGTH = GENERADORA[0].length;

    /**
     * @param texto
     * @return array de strings con todas las letras del texto en binario
     */

	public static StringBuilder [] palabrasCodigos(String texto) {
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

	public static void codificar (StringBuilder [] texto) {
		short [][] textoCodificado = new short [texto.length] [GENERADORA[0].length];

		for (int i = 0; i < texto.length ; i++) {
            /**
             * Multiplicamos cada bit de la palabra casteada a shrots por la matriz generadora
             */
            textoCodificado[i] = multiplicar(palabraToShort(texto[i].toString().split("")), GENERADORA);
		}

		escribir(textoCodificado);
	}

    public static short [] palabraToShort(String [] palabra){
        short [] toret = new short[palabra.length];

        for (int i = 0; i < palabra.length; i++) {
            toret[i] = Short.parseShort(palabra[i]);
        }

        return toret;
    }

	private static short[] multiplicar(short [] palabra, short [][] matriz) {
		short [] retorno = new short[matriz[0].length];

		for (int i = 0; i < matriz[0].length; i++) {
			int suma = 0;

			for (int j = 0; j < palabra.length; j++) {
				suma += palabra[j] * matriz[j][i];
			}

			if (suma % 2 == 0)
				retorno[i] = 0;
			else
				retorno[i] = 1;
		}

		return retorno;
	}

	private static void mostrar(short[] palabra) {
		for (int i = 0; i < palabra.length; i++) {
			System.out.print(palabra[i]);
		}
		System.out.println();
	}

	private static void escribir(short[][] textoCodificado) {

		try {
            FileWriter fichero = new FileWriter("coran_codificado.txt");
			PrintWriter pw = new PrintWriter(fichero);

			for (int i = 0; i < textoCodificado.length; i++) {

				for (int j = 0; j < textoCodificado[i].length; j++) {
					pw.print(textoCodificado[i][j]);
				}

				pw.println();
			}

            fichero.close();
		} catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
		}

	}

	public static short[][] leerFichero(String url) {
		try {
			List <String> todo = new LinkedList<>();
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

				return retorno;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
            e.printStackTrace();
		}

		return null;
	}

	public static void decodificar(short[][] palabras) {
		for (int i = 0; i < palabras.length; i++) {
			short [] resultadoMultiplicacion = multiplicar(palabras[i], PARIDAD_TRASPUESTA);
			int posError = hayError(resultadoMultiplicacion);
			if (posError != 0) {
				invertir(posError, palabras[i]);
				System.err.println("Encontrado un error en la posicion: "+posError);
			}
		}
		String [] coran = quitarParidad(palabras);
		imprimirMensaje(coran);

	}

	private static int hayError(short[] palabra) {
		int suma = 0;
		int exponente = palabra.length - 1;

        //Este bucle pasa un valor binario a base diez para comprobar si es distinto de 0
		for (int i = 0; i < palabra.length; i++) {
			suma += Math.pow(2, exponente) * palabra[i];
			exponente--;
		}

		return suma;
	}

	private static void invertir(int posicion, short[] palabraErronea) {
		if (palabraErronea[posicion-1] == 0)
			palabraErronea[posicion-1] = 1;
		else
			palabraErronea[posicion-1] = 0;
	}


	private static String[] quitarParidad(short[][] palabras){

		String[] retorno=new String[palabras.length];

		for (int i = 0; i < palabras.length; i++) {
			String palabra="";
			for(int j = 0; j < palabras[i].length; j++){
                //Si estamos en una posicion que no es de paridad
				if(j!=0 && j!=1 && j!=3 && j!=7){
					palabra+=palabras[i][j];
				}
			}
			retorno[i]=palabra;
		}
		return retorno;
	}

	private static void imprimirMensaje(String[] mensaje){
		for(String palabraBinario : mensaje){
			int palabraDecimal = Integer.parseInt(palabraBinario, 2);
			System.out.print((char)palabraDecimal);
		}
	}
}
