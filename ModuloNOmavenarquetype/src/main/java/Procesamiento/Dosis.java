package Procesamiento;

public class Dosis {
    double cantidadInicial;
    double diaDeIncremento;
    double cantidadIncremento;
    double cantidadFinal;

    public Dosis(double cantidadInicial, double diaDeIncremento, double cantidadIncremento, double cantidadFinal){
        if (diaDeIncremento == 1 || diaDeIncremento == 30) {
            throw new IllegalArgumentException("Invalid increment day");
        }
        this.cantidadInicial = cantidadInicial;
        this.diaDeIncremento = diaDeIncremento;
        this.cantidadIncremento = cantidadIncremento;
        this.cantidadFinal = cantidadFinal;
    }
    //Realizado con Maria Casás en la clase de 21/03/2024
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

    @Override
    public String toString() {
        return "Dosis{" +
                "cantidadInicial=" + cantidadInicial +
                ", diaDeIncremento=" + diaDeIncremento +
                ", cantidadIncremento=" + cantidadIncremento +
                ", cantidadFinal=" + cantidadFinal +
                '}';
    }

    public double getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(double cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public double getDiaDeIncremento() {
        return diaDeIncremento;
    }

    public void setDiaDeIncremento(double diaDeIncremento) {
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
