// COMENTADO
//
// - ¡Necesitas usar paquetes para organizar tu código!
// - CORREGIDO MAS O MENOS definición de la interfaz “Algortihm” correcta, pero kMeans no implementa la interfaz
// - falta SongRecSys, no ejecuta el código; sin que kMeans implemente Algorithm, no puede funcionar
// - SongRecSys está en las pruebas, pero no ejecuta (como kMeans no implementa la interfaz Algorithm):
// Exception in thread "main" java.lang.ClassCastException: class es.uji.al426187.KMeans cannot be cast to class
// es.uji.al426187.Algorithm (es.uji.al426187.KMeans and es.uji.al426187.Algorithm are in unnamed module of loader 'app')
//	at es.uji.al426187.SongRecSys.<init>(SongRecSys.java:28)
//	at es.uji.al426187.SongRecSys.main(SongRecSys.java:88)
// - duplicación del método para calcular distancia (en KNN y kMeans)
// - ninguna de las pruebas ejecuta (por varios problemas, no solo la ruta), menos kMeans,
// pero las pruebas en kMeanTest no son válidas para comprobar kMeans (ver código)
// - Para hacer bien las pruebas de kMeans, tienes que crear una table con unos pocos datos distribuidos en unos pocos grupos
// claramente distinguibles - y comprobar con 2 (nuevos) puntos muy claramente dentro de un grupo (el estimate tiene que ser igual) o muy
// claramente en diferentes grupos (el estimate tiene que ser diferente); ver enunciado
// - Verifica la jerarquía de excepciones en Java y verifica si tu propia excepción no puede heredar de una clase más específica (en lugar de generalmente de "Excepción”), por ejemplo, de “RunTimeException”.
// - seleccionarPrototiposIniciales(…): método con una perdida de rendimiento (O(n) donde O(numCluster) es posible) y espacio (n, donde numClusters es posible).
// - faltan pruebas para la excepción (era opcional - entonces eso no es esencial)
//
// - En resumen: las pruebas no ejecutan (menos kMeans, pero estas pruebas no comprueban el algoritmo bien), no ejecuta SongRecSys,
// habéis solucionado algunas cosas en las clases anteriores (p.e., números mágicos, bien!) pero habéis introducido nuevos errores también (p.e. Table extends CSV),
// hay duplicación del método para calcular distancia (KNN y kMeans), hay un problema de rendimiento, faltan paquetes, errores de convenciones de nombres, y varios otros errores.
//
// Lo siento, pero hay demasiados problemas/errores, no puedo aprobar esta entrega.
//
// En la próxima entrega, solo me voy a centrar en la implementación del patrón estrategia y método de la plantilla
// (+ pruebas correspondientes, + detección del patrón existente en el código), así que tenéis hasta la entrega final para solucionar estos problemas.

package es.uji.al426187;

import java.util.*;


import java.util.*;


public class KMeans implements Algorithm<Table, Integer, List<Double>> {
    private final int numClusters; //numero de grupos
    private final int numIterations;
    private final long seed;

    public Distance distance;


    private final List<Row> prototipos = new ArrayList<>();

    public KMeans(int numClusters, int numIterations, long seed, Distance distance) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.distance = distance;

    }



    @Override
    public void train(Table datos) throws KMeansException {

        if (numClusters > datos.getNumRows()) {
            throw new KMeansException("El número de grupos es mayor que el número en la tabla");
        } else {
            seleccionarPrototiposIniciales(datos); //tantos como grupos
            List<Integer> asignaciones;

            for (int i = 0; i < numIterations; i++) {

                asignaciones = asignarGrupos(datos);
                actualizarCentroides(datos, asignaciones);
            }
        }
    }

    @Override
    public Integer estimate(List<Double> dato) {
        // SVEN: no tendría que ser necesario crear un "Table" con una "Row" para hacer el "asignargrupos(...).
        // Necesitas más abstraciones y/o cambiar "asignargrupos"
        Table data = new Table();
        data.addRow(dato);
        List<Integer> grupo = asignarGrupos(data);
        return grupo.get(0);
    }

    // SVEN: - método con una perdida de rendimiento (O(n) donde
    // O(numCluster) es posible) y espacio (n, donde numClusters es posible).
    public void seleccionarPrototiposIniciales(Table datos) {
        List<Integer> indices = new ArrayList<>();
        // SVEN: si tenemos por ejemplo 1.000.000 rows,
        // vas a crear una lista de 1.000.000 elementos, solo para seleccionar
        // numClusters (p.e. 3 en nuestros ejemplos) rows aleatoriamente?
        for (int i = 0; i < datos.getNumRows(); i++) {
            indices.add(i);
        }

        Collections.shuffle(indices, new Random(seed));

        for (int i = 0; i < numClusters; i++) {
            prototipos.add(datos.getRowAt(indices.get(i)));
        }

    }

    public void actualizarCentroides(Table datos, List<Integer> asignaciones) {

        List<Double> zeros = new ArrayList<>(Collections.nCopies(datos.getNumCols(), 0.0));
        List<Double> contprototipos = new ArrayList<>(Collections.nCopies(prototipos.size(), 0.0));

        for (int j = 0; j < prototipos.size(); j++) {
            prototipos.set(j, new Row(zeros));
        }

        int j;
        Row suma;

        for (int i = 0; i < datos.getNumRows(); i++) {
            j = asignaciones.get(i);
            suma = new Row(sumar(prototipos.get(j).getData(), datos.getRowAt(i).getData()));
            prototipos.set(j, suma);

            contprototipos.set(j, contprototipos.get(j) + 1);
        }

        for (int k = 0; k < prototipos.size(); k++) {
            Row prod = new Row(multiplicar(prototipos.get(k).getData(), 1 / contprototipos.get(k)));
            prototipos.set(k, prod);
        }

    }

    List<Double> sumar(List<Double> a, List<Double> b) {

        List<Double> suma = new ArrayList<>();

        for (int i = 0; i < b.size(); i++) {
            suma.add(i, a.get(i) + b.get(i));
        }
        return suma;
    }

    List<Double> multiplicar(List<Double> a, Double factor) {

        ArrayList<Double> multiplica = new ArrayList<>(a.size());

        for (Double aDouble : a) {
            multiplica.add(aDouble * factor);
        }

        return multiplica;
    }

    // SVEN: duplicación de código: calculateDistance ya está en KNN.
    public double calculateDistance(List<Double> p, List<Double> q) {
        return distance.calculateDistance(p,q);

    }


    private List<Integer> asignarGrupos(Table datos) {
        List<Integer> asignaciones = new ArrayList<>();
        double d;
        int asignacionesnum = 0;

        for (int i = 0; i < datos.getNumRows(); i++) {

            double dMin = Double.POSITIVE_INFINITY;
            Row dato_actual = datos.getRowAt(i);

            for (int j = 0; j < prototipos.size(); j++) {

                d = calculateDistance(dato_actual.getData(), prototipos.get(j).getData());
                if (d < dMin) {
                    asignacionesnum = j;
                    dMin = d;

                }
            }
            asignaciones.add(asignacionesnum);
        }
        return asignaciones;
    }
}
