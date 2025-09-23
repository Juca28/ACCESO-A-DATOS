import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogActividad1 {
    public static void main (String[] args) {

        //Creacion del directorio logs/seguridad
        String ruta = "logs/seguridad";
        File directorio = new File(ruta);

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

        //Creacion del fichero de log
        File fichLog = new File(ruta + "seguridad_actividad1.log");
        try {
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

        //
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        File rotatedFile = new File(directorio, "seguridad_actividad1_" + timestamp + ".log");

        if (fichLog.renameTo(rotatedFile)) {
            System.out.println("Fichero renombrado a: " + rotatedFile.getName());
        } else {
            System.out.println("No se pudo renombrar el fichero.");
            return;
        }
    }
}