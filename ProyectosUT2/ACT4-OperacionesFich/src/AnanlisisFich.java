import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnanlisisFich {
    public static void main(String[] args) {
        //Variable para el conteo del numero de lineas, caractaeres y palabras
        int numLineas = 0;
        int numPalabras = 0;
        int numCaracteres = 0;

        //Primera lectura del fichero
        try (FileReader reader = new FileReader("seguridad.txt");
             BufferedReader bufferedReader = new BufferedReader(reader);) {
            String linea;
            System.out.println("Contenido del fichero antes de modificarlo: ");
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error: no se puede leer el fichero" + e.getMessage());
        }

        //Añadimos las lineas solicitadas
        try (FileWriter writer = new FileWriter("seguridad.txt", true)) {
            writer.write("[INFO] Nuevo intento de inicio de sesión \n");
            writer.write("[WARNING] Contraseña incorrecta introducida 3 veces");
        } catch (IOException e) {
            System.out.println("Error: no se puede abrir el fichero" + e.getMessage());
            return;
        }

        //Volvemos a abrir el fichero
        try (FileReader reader = new FileReader("seguridad.txt");
             BufferedReader bufferedReader = new BufferedReader(reader);) {
            String linea;
            System.out.println("\nContenido del fichero despues de modificarlo: ");
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);

                //Contadores
                numLineas++;
                numCaracteres += linea.length();
                if (!linea.trim().isEmpty()) { //Evitamos contar lineas vacias como palabras
                    numPalabras += linea.trim().split("\\s+").length;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: no se puede leer el fichero" + e.getMessage());
        }
        System.out.println("\nResumen del Archivo");
        System.out.println("Numero de palabras: " + numPalabras);
        System.out.println("Numero de caracteres: " + numCaracteres);
        System.out.println("Numero de lineas: " + numLineas);
    }
}

