package Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Procesamiento.*;
import GestionDeMemoria.*;

/**
 * The MainLaboratorio class is the main entry point of the application.
 * It contains a list of laboratories and a main method that provides a menu for the user to interact with the application.
 */
public class MainLaboratorio {
    // List of laboratories
    public static List<Laboratorio> laboratorios;

    /**
     * The main method of the application.
     * It provides a menu for the user to interact with the application.
     * The user can choose to open an experiment file, create a new experiment, create a bacteria population, view all bacteria populations,
     * delete a bacteria population, view detailed information of a bacteria population, save the current state, save the current state as a new file, or exit the application.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Variable to store the user's menu choice
        int option;

        // Loop until the user chooses to exit
        do {
            // Print the menu
            System.out.println("Menu:");
            System.out.println("1. Abrir un archivo que contenga un experimento");
            System.out.println("2. Crear un nuevo experimento");
            System.out.println("3. Crear una población de bacterias y añadirla al experimento actual");
            System.out.println("4. Visualizar los nombres de todas las poblaciones de bacterias del experimento actual");
            System.out.println("5. Borrar una población de bacterias del experimento actual");
            System.out.println("6. Ver información detallada de una población de bacterias del experimento actual");
            System.out.println("7. Guardar");
            System.out.println("8. Guardar como");
            System.out.println("0. Salir");

            // Prompt the user to enter a choice
            System.out.print("Ingrese la opción deseada: ");
            option = scanner.nextInt();
            scanner.nextLine();

            // Perform an action based on the user's choice
            switch (option) {
                case 1:
                    System.out.println("Opción 1 seleccionada: Abrir un archivo que contenga un experimento");
                    System.out.println("Ingrese la ruta del archivo:");
                    String path = scanner.nextLine();
                    Experimento experimento = LeerDeMemoria.leerExperimentoDesdeArchivo(path);
                    if (experimento != null) {
                        System.out.println("Experimento cargado con éxito.");
                        System.out.println(experimento);
                    } else {
                        System.out.println("No se pudo cargar el experimento.");
                    }

                    break;
                case 2:
                    System.out.println("Opción 2 seleccionada: Crear un nuevo experimento");

                    if (laboratorios.isEmpty()) {
                        System.out.println("No hay laboratorios creados. Por favor, cree uno.");
                        System.out.println("Ingrese el nombre del laboratorio:");
                        String nombreLab = scanner.nextLine();
                        Laboratorio newLaboratorio = new Laboratorio(nombreLab);
                        laboratorios.add(newLaboratorio);
                        System.out.println("Laboratorio creado con éxito.");
                    }
                    System.out.println("Ingrese el nombre del laboratorio en el que desea crear el experimento:");
                    String nombreLab = scanner.nextLine();
                    Laboratorio laboratorio = null;
                    for (Laboratorio lab : laboratorios) {
                        if (lab.getLabName().equals(nombreLab)) {
                            laboratorio = lab;
                            break;
                        }
                    }

                    if (laboratorio == null) {
                        System.out.println("No se encontró el laboratorio con el nombre dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID del experimento:");
                    int idExperimento = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingrese el nombre del experimento:");
                    String nombreExp = scanner.nextLine();

                    System.out.println("Ingrese la ruta del archivo:");
                    String pathArchivo = scanner.nextLine();
                    ArrayList<Poblacion> poblaciones = new ArrayList<>();

                    laboratorio.crearExperimento(idExperimento, nombreExp, pathArchivo, poblaciones);
                    System.out.println("Experimento creado con éxito en el laboratorio " + nombreLab + ".");
                    break;
                case 3:
                    System.out.println("Opción 3 seleccionada: Crear una población de bacterias y añadirla a un experimento");

                    System.out.println("Ingrese el nombre del laboratorio en el que desea crear la población:");
                    String nombreLabCase3 = scanner.nextLine();
                    Laboratorio laboratorioCase3 = null;
                    for (Laboratorio lab : laboratorios) {
                        if (lab.getLabName().equals(nombreLabCase3)) {
                            laboratorioCase3 = lab;
                            break;
                        }
                    }
                    if (laboratorioCase3 == null) {
                        System.out.println("No se encontró el laboratorio con el nombre dado.");
                        break;
                    }

                    if (laboratorioCase3.getExperimentos().isEmpty()) {
                        System.out.println("No hay experimentos creados en este laboratorio. Por favor, cree uno.");
                        System.out.println("Ingrese el ID del experimento:");
                        int idExperimentoCase3 = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Ingrese el nombre del experimento:");
                        String nombreExp2 = scanner.nextLine();

                        System.out.println("Ingrese la ruta del archivo:");
                        String pathArchivo2= scanner.nextLine();
                        ArrayList<Poblacion> poblaciones2 = new ArrayList<>();

                        laboratorioCase3.crearExperimento(idExperimentoCase3, nombreExp2, pathArchivo2, poblaciones2);
                        System.out.println("Experimento creado con éxito en el laboratorio " + nombreLabCase3 + ".");
                    }

                    System.out.println("Ingrese el ID del experimento en el que desea crear la población:");
                    int idExperimentoCase3 = scanner.nextInt();
                    scanner.nextLine();

                    Experimento experimentoCase3 = null;
                    for (Experimento exp : laboratorioCase3.getExperimentos()) {
                        if (exp.getIdExperimento() == idExperimentoCase3) {
                            experimentoCase3 = exp;
                            break;
                        }
                    }
                    if (experimentoCase3 == null) {
                        System.out.println("No se encontró el experimento con el ID dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID de la población:");
                    int idPoblacion = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingrese el nombre de la población:");
                    String nombrePoblacion = scanner.nextLine();

                    Date fechaInicio = null;
                    Date fechaFin = null;
                    try {
                        System.out.println("Ingrese la fecha de inicio (yyyy-MM-dd):");
                        String fechaInicioStr = scanner.nextLine();
                        fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioStr);

                        System.out.println("Ingrese la fecha de fin (yyyy-MM-dd):");
                        String fechaFinStr = scanner.nextLine();
                        fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                    }

                    System.out.println("Ingrese el número de bacterias:");
                    int numBacterias = scanner.nextInt();

                    System.out.println("Ingrese la temperatura:");
                    float temperatura = scanner.nextFloat();

                    System.out.println("Ingrese la luminosidad (ALTA, MEDIA, BAJA):");
                    Luminosidad luminosidad = Luminosidad.valueOf(scanner.next().toUpperCase());

                    System.out.println("Ingrese la dosis de comida (cantidad inicial, día de incremento, cantidad de incremento, cantidad final):");
                    double cantidadInicial = scanner.nextDouble();
                    int diaDeIncremento = scanner.nextInt();
                    double cantidadIncremento = scanner.nextDouble();
                    double cantidadFinal = scanner.nextDouble();
                    Dosis dosisComida = new Dosis(cantidadInicial, diaDeIncremento, cantidadIncremento, cantidadFinal);

                    Poblacion poblacionCase3 = new Poblacion(nombrePoblacion, idPoblacion, fechaInicio, fechaFin, numBacterias, temperatura, luminosidad, dosisComida);
                    experimentoCase3.getPoblaciones().add(poblacionCase3);
                    System.out.println("Población creada con éxito en el experimento " + idExperimentoCase3 + ".");
                    break;
                case 4:
                    System.out.println("Opción 4 seleccionada: Visualizar los nombres de todas las poblaciones de bacterias de un experimento ");

                    System.out.println("Ingrese el nombre del laboratorio con el experimento que desea ver:");
                    String nombreLab2 = scanner.nextLine();

                    Laboratorio laboratorio2 = null;
                    for (Laboratorio lab : laboratorios) {
                        if (lab.getLabName().equals(nombreLab2)) {
                            laboratorio2 = lab;
                            break;
                        }
                    }
                    if (laboratorio2 == null) {
                        System.out.println("No se encontró el laboratorio con el nombre dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID del experimento:");
                    int idExperimento2 = scanner.nextInt();
                    scanner.nextLine();

                    Experimento experimento2 = null;
                    for (Experimento exp : laboratorio2.getExperimentos()) {
                        if (exp.getIdExperimento() == idExperimento2) {
                            experimento2 = exp;
                            break;
                        }
                    }
                    if (experimento2 == null) {
                        System.out.println("No se encontró el experimento con el ID dado.");
                        break;
                    }

                    for (Poblacion poblacion : experimento2.getPoblaciones()) {
                        System.out.println("Poblacion ID: " + poblacion.getIdPoblacion() + ", Nombre: " + poblacion.getNombre());
                    }
                    break;
                case 5:
                    System.out.println("Opción 5 seleccionada: Borrar una población de bacterias del experimento actual");

                    System.out.println("Ingrese el nombre del laboratorio:");
                    String nombreLab3 = scanner.nextLine();

                    // Find the laboratory with the given name
                    Laboratorio laboratorio3 = null;
                    for (Laboratorio lab : laboratorios) {
                        if (lab.getLabName().equals(nombreLab3)) {
                            laboratorio3 = lab;
                            break;
                        }
                    }

                    if (laboratorio3 == null) {
                        System.out.println("No se encontró el laboratorio con el nombre dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID del experimento:");
                    int idExperimento3 = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left-over

                    // Find the experiment with the given ID
                    Experimento experimento3= null;
                    for (Experimento exp : laboratorio3.getExperimentos()) {
                        if (exp.getIdExperimento() == idExperimento3) {
                            experimento3 = exp;
                            break;
                        }
                    }

                    if (experimento3 == null) {
                        System.out.println("No se encontró el experimento con el ID dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID de la población que desea borrar:");
                    int idPoblacion3 = scanner.nextInt();
                    scanner.nextLine();

                    Poblacion poblacionToRemove = null;
                    for (Poblacion poblacion : experimento3.getPoblaciones()) {
                        if (poblacion.getIdPoblacion() == idPoblacion3) {
                            poblacionToRemove = poblacion;
                            break;
                        }
                    }

                    if (poblacionToRemove == null) {
                        System.out.println("No se encontró la población con el ID dado.");
                    } else {
                        experimento3.borrarPoblacion(poblacionToRemove);
                        System.out.println("Población borrada con éxito del experimento " + idExperimento3 + ".");
                    }
                    break;
                case 6:
                    System.out.println("Opción 6 seleccionada: Ver información detallada de una población de bacterias de un experimento");

                    System.out.println("Ingrese el nombre del laboratorio:");
                    String nombreLab6 = scanner.nextLine();
                    Laboratorio laboratorio6 = null;
                    for (Laboratorio lab : laboratorios) {
                        if (lab.getLabName().equals(nombreLab6)) {
                            laboratorio6 = lab;
                            break;
                        }
                    }
                    if (laboratorio6 == null) {
                        System.out.println("No se encontró el laboratorio con el nombre dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID del experimento:");
                    int idExperimento6 = scanner.nextInt();
                    scanner.nextLine();
                    Experimento experimento6 = null;
                    for (Experimento exp : laboratorio6.getExperimentos()) {
                        if (exp.getIdExperimento() == idExperimento6) {
                            experimento6 = exp;
                            break;
                        }
                    }
                    if (experimento6 == null) {
                        System.out.println("No se encontró el experimento con el ID dado.");
                        break;
                    }

                    System.out.println("Ingrese el nombre de la población que desea ver:");
                    String nombrePoblacion6 = scanner.nextLine();
                    String result6 = experimento6.mostrarPoblacion(nombrePoblacion6);
                    System.out.println(result6);
                    Poblacion poblacion6 = null;
                    for (Poblacion poblacion : experimento6.getPoblaciones()) {
                        if (poblacion.getNombre().equals(nombrePoblacion6)) {
                            poblacion6 = poblacion;
                            break;
                        }
                    }
                    if (poblacion6 != null) {
                        Dosis dosis = poblacion6.getDosisComida();
                        dosis.calcularDosisDiaria(dosis);
                    } else {
                        System.out.println("No se encontró la población con el nombre dado.");
                    }

                    System.out.println(result6);
                    break;
                case 7:
                    System.out.println("Opción 7 seleccionada: Guardar");
                    System.out.println("Ingrese el nombre del laboratorio:");
                    String nombreLab7 = scanner.nextLine();
                    Laboratorio laboratorio7 = null;
                    for (Laboratorio lab : laboratorios) {
                        if (lab.getLabName().equals(nombreLab7)) {
                            laboratorio7 = lab;
                            break;
                        }
                    }
                    if (laboratorio7 == null) {
                        System.out.println("No se encontró el laboratorio con el nombre dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID del experimento:");
                    int idExperimento7 = scanner.nextInt();
                    scanner.nextLine();
                    Experimento experimento7 = null;
                    for (Experimento exp : laboratorio7.getExperimentos()) {
                        if (exp.getIdExperimento() == idExperimento7) {
                            experimento7 = exp;
                            break;
                        }
                    }
                    if (experimento7 == null) {
                        System.out.println("No se encontró el experimento con el ID dado.");
                        break;
                    }

                    boolean result7 = GuardarEnMemoria.guardar(experimento7);
                    if (result7) {
                        System.out.println("Experimento guardado con éxito.");
                    } else {
                        System.out.println("Hubo un error al guardar el experimento.");
                    }
                    break;
                case 8:
                    System.out.println("Opción 8 seleccionada: Guardar como");
                    System.out.println("Ingrese el nombre del laboratorio:");
                    String nombreLab8 = scanner.nextLine();
                    Laboratorio laboratorio8 = null;
                    for (Laboratorio lab : laboratorios) {
                        if (lab.getLabName().equals(nombreLab8)) {
                            laboratorio8 = lab;
                            break;
                        }
                    }
                    if (laboratorio8 == null) {
                        System.out.println("No se encontró el laboratorio con el nombre dado.");
                        break;
                    }

                    System.out.println("Ingrese el ID del experimento:");
                    int idExperimento8 = scanner.nextInt();
                    scanner.nextLine();

                    Experimento experimento8 = null;
                    for (Experimento exp : laboratorio8.getExperimentos()) {
                        if (exp.getIdExperimento() == idExperimento8) {
                            experimento8 = exp;
                            break;
                        }
                    }
                    if (experimento8 == null) {
                        System.out.println("No se encontró el experimento con el ID dado.");
                        break;
                    }
                    System.out.println("Ingrese la nueva ruta del archivo:");
                    String newPath = scanner.nextLine();

                    experimento8.setPathArchivo(newPath);
                    boolean result8 = GuardarEnMemoria.guardar(experimento8);
                    if (result8) {
                        System.out.println("Experimento guardado con éxito en la nueva ruta.");
                    } else {
                        System.out.println("Hubo un error al guardar el experimento en la nueva ruta.");
                    }

                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (option != 0);
        // Close the scanner
        scanner.close();
    }
}
