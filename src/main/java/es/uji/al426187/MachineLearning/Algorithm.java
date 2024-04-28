package es.uji.al426187.MachineLearning;

import es.uji.al426187.Table;
public interface Algorithm < T extends Table,E,V >{
    void train(T Table) throws KMeansException;
    E estimate(V valor);
}