import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Movimento {
    private String categoria;
    private double importo;
    private LocalDate data;

    public Movimento(String categoria, double importo, LocalDate data){
        this.categoria=categoria;
        this.importo=importo;
        this.data=data;
    }

    public String getCategoria(){
        return this.categoria;
    }

    public double getImporto(){
        return this.importo;
    }

    public LocalDate getData(){
        return this.data;
    }

    public String getDataString(){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(f);
    }

    @Override
    public String toString(){
        return this.categoria+" - "+this.importo+" - "+this.getDataString();
    }

}
