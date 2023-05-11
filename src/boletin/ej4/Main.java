package boletin.ej4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc;
        int num;
        do {
            System.out.println("*************** Opcion 1: Añadir datos ***************");
            System.out.println("*************** Opcion 2: Listar datos ***************");
            System.out.println("*************** Opcion 3: Buscar datos ***************");
            System.out.println("*************** Opcion 4: Borrar datos ***************");

            sc = new Scanner(System.in);
            num = sc.nextInt();

            switch (num){
                case 0:
                    System.out.println("Fin del programa");
                    break;
                case 1:
                    ej4.addRegistros();
                    break;
                case 2:
                    ej4.listarRegistros();
                    break;
                case 3:
                    ej4.buscarRegistro();
                    break;
                case 4:
                    ej4.borrarRegistro();
                    break;
                default:
                    System.out.println("El numero no coincide con ninguna opcion");
            }
        }while (num != 0);
    }
}
