package boletin.ej1;

import java.io.*;
import java.util.Scanner;

public final class Ej1 {
    static File file = new File(".\\src\\boletin\\files\\datos.txt");

    static Scanner sc = new Scanner(System.in);

    public static void escribirFichero(){
        String[] personas = {"Juan","Maria","Paco"};
        isCreated(file);
        //Escribimos el archivo
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            String linea;
            int cont = 0;
            for (String s: personas) {
                dos.writeUTF(s);
            }
        //Leemos el archivo
            while (true){
               cont++;
               linea = dis.readUTF();
               System.out.println(cont + "." + linea);
            }

        }catch (EOFException e){
            System.err.println("Fin del fichero");
        }
        catch (FileNotFoundException e) {
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
