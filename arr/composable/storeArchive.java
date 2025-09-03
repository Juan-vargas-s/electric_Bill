package composable;

import Repositories.User;
import Repositories.ArchiveUtil;
import Process.MainProcess;
import validate.Validations;

public class storeArchive {


    /*public static void showBill(double[][] average, double cost, String []consume, String customersNames,String router){
        String texts = "";

        Validations.useArchive("                                | Inicio del Programa                |\n", router, true);

        Validations.useArchive(String.format("%-5s | %-25s | %-18s | %-15s", "Casa", "Nombre del propietario", "Promedio del", "Costo total"), router, false);
        Validations.useArchive(String.format("%-5s | %-25s | %-18s | %-15s", "    ", " de la casa             ", " consumo de kw  ", " de kw          "), router, true);

        if (average != null && cost != 0 && consume != null && customersNames != null) {

            for (int i = 0; i < 1; i++) {

                texts = String.format("%-5d | %-25s | %-15.2f kw/mes | %-12.2f$", (i + 1), customersNames, average[i][0], cost);
                Validations.useArchive(texts, router, true);
            }

            Validations.useArchive("-------------------------------------------------------------------------------", router, true);

        
        }
    }*/

    public static void saveUserBill(User user, ArchiveUtil archiveUtil) {
        if(user == null || archiveUtil == null){
            System.out.println("Error: El usuario o el archivo no puede ser nulo.");
            return;
        } 
        String userName = user.getUserName();
        int municipioIndex = user.getOption();
        double kilowatts = user.getKilowattHours();
        double totalPagar = MainProcess.payElectricBill(user);

        String[] municipios = new String[]{"San Diego","Juan José Mora","Valencia","Puerto Cabello","Los Guayos"};
        String municipio = (municipioIndex > 0 && municipioIndex <= municipios.length) ? municipios[municipioIndex - 1] : "Desconocido";

         if (userName == null || userName.equals("") || municipioIndex < 0) {
            System.out.println("no se pudo imprimir ");
            return;
        }
        
        StringBuilder contenido = new StringBuilder();
        contenido.append("Cliente         Municipio         Kilowatts Consumidos        Total a Pagar\n");
        contenido.append(String.format("%-15s %-20s %-25s %-10s\n",
                userName,
                municipio,
                String.format("%.1f kw/h", kilowatts),
                String.format("%.2f $", totalPagar)
        ));

        archiveUtil.setCreateArchive(contenido.toString(), Validations.nameArchiveGenerate("Factura"), false,true);
    }
}
