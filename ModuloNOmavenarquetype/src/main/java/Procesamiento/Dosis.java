package Procesamiento;

/**
 * The Dosis class represents a dose in an experiment.
 * It contains information about the dose such as its initial amount, increment day, increment amount, and final amount.
 */
public class Dosis {
    // The initial amount of the dose
    double cantidadInicial;
    // The day on which the dose is incremented
    int diaDeIncremento;
    // The amount by which the dose is incremented
    double cantidadIncremento;
    // The final amount of the dose
    double cantidadFinal;

    /**
     * Constructs a new Dosis with the given parameters.
     * @param cantidadInicial The initial amount of the dose
     * @param diaDeIncremento The day on which the dose is incremented
     * @param cantidadIncremento The amount by which the dose is incremented
     * @param cantidadFinal The final amount of the dose
     * @throws IllegalArgumentException If the increment day is 1 or 30, or if the initial or final amount exceeds 300, or if the increment amount exceeds 300 minus the initial amount
     */
    public Dosis(double cantidadInicial, int diaDeIncremento, double cantidadIncremento, double cantidadFinal){
        if (diaDeIncremento == 1 || diaDeIncremento == 30) {
            throw new IllegalArgumentException("Invalid increment day");
        }
        if (cantidadInicial > 300 || cantidadFinal > 300) {
            throw new IllegalArgumentException("cantidadInicial and cantidadFinal must not exceed 300");
        }
        if (cantidadIncremento > (300 - cantidadInicial)) {
            throw new IllegalArgumentException("cantidadIncremento must not exceed 300 - cantidadInicial");
        }
        this.cantidadInicial = cantidadInicial;
        this.diaDeIncremento = diaDeIncremento;
        this.cantidadIncremento = cantidadIncremento;
        this.cantidadFinal = cantidadFinal;
    }

    /**
     * Calculates the daily dose for a given Dosis object.
     * @param newdosis The Dosis object for which the daily dose is calculated
     */
    public void calcularDosisDiaria (Dosis newdosis){
        double comidaDia = cantidadInicial;
        double incrementoDiario = newdosis.getCantidadIncremento()/ (newdosis.getDiaDeIncremento()-1);
        double decrementoDiario = (newdosis.getCantidadIncremento() + newdosis.getCantidadInicial() - newdosis.getCantidadFinal())/ (30 - getDiaDeIncremento());

        for (int dia = 2; dia <= 30; dia++) {
            if (dia <= newdosis.getDiaDeIncremento()) {
                comidaDia = comidaDia + incrementoDiario;
                System.out.println("Día " + dia + ": Se deben proporcionar " + comidaDia + " unidades de comida.");
            } else {
                comidaDia = comidaDia - decrementoDiario;
                System.out.println("Día " + dia + ": Se deben proporcionar " + comidaDia + " unidades de comida.");
            }
        }
    }

    /**
     * Returns a string representation of the Dosis.
     * @return A string representation of the Dosis
     */
    @Override
    public String toString() {
        return "Dosis{" +
                "  cantidadInicial=" + cantidadInicial +
                ", diaDeIncremento=" + diaDeIncremento +
                ", cantidadIncremento=" + cantidadIncremento +
                ", cantidadFinal=" + cantidadFinal +
                '}';
    }

    // Getters and setters for the Dosis's fields
    public double getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(double cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public int getDiaDeIncremento() {
        return diaDeIncremento;
    }

    public void setDiaDeIncremento(int diaDeIncremento) {
        this.diaDeIncremento = diaDeIncremento;
    }

    public double getCantidadIncremento() {
        return cantidadIncremento;
    }

    public void setCantidadIncremento(double cantidadIncremento) {
        this.cantidadIncremento = cantidadIncremento;
    }

    public double getCantidadFinal() {
        return cantidadFinal;
    }

    public void setCantidadFinal(double cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }
}
