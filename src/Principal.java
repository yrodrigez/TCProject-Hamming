import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Principal {

	public static void main(String[] args) throws InterruptedException {
		int GENERADORA[][] = {
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

		int multiplicar[] [] ={
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }
		};

		Matriz m = new Matriz(GENERADORA);

		Matriz multiplicada = m.multiplica(new Matriz(multiplicar));
		System.out.println(multiplicada.toString());
		Thread.sleep(1000);
		multiplicada.trasponer();
		System.out.println(multiplicada.toString());


		/*try {
			StringBuilder texto = new StringBuilder();
			File archivo = new File ("El coran.txt");
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;

			while((linea=br.readLine())!=null){
				texto.append(linea).append("\n");
			}

			StringBuilder[] textoBinario = Util.palabrasCodigos(texto.toString());

			for (StringBuilder s: textoBinario) {
				System.out.println(s);
			}

			Util.codificar(textoBinario);

			Util.decodificar(Util.leerFichero("coran_codificado.txt"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
            
	}

}
