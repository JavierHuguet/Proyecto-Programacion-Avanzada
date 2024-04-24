package es.uji.al426187;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{
    private final Map<String, Integer> labelsToIndex = new HashMap<>();



    public RowWithLabel getRowAt(int rowNumber) {
        return (RowWithLabel) super.getRowAt(rowNumber); }

    public void addRow(List<Double> datos, String etiqueta) {
        int nf;
        if (!labelsToIndex.containsValue(etiqueta)) {
            nf = labelsToIndex.size() + 1;
            labelsToIndex.put(etiqueta, nf);
        } else
            nf = labelsToIndex.get(etiqueta);
        RowWithLabel newRow = new RowWithLabel(datos, nf);
        rows.add(newRow);
    }
}
