package es.uji.al426187.Lectores;

import es.uji.al426187.Estructuras.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVLableledFileReaderTest {

    private ReaderTemplate lector;
    private String separator;
    private TableWithLabels tabla;
    private List<Double> lista1;
    private List<Double> lista2;
    private List<Double> lista3;
    private List<String> cabeceras;


    @BeforeEach
    void iniciar(){
        separator = System.getProperty("file.separator");
        lector = new CSVLableledFileReader("."+separator+"Prueba2.csv");
        tabla = new TableWithLabels();
        lista1 = Arrays.asList(5.0,3.6,1.4,0.2);
        lista2 = Arrays.asList(5.5,2.3,4.0,1.3);
        lista3 = Arrays.asList(4.9,2.5,4.5,1.7);
        cabeceras = Arrays.asList("sepal length","sepal width","petal length","petal width");
        tabla.setHeaders(cabeceras);
        tabla.addRow(lista1,"Iris-setosa");
        tabla.addRow(lista2,"Iris-versicolor");
        tabla.addRow(lista3,"Iris-virginica");
    }

    @Test
    void numeroLineas() throws IOException {
        lector.readTableFromSource();
        assertEquals(3, lector.tabla.getNumRows());
    }

}