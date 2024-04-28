package es.uji.al426187.Lectores;

import java.io.*;
import java.util.*;

public class CSVUnlabeledFileReader extends ReaderTemplate{



    public CSVUnlabeledFileReader(String Fichero){
        super();
    }

    @Override
    public void openSource(String source) throws IOException {
        sc = new Scanner(new File(source));
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
        sc.close();
    }

    @Override
    public String getNextData() throws IOException {
        return sc.nextLine();
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
