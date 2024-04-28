

package es.uji.al426187;

import es.uji.al426187.Estructuras.Row;

import java.util.ArrayList;
import java.util.List;


public class Table {


    protected List<Row> rows = new ArrayList<>();
    private List<String> headers = new ArrayList<>();


    public List<String> getHeaders() { return headers; }

    public void addRow(List<Double> datos) { this.rows.add(new Row(datos)); }

    public Row getRowAt(int rowNumber) {
        return rows.get(rowNumber);
    }

    public int getNumCols()
    {
        return rows.get(0).getData().size();
    }
    public int getNumRows() { return rows.size(); }

    public void setHeaders(List<String> etiquetas) {
        this.headers = etiquetas;
    }





}
