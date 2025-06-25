package arr;

import java.util.Scanner;


public class Process {

    public static void iniMatrix(double[][][] matrix) {
        if (matrix != null) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    for (int k = 0; k < matrix[0][0].length; k++) {
                        matrix[i][j][k] = 0;
                    }
                }
            }
        }
    }

    public static void iniDouble(double[] average) {
        if (average != null) {
            for (int i = 0; i < average.length; i++) {

                average[i] = 0;

            }
        }
    }

    public static void iniAverage(double[][] average) {
        if (average != null) {
            for (int i = 0; i < average.length; i++) {
                for (int j = 0; j < average[i].length; j++) {
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

    public static void iniString(String[] array) {
        if (array != null) {
            for(int i = 0; i < array.length; i++){
                array[i] = "";
            }
        }
    }


    public static void fillArrays(double[][][] customers, String[] customersnames) {
    Scanner scanner = new Scanner(System.in);
    String text, name = "";
        if (customers != null && customersnames != null) {
            for (int x = 0; x < customers.length; x++) {
                text = "Ingrese el nombre del propietario de la casa [" + x + "]: ";
                System.out.print(text);
                name = scanner.nextLine();
                customersnames[x] = Validations.valSubName(name,text);
                for (int y = 0; y < customers[x].length; y++) {
                    text = "Mes: " + (y + 1);
                    System.out.println(text);
                    for (int z = 0; z < customers[x][y].length; z++) {
                        text = "Ingrese el consumo mensual: ";
                        customers[x][y][z] = Validations.valDouble(text);
                    }
                }
            }
        }
    }
    public static void calculateAverage(double [][][] customers, double [][] average) {
        double suma = 0;

        if (customers.length != 0 && average.length != 0) {
            for (int i = 0; i < customers.length; i++) {
                for (int j = 0; j < customers[i].length; j++) {
                    for (int k = 0; k < customers[i][j].length; k++) {
                        suma = suma + customers[i][j][k];
                    }
                }
                average [i][0] = suma;
                average[i][1] = suma / (customers[i].length);
                suma = 0;
            }
        }
    }
}