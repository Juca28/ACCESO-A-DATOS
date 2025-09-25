import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class AnanlisisFich {
    public static void main(String[] args) {
        //Variable para el conteo del numero de lineas, caractaeres y palabras
        int numLineas = 0;
        int numPalabras = 0;
        int numCaracteres = 0;

        //Variables que define el archivo copia y palabras que se quieren borrar de el
        String archivoCopia = "seguridad_copia.txt";
        String eliminar = "Contraseña";

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

        try(BufferedReader br = new BufferedReader(new FileReader("seguridad.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("seguridad_copia.txt"))){

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine(); //Esto mantiene los saltos de línea
            }
            System.out.println("\nArchivo copiado correctamente");
        }catch(IOException e){
            System.out.println("Error: no se ha podido copiar el fichero" + e.getMessage());
        }

        //Creamos un array que va a guardar la información del fichero
        List<String> lineas = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new  FileReader(archivoCopia))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                //Con esta linea eliminamos las palabras seleccionadas en el archivo
                linea = linea.replaceAll("\\b" + eliminar + "\\b", "");
                //Añadimos la linea modificada al Array
                lineas.add(linea);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivoCopia))){
            for (String l : lineas) {
                bufferedWriter.write(l.trim());
                bufferedWriter.newLine();
            }
            System.out.println("\nPalabras eliminada correctamente");
        }catch (IOException e){
            System.out.println("Error: no se han podido eliminar las palabras seleccionadas" + e.getMessage());
        }
        try (FileReader reader = new FileReader(archivoCopia);
             BufferedReader bufferedReader = new BufferedReader(reader);) {
            String linea;
            System.out.println("\nContenido del fichero copia modificado: ");
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error: no se puede leer el fichero" + e.getMessage());
        }
    }
}

