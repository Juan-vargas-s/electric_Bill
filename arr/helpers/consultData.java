package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import validate.Validations;

public class consultData {
   
    // Método recursivo: busca la contraseña en la matriz leyendo el archivo contraseñas.txt
    // Si no encuentra coincidencia, retorna {-1, -1}
    public static int[] findPasswordIndex(Scanner archive, String password, int row, String separator) {
        if (!archive.hasNextLine()) {
            return new int[]{-1, -1};
        }
        String[] values = archive.nextLine().split(separator);
        for (int col = 0; col < values.length; col++) {
            if (values[col].trim().equals(password.trim())) {
                return new int[]{row, col};
            }
        }
        return findPasswordIndex(archive, password, row + 1, separator);
    }

    // Método para pedir al usuario la contraseña y validar recursivamente hasta encontrar coincidencia
    public static int[] requestAndValidatePassword(java.util.Scanner userInput, String filePath, String separator) throws java.io.FileNotFoundException {
        System.out.print("Ingrese su contraseña: ");
        String password = userInput.nextLine();
        java.io.File file = new java.io.File(filePath);
        Scanner archive = new Scanner(file);
        int[] result = findPasswordIndex(archive, password, 0, separator);
        archive.close();
        if (result[0] == -1) {
            System.out.println("Contraseña no encontrada. Intente de nuevo.\n");
            Validations.logError("consultData: Error: [Contraseña no encontrada] ");
            return requestAndValidatePassword(userInput, filePath, separator);
        }
        return result;
    }


    
    // Método para cargar el archivo de contraseñas en una matriz bidimensional (sin usar ArrayList)
    public static String[][] loadPasswordsMatrix(String filePath, String separator) throws java.io.FileNotFoundException {
        java.io.File file = new java.io.File(filePath);
        Scanner countScanner = new Scanner(file);
        int rowCount = 0;
        int colCount = 0;
        while (countScanner.hasNextLine()) {
            String line = countScanner.nextLine();
            String[] values = line.split(separator);
            if (values.length > colCount) colCount = values.length;
            rowCount++;
        }
        countScanner.close();
        String[][] matrix = new String[rowCount][colCount];
        Scanner archive = new Scanner(file);
        int row = 0;
        while (archive.hasNextLine()) {
            String[] values = archive.nextLine().split(separator);
            for (int col = 0; col < values.length; col++) {
                matrix[row][col] = values[col];
            }
            row++;
        }
        archive.close();
        return matrix;
    }

    // Método recursivo: busca el usuario en la matriz leyendo el archivo Clientes.txt
    // Si no encuentra coincidencia, retorna {-1, -1}
    public static int[] findClientIndex(Scanner archive, String username, int row, String separator) {
        if (!archive.hasNextLine()) {
            return new int[]{-1, -1};
        }
        String[] values = archive.nextLine().split(separator);
        for (int col = 0; col < values.length; col++) {
            if (values[col].trim().equals(username.trim())) {
                return new int[]{row, col};
            }
        }
        return findClientIndex(archive, username, row + 1, separator);
    }

    // Método para pedir al usuario el nombre y validar recursivamente hasta encontrar coincidencia
    public static int[] requestAndValidateClient(java.util.Scanner userInput, String filePath, String separator) throws java.io.FileNotFoundException {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = userInput.nextLine();
        java.io.File file = new java.io.File(filePath);
        Scanner archive = new Scanner(file);
        int[] result = findClientIndex(archive, username, 0, separator);
        archive.close();
        if (result[0] == -1) {
            System.out.println("Usuario no encontrado. Intente de nuevo.\n");
            Validations.logError("consultData: Error: [Usuario no encontrado] ");
            return requestAndValidateClient(userInput, filePath, separator);
        }
        return result;
    }

   
    
    // Método para cargar el archivo en una matriz bidimensional (sin usar ArrayList)
    public static String[][] loadClientsMatrix(String filePath, String separator) throws java.io.FileNotFoundException {
        java.io.File file = new java.io.File(filePath);
        Scanner countScanner = new Scanner(file);
        int rowCount = 0;
        int colCount = 0;
        while (countScanner.hasNextLine()) {
            String line = countScanner.nextLine();
            String[] values = line.split(separator);
            if (values.length > colCount) colCount = values.length;
            rowCount++;
        }
        countScanner.close();
        String[][] matrix = new String[rowCount][colCount];
        Scanner archive = new Scanner(file);
        int row = 0;
        while (archive.hasNextLine()) {
            String[] values = archive.nextLine().split(separator);
            for (int col = 0; col < values.length; col++) {
                matrix[row][col] = values[col];
            }
            row++;
        }
        archive.close();
        return matrix;
    }
    /**
 * Lee la fila `targetRow` y retorna el valor en la columna `targetCol`
 * del archivo de kilovatios, usando recursividad o iteración simple.
 */
    /**
 * Lee la fila `clientRowIndex` de kilovatios.txt
 * y la guarda en matriz[0][mes][0].
 */
public static void fillKilovatios3D(double[][][] matriz, String filePath, String separator,int[] clientRowIndex) throws FileNotFoundException
{
    // Validar dimensiones
    if (matriz.length < 1 || matriz[0].length < 12 || matriz[0][0].length < 1)
        throw new IllegalArgumentException("La matriz debe ser [1][12][1].");
    
    try (Scanner sc = new Scanner(new File(filePath))) {
        int currentRow = 0;
        // Saltar hasta la fila del cliente
        while (currentRow < clientRowIndex[0] && sc.hasNextLine()) {
            sc.nextLine();
            currentRow++;
        }
        if (!sc.hasNextLine())
            throw new IllegalArgumentException("No existe la fila " + clientRowIndex);
        
        // Leer y rellenar esa única línea
        String[] parts = sc.nextLine().split(separator);
        if (parts.length < 12)
            throw new IllegalArgumentException("Se esperaban 12 valores en la fila " + clientRowIndex);
        
        for (int m = 0; m < 12; m++) {
            matriz[0][m][0] = Double.parseDouble(parts[m].trim());
        }
    }
}
}
    



