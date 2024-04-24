// COMENTADO
//

package es.uji.al426187;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {

    // SVEN: por qué son static?
    static KNN objetoKNN;
    static CSV objetoCSV;
    private final List<Double> ListaSetosa = new LinkedList<>(){{add(4.4);add(2.9);add(1.4);add(0.4);}};



    @BeforeAll
    static void initAll() {
        //Se ejecuta una vez antes que todos los test
    }

    @BeforeEach
    public void inicio() throws IOException {
        objetoKNN = new KNN();
        objetoCSV = new CSV();
    }

    @Test
    public void estimarSetosa() throws IOException {
        // SVEN: ruta hard coded - falta independencia de la plataforma. No ejecuta la prueba.
        // java.io.FileNotFoundException: src\main\resources\iris.csv (No such file or directory)
        TableWithLabels tabla = (TableWithLabels) objetoCSV.readTableWithLabels("src\\main\\resources\\iris.csv");
        objetoKNN.train(tabla);
        assertEquals(2, objetoKNN.estimate(ListaSetosa));
    }
}