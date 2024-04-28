package es.uji.al426187;

public abstract class ReaderTemplate {

    private String fichero;

    public ReaderTemplate(String fichero){this.fichero = fichero;}

    public abstract void openSource(String source);

    public abstract void processHeaders(String headers);

    public abstract void processData(String data);

    public abstract void closeSource();

    public abstract boolean hasMoreData();

    public abstract String getNextData();

    public final Table readTableFromSource(){
        openSource(fichero);
        processHeaders();
    }
}
