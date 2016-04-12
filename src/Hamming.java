class Hamming {

	static void codificar (StringBuilder [] texto, Matriz mGeneradora) {
		short [][] textoCodificado = new short [texto.length][mGeneradora.getY()];
		Matriz mTextoCodificado = new Matriz(textoCodificado);

		for (int i = 0; i < mTextoCodificado.getX(); i++){
			mTextoCodificado.getMatriz()[i] = Principal.palabraToShort(texto[i].toString().split(""));
		}
		mTextoCodificado = mTextoCodificado.getMultiplicacion(mGeneradora);

		Principal.matrizToFichero(mTextoCodificado);
	}


	static void decodificar(Matriz palabras, Matriz mParidadTraspuesta) {
		Matriz mResultadoMultiplicacion = palabras.getMultiplicacion(mParidadTraspuesta);
		for (int i = 0 ; i < mResultadoMultiplicacion.getX() ; i++) {
			int posError = comprobarPalabra(mResultadoMultiplicacion.getMatriz()[i]);
			if (posError != 0) {
				corregirError(posError, palabras.getMatriz()[i]);
				System.err.println("Encontrado un error en la posicion: " + posError);
			}
		}
		String [] tDecodificado = quitarParidad(palabras);
		Principal.imprimirMensaje(tDecodificado);

	}

	static int comprobarPalabra(short[] palabra) {
		int suma = 0;
		int exponente = palabra.length - 1;

        //Este bucle pasa un valor binario a base diez para comprobar si es distinto de 0
		for (int i = 0; i < palabra.length; i++) {
			suma += Math.pow(2, exponente) * palabra[i];
			exponente--;
		}

		return suma;
	}

	static void corregirError(int posicion, short[] palabraErronea) {
		palabraErronea[posicion-1] = palabraErronea[posicion-1] == 0 ? (short) 1 : (short) 0;
	}


	static String[] quitarParidad(Matriz palabras){

		String[] retorno=new String[palabras.getX()];

		for (int i = 0; i < palabras.getX(); i++) {
			String palabra="";
			for(int j = 0; j < palabras.getY(); j++){
                //Si estamos en una posicion que no es de paridad
				if(j!=0 && j!=1 && j!=3 && j!=7){
					palabra+=palabras.getMatriz()[i][j];
				}
			}
			retorno[i]=palabra;
		}
		return retorno;
	}
}
