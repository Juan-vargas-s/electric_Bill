package composable;
import validate.Validations;

public class storeArchive {


    public static void showBill(double[][] average, double cost, String []consume, String customersNames,String router){
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
    }

}
