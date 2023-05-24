import java.io.File;
import java.util.Scanner;

/**
 * Implementa un programa que reciba el nombre de un directorio y
 * los 4 primeros caracteres por los que deben comenzar un grupo de ficheros (por ejemplo "PRO_").
 * Para cada uno de los archivos contenidos en dicho directorio cuyo nombre comience por
 * la cadena indicada, mostrará lo siguiente: (2 puntos)
 *
 * a. Sus nombres completos
 *
 * b. Si se trata de un fichero o un directorio
 *
 * C. Si tienen permisos de escritura y de lectura
 *
 * d. Su tamaño en KB..
 */

public class Ejercicio1 {
        static Scanner sc;
        public static void buscarArchivos(){
            sc = new Scanner(System.in);
            System.out.println("Introduce la ruta de un directorio");
            String ruta = sc.nextLine();
            System.out.println("Introduce los 4 primeros caracteres de ese archivo: ");
            String comienzo = sc.nextLine();
            File file = new File(ruta);

            if (file.isDirectory()){
                File[] listaArchivos = file.listFiles();
                for (File f : listaArchivos) {
                    if (f.getName().startsWith(comienzo)){
                        System.out.println("Archivo: ");
                        System.out.println(f);
                        if (f.isDirectory()){
                            System.out.println("Es un directorio");
                        }else if (f.isFile()){
                            System.out.println("Es un archivo");
                        }
                        if (f.canRead()){
                            System.out.println("Tiene permisos de lectura");
                        }
                        if (f.canWrite()){
                            System.out.println("Tiene permisos de escritura");
                        }
                        System.out.println("Tamaño del archivo en KB: "+ f.length() / 1000 + "KB");
                    }else System.out.println("No se encontraron archivos que comiencen por: " + comienzo);

                }
            }
        }

    }

