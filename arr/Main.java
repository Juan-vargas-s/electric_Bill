package arr;


import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        String [] customersnames;
        String [] consume;
        double [][][] customers;
        double [][] average;
        double [] cost;
        double [] finalprice;
        int casa = 0, months =0;

        String router = Paths.get("").toRealPath().toString()+"/src/arr";
        router  = Validations.utilDirectory(router)+"/"+Validations.nameArchiveGenerate()+".txt";

        months = Validations.valMaxvalues("Ingrese cuántos meses seran para cada casa: ", 13);
        casa = Validations.valInt("seleccione cuantas casas seran por mes: ");


        customersnames = new String[casa];
        customers = new double[casa][months][1];
        average = new double [casa][2];
        cost = new double [casa];
        consume = new String[2];
        finalprice = new double [casa];

        Process.iniMatrix(customers);
        Process.iniAverage(average);
        Process.initNames(customersnames);
        Process.iniDouble(cost);
        Process.initNames(consume);
        Process.iniDouble(finalprice);

        Process.fillArrays(customers, customersnames);
        Process.calculateAverage(customers,average);
        Process.calculatePrice(customers,cost);
        Process.hig_LowConsume(cost,customersnames,consume);
        Process.calculaterMunipality(cost,finalprice,customersnames);
        Process.showBill(average,finalprice,consume,customersnames,router);
        Process.showAverage(average,finalprice,consume);

        customersnames = null;
        average = null;
        customers = null;
        cost = null;
        consume = null;
        finalprice = null;

    }
}

