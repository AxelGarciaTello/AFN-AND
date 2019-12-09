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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Archivo {
    private Scanner teclado;
    private FileReader fp;
    private BufferedReader caracter;
    private String textoCompleto="";
    private void leerArchivo() throws FileNotFoundException, IOException{
        teclado=new Scanner(System.in);
        String archivo,
               texto;
        System.out.println("¿Cómo se llama su archivo?");
        archivo=teclado.nextLine();
        fp=new FileReader(archivo);
        caracter=new BufferedReader(fp);
        texto=caracter.readLine();
        while(texto!=null){
            textoCompleto+=texto;
            texto=caracter.readLine();
        }
        caracter.close();
        fp.close();
    }
    
    public void crearTabla(Tabla afn) throws IOException{
        leerArchivo();
        int contador=0,
            tamanio,
            aceptacion=0,
            fila=-1,
            columna=-1,
            volumen=0;
        char dato;
        tamanio=textoCompleto.length();
        while(textoCompleto.charAt(contador)!=';'){
            if(textoCompleto.charAt(contador)=='q'){
                contador++;
                dato=textoCompleto.charAt(contador);
                afn.ingresarAceptacion(aceptacion, dato);
                aceptacion++;
            }
            contador++;
        }
        while(contador<tamanio){
            if(textoCompleto.charAt(contador)==','){
                volumen++;
            }
            else if(textoCompleto.charAt(contador)=='|'){
                columna++;
                volumen=0;
            }
            else if(textoCompleto.charAt(contador)==';'){
                fila++;
                columna=0;
                volumen=0;
            }
            else if(textoCompleto.charAt(contador)=='q'){
                contador++;
                dato=textoCompleto.charAt(contador);
                afn.ingresarDato(fila, columna, volumen, dato);
            }
            contador++;
        }
    }
    
}
