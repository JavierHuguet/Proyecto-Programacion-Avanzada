package es.uji.al426187;

import java.io.IOException;
import java.util.List;

public class KNN implements Algorithm<TableWithLabels, Integer, List<Double>> {



    public TableWithLabels tablaCompleta;

    public Distance distance;

    public KNN(Distance distance){
        this.tablaCompleta = new TableWithLabels();
        this.distance = distance;
    }




    @Override
    public void train(TableWithLabels data) {
        this.tablaCompleta = data;
    }

    @Override
    public Integer estimate(List<Double> data) {

        double distancia;
        double distancia_min = Double.MAX_VALUE;
        int claseCercana = 0;

        if (data == null) {
            throw new IllegalStateException("No hay nada en la tabla");
        }

        for (int i = 0; i < this.tablaCompleta.getNumRows(); i++) {

            distancia = calculateDistance(data, tablaCompleta.getRowAt(i).getData());

            if (distancia < distancia_min) {
                distancia_min = distancia;
                claseCercana = tablaCompleta.getRowAt(i).getNumberClass();
            }
        }
        return claseCercana;
    }



    public double calculateDistance(List<Double> p, List<Double> q) {
        return distance.calculateDistance(p,q);
    }
}