import java.util.Collection;
import java.util.Iterator;

public class Matriz {
    /*          X  Y    */
    private int [] []  matriz;

    public Matriz(int[][] matriz){
        this.matriz = matriz;
    }

    public Matriz(int x, int y) {
       this.matriz = new int[x][y];
    }

    public void trasponer() {
        Matriz retorno = new Matriz(this.getY(),this.getX());

        for(int i= 0; i<retorno.getX() ; i++){
            for(int j = 0; j<retorno.getY(); j++){
                retorno.getMatriz()[i][j] = this.matriz[j][i];
            }
        }

        this.matriz = retorno.getMatriz();
    }

    public int getX(){
        try {
            return this.matriz[0].length;
        }catch (NullPointerException ex) {
            System.err.println("La matriz es nula");
            ex.printStackTrace();
            return 0;
        }
    }

    public int getY(){
        try{
            return this.matriz.length;
        }catch (NullPointerException ex) {
            System.err.println("La matriz esta completa");
            ex.printStackTrace();
            return 0;
        }
    }

    public String toString(){
        return String.format("%d x %d", this.getX(), this.getY());
    }

    public Matriz multiplica(Matriz multiplicar) {
        if(this.getY() != multiplicar.getX()) {
            throw new ArithmeticException("No se puede multiplicar una matriz " + this.getX() + "x" + this.getY()
                    + " por otra " + multiplicar.getX() + "x" + multiplicar.getY());
        }

        System.out.println("Creando matriz de "+this.getX()+"x"+multiplicar.getY());

        Matriz retorno = new Matriz(
                this.getX()
                , multiplicar.getY()
        );

/*
        for (int i = 0; i < matriz[0].length; i++) {
            int suma = 0;

            for (int j = 0; j < multiplicar.getMatriz().length; j++) {
                suma += palabra[j] * matriz[j][i];
            }

        }
        */
        retorno.binarize();
        return retorno;
    }

    public void binarize(){
        for(int i = 0; i < this.matriz.length; i++){
            for(int j = 0; j < this.matriz[0].length; j++){
                this.matriz[i][j] = (
                        this.matriz[i][j] % 2 == 0 ? 0 : 1
                        );
            }
        }
    }

    public int[][] getMatriz(){
        return this.getMatriz();
    }


}
