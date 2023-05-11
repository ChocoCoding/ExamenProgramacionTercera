package boletin.ej7;

import boletin.ej4.ej4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner sc;
        int num = 0;
        while (true){
            System.out.println("*************** Opcion 1: Añadir Alumnos ***************");
            System.out.println("*************** Opcion 2: Buscar Alumnos ***************");
            System.out.println("*************** Opcion 3: Modificar Alumnos ***************");
            System.out.println("*************** Opcion 4: Listar Alumnos ***************");
            System.out.println("*************** Opcion 5: Borrar Alumnos ***************");

            sc = new Scanner(System.in);
            System.out.println("Introduce una opcion");
            try {
                num = sc.nextInt();
            }catch (InputMismatchException ex){
                System.out.println("Dato introducido incorrecto");
                menu();
            }


            switch (num){
                case 0:
                    System.out.println("Fin del programa");
                    return;
                case 1:
                    Ej7.addAlumnos();
                    break;
                case 2:
                    Ej7.consultarAlumno();
                    break;
                case 3:
                    Ej7.modificarAlumno();
                    break;
                case 4:
                    Ej7.listarAlumnos();
                    break;
                case 5:
                    Ej7.eliminarAlumno();
                    break;
                default:
                    System.out.println("El numero no coincide con ninguna opcion");
            }
        }
    }


}
