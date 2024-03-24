package org.example;
import Procesamiento.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
    //test dosis

    //crear dosis
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Creando dosis");
            System.out.println("introduzca la cantidad inicial: ");
            double cantidadInicial = Integer.parseInt(bufr.readLine());
            System.out.println("introduzca el dia de incremento: ");
            double diaDeIncremento = Integer.parseInt(bufr.readLine());
            System.out.println("introduzca la cantidad de incremento: ");
            double cantidadIncremento = Integer.parseInt(bufr.readLine());
            System.out.println("introduzca la cantidad final: ");
            double cantidadFinal = Integer.parseInt(bufr.readLine());
            Dosis dosistest = new Dosis(cantidadInicial, diaDeIncremento, cantidadIncremento, cantidadFinal);
            dosistest.calcularDosisDiaria(dosistest);

        } catch (IOException e) {
            System.out.println("Error en la entrada de datos");
        }

    }


}



