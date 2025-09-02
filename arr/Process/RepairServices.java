package Process;
import validate.Validations;

public class RepairServices {

        public static void callTecnician(){
        int option = 0;
        double finalPrice;
        String text = "";

        text = "escriba que acción quiere tomar: -reconexion, -reparacion, -instalacion, -mantenimiento";
        System.out.println(text);
        option = Validations.valTecnician(text);


       if (!(option <= 0) && !(option > 5)){
        if (option == 1) {
            text = "mandando un tecnico para la reconexion ";
            requestRepairService(text);
            
        }
        else if(option == 2){
            text = "mandando un tecnico para la reparacion ";
            requestRepairService(text);
            
        }
        else if (option == 3){
            text = "mandando un tecnico para la instalacion ";
             requestRepairService(text);

        }
        else if (option == 4){
            text = "mandando un tecnico para la mantenimiento ";
            requestRepairService(text);
        }

       }
       else{
        text = "esa opción no existe";
        System.out.println(text);
       }
    }

    public static double requestRepairService(int zone,double clientMoney) {
        double finalPrice = 0;
        double price = 0;

        if (zone == 1){
            price = 20;
            finalPrice  = price + (price * 0.07);

        }
        else if (zone == 2){
            price = 15;
            finalPrice  = price + (price * 0.05);

        }
        else if (zone == 3){
            price = 40;
            finalPrice  = price + (price * 0.08);

        }
        else if (zone == 4){
            price = 10;
            finalPrice  = price + (price * 0.1);

        }
        else if (zone == 5){
            price = 30;
            finalPrice  = price + (price * 0.12);

        }
        
        return finalPrice;

    }

    public static void requestRepairService(String content) {
        int zone = Validations.valMaxvalues("Seleccione un municipio de residencia:\n1.juanjosemora\n2.puertocabello\n3.sandiego\n4.valencia\n5.losguayos", 6);
        double clientMoney = Validations.valDouble("Ingrese la cantidad de dinero que tiene el cliente: ");
        double finalPrice = requestRepairService(zone, clientMoney);
        String municipality = "";
        if (zone == 1){
            municipality = "juanjosemora";
        }
        else if (zone == 2){
            municipality = "puertocabello";
        }
        else if (zone == 3){
            municipality = "sandiego";
        }
        else if (zone == 4){
            municipality = "valencia";
        }
        else if (zone == 5){
            municipality = "losguayos";
        }
        validate.Validations.valMoney(finalPrice, clientMoney,content+"en la zona de: "+municipality+" con un precio de: "+finalPrice+"$");

    }
}