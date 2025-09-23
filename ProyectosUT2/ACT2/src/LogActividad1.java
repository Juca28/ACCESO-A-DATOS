/**
 * @author Juan Carlos Vázquez Lozano
 * @date 23/09/2025
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogActividad1 {
    public static void main (String[] args) {

        //1. Creacion del directorio logs/seguridad

        //Creamos el directorio
        String ruta = "logs/seguridad";
        File directorio = new File(ruta);

        //Mandamos por consola si se ha creado correctamente, ya existia o no se ha podido crear
        if (!directorio.exists()) {
            boolean existe = directorio.mkdirs();
            if (existe) {
                System.out.println("Directorio creado: " + directorio.getAbsolutePath());
            } else {
                System.out.println("No se ha podido crear el directorio");
                return;
            }
        } else {
            System.out.println("El directorio ya existe: " + directorio.getAbsolutePath());
        }

        //2. Creacion del fichero de log

        //Creamos el fichero log
        File fichLog = new File(ruta + "seguridad_actividad1.log");
        //Usamos un try para poder manejar exepciones
        try {
            //Mandamos por consola si se ha creado correctamente, ya existia o no se ha podido crear
            if (fichLog.exists()) {
                System.out.println("El fichero ya existe: " + fichLog.getAbsolutePath());
            } else {
                if (fichLog.createNewFile()) {
                    System.out.println("El fichero se creo correctamente:  " + fichLog.getAbsolutePath());
                } else {
                    System.out.println("No se ha podido crear el fichero");
                }
            }
        }catch (IOException e){
            System.out.println("Error al crear el fichero" + e.getMessage());
            return;
        }

        //3. Rotacion por renonmbrado
        //Creamos la fecha
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        //Renombramos el fichero
        File ficheroRotado = new File(directorio, "seguridad_actividad1_" + fecha + ".log");

        //Mandamos por consola si se ha renombrado o no
        if (fichLog.renameTo(ficheroRotado)) {
            System.out.println("Fichero renombrado a: " + ficheroRotado.getName());
        } else {
            System.out.println("No se pudo renombrar el fichero.");
            return;
        }

        //4. Eliminamos el fichero renombrado
        if(ficheroRotado.delete()){
            System.out.println("Fichero eliminado correctamente: " + ficheroRotado.getName());
        }else{
            System.out.println("Error al eliminar el fichero.");
        }
    }
}