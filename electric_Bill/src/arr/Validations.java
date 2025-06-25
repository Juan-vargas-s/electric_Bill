package arr;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validations {
   
    public static double valDouble(String text) {
        double value = 0.0;
        Scanner enter = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(text);
                value = enter.nextDouble();
                if (value < 0) {  
                    System.out.println("Error: [No se permiten valores negativos]");
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [ingrese Por favor un número válido]");
                enter.nextLine();
            }
        }
    }
    public static int valInt(String text){
        int size = 0;
        Scanner enter = new Scanner(System.in);

        while (true){
            try {
                System.out.print(text);
                size = enter.nextInt();

                if (size < 1){
                    System.out.println("Error: [No se permiten valores negativos o cero, ingrese Por favor un número válido]");
                } else {
                    return size;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [No se pueden usar caracteres]");
                enter.nextLine();
            }
        }
    }
   
  public static boolean valString(String text)
    {
        String limit = "0123456789!#$%/()=?¡¨*[]_";

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < limit.length(); j++) {
                if (text.charAt(i) == limit.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

} 