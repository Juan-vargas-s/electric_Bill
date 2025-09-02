package Repositories;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class UserValidation {

    private double id;
    private String userName;
    private String password;
    private ArchiveUtil archiveUtil;
    private String[] userCredentials;

    public UserValidation(String userName, String password, String route, String[] userCredentials)
            throws IllegalArgumentException, FileNotFoundException {

        this.id = 0;
        this.userName = userName;
        this.password = password;
        this.archiveUtil = new ArchiveUtil(route);
        this.userCredentials = userCredentials;
    }

    public boolean validateUser() {
        id = 0;
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }
        utilValSubName(userName, true);
        readAndSeparateUsersAndPasswords(archiveUtil, "user.txt", userCredentials);
        for (int i = 0; i < userCredentials.length; i++) {
            if (userName.equals(userCredentials[i])) {
                id = i;
                clearStringArray(userCredentials);
                return true;
            }

        }
        clearStringArray(userCredentials);
        return false;
    }

    public boolean validatePassword() {

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }

        if (id == -1) {
            throw new IllegalArgumentException("Incorrect password");
        }

        utilValSubName(password, false);
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }

        String route = "passwords.txt";
        readAndSeparateUsersAndPasswords(archiveUtil, route, userCredentials);
        for (int i = 0; i < userCredentials.length; i++) {
            if (password.equals(userCredentials[i]) && id == i) {
                System.out.println("acceso concedido");
                clearStringArray(userCredentials);
                return true;
            }
        }
        clearStringArray(userCredentials);
        return false;
    }

    private void utilValSubName(String name, boolean path) throws IllegalArgumentException {

        if (name.isEmpty()) {
            throw new IllegalArgumentException(" [El nombre no puede ser nulo o vacio] ");
        }
        if (path)
            if (!utilValString(name)) {
                throw new IllegalArgumentException(" [Objecto no permite caracteres especiales o numeros]");
            } else {
                this.userName = name;
            }
        if (!path) {
            if (!utilValPass(name)) {
                throw new IllegalArgumentException(" [Objecto no permite caracteres especiales]");
            } else {
                this.userName = name;
            }
        }

    }

    private boolean utilValString(String text) {
        String limit = "0123456789!#$%/()=?¡¨*[]_";

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < limit.length(); j++) {
                if (text.charAt(i) == limit.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean utilValPass(String text) {
        String limit = "!#$%/()=?¡¨*[]_";

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < limit.length(); j++) {
                if (text.charAt(i) == limit.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void readAndSeparateUsersAndPasswords(ArchiveUtil archiveUtil, String archiveName,
            String[] userCredentials) {
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

    private void clearStringArray(String[] userCredentials) {
        for (int i = 0; i < userCredentials.length; i++) {
            userCredentials[i] = "";
        }

    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

}