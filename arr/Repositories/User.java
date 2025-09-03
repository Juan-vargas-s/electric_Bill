package Repositories;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {

    private UserValidation userValidation;
    private String userName;
    private String password;
    private double kilowattHours;
    private int id;

    public User( String userName, String router, String[] userCredentials) throws IllegalArgumentException{

        if (userName == null){
            throw new IllegalArgumentException("- Error-Instancia: Objeto incompleto. ");
        }

        try {
            this.userValidation = new UserValidation(userName, "access", router, userCredentials);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fillUserAndPassword();
        fillKilowattHours();
    }

    //getters
    public String getUserName(){
        return this.userName;
    }

    public double getKilowattHours(){
        return this.kilowattHours;
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
        if (userValidation.validateUser()) {
            this.userName = userValidation.getUserName();
            if (userValidation.validatePassword()) {
                this.password = userValidation.getPassword();
            } else {
                throw new IllegalArgumentException("Contraseña incorrecta");
            }
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    private void fillKilowattHours(){
        id = userValidation.getId();
        kilowattHours = 0.0;
        if (id < 0) {
            throw new IllegalArgumentException("ID de usuario no válido.");
        }

        String folderRoute = "storage/txt/"; // Ajusta según tu estructura real
        double[] kilowattHoursArray = null;
        try {
            kilowattHoursArray = readUserKilowatts(new ArchiveUtil(folderRoute), id);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
        if (kilowattHoursArray != null) {
            for(int i= 0; i < kilowattHoursArray.length; i++){
                kilowattHours += kilowattHoursArray[i];
            }
        }
    }

    //utilitarias

    private void utilValDouble(double number) throws IllegalArgumentException {

        if (number < 1){
            throw new IllegalArgumentException("Promedio fuera de rango.");
        }

        this.kilowattHours = number;

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
                        consumos[i] = 0.0; 
                    }
                }
                break; 
            }
            currentLine++;
        }
        scanner.close();
        return consumos;
    }
}
