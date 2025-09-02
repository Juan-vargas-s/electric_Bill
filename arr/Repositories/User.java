package Repositories;

import java.io.FileNotFoundException;

public class User {

    private UserValidation userValidation;
    private String userName;
    private String password;
    private double clientMoney;
    private double id;

    public User( String userName, String router, String[] userCredentials) throws IllegalArgumentException{

        if (userName == null){
            throw new IllegalArgumentException("- Error-Instancia: Objeto incompleto. ");
        }

        this.userName = userName;
        this.password = password;

        try {
            this.userValidation = new UserValidation(this.userName, this.password, router, userCredentials);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
}
