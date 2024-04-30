package es.uji.al426187.MachineLearning;

import es.uji.al426187.Distancias.Distance;
import es.uji.al426187.Estructuras.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels, Integer, List<Double>> {

    public TableWithLabels tablaCompleta;

    private Distance distance;

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

            distancia = distance.calculateDistance(data, tablaCompleta.getRowAt(i).getData());

            if (distancia < distancia_min) {
                distancia_min = distancia;
                claseCercana = tablaCompleta.getRowAt(i).getNumberClass();
            }
        }
        return claseCercana;
    }
}