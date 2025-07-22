package Process;
import composable.mainStoreArchive;
import validate.Validations;
import composable.mainStoreArchive;
import java.io.IOException;
public class MainProcess{

    public static void Process(String customersnames[],String consume[] ,double customers[][][],double average[][],double cost[] ,double finalprice[]) {
        int option = 0;
        String text = "";
        
        text = "escriba que acción quiere tomar: -pagar, -tecnico ";
        System.out.println(text);
        option = Validations.valOption(text);

       if (!(option == 0) && !(option > 2)){
        if (option == 1) {
           payElectricBill(customersnames, consume, customers, average, cost, finalprice);
        }
        else if(option == 2){
            RepairServices.requestRepairService();
        }
       }
       else{
        text = "esa opción no existe";
        System.out.println(text);
       }
    }


    public static void payElectricBill(String customersnames[],String consume[] ,double customers[][][],double average[][],double cost[] ,double finalprice[]){
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
        try {
            mainStoreArchive.store(customersnames, consume, customers, average, cost, finalprice);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
