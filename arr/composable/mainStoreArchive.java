package composable;

import java.io.IOException;
import java.nio.file.Paths;
import validate.Validations;

public class mainStoreArchive {
    public static void store(String customersnames, String consume[], double customers[][][], double average[][], double cost) throws IOException{
       String route = Paths.get("").toRealPath().toString()+"/storage/txt";
       route  = Validations.utilDirectory(route)+"/"+Validations.nameArchiveGenerate("FacturaElectricidad")+".txt";

       storeArchive.showBill( average, cost, consume, customersnames,route);
    }
}
