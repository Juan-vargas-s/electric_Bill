package Process;
public class MainProcess{

    public static void Process(String customersnames[],String consume[] ,String router ,double customers[][][],double average[][],double cost[] ,double finalprice[]){
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
    }
}
