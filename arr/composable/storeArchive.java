package composable;
import validate.Validations;

public class storeArchive {


    public static void showBill(double[][] average, double [] finalPrice, String []consume, String [] customersNames,String router){
        String texts = "";

        Validations.useArchive("                                | Inicio del Programa                |\n", router, true);

        Validations.useArchive(String.format("%-5s | %-25s | %-18s | %-15s", "Casa", "Nombre del propietario", "Promedio del", "Costo total"), router, false);
        Validations.useArchive(String.format("%-5s | %-25s | %-18s | %-15s", "    ", " de la casa             ", " consumo de kw  ", " de kw          "), router, true);

        if (average != null && finalPrice != null && consume != null && customersNames!= null) {

            for (int i = 0; i < customersNames.length; i++) {

                texts = String.format("%-5d | %-25s | %-15.2f kw/mes | %-12.2f$", (i + 1), customersNames[i], average[i][0], finalPrice[i]);
                Validations.useArchive(texts, router, true);
            }

            Validations.useArchive("-------------------------------------------------------------------------------", router, true);

            Validations.useArchive("Cliente con mas consumido de kw: " + consume[0], router, true);
            Validations.useArchive("Cliente con menos consumido de kw : " + consume[1], router, true);
        }
    }

}
