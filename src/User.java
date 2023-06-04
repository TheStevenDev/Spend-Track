import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {
    //Files
    private File operazioniFile = new File("operazioni.txt");
    private File obiettivoFile = new File("obiettivo.txt");

    //Attributi
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public ArrayList<Movimento> movimenti = new ArrayList<Movimento>();
    public Obiettivo obiettivoRisparmio;

    //Constructor e metodi
    public User(){
        //Leggo i movimenti
        try {
            BufferedReader reader = new BufferedReader(new FileReader(operazioniFile));

            String data;
            String[] datas;

            while ((data= reader.readLine())!=null){
                datas=data.split(",");

                movimenti.add(new Movimento(datas[0],Double.parseDouble(datas[1]), LocalDate.parse(datas[2],formatter))); //Nuovo movimento

            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Leggo Obiettivo
        try {
            BufferedReader reader = new BufferedReader(new FileReader(obiettivoFile));

            String data = reader.readLine();
            String[] datas = data.split(",");

            this.obiettivoRisparmio = new Obiettivo(datas[0],Double.parseDouble(datas[1]),Double.parseDouble(datas[2]));


            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void salvaSuFile(){
        //Salva movimenti
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(operazioniFile));

            for (Movimento m : movimenti){
                writer.write(m.getCategoria()+","+String.valueOf(m.getImporto())+","+m.getDataString()+"\n");
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Salva obiettivo
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(obiettivoFile));

            writer.write(this.obiettivoRisparmio.getName()+","+this.obiettivoRisparmio.getSaldoIniziale()+","+this.obiettivoRisparmio.getSaldoFinale());

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void addMovimento(Movimento m){
        movimenti.add(m);
    }

    public ArrayList<Movimento> getMovimenti(){
        return this.movimenti;
    }

    public void setMovimenti(ArrayList<Movimento> n){
        this.movimenti = n;
    }





}
