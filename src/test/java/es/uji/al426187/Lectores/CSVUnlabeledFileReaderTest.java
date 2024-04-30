package es.uji.al426187.Lectores;

import es.uji.al426187.Estructuras.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVUnlabeledFileReaderTest {

    private ReaderTemplate lector;
    private String separator;
    private Table tabla;
    private List<Double> lista1;
    private List<Double> lista2;
    private List<Double> lista3;
    private List<String> cabeceras;


    @BeforeEach
    void iniciar(){
        separator = System.getProperty("file.separator");
        lector = new CSVUnlabeledFileReader("."+separator+"Prueba.csv");
        tabla = new Table();
        lista1 = Arrays.asList(2699.0,3371.0);
        lista2 = Arrays.asList(2806.0,3998.0);
        lista3 = Arrays.asList(3082.0,3555.0);
        cabeceras = Arrays.asList("Miles","Dollars");
        tabla.setHeaders(cabeceras);
        tabla.addRow(lista1);
        tabla.addRow(lista2);
        tabla.addRow(lista3);
    }

    @Test
    void numeroLineas() throws IOException {
        lector.readTableFromSource();
        assertEquals(3, lector.tabla.getNumRows());
    }


}