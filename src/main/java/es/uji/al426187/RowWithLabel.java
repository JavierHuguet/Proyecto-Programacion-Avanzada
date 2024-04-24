package es.uji.al426187;

import java.util.List;

public class RowWithLabel extends Row{
    protected int numberClass;

    public RowWithLabel(List<Double> d, int n) {
        super(d);
        this.numberClass = n;
    }

    public int getNumberClass() {
        return numberClass;
    }


}
