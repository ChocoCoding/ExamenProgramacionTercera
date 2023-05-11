package boletin.ej6;

import java.io.*;

public class Ej6 {
    static File fic = new File(".\\src\\boletin\\files\\datos_mayus.txt");
    static File fic2 = new File (".\\src\\boletin\\files\\datos_mayus2.txt");



    public static void copiarMayusculas(){
        crearArchivo(fic);
        crearArchivo(fic2);

        try(FileReader fr = new FileReader(fic);
        BufferedReader br = new BufferedReader(fr);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(fic2))) {

            //FileWriter fw = new FileWriter(fic2);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(linea.toUpperCase())) {
                    dos.writeUTF(linea+ "\n");
                    System.out.println(linea);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


       /*
        try(DataInputStream dis = new DataInputStream(new FileInputStream(fic));
            DataOutputStream dosArchivoTemp = new DataOutputStream(new FileOutputStream(fic2))) {
                String linea;
                int s ;
                while (true){
                    linea = "";
                    while ((s = dis.read()) != 10 && s != -1) {
                        linea += (char)s;
                        System.out.println(linea);
                    }
                        if (linea.equals(linea.toUpperCase())){
                            dosArchivoTemp.writeUTF(linea);
                            System.out.println(linea);
                    }
                }

        }catch (EOFException e){
            System.err.println("Fin del archivo");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

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


    public static void main(String[] args) {
        Ej6.copiarMayusculas();
    }
}
