import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Principal {

	public static void main(String[] args) throws InterruptedException, IOException {
		short GENERADORA[][] = {
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


		Matriz mGeneradora = new Matriz(GENERADORA);

		short PARIDAD_TRASPUESTA[][] = {
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

		Matriz mParidad = new Matriz(PARIDAD_TRASPUESTA);

		StringBuilder texto = new StringBuilder();
		File archivo = new File ("coran_red.txt");
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea;

		while((linea=br.readLine())!=null){
			texto.append(linea).append("\n");
		}
		StringBuilder[] textoBinario = Hamming.palabrasCodigos(texto.toString());
		Hamming.codificar(textoBinario, mGeneradora);
		Hamming.decodificar(Hamming.leerFichero("Quijote.txt"), mParidad);
/*
		short [][] a = {
				{1,0,0},
				{3,4,2}
		};
		short [][] b = {
				{2,1},
				{0,3},
				{1,0}
		};

		Matriz A = new Matriz(a);
		Matriz B = new Matriz(b);

		Matriz res = A.multiplica(B);
		System.out.println(res.toString());*/
		/*try {



			for (StringBuilder s: textoBinario) {
				System.out.println(s);
			}

			Hamming.codificar(textoBinario);

			Hamming.decodificar(Hamming.leerFichero("coran_codificado.txt"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
            
	}

}
