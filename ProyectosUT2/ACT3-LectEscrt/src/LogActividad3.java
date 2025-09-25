/**
 * @author Juan Carlos Vázquez Lozano
 * @date 25/09/2025
 */
import java.io.*;

public class LogActividad3 {
    public static void main(String[] args) {
        //Creamos el fichero "seguridad_actividad3.log"
        final String NOMBRE_FICHERO = "seguridad_actividad3.log";
        //Abrimos el fichero y es escribimos lo pedido
        try (FileWriter writer = new FileWriter(NOMBRE_FICHERO)){
            writer.write("Intento de acceso fallido \n");
            writer.write("Usuario autenticado correctamente \n");
        }catch(IOException e){
            System.out.println("Error: no se puede abrir el fichero" + e.getMessage());
            return;
        }
        //Leemos lo escrito en el fichero
        try(FileReader reader = new FileReader(NOMBRE_FICHERO);
            BufferedReader bufferedReader = new BufferedReader(reader);){
            String linea;
            System.out.println("Contenido del fichero");
            while((linea = bufferedReader.readLine()) != null){
                System.out.println(linea);
            }
        }catch(IOException e){
            System.out.println("Error: no se puede leer el fichero" + e.getMessage());
        }
    }
}
