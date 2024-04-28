package es.uji.al426187.Distancias;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanDistanceTest {

    @Test
    @DisplayName("Calculo de distancia")
    public void calculoDistancia(){
        Distance prueba = new ManhattanDistance();
        List<Double> p = new ArrayList<>();
        p.add(8.0);
        p.add(4.0);
        List<Double> q = new ArrayList<>();
        q.add(4.0);
        q.add(2.0);
        assertEquals(6.0,prueba.calculateDistance(p,q));
    }

}