package es.uji.al426187;

import es.uji.al426187.KMeansException;
import es.uji.al426187.KMeans;
import es.uji.al426187.KNN;

import es.uji.al426187.Table;
public interface Algorithm < T extends Table,E,V >{
    void train(T Table) throws KMeansException;
    E estimate(V valor);
}