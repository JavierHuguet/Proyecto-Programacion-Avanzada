package es.uji.al426187.Distancias;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {
    @Test
    @DisplayName("Calculo de distancia")
    public void calculoDistancia(){
        Distance prueba = new EuclideanDistance();
        List<Double> p = new ArrayList<>();
        p.add(2.0);
        p.add(2.0);
        List<Double> q = new ArrayList<>();
        q.add(4.0);
        q.add(2.0);
        assertEquals(2.0,prueba.calculateDistance(p, q));
    }

}