// COMENTADO

package es.uji.al426187;

import org.junit.jupiter.api.*;

import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class KMeansTest {

    // SVEN: inicialización vacía
    @BeforeAll
    static void initAll() {
        //Se ejecuta una vez antes que todos los test
    }

    @Test
    @DisplayName("Probamos el método train")
    public void testKmeansNotDeterministic() throws KMeansException {
        Table datos = new Table();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            List<Double> fila = Arrays.asList(rand.nextDouble(), rand.nextDouble());
            datos.addRow(fila);
        }
        long semilla = rand.nextLong();
        KMeans kmeans1 = new KMeans(2, 10, semilla, new EuclideanDistance());
        kmeans1.train(datos);
        KMeans kmeans2 = new KMeans(2, 10, semilla, new EuclideanDistance());
        kmeans2.train(datos);
        // SVEN: evidentemente, pasa la prueba, pero solo confirma que la versión de kMeans es determinista: dos versiones del algoritmo hacen lo mismo con los mismos datos de entrada
        // No dice nada sobre el correcto funcionamiento del algoritmo.
        assertEquals(kmeans1.estimate(Arrays.asList(0.0, 0.0)), kmeans2.estimate(Arrays.asList(0.0, 0.0)));
    }
}