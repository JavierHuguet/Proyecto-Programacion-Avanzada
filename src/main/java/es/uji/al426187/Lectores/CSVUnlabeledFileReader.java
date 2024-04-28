package es.uji.al426187.Lectores;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CSVUnlabeledFileReader extends ReaderTemplate{

    protected FileReader fr;
    protected BufferedReader br;

    public CSVUnlabeledFileReader(String Fichero){
        super();
    }

    @Override
    public void openSource(String source) throws IOException {
        File archivo = new File(source);
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
    }

    @Override
    public void processHeaders(String headers) {
        String[] fields = headers.split(",");
        List<String> cabeceras = new ArrayList<>(Arrays.asList(fields));
        tabla.setHeaders(cabeceras);
    }

    @Override
    public void processData(String data) {
        String[] elementos = data.split(",");
        List<Double> datos = new LinkedList<>();
        for (String s : elementos) {
            datos.add(Double.parseDouble(s));
        }
        tabla.addRow(datos);
    }

    @Override
    public void closeSource() throws IOException {
        br.close();
        fr.close();
    }

    @Override
    public String getNextData() throws IOException {
        return br.readLine();
    }

    @Override
    public boolean hasMoreData() throws IOException {
        if(getNextData() == null){
            return false;
        }else {
            return true;
        }
    }
}
