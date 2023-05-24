package ejercicioExamen2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * . Desarrolla un programa que: (3 puntos)
 * Para cada elemento de un array con nombres de provincias españolas, solicite al usuario el código postal asociado
 * y almacenar dicho contenido en un fichero, donde cada línea constará
 * de nombre de provincia y código postal.
 * Se deberá comprobar que los datos leídos de teclado sean correctos y lanzar una excepción en los siguientes casos:
 * En caso de introducir un valor negativo.
 * En caso de que el código postal no empiece por alguno de los valores comprendidos entre 01 y 52.
 * En caso de que el código postal posea una longitud distinta de 5 caracteres. Para ello, se creará una clase para
 * cada excepción y se lanzará la que corresponda en cada
 * caso.
 * En caso de que se produzca alguna de estas circunstancias, se deberá volver a solicitar el dato.
 * b. Conste de un método que liste los elementos del fichero
 */
public class main {


    public static void main(String[] args) {
        cargarfichero();
        leerfichero();
    }

    //Crea un fichero y lo carga con 4 CP's
    public static void cargarfichero() {

        File archivo = new File("cps.txt");
        Scanner sc = new Scanner(System.in);

        //Creo 2 arrays de 4 posiciones y se recorre un bucle 4 veces para introducir los datos
        ArrayList<String> provincias = new ArrayList<>();
        ArrayList<Integer> codigos_postales = new ArrayList<>();

        //Rellena el array teniendo en cuenta las excepciones
        for (int i = 0; i < 4; i++) {
            System.out.println("Introduce el Nombre de la provincia");
            String provincia = new Scanner(System.in).nextLine();
            boolean correcto = false;


            while (!correcto) {
                try {
                    System.out.println("Introduce el codigo postal: ");
                    int codigo_postal = new Scanner(System.in).nextInt();
                    comprobaciones(codigo_postal);

                    correcto = true;
                    provincias.add(provincia);
                    codigos_postales.add(codigo_postal);

                } catch (Valor_Negativo | ValoresIncomprendidos | longitud_erronea ex) {
                    correcto = false;

                    System.out.println("Error al introducir el codigo postal");
                    System.out.println(ex.getMessage());
                }

            }

        }

        //Escribe la informacion en el fichero
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo));) {


            for (int i = 0; i < provincias.size(); i++) {
                dos.writeUTF(provincias.get(i) + " " + codigos_postales.get(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void comprobaciones(int cp) {
        //Compruebo que se cumplas las siguientes condiciones:
        if (cp < 0) {
            throw new Valor_Negativo("El CP no puede ser negativo");
        }
        if (cp < 01 && cp > 52000) {
            throw new ValoresIncomprendidos("Los dos primeros numeros solo pueden estar comprendidos entre 0 y 52");
        }
        String codigo = String.valueOf(cp);
        if (codigo.length() != 5) {
            throw new longitud_erronea("La longitud debe de ser de 5 caracteres");
        }

    }

    public static void leerfichero() {
        System.out.println("Leyendo archivo...");

        //Se genera un DataInputStream para leer el contenido del fichero
        File archivo = new File("cps.txt");

        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivo))) {
            int i = 1;
            while (dis.available() != 0) {
                System.out.println("Provincia " + i);
                System.out.println(dis.readUTF());
                System.out.println("");
                i++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
