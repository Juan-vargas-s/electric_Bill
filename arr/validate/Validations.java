package validate;

import java.io.File;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import Repositories.ArchiveUtil;

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
        String[] verify = {"pagar", "estadisticas","salir"};

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
                System.out.println("Error: [Ingrese una opción válida] -pagar, -tecnico, -consultar");
                logError("valOption: Error: [Ingrese una opción válida]" + e.getMessage());
            }
        }
    }

     public static int valTecnician(String text) {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        String[] verify = {"reconexion", "reparacion","instalacion","mantenimiento"};

        while (true) {
            option = scanner.nextLine().toLowerCase();
            option = valSubName(option, text);
            for (int i = 0; i < verify.length; i++) {
                if (option.equals(verify[i])) {
                    return i + 1;
                }
            }
            try {
                throw new InvalidOptionException("Opción inválida ingresada: -reconexion, -reparacion, -instalacion, -mantenimiento ");
            } catch (InvalidOptionException e) {
                System.out.println("Error: [\"Opción inválida ingresada: -reconexion, -reparacion, -instalacion, -mantenimiento] ");
                logError("valTecnician: Error: [Ingrese una opción válida]" + e.getMessage());
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

    public static String nameArchiveGenerate(String name) {
        LocalDateTime actualDateTime = LocalDateTime.now();
        int rand = (int) (Math.random() * 100) + 1;;
        
        String safeDateTime = actualDateTime.toString()
            .replace(":", "_")
            .replace("T", "_")
            .replace(".", "_");
        return name + "_" + safeDateTime + "_Serial" + rand;
    }

    public static void logError(String mensaje) {
        String logDir = "storage/logs/"; 

        File dir = new File(logDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        try {
            ArchiveUtil archiveUtil = new ArchiveUtil(logDir);
            String logFile = nameArchiveGenerate("error");
            String logMsg = LocalDateTime.now() + " - " + mensaje;
            archiveUtil.setCreateArchive(logMsg, logFile, true);
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }

    public static void payTecnician(String mensaje) {
        String logDir = "storage/txt/"; 

        File dir = new File(logDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        try {
            ArchiveUtil archiveUtil = new ArchiveUtil(logDir);
            String logFile = nameArchiveGenerate("FacturaTecnico");
            String logMsg = " - " + mensaje;
            archiveUtil.setCreateArchive(logMsg, logFile, true);
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void valMoney(double finalPrice, double clientMoney, String content) {
        if (finalPrice > clientMoney) {
            String logDir = "storage/txt/";
            System.out.println("Error: [No tiene suficiente dinero para pagar el servicio]");
        } else {
            System.out.println("pago hecho satisfactoriamente, emitiendo factura");
            payTecnician(content);
        }
    }

     public static void utilValSubName(String name) throws IllegalArgumentException {

        if (name.isEmpty()) {
            throw new IllegalArgumentException(" [El nombre no puede ser nulo o vacio] ");
       }

            if(valPass(name));{

            }
        }


        public static boolean valPass(String text) {
        String limit = "!#$%/()=?¡¨*[]_";

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < limit.length(); j++) {
                if (text.charAt(i) == limit.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int countArchiveElements(ArchiveUtil archiveUtil, String archivo) {
    Scanner scanner = archiveUtil.getArchive(archivo);
    if (scanner == null) return 0;
    int total = 0;
    while (scanner.hasNextLine()) {
        String[] partes = scanner.nextLine().split(",");
        total += partes.length;
    }
    scanner.close();
    return total;
    }
}
