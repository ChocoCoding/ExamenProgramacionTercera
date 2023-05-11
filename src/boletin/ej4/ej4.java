package boletin.ej4;

import java.io.*;
import java.util.Scanner;

public class ej4 {
    static Scanner sc;
    static File file = new File(".\\src\\boletin\\files\\registros.txt");
    static File temp = new File(".\\src\\boletin\\files\\temp.txt");

    public static void borrarRegistro(){
        crearArchivo(temp);
        sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file));
        DataOutputStream dosArchivoTemp = new DataOutputStream(new FileOutputStream(temp))){
            String linea;
            while (true){
                linea = dis.readUTF();
                if (!linea.split(" ")[0].equalsIgnoreCase(nombre)){
                    dosArchivoTemp.writeUTF(linea);
                    return;
                }
            }

        }catch (EOFException e){
            System.out.println("Fin del archivo");
            file.delete();
            temp.renameTo(file);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void buscarRegistro(){
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre que quieres buscar: ");
        String nombre = sc.nextLine();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){
            String linea;
            while (true){
                linea = dis.readUTF();
                if (linea.split(" ")[0].equalsIgnoreCase(nombre)){
                    System.out.println(linea);
                    return;
                }
            }
        }catch (EOFException e){
            System.out.println("Fin del archivo.");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addRegistros(){
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre: ");
        String nombre = sc.next();
        System.out.println("Introduce el direccion: ");
        String direccion =sc.next();
        System.out.println("Introduce el telefono: ");
        String telefono =sc.next();

        crearArchivo(file);
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file,true))) {
            dos.writeUTF(nombre + " " + direccion + " " + telefono);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarRegistros(){
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){
            String linea;
            while (true){
                linea = dis.readUTF();
                System.out.println(linea);
            }
        }catch (EOFException e){
            System.out.println("Fin del archivo");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void crearArchivo(File file){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
