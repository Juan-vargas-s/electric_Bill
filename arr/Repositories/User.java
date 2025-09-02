package Repositories;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {

    private UserValidation userValidation;
    private String userName;
    private String password;
    private double clientMoney;
    private double id;

    public User( String userName, String router, String[] userCredentials, double[] userDigitData) throws IllegalArgumentException{

        if (userName == null){
            throw new IllegalArgumentException("- Error-Instancia: Objeto incompleto. ");
        }

        try {
            this.userValidation = new UserValidation(this.userName, this.password, router, userCredentials);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fillUserAndPassword();
    }

    //getters
    public String getUserName(){
        return this.userName;
    }

    public double getClientMoney(){
        return this.clientMoney;
    }

    //setters
    public void setUserName(String Name){
        this.utilValSubName(Name);
    }

    public void setClientMoney(double clientMoney){
        this.utilValDouble(clientMoney);
    }

    //Comportamientos
    private void fillUserAndPassword(){
       if (userValidation.validateUser()){
            this.userName = userValidation.getUserName();
       }
       else {
        throw new IllegalArgumentException("Usuario no encontrado");
       }

       if (userValidation.validatePassword()) {
          this.password = userValidation.getPassword();
       }
       else{
        throw new IllegalArgumentException("Contraseña incorrecta");
       }
    }

    private void fillKilowattHours(double kilowattHours) throws IllegalArgumentException {
        
    }

    //utilitarias

    private void utilValDouble(double number) throws IllegalArgumentException {

        if (number < 1){
            throw new IllegalArgumentException("Promedio fuera de rango.");
        }

        this.clientMoney = number;

    }

    private void utilValSubName(String name) throws IllegalArgumentException {

        if (name.isEmpty()){
            throw new IllegalArgumentException(" [El nombre no puede ser nulo o vacio] ");
        }

        if (!utilValString(name)){
            throw new IllegalArgumentException(" [Objecto no permite caracteres especiales o numeros]");
        } else {
            this.userName = name;
        }

    }

    private boolean utilValString(String text)
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

    private void readAndSeparateUsersAndPasswords(ArchiveUtil archiveUtil, String archiveName, String[] userCredentials) {
        Scanner scanner = archiveUtil.getArchive(archiveName);
        if (scanner == null) {
            System.out.println("No se pudo abrir el archivo: " + archiveName);
            return;
        }

        int index = 0;
        while (scanner.hasNextLine() && index < userCredentials.length) {
            String linea = scanner.nextLine();
            Scanner scLinea = new Scanner(linea);
            scLinea.useDelimiter(",");
            while (scLinea.hasNext() && index < userCredentials.length) {
                userCredentials[index++] = scLinea.next().trim();
            }
            scLinea.close();
        }
        scanner.close();
    }

    public double[] readUserKilowatts(ArchiveUtil archiveUtil, int userId) {
        Scanner scanner = archiveUtil.getArchive("kilowatts.txt");
        if (scanner == null) {
            System.out.println("No se pudo abrir el archivo: kilowatts.txt");
            return null;
        }

        int currentLine = 0;
        double[] consumos = null;
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (currentLine == userId) {
                String[] partes = linea.split(",");
                consumos = new double[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    try {
                        consumos[i] = Double.parseDouble(partes[i].trim());
                    } catch (NumberFormatException e) {
                        consumos[i] = 0.0; // O maneja el error como prefieras
                    }
                }
                break; // Ya encontramos la línea del usuario
            }
            currentLine++;
        }
        scanner.close();
        return consumos;
    }
}
