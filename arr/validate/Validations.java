package validate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validations {

    public static class InvalidOptionException extends Exception {
        public InvalidOptionException(String message) {
            super(message);
        }
    }
   
    public static double valDouble(String text) {
        double value = 0.0;
        Scanner enter = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(text);
                value = enter.nextDouble();
                if (value < 0) {  
                    System.out.println("Error: [No se permiten valores negativos]");
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [ingrese Por favor un número válido]");
                logError("valDouble: Error: [ingrese Por favor un número válido] " + e.getMessage());
                enter.nextLine();
            }
        }
    }
    public static int valInt(String text){
        int size = 0;
        Scanner enter = new Scanner(System.in);

        while (true){
            try {
                System.out.print(text);
                size = enter.nextInt();

                if (size < 1){
                    System.out.println("Error: [No se permiten valores negativos o cero, ingrese Por favor un número válido]");
                } else {
                    return size;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [No se pueden usar caracteres]");
                logError("valInt:Error: [No se pueden usar caracteres] " + e.getMessage());
                enter.nextLine();
            }
        }
    }


    public static int valMaxvalues(String text, int maxValues) {
        int value = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(text);
                value = scanner.nextInt();
                if (value >= maxValues) {
                    System.out.println("Error: El valor debe ser menor a " + maxValues);
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [No se aceptan caracteres]");
                logError("valMaxvalues: Error: [No se aceptan caracteres] " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public static int valOption(String text) {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        String[] verify = {"pagar", "tecnico"};

        while (true) {
            option = scanner.nextLine().toLowerCase();
            option = valSubName(option, text);
            for (int i = 0; i < verify.length; i++) {
                if (option.equals(verify[i])) {
                    return i + 1;
                }
            }
            try {
                throw new InvalidOptionException("Opción inválida ingresada: " + option);
            } catch (InvalidOptionException e) {
                System.out.println("Error: [Ingrese una opción válida] -pagar, -tecnico");
                logError("valOption: Error: [Ingrese una opción válida]" + e.getMessage());
            }
        }
    }

  public static boolean valString(String text)
    {
        String limit = "0123456789!#$%/()=?¡¨*[]_";

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < limit.length(); j++) {
                if (text.charAt(i) == limit.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    public static String valSubName(String name, String text)
    {
        Scanner enter = new Scanner(System.in);

        while (true){
            if (!valString(name)){
                try {
                    throw new IllegalArgumentException("Nombre inválido: " + name);
                } catch (IllegalArgumentException e) {
                    logError("valSubName: Error: [Ingrese un nombre correcto] " + e.getMessage());
                }
                System.out.println("- Error: [Ingrese un nombre correcto]");
                System.out.println(text);
                name = enter.nextLine();
            } else {
                return name;
            }
        }
    }

    public static String utilDirectory(String router) {

        if (router.trim().isEmpty()) {
            System.out.println("Manage-Error: Ruta no existe.");
            return null;
        }

        File directories = new File(router);
        if (!directories.exists()) {
            System.out.println("Manage-Error: El directorio a guardar no existe. ");
            return null;
        }
        return router;

    }

    public static String nameArchiveGenerate() {
        LocalDateTime actualDateTime = LocalDateTime.now();
        int rand = (int) (Math.random() * 100) + 1;
        String name = "resultaExam";
        
        String safeDateTime = actualDateTime.toString()
            .replace(":", "_")
            .replace("T", "_")
            .replace(".", "_");
        return name + "_" + safeDateTime + "_Serial" + rand;
    }

    public static void useArchive(String content, String route, boolean bool)
    {
        if (route == null || route.trim().isEmpty()) {
            System.out.println("Manage-Error: Ruta no existe.");
            return;
        }
        // No escribir si el contenido es vacío o nulo
        if (content == null || content.trim().isEmpty()) {
            return;
        }

        // Asegura que el directorio exista
        File file = new File(route);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter addArchive = new BufferedWriter(new FileWriter(route, true))) { 
            addArchive.write(content);  
            if (bool){
                addArchive.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("- Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void logError(String mensaje) {
        String logDir = "storage/logs/"; // Ruta relativa para Windows y multiplataforma
        // Asegura que el directorio exista
        File dir = new File(logDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // Genera un archivo único por error
        String logFile = logDir + nameArchiveGenerate() + ".log";
        String logMsg = LocalDateTime.now() + " - " + mensaje;
        useArchive(logMsg, logFile, true);
    }
}
