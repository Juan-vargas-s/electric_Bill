package validate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validations {
   
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
                scanner.nextLine();
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
        if (route.trim().isEmpty()) {
            System.out.println("Manage-Error: Ruta no existe.");
            return;
        }

        try (BufferedWriter addArchive = new BufferedWriter(new FileWriter(route, true))) { 
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException(" EL nombre del archivo es requerido. ");
            }

            addArchive.write(content);  
            if (bool){
                addArchive.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("- Error al escribir en el archivo: " + e.getMessage());
            return;
        }
    }
}
