/*
 * Instituto Politecnico Nacional
 * Escuela Superior de Computo
 * Teoria computacional
 * Grupo 2CV1
 * Alumno: García Tello Axel
 * Profesor: Benjamin Luna Benoso
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * De AFN a AFD
 *
 * Fecha: 5 de septiembre de 2019
*/

public class Tabla {
    private char[][][] tabla;
    private char[] estadosAceptacion;
    
    public void crearTabla(){
        tabla=new char[15][15][15];
        estadosAceptacion=new char[15];
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                for(int k=0;k<15;k++){
                    tabla[i][j][k]='*';
                }
            }
        }
    }
    
    public void ingresarDato(int fila, int columna, int volumen, char dato){
        tabla[fila][columna][volumen]=dato;
    }
    
    public char sacarDato(int fila, int columna, int volumen){
        return tabla[fila][columna][volumen];
    }
    
    public void ingresarAceptacion(int lugar, char dato){
        estadosAceptacion[lugar]=dato;
    }
    
    public char sacarAceptacion(int lugar){
        return estadosAceptacion[lugar];
    }
}
