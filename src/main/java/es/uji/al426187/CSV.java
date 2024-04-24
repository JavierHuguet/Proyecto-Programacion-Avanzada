package es.uji.al426187;
import java.io.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class CSV {

    public Table readTable(String fileName) throws IOException {
        Table table = new Table();
        File archivo = new File(fileName);


        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea;
        linea = br.readLine();
        String[] fields = linea.split(",");
        List<String> cabeceras = new ArrayList<>(Arrays.asList(fields));
        table.setHeaders(cabeceras);

        while ((linea = br.readLine()) != null) {
            String[] elementos = linea.split(",");
            List<Double> data = new LinkedList<>();
            for (String s : elementos) {
                data.add(Double.parseDouble(s));
            }
            table.addRow(data);
        }

        br.close();
        fr.close();

        return table;
    }

    public TableWithLabels readTableWithLabels(String fileName) throws IOException {

        TableWithLabels table = new TableWithLabels();
        File archivo = new File(fileName);


        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea;
        linea = br.readLine();
        String[] fields = linea.split(",");
        List<String> headers = new ArrayList<>(Arrays.asList(fields));
        table.setHeaders(headers);
        String etiqueta;

        while ((linea = br.readLine()) != null) {

            String[] elementos = linea.split(",");
            String[] datos = Arrays.copyOfRange(elementos, 0, elementos.length - 1 );
            List<Double> data = new LinkedList<>();

            for (String s : datos) {
                data.add(Double.parseDouble(s));
            }

            etiqueta = elementos[elementos.length - 1];
            table.addRow(data,etiqueta);
            //System.out.println(table.getNumRows());
        }
        br.close();
        fr.close();

        return table;
    }
}