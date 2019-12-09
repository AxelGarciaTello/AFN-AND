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

public class Analizador {
    
    private void inicializarAFD(Tabla afn, Tabla afd){
        char nulo=afd.sacarDato(0, 0, 0);
        char caracter;
        int i=0;
        while((caracter=afn.sacarDato(0, 0, i))!=nulo){
            afd.ingresarDato(0, 0, i, caracter);
            i++;
        }
        i=0;
        while((caracter=afn.sacarDato(0, 1, i))!=nulo){
            afd.ingresarDato(0, 1, i, caracter);
            i++;
        }
        i=0;
        while((caracter=afn.sacarDato(0, 2, i))!=nulo){
            afd.ingresarDato(0, 2, i, caracter);
            i++;
        }
    }
    
    private void agregarEstado(Tabla afn, Tabla afd, char[] estado, int filaA,
            int columna){
        int i=0, lugar, j, a=0, b=0, c=0, bandera=0;
        char nulo=afd.sacarDato(14, 14, 14);
        char caracter;
        char[] salida=new char[15];
        for(i=0;i<15;i++){
            salida[i]='*';
        }
        char[] filaAFN=new char[15];
        char[] fila=salida;
        for(i=0;i<15;i++){
            lugar=estado[i]-'0';
            if(lugar>=0 && lugar<15){
                //System.out.println("Estado\n"+lugar);
                for(j=0;j<15;j++){
                    caracter=afn.sacarDato(lugar, columna, j);
                    filaAFN[j]=caracter;
                }
                fila=salida;
                //System.out.println(fila[0]);
                //System.out.println(filaAFN[0]);
                bandera=0;
                a=0;
                b=0;
                c=0;
                while(bandera==0){
                    //System.out.println("Entra");
                    //System.out.println(fila[0]);
                    if(fila[b]==nulo){
                        //System.out.println("fila vacia");
                        while(filaAFN[a]!=nulo && a<15 && c<15){
                            salida[c]=filaAFN[a];
                            a++;
                            c++;
                            bandera=1;
                        }
                    }
                    else if(filaAFN[a]==nulo){
                        //System.out.println("no se ingresa nada");
                        while(fila[b]!=nulo && b<15 && c<15){
                            //System.out.println(c+" "+b);
                            salida[c]=fila[b];
                            b++;
                            c++;
                            bandera=1;
                        }
                    }
                    else if(filaAFN[a]==fila[b]){
                        //System.out.println("iguales");
                        salida[c]=fila[b];
                        a++;
                        b++;
                        c++;
                    }
                    else if(filaAFN[a]<fila[b]){
                        //System.out.println("gana sistema");
                        salida[c]=filaAFN[a];
                        a++;
                        c++;
                    }
                    else if(filaAFN[a]>fila[b]){
                        //System.out.println("gana ingresado");
                        salida[c]=fila[b];
                        b++;
                        c++;
                    }
                    if(a>=14 || b>=14 || c>=14){
                        bandera=1;
                    }
                }
                //bandera=0;
            }
        }
        //System.out.println("Nuevo");
        for(i=0;i<15;i++){
            //System.out.println(filaAFN[i]);
            afd.ingresarDato(filaA,columna, i,salida[i]);
        }
        //System.out.println("Systema");
        for(i=0;i<15;i++){
            //System.out.println(fila[i]);
            afd.ingresarDato(filaA, 0, i, estado[i]);
        }
    }
    
    private int analizar(Tabla afn, Tabla afd,int fila, int columna){
        int i=0, j=0, bandera=0;
        char estado, direccion;
        char nulo=afd.sacarDato(14, 14, 14);
        char[] tablaEstado=new char[15];
        for(j=0;j<15;j++){
            for(i=0;i<15;i++){
                estado=afd.sacarDato(j, 0, i);
                direccion=afd.sacarDato(fila, columna, i);
                if(estado!=direccion){
                    i=16;
                    bandera=0;
                }
                else{
                    bandera=1;
                }
            }
            if(bandera==1){
                j=16;
            }
        }
        if(bandera==0){
            for(i=0;i<15;i++){
                tablaEstado[i]=afd.sacarDato(fila, columna, i);
                //System.out.println(tablaEstado[i]);
            }
            for(i=0;i<15;i++){
                estado=afd.sacarDato(i,0,0);
                if(estado==nulo){
                    j=i;
                    i=16;
                }
            }
            //System.out.println(j+" "+columna+" "+tablaEstado[1]+":"+tablaEstado[2]);
            agregarEstado(afn, afd, tablaEstado, j, 1);
            agregarEstado(afn,afd,tablaEstado,j,2);
        }
        return bandera;
    }
    
    public void convertir(Tabla afn, Tabla afd){
        int bandera1=0, bandera2=0, fila=0;
        inicializarAFD(afn, afd);
        while((bandera1==0 || bandera2==0) && fila<15){
            bandera1=analizar(afn,afd,fila,1);
            //System.out.println(bandera1);
            bandera2=analizar(afn,afd,fila,2);
            fila++;
            //System.out.println(bandera2);
        }
    }
}
