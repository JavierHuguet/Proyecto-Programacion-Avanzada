package es.uji.al426187.Lectores;

import es.uji.al426187.Table;

import java.io.IOException;

public abstract class ReaderTemplate {

    private String fichero;

    protected Table tabla;

    public ReaderTemplate(){
        tabla = new Table();
    }

    public ReaderTemplate(String fichero){
        this.fichero = fichero;
        this.tabla = new Table();
    }

    public abstract void openSource(String source) throws IOException;

    public abstract void processHeaders(String headers);

    public abstract void processData(String data);

    public abstract void closeSource();

    public abstract boolean hasMoreData();

    public abstract String getNextData();

    public final Table readTableFromSource(){
        openSource(fichero);
        processHeaders(getNextData());
        while(hasMoreData()){
            processData(getNextData());
        }
        closeSource();
        return tabla;
    }
}
