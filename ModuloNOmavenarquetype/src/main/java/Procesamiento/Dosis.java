package Procesamiento;

public class Dosis {
    double cantidadInicial;
    double diaDeIncremento;
    double cantidadIncremento;
    double cantidadFinal;

    public Dosis(double cantidadInicial, double diaDeIncremento, double cantidadIncremento, double cantidadFinal){
        this.cantidadInicial = cantidadInicial;
        this.diaDeIncremento = diaDeIncremento;
        this.cantidadIncremento = cantidadIncremento;
        this.cantidadFinal = cantidadFinal;
    }
    //Realizado con Maria Casás en la clase de 21/03/2024
    public void calcularDosisDiaria (Dosis newdosis){
        double comidaDia = cantidadInicial;
        double incrementoDiario = newdosis.getcantidadIncremento()/ (newdosis.getDiaDeIncremento()-1);
        double decrementoDiario = (newdosis.getcantidadIncremento() + newdosis.getCantidadInicial() - newdosis.getCantidadFinal())/ (30 - getDiaDeIncremento());

        for (double dia = 2; dia <= 30; dia++) {
            if (dia <= newdosis.getDiaDeIncremento()) {
                comidaDia = comidaDia + incrementoDiario;
                System.out.println("Día " + dia + ": Se deben proporcionar " + comidaDia + " unidades de comida.");
            } else {
                comidaDia = comidaDia - decrementoDiario;
                System.out.println("Día " + dia + ": Se deben proporcionar " + comidaDia + " unidades de comida.");
            }
        }
    }

    public void setCantidadInicial(double cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public void setDiaDeIncremento(double diaDeIncremento) {
        this.diaDeIncremento = diaDeIncremento;
    }

    public void setcantidadIncremento(double cantidadIncremento) {
        this.cantidadIncremento = cantidadIncremento;
    }

    public void setCantidadFinal(double cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }

    public double getCantidadInicial() {
        return cantidadInicial;
    }

    public double getDiaDeIncremento() {
        return diaDeIncremento;
    }

    public double getcantidadIncremento() {
        return cantidadIncremento;
    }

    public double getCantidadFinal() {
        return cantidadFinal;
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
}
