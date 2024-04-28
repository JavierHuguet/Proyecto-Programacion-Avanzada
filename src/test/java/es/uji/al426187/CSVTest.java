// COMENTADO

package es.uji.al426187;
import es.uji.al426187.Lectores.CSV;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVTest {

    // SVEN: ruta hard coded - falta independencia de la plataforma. No ejecuta la prueba.
    // java.lang.NullPointerException: Cannot invoke "es.uji.al426187.Lectores.CSV.readTableWithLabels(String)" because "es.uji.al426187.CSVTest.Nuevocsv" is null

    String nombreFicheroMiles = "src\\main\\resources\\miles_dollars.csv";
    String nombreFicheroIris = "src\\main\\resources\\iris.csv";
    List<String> headerIris = List.of("sepal","length","sepal width","petal","petal length","petal width","class");
    List<String> headerMiles = List.of("Miles","Dollars");

    // SVEN: por qué és static?
    static CSV Nuevocsv;

    @BeforeAll
    static void initAll() {
        //Se ejecuta una vez antes que todos los test
    }

    // SVEN: parece que no se ejecuta initTest; Nuevocsv no está initializado
    // SVEN: en KNNTest si que se ejecuta "@BeforeAll ... inicio()", pero entonces falla por tener ruta incorrecta
    @BeforeEach
    void initTest(){
        Nuevocsv = new CSV();
    }
    @Test
    @DisplayName("Abre archivo Iris")
    public void existeArchivoIris() throws IOException {
        Nuevocsv.readTableWithLabels(nombreFicheroIris);

    }


    @Test
    @DisplayName("Abre Archivo Milesdollars")
    public void existeArchivoMilesDollars() throws IOException {
        Nuevocsv.readTable(nombreFicheroMiles);
    }

    @Test
    @DisplayName("Encabezados correctos Iris")
    public void headerCorrectoIris() throws IOException {
        assertEquals(headerIris,Nuevocsv.readTableWithLabels(nombreFicheroIris).getHeaders());
    }

    @Test
    @DisplayName("Encabezados correctos Miles dollars")
    public void headerCorrectoMiles() throws IOException {
        assertEquals(headerMiles,Nuevocsv.readTable(nombreFicheroMiles).getHeaders());
    }

    @Test
    public void numEncabezadosBienIris() throws IOException {
        existeArchivoIris();
        assertEquals(5,Nuevocsv.readTableWithLabels(nombreFicheroIris).getHeaders());
    }

    @Test
    public void numEncabezadosBienMilles() throws IOException {
        existeArchivoMilesDollars();
        assertEquals(2,Nuevocsv.readTable(nombreFicheroMiles).getHeaders());
    }

    @Test
    public void nFilasCorrectasIris() throws IOException {
        assertEquals(151,Nuevocsv.readTableWithLabels(nombreFicheroIris).rows.size());
    }

    @Test
    public void nFilasCorrectasMiles() throws IOException {
        assertEquals(26,Nuevocsv.readTable(nombreFicheroMiles).rows.size());
    }

}