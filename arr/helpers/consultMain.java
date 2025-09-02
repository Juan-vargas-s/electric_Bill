package helpers;

import java.io.FileNotFoundException;
import java.util.Scanner;
import validate.Validations;


public class consultMain {



   public static void main(double[][][] matriz) {
         Scanner input        = new Scanner(System.in);
         String clientFile    = "Storage/txt/user.txt";
         String passwordFile  = "Storage/txt/passwords.txt";
         String kWFile        = "Storage/txt/kilowatts.txt";
         String separator     = ",";

         try {
             // 1) Validar usuario y obtener {fila, columna}
             int[] clientPosition = consultData
                 .requestAndValidateClient(input, clientFile, separator);
             System.out.println("Usuario válido.");

             // 2) Validar contraseña
             int[] passwordPosition = consultData
                 .requestAndValidatePassword(input, passwordFile, separator);
             System.out.println("Contraseña válida.");
             System.out.println("Acceso concedido.");

            // 3) Cargar toda la matriz de usuarios para conocer el número de columnas
           String[][] allClients = consultData
                .loadClientsMatrix(clientFile, separator);
            int cols = allClients[0].length;

            // 4) Aplanar fila/columna a índice 0-based
            int flatUserIndex = clientPosition[0] * cols + clientPosition[1];

            // 5) Llamar a fillKilovatios3D con {flatUserIndex, 0}
             consultData.fillKilovatios3D(
                 matriz,
                 kWFile,
                 separator,
                 new int[]{ flatUserIndex, 0 }
             );


         } catch (FileNotFoundException e) {
             System.out.println("No se encontró uno de los archivos: " + e.getMessage());
             Validations.logError("consultMain: Error: [No se encontró uno de los archivos] " + e);
         }

         input.close();
     }
 }
    

 
    

