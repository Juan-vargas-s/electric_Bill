package Repositories;

import java.util.Scanner;

public class Stadistics {
    private String[] users;
    private double[][] kilowatts;
    private String[] municipalities;

    public Stadistics(ArchiveUtil archiveUtil) {
        readUsers(archiveUtil);
        readKilowatts(archiveUtil);
        readMunicipalities(archiveUtil);
    }

    private void readUsers(ArchiveUtil archiveUtil) {
        Scanner scanner = archiveUtil.getArchive("user.txt");
        if (scanner == null) {
            users = new String[0];
            return;
        }
        int total = 0;
        StringBuilder allUsers = new StringBuilder();
        while (scanner.hasNextLine()) {
            String[] partes = scanner.nextLine().split(",");
            total += partes.length;
            for (String parte : partes) {
                allUsers.append(parte.trim()).append(",");
            }
        }
        scanner.close();
        users = new String[total];
        String[] temp = allUsers.toString().split(",");
        for (int i = 0; i < total; i++) {
            users[i] = temp[i];
        }
    }

    private void readKilowatts(ArchiveUtil archiveUtil) {
        Scanner scanner = archiveUtil.getArchive("kilowatts.txt");
        if (scanner == null) {
            kilowatts = new double[0][0];
            return;
        }
        int filas = 0;
        int columnas = 0;
        StringBuilder allLines = new StringBuilder();
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            allLines.append(linea).append(";");
            String[] partes = linea.split(",");
            columnas = Math.max(columnas, partes.length);
            filas++;
        }
        scanner.close();

        kilowatts = new double[filas][columnas];
        String[] lineas = allLines.toString().split(";");
        for (int i = 0; i < filas; i++) {
            String[] partes = lineas[i].split(",");
            for (int j = 0; j < partes.length; j++) {
                try {
                    kilowatts[i][j] = Double.parseDouble(partes[j].trim());
                } catch (NumberFormatException e) {
                    kilowatts[i][j] = 0.0;
                }
            }
        }
    }

    private void readMunicipalities(ArchiveUtil archiveUtil) {
        Scanner scanner = archiveUtil.getArchive("municipality.txt");
        if (scanner == null) {
            municipalities = new String[0];
            return;
        }
        int total = 0;
        StringBuilder allMunicipalities = new StringBuilder();
        while (scanner.hasNextLine()) {
            String[] partes = scanner.nextLine().split(",");
            total += partes.length;
            for (String parte : partes) {
                allMunicipalities.append(parte.trim()).append(",");
            }
        }
        scanner.close();
        municipalities = new String[total];
        String[] temp = allMunicipalities.toString().split(",");
        for (int i = 0; i < total; i++) {
            municipalities[i] = temp[i];
        }
    }

    public String[] getUsers() {
        return users;
    }

    public double[][] getKilowatts() {
        return kilowatts;
    }

    public String[] getMunicipalities() {
        return municipalities;
    }

    // Libera memoria de los arreglos
    public void clearArrays() {
        users = null;
        kilowatts = null;
        municipalities = null;
    }

    

    public void saveTableFormatted(ArchiveUtil archiveUtil, String outputFile, double precioPorKw) {
        // Escribe el encabezado (sobrescribe si existe)
        String header = String.format("%-25s%-25s%-25s%-25s%n",
                "Nombre de Usuarios", "Municipios", "total de Kilowatts", "total a pagar por kw/h");
        archiveUtil.setCreateArchive(header, outputFile, false);

        int n = Math.min(users.length, municipalities.length);
        for (int i = 0; i < n; i++) {
            double totalKw = 0.0;
            int filaKw = i < kilowatts.length ? i : -1;
            if (filaKw != -1) {
                for (int j = 0; j < kilowatts[filaKw].length; j++) {
                    totalKw += kilowatts[filaKw][j];
                }
            }
            double totalPagar = totalKw * precioPorKw;
            String linea = String.format("%-25s%-25s%-25.2f%-25.2f%n",
                    users[i], municipalities[i], totalKw, totalPagar);
            // Escribe cada línea agregando al archivo
            archiveUtil.setCreateArchive(linea, outputFile, true);
        }
    }
}
