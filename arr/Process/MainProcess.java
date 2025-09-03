package Process;
import composable.mainStoreArchive;
import helpers.consultMain;
import validate.Validations;
import java.io.IOException;

import Repositories.ArchiveUtil;
import Repositories.Stadistics;
import Repositories.User;
public class MainProcess{


    public static void Process(User user, Stadistics stadistics, ArchiveUtil archiveUtil) {
        int option = 0;
        String text = "";

        text = "escriba que acción quiere tomar: -pagar, -estadisticas, -salir";
        System.out.println(text);
        option = Validations.valOption(text);

       if (!(option == 0) && !(option > 4)){
        if (option == 1) {
            
           payElectricBill(user);
        }
        else if(option == 2){
            stadistics.saveTableFormatted(archiveUtil, "output", 0.47);
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


    public static void payElectricBill(User user){
        String userName = user.getUserName();
        double kilowattHours = user.getKilowattHours();
        int  municipality = user.getOption();
        System.out.println(municipality);
        double cost = Process.calculatePrice(kilowattHours, municipality);
        System.out.println(cost);

       /*  double cost = 0.0;
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
        */
    }


}
