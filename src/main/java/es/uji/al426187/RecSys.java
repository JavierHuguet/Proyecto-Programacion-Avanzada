package es.uji.al426187;

import es.uji.al426187.MachineLearning.Algorithm;
import es.uji.al426187.MachineLearning.KMeansException;

import java.util.ArrayList;
import java.util.List;

public class RecSys {

    private final Algorithm algorithm;
    private List<Integer> seleccionados = new ArrayList<>();
    private List<String> itemNames;
    private List<Integer> estimado = new ArrayList<>();
    private Table testDatos;
    List<String> recomendaciones = new ArrayList<>();

    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void train(Table trainData) throws KMeansException {
        algorithm.train(trainData);
    }

    // SVEN: es un método interno, no tiene que ser public.
    public void estimate(){

        for (int i = 0; i < testDatos.getNumRows(); i++) {

            estimado.add((Integer) algorithm.estimate(testDatos.getRowAt(i).getData()));
        }
    }
    public void run(Table testData, List<String> testItemNames) {

        itemNames =  testItemNames;
        System.out.println("Testdatos:"+ testData.getNumRows());
        testDatos = testData;
        estimate();
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) {

        seleccionados.clear();

        recomendaciones.clear();



        int idx = findName(nameLikedItem);

        //List<Double> data = testDatos.getRowAt(idx).getData();
        //int lbl = (int) algorithm.estimate(data);
        int lbl = estimado.get(idx);

        selectItems(lbl, numRecommendations, idx);
        return getNamesSelectedItems();
    }

    private Integer findName(String itemName) {
        for (int i = 0; i < itemNames.size(); i++) {

            if (itemName.equals(itemNames.get(i)))
                return i;
            //System.out.println(predictions);
        }
        return -1;
    }

    private void selectItems(int labelLikedItem, int numRec, int idxLikedItem){
        int incluidos = 0;

        for (int j = 0; j < estimado.size(); j++) {

            if (incluidos >= numRec) break;
            if (estimado.get(j) == labelLikedItem && j != idxLikedItem) {

                seleccionados.add(j);
                incluidos++;
            }
        }
    }

    private List<String> getNamesSelectedItems() {
        for (Integer seleccionado : seleccionados) {
            recomendaciones.add(itemNames.get(seleccionado));
        }
        return recomendaciones;
    }

}