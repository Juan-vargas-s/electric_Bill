package arr;

import java.util.Scanner;


public class Process {

    public static void iniMatriz(double[][][] matriz) {
        if (matriz != null) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    for (int k = 0; k < matriz[0][0].length; k++) {
                        matriz[i][j][k] = 0;
                    }
                }
            }
        }
    }

    public static void iniAverage(double[][] average) {
        if (average != null) {
            for (int i = 0; i < average.length; i++) {
                for (int j = 0; j < average[0].length; j++) {
                    average[i][j] = 0;
                }
            }
        }
    }

    public static void initStudentNames(String[][] array) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    array[i][j] = "";
                }
            }
        }
    }


    public static void fillArrays(int[][][] clientes, String[] nombreclientes) {
        Scanner scanner = new Scanner(System.in);
        for (int x = 0; x < clientes.length; x++) {
            System.out.print("Ingrese el nombre del propietario de la casa [" + x + "]: ");
            nombreclientes[x] = scanner.nextLine();
            for (int y = 0; y < clientes[x].length; y++) {
                System.out.println("Mes: " + (y + 1));
                for (int z = 0; z < clientes[x][y].length; z++) {                
                clientes[x][y][z] = Validations.valInt("Ingrese el consumo mensual: ");
                }
            }
        }
    }
}