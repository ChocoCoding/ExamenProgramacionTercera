package boletin.ej7;

import java.io.*;
import java.util.Scanner;

/**
 * 7. Se desea implementar un programa para gestionar la información de los alumnos de un
 * colegio.
 * Entre los datos a almacenar de cada alumno está el nombre, apellidos y edad.
 * a. Se permitirá añadir nuevos alumnos, consultar modificar y eliminar datos de un
 * alumno en una posición determinada en el fichero.
 * b. La información de cada alta, baja o modificación debe ser persistente, lo que implica
 * que debe realizarse sobre el fichero.
 * c. La aplicación deberá permitir obtener un listado de los alumnos, en el orden en el
 * que fueron dados de alta.
 */

public class Ej7 {
    static Scanner sc;
    static File file = new File(".\\src\\boletin\\files\\alumnos.txt");
    static File temp = new File(".\\src\\boletin\\files\\temp.txt");
    public static void addAlumnos(){
        crearFichero(file);
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del alumno");
        String nombre = sc.next();
        System.out.println("Introduce el apellidos del alumno");
        String apellidos = sc.next();
        sc = new Scanner(System.in);
        System.out.println("Introduce el edad del alumno");
        int edad = sc.nextInt();

        Alumno alumno = new Alumno(nombre,apellidos,edad);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file,true))) {
            oos.writeObject(alumno);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consultarAlumno(){
        crearFichero(file);

        System.out.println("Introduce un nombre del alumno: ");
        sc = new Scanner(System.in);
        int cont = 0;
        String nombre = sc.next();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Alumno alumno;
            while (true){
                alumno = (Alumno) ois.readObject();
                if (nombre.equalsIgnoreCase(alumno.getNombre())){
                    System.out.println(alumno);
                    cont++;
                }

            }

        }catch (StreamCorruptedException e){
            System.out.println("Excepcion de mierda");
        }
        catch (EOFException e) {
            System.out.println("Fin del archivo");
            if (cont == 0){
                System.out.println("No hay alumnos registrados");
            }
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void modificarAlumno(){
        crearFichero(temp);
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del alumno que quieres modificar: ");
        String nombre = sc.nextLine();
        int cont = 0;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream(temp,true))) {

            Alumno alumno;
            while (true){
                alumno = (Alumno) ois.readObject();
                if (alumno.getNombre().equalsIgnoreCase(nombre)){
                    System.out.println("Nuevo nombre");
                    alumno.setNombre(sc.nextLine());
                    System.out.println("Nuevo apellido");
                    alumno.setApellidos(sc.nextLine());
                    System.out.println("Nueva edad");
                    alumno.setEdad(sc.nextInt());
                    sc = new Scanner(System.in);
                    System.out.println(alumno.getNombre() + " " + alumno.getApellidos() + " " + alumno.getEdad());
                    toos.writeObject(alumno);

                }else {
                    toos.writeObject(alumno);
                    cont++;
                }
            }
        }catch (EOFException e){
            System.out.println("Fin del archivo");
            file.delete();
            temp.renameTo(file);
            if (cont == 0){
                System.out.println("No hay alumnos registrados");
            }
        }catch (StreamCorruptedException e){
            System.out.println("Excepcion de mierda");
            file.delete();
            temp.renameTo(file);
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public static void listarAlumnos(){
        crearFichero(file);
        int cont = 0;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Alumno alumno;
            while (true){
                alumno = (Alumno) ois.readObject();
                System.out.println(alumno);
                cont++;
            }

        }catch (StreamCorruptedException e){
            System.out.println("Excepcion de mierda");
        }
        catch (EOFException e) {
            System.out.println("Fin del archivo");
            if (cont == 0){
                System.out.println("No hay alumnos registrados");
            }
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void eliminarAlumno(){
        crearFichero(temp);
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del alumno que quieres Eliminar: ");
        String nombre = sc.nextLine();
        int cont = 0;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream(temp,true))) {

            Alumno alumno;
            while (true){
                alumno = (Alumno) ois.readObject();
                if (!alumno.getNombre().equalsIgnoreCase(nombre)){
                    toos.writeObject(alumno);
                }else System.out.println("Se ha eliminado el alumno: " + alumno);
            }
        }catch (EOFException e){
            System.out.println("Fin del archivo");
            file.delete();
            temp.renameTo(file);
            if (cont == 0){
                System.out.println("No hay alumnos registrados");
            }
        }catch (StreamCorruptedException e){
            System.out.println("Excepcion de mierda");
            file.delete();
            temp.renameTo(file);
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    private static void crearFichero(File file){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
