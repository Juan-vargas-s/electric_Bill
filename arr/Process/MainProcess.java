package Process;
import composable.mainStoreArchive;
import helpers.consultMain;
import validate.Validations;
import java.io.IOException;
public class MainProcess{


    public static void Process(String customersnames,String consume[],double customers[][][],double average[][] ,double finalprice[]) {
        int option = 0;
        String text = "";
        double cost = 0.0;

        text = "escriba que acción quiere tomar: -pagar, -tecnico, -salir";
        System.out.println(text);
        option = Validations.valOption(text);

       if (!(option == 0) && !(option > 4)){
        if (option == 1) {
            
           payElectricBill( customersnames, consume , customers,  average);
        }
        else if(option == 2){
            RepairServices.callTecnician();
        }
            
        else if (option == 3){
            text = "saliendo del programa";
            System.out.println(text);
        }
       }
       else{
        text = "esa opción no existe";
        System.out.println(text);
       }
    }


    public static void payElectricBill(String customersnames,String consume[] ,double customers[][][],double average[][] ){
        double cost = 0.0;
        Process.iniMatrix(customers);
        Process.iniAverage(average);
       // Process.initNames(customersnames);
    //Process.iniDouble(cost);
        Process.initNames(consume);
        //Process.iniDouble(finalprice);

        consultMain.main(customers);
        Process.calculateAverage(customers,average);
       cost = Process.calculatePrice(customers,cost);
       // Process.hig_LowConsume(cost,customersnames,consume);
       // Process.calculaterMunipality(cost,finalprice,customersnames);
        try {
            mainStoreArchive.store(customersnames, consume, customers, average, cost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
