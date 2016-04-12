public class Matriz {
    /*          X  Y    */
    private short [] []  matriz;

    public Matriz(short[][] matriz){
        this.matriz = matriz;
    }

    public Matriz(int x, int y) {
       matriz = new short[x][y];
    }

    public Matriz trasponer() {
        Matriz traspuesta = new Matriz(getY(), getX());

        for (int i = 0 ; i < this.getX() ; i++){
            for(int j = 0 ; j < this.getY() ; j++){
                traspuesta.getMatriz()[j][i] = this.matriz[i][j];
            }

        }

        return traspuesta;
    }

    public int getX(){
        try {
            return this.matriz.length;
        }catch (NullPointerException ex) {
            System.err.println("La matriz es nula");
            ex.printStackTrace();
            return 0;
        }
    }

    public int getY(){
        try{
            return this.matriz[0].length;
        }catch (NullPointerException ex) {
            System.err.println("La matriz es nula");
            ex.printStackTrace();
            return 0;
        }
    }

    public String toString(){
        String ret = String.format("%d x %d \n", this.getX(), this.getY());
        for(int i = 0 ; i< this.getX() ; i++){
            ret+= "[ ";
            for (int j= 0 ; j < this.getY() ; j++){
               ret+= this.getMatriz()[i][j]+", ";
            }
            ret+="]\n";
        }
        return ret;
    }

    public Matriz multiplica(Matriz multiplicar) {
        if(this.getY() != multiplicar.getX()) {
            throw new ArithmeticException("No se puede multiplicar una matriz Generadora: " + this.getX() + "x" + this.getY()
                    + " por otra multiplicar: " + multiplicar.getX() + "x" + multiplicar.getY());
        }

        Matriz retorno = new Matriz(
                this.getX()
                , multiplicar.getY()
        );

        multiplicar = multiplicar.trasponer();

        for (int i = 0; i < retorno.getX() ; i++) {
            for(int j=0 ; j < retorno.getY(); j++){
                retorno.getMatriz()[i][j] = multiplicarVectores(
                        this.getMatriz()[i],
                        multiplicar.getMatriz()[j]
                );
            }

        }
        retorno.binarize();
        return retorno;
    }

    private short multiplicarVectores(short [] v1, short [] v2){
        if(v1.length != v2.length) {
            throw new ArithmeticException("Vectores de diferente longitud. "+v1.length+"x"+v2.length);
        }
        short suma = 0;
        for (int i = 0; i < v1.length; i++){
            suma += v1[i] * v2[i];
        }

        return suma;
    }

    public void binarize(){
        for(int i = 0; i < this.matriz.length; i++){
            for(int j = 0; j < this.matriz[0].length; j++){
                this.matriz[i][j] = (
                        this.matriz[i][j] % 2 == 0 ? (short)0 : (short)1
                        );
            }
        }
    }

    public short[][] getMatriz(){
        return this.matriz;
    }


}
