public class Obiettivo {
    private double saldoIniziale=0;
    private double saldoFinale=0;
    private String nome;

    public Obiettivo(String nome, double saldoIniziale,double saldoFinale){
        this.nome=nome;
        this.saldoIniziale = saldoIniziale;
        this.saldoFinale=saldoFinale;
    }

    public String getName(){
        return this.nome;
    }

    public double getSaldoFinale(){
        return this.saldoFinale;
    }

    public double getSaldoIniziale(){
        return this.saldoIniziale;
    }

    public double getSaldoCorrente(){
        return saldoFinale-saldoIniziale;
    }

    public double getPercent(){
        return (saldoIniziale/saldoFinale) * 100;
    }

    public void setName(String newName){
        this.nome=newName;
    }

    public void setSaldoIniziale(double n){
        this.saldoIniziale=n;
    }

    public void setSaldoFinale(double n){
        this.saldoFinale=n;
    }

    public void add(double n){
        this.saldoIniziale+=n;
    }
    public boolean check(){
        if(saldoIniziale>=saldoFinale) return true;

        return false;
    }

}
