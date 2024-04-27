package es.uji.al426187;

import java.util.List;

public class EuclideanDistance implements Distance{
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        double distancia = 0;

        for (int i = 0; i < p.size(); i++) {
            distancia += Math.sqrt(Math.pow(p.get(i) - q.get(i), 2));
        }
        return distancia;

    }
}
