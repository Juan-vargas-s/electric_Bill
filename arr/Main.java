package arr;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //declaramos los arreglos y variables que vayamos a usar
        Scanner scanner = new Scanner(System.in);
        String [] customersnames;
        double [][][] customers;
        double [][] average;
        int casa = 0, months =0;

        //Recibimos y validamos los datos de entrada para instanciar los arreglos
        months = Validations.valInt("Ingrese cuántos meses seran para cada casa: ");
        casa = Validations.valInt("seleccione cuantas casas seran por mes: ");
    

        //Instanciamos los arreglos
        customersnames = new String[casa];
        customers = new double[casa][months][1];
        average = new double [casa][2];

        //inicializamos los arreglos
        Process.iniMatrix(customers);
        Process.fillArrays(customers, customersnames);
        Process.iniAverage(average);

        //Cerramos instancias
        customersnames = null;
        customers = null;
        average = null;
    }
}

