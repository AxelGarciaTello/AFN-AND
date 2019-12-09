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

import java.io.IOException;

public class Programa {
    private Tabla afn;
    private Tabla afd;
    private Archivo archivo;
    private Analizador analizador;
    
    public void iniciar() throws IOException{
        char caracter;
        afn=new Tabla();
        afd=new Tabla();
        afn.crearTabla();
        afd.crearTabla();
        archivo=new Archivo();
        analizador=new Analizador();
        archivo.crearTabla(afn);
        analizador.convertir(afn, afd);
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                for(int k=0;k<15;k++){
                    caracter=afd.sacarDato(i, j, k);
                    if(caracter!='*'){
                        System.out.print(caracter+",");
                    }
                }
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
}
