import java.io.IOException;
import java.nio.file.Paths;
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

        String router = Paths.get("").toRealPath().toString()+"/arr/storage/txt/";
        router  = Validations.utilDirectory(router)+"/"+Validations.nameArchiveGenerate()+".txt";

        months = Validations.valMaxvalues("Ingrese cuántos meses seran para cada casa: ", 13);
        casa = Validations.valInt("seleccione cuantas casas seran por mes: ");


        customersnames = new String[casa];
        customers = new double[casa][months][1];
        average = new double [casa][2];
        cost = new double [casa];
        consume = new String[2];
        finalprice = new double [casa];

        MainProcess.Process(customersnames, consume, router, customers, average, cost, finalprice);

        customersnames = null;
        average = null;
        customers = null;
        cost = null;
        consume = null;
        finalprice = null;

    }
}

