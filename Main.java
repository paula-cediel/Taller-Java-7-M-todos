package org.example;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double peso;
        int distancia;
        String tipo;
        double recTipo;

        // Pedir y validar peso
        do {
            System.out.print("Ingrese el peso del paquete (kg): ");
            peso = sc.nextDouble();
        } while (!validarPeso(peso));

        // Pedir distancia
        System.out.print("Ingrese la distancia en km: ");
        distancia = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        // Pedir tipo de envío
        do {
            System.out.print("Ingrese el tipo de envío (normal, express, prioritario): ");
            tipo = sc.nextLine();
            recTipo = calcularRecargoTipo(tipo);
        } while (recTipo == -1);

        // Cálculos
        double tarifaBase = calcularTarifaBase(peso);
        double recDistancia = calcularRecargoDistancia(distancia);

        // Generar e imprimir resumen
        String resumen = generarResumen(tarifaBase, recDistancia, recTipo);
        System.out.println(resumen);
    }

    // ===== MÉTODOS =====

    public static boolean validarPeso(double peso) {
        return peso > 0 && peso <= 50;
    }

    public static double calcularTarifaBase(double peso) {
        if (peso <= 1) {
            return 5;
        } else if (peso <= 5) {
            return 10;
        } else if (peso <= 20) {
            return 20;
        } else {
            return 50;
        }
    }

    public static double calcularRecargoDistancia(int km) {
        if (km < 10) {
            return 0;
        } else if (km <= 50) {
            return 5;
        } else if (km <= 200) {
            return 15;
        } else {
            return 30;
        }
    }

    public static double calcularRecargoTipo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "normal":
                return 0;
            case "express":
                return 15;
            case "prioritario":
                return 30;
            default:
                return -1;
        }
    }

    public static String generarResumen(double tarifaBase, double recDistancia, double recTipo) {
        double total = tarifaBase + recDistancia + recTipo;

        String separador = "";
        for (int i = 0; i < 30; i++) {
            separador += "-";
        }

        return separador + "\n"
                + "Tarifa base: $" + tarifaBase + "\n"
                + "Recargo por distancia: $" + recDistancia + "\n"
                + "Recargo por tipo de envío: $" + recTipo + "\n"
                + "Total final: $" + total + "\n"
                + separador;
    }
}
