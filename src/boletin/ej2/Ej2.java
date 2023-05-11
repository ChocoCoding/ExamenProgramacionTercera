package boletin.ej2;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Ej2 {
    static File file = new File(".\\src\\boletin\\files\\productos.txt");
    static File file2 = new File(".\\src\\boletin\\files\\temp.txt");
    static Scanner sc;
    public static void addToArray(){
        isCreated(file2);
        String[] productos = {"Cereales","Leche","Yogur"};
        Double[] precios = {2.0,5.0,9.0};

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
             DataInputStream dis = new DataInputStream(new FileInputStream(file))){

            //Añadimos los productos y precios al archivo
            for (int x = 0; x< productos.length; x++){
                dos.writeUTF(productos[x] + " " + precios[x]);
            }
            String linea;
            while(true){
                linea = dis.readUTF();
                System.out.println(linea);
            }

        }catch (EOFException e){
            System.out.println("Fin del fichero");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void eliminarDelFichero(){
        String nombreProducto;
        isCreated(file2);
        try(DataOutputStream escritorArchivoTemporal = new DataOutputStream(new FileOutputStream(file2));
            DataInputStream lectorArchivoOriginal = new DataInputStream(new FileInputStream(file))){
            System.out.println("Introduce el nombre del producto: ");
            sc = new Scanner(System.in);
            nombreProducto = sc.next();
            String linea;
            while (true){
                linea = lectorArchivoOriginal.readUTF();
                if (linea.split(" ")[0].equalsIgnoreCase(nombreProducto)){
                    System.out.println(linea);
                }else escritorArchivoTemporal.writeUTF(linea);
            }


        }catch (EOFException e){
            System.out.println("Fin del fichero");
            //Eliminar Fichero y renombrarlo
            file.delete();
            file2.renameTo(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void isCreated(File file){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
