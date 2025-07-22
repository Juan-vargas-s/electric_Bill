import java.io.IOException;
import Process.MainProcess;
import validate.Validations;


public class Main {
    public static void main(String[] args) throws IOException {

        String [] customersnames;
        String [] consume;
        double [][][] customers;
        double [][] average;
        double [] cost;
        double [] finalprice;
        int casa = 0, months =0;


        months = Validations.valMaxvalues("Ingrese cuántos meses seran para cada casa: ", 13);
        casa = Validations.valInt("seleccione cuantas casas seran por mes: ");


        customersnames = new String[casa];
        customers = new double[casa][months][1];

        MainProcess.Process(customersnames, consume, customers, average, cost , finalprice);

        customersnames = null;
        average = null;
        customers = null;
        cost = null;
        consume = null;
        finalprice = null;

    }
}

