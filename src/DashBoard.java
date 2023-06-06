import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;




public class DashBoard extends JFrame implements MouseListener, ActionListener {
    private User user;
    private String periodo = "TOT";
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    //Buttons
    private JButton addMovimento = new JButton();
    private JButton refreshButton = new JButton();
    private JButton movimentiButton = new JButton();
    private JButton settimanaButton = new JButton();
    private JButton meseButton = new JButton();
    private JButton totaleButton = new JButton();
    private JButton modificaObiettivo = new JButton();
    private JButton addObiettivo = new JButton();

    //Labels
    private JLabel mainChartLabel = new JLabel();
    private JLabel chartTitle = new JLabel();
    private JLabel entrateLabel = new JLabel();
    private JLabel usciteLabel = new JLabel();
    private JLabel entrateBar = new JLabel();
    private JLabel usciteBar = new JLabel();
    private JLabel obiettivoLabel = new JLabel();
    private JLabel downBarLabel = new JLabel();
    private JLabel upBarLabel = new JLabel();
    private JLabel categoryLabel = new JLabel();
    private JLabel lastLabel = new JLabel("SpendTrack - By Steven");


    public DashBoard(User user){
        this.user = user;

        this.setLayout(null);
        this.setSize(1100,725);
        this.setTitle("SpendTrack - Dashboard");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Style.mediumGreen);
        this.setVisible(true);

        mainChartLabel.setBounds(70, 111, 491, 476);
        mainChartLabel.setBackground(Style.lightGreen);
        mainChartLabel.setOpaque(true);

        chartTitle.setBounds(70, 113, 491, 32);
        chartTitle.setOpaque(false);
        chartTitle.setHorizontalAlignment(JLabel.CENTER);
        chartTitle.setVerticalAlignment(JLabel.CENTER);
        chartTitle.setFont(Style.textFont);
        chartTitle.setForeground(Style.darkGreen);

        double e=0, u=0;
        for (Movimento m : user.getMovimenti()){
            if(m.getImporto()>=0) e+=m.getImporto();
            else u+=m.getImporto();
        }
        chartTitle.setText("ENTRATE: "+decimalFormat.format(e)+"€ - USCITE: "+decimalFormat.format(u)+"€");

        entrateLabel.setBounds(110,528, 162, 30);
        entrateLabel.setOpaque(false);
        entrateLabel.setHorizontalAlignment(JLabel.CENTER);
        entrateLabel.setVerticalAlignment(JLabel.CENTER);
        entrateLabel.setFont(Style.textFont);
        entrateLabel.setForeground(Style.darkestGreen);
        entrateLabel.setText("ENTRATE");

        usciteLabel.setBounds(338,528, 162, 30);
        usciteLabel.setOpaque(false);
        usciteLabel.setHorizontalAlignment(JLabel.CENTER);
        usciteLabel.setVerticalAlignment(JLabel.CENTER);
        usciteLabel.setFont(Style.textFont);
        usciteLabel.setForeground(Style.darkestGreen);
        usciteLabel.setText("USCITE");

        addMovimento.setBounds(70, 38, 404, 66);
        addMovimento.setText("AGGIUNGI MOVIMENTO");
        addMovimento.setFocusable(false);
        addMovimento.setOpaque(true);
        addMovimento.setBorder(null);
        addMovimento.setFont(Style.buttonsFont);
        addMovimento.setForeground(Style.darkGreen);
        addMovimento.setBackground(Style.lightGreen);
        addMovimento.addMouseListener(this);
        addMovimento.addActionListener(this);

        refreshButton.setBounds(491, 38, 70, 66);
        refreshButton.setText("🔄");
        refreshButton.setFocusable(false);
        refreshButton.setOpaque(true);
        refreshButton.setBorder(null);
        refreshButton.setFont(Style.buttonsFont);
        refreshButton.setForeground(Style.darkGreen);
        refreshButton.setBackground(Style.lightGreen);
        refreshButton.addMouseListener(this);
        refreshButton.addActionListener(this);

        entrateBar.setVisible(true);
        entrateBar.setBackground(Style.darkestGreen);
        entrateBar.setOpaque(true);

        usciteBar.setBackground(Style.darkestGreen);
        usciteBar.setOpaque(true);
        usciteBar.setVisible(true);

        double diff = (e) - (u);

        double proporzione = 0.0;
        if (diff != 0) proporzione = 359.0 / diff;

        int altezzaEntRateBar = (int) (proporzione * e);
        entrateBar.setBounds(110, 528 - altezzaEntRateBar, 162, altezzaEntRateBar);
        entrateBar.setOpaque(true);
        entrateBar.setBackground(Style.darkestGreen);

        int altezzaUsciteBar = 359 - altezzaEntRateBar;
        usciteBar.setBounds(338, 528 - altezzaUsciteBar, 162, altezzaUsciteBar);
        usciteBar.setOpaque(true);
        usciteBar.setBackground(Style.darkestGreen);


        movimentiButton.setBounds(70,597,243,66);
        movimentiButton.setText("ULTIMI MOVIMENTI");
        movimentiButton.setFocusable(false);
        movimentiButton.setOpaque(true);
        movimentiButton.setBorder(null);
        movimentiButton.setFont(Style.buttonsFont);
        movimentiButton.setForeground(Style.darkGreen);
        movimentiButton.setBackground(Style.lightGreen);
        movimentiButton.addMouseListener(this);
        movimentiButton.addActionListener(this);

        settimanaButton.setBounds(352,596,66,66);
        settimanaButton.setText("SS");
        settimanaButton.setFocusable(false);
        settimanaButton.setOpaque(true);
        settimanaButton.setBorder(null);
        settimanaButton.setFont(Style.buttonsFont);
        settimanaButton.setForeground(Style.darkGreen);
        settimanaButton.setBackground(Style.lightGreen);
        settimanaButton.addMouseListener(this);
        settimanaButton.addActionListener(this);

        meseButton.setBounds(424,596,66,66);
        meseButton.setText("MM");
        meseButton.setFocusable(false);
        meseButton.setOpaque(true);
        meseButton.setBorder(null);
        meseButton.setFont(Style.buttonsFont);
        meseButton.setForeground(Style.darkGreen);
        meseButton.setBackground(Style.lightGreen);
        meseButton.addMouseListener(this);
        meseButton.addActionListener(this);

        totaleButton.setBounds(495,596,66,66);
        totaleButton.setText("TOT");
        totaleButton.setFocusable(false);
        totaleButton.setOpaque(true);
        totaleButton.setBorder(null);
        totaleButton.setFont(Style.buttonsFont);
        totaleButton.setForeground(Style.lightGreen);
        totaleButton.setBackground(Style.darkGreen);
        totaleButton.addMouseListener(this);
        totaleButton.addActionListener(this);

        obiettivoLabel.setBounds(578,37,489,66);
        obiettivoLabel.setOpaque(true);
        obiettivoLabel.setText(this.user.obiettivoRisparmio.getName()+" - "+decimalFormat.format(this.user.obiettivoRisparmio.getPercent())+"% su: "+decimalFormat.format(this.user.obiettivoRisparmio.getSaldoFinale())+"€");
        obiettivoLabel.setBackground(Style.lightGreen);
        obiettivoLabel.setHorizontalAlignment(JLabel.CENTER);
        obiettivoLabel.setFont(Style.textFont);
        obiettivoLabel.setForeground(Style.darkGreen);

        downBarLabel.setOpaque(true);
        downBarLabel.setBounds(578,113,489,45);
        downBarLabel.setBackground(Style.lightGreen);

        upBarLabel.setBounds(578,113,(int) (489*this.user.obiettivoRisparmio.getPercent())/100,45);
        upBarLabel.setOpaque(true);
        upBarLabel.setBackground(Style.darkestGreen);
        if (upBarLabel.getWidth()>489) upBarLabel.setBounds(578,113,489,45);

        modificaObiettivo.setBounds(578,168,234,66);
        modificaObiettivo.setText("MODIFICA");
        modificaObiettivo.setFocusable(false);
        modificaObiettivo.setOpaque(true);
        modificaObiettivo.setBorder(null);
        modificaObiettivo.setFont(Style.buttonsFont);
        modificaObiettivo.setForeground(Style.darkGreen);
        modificaObiettivo.setBackground(Style.lightGreen);
        modificaObiettivo.addMouseListener(this);
        modificaObiettivo.addActionListener(this);

        addObiettivo.setBounds(829,168,238,66);
        addObiettivo.setText("AGGIUNGI FONDI");
        addObiettivo.setFocusable(false);
        addObiettivo.setOpaque(true);
        addObiettivo.setBorder(null);
        addObiettivo.setFont(Style.buttonsFont);
        addObiettivo.setForeground(Style.darkGreen);
        addObiettivo.setBackground(Style.lightGreen);
        addObiettivo.addMouseListener(this);
        addObiettivo.addActionListener(this);

        categoryLabel.setBounds(578,244,489,343);
        categoryLabel.setBackground(Style.lightGreen);
        categoryLabel.setOpaque(true);
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        categoryLabel.setHorizontalTextPosition(JLabel.CENTER);
        categoryLabel.setFont(Style.buttonsFont);
        categoryLabel.setForeground(Style.darkGreen);

        ArrayList<String> categorie = new ArrayList<>();

        for (Movimento m : this.user.getMovimenti()){
            boolean flag=false;

            for (int i=0; i<categorie.size();i++){
                if(m.getCategoria().equalsIgnoreCase(categorie.get(i))){
                    flag = true;
                    break;
                }

            }

            if(!flag) categorie.add(m.getCategoria());
        }

        double[] importi = new double[categorie.size()];
        for (int i=0; i<categorie.size();i++){
            for (Movimento m: this.user.getMovimenti()) if(m.getCategoria().equalsIgnoreCase(categorie.get(i))) importi[i]+=m.getImporto();
        }

        String textCategory="<html>SPESE PER CATEGORIA:<br><br>";
        for (int i=0; i<categorie.size();i++){
            textCategory+= categorie.get(i) +": "+decimalFormat.format(importi[i])+"€<br>";
        }
        textCategory+="</html>";

        categoryLabel.setText(textCategory);

        lastLabel.setBounds(578,618,489,23);
        lastLabel.setFont(Style.textFont);
        lastLabel.setForeground(Color.BLACK);
        lastLabel.setHorizontalAlignment(JLabel.CENTER);


        this.add(lastLabel);
        this.add(categoryLabel);
        this.add(addObiettivo);
        this.add(modificaObiettivo);
        this.add(upBarLabel);
        this.add(downBarLabel);
        this.add(obiettivoLabel);
        this.add(totaleButton);
        this.add(meseButton);
        this.add(settimanaButton);
        this.add(movimentiButton);
        this.add(usciteBar);
        this.add(entrateBar);
        this.add(refreshButton);
        this.add(addMovimento);
        this.add(usciteLabel);
        this.add(entrateLabel);
        this.add(chartTitle);
        this.add(mainChartLabel);
    }

    private void aggiorna(){
        int range=0;
        if(periodo.equals("SS")) range =7;
        if(periodo.equals("MM")) range =30;

        LocalDate dataCorrente = LocalDate.now();

        double e=0, u=0;
        for (Movimento m : user.getMovimenti()){
            LocalDate d = m.getData();

            if(range==0){
                if(m.getImporto()>=0) e+=m.getImporto();
                else u+=m.getImporto();
            }
            else{
                long differenzaGiorni = ChronoUnit.DAYS.between(d, dataCorrente);

                if (differenzaGiorni <= range) {
                    if(m.getImporto()>=0) e+=m.getImporto();
                    else u+=m.getImporto();
                }
            }
        }

        double diff = (e) - (u);
        double proporzione = 0.0;
        if (diff != 0) proporzione = 359.0 / diff;

        int altezzaEntRateBar = (int) (proporzione * e);
        entrateBar.setBounds(110, 528 - altezzaEntRateBar, 162, altezzaEntRateBar);


        int altezzaUsciteBar = 359 - altezzaEntRateBar;
        usciteBar.setBounds(338, 528 - altezzaUsciteBar, 162, altezzaUsciteBar);

        chartTitle.setText("ENTRATE: "+decimalFormat.format(e)+"€ - USCITE: "+decimalFormat.format(u)+"€");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ultimi
        if(e.getSource()==movimentiButton){
            dispose();
            SwingUtilities.invokeLater(() -> {
                UltimiMovimenti d = new UltimiMovimenti(this.user);
                d.setVisible(true);
            });

            try {Thread.sleep(100);} catch (InterruptedException e1) {e1.printStackTrace();}
        }


        //Refresh
        if (e.getSource()==refreshButton){
            dispose();
            SwingUtilities.invokeLater(() -> {
                DashBoard d = new DashBoard(this.user);
                d.setVisible(true);
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {e1.printStackTrace();}
        }

        if(e.getSource()==totaleButton && !periodo.equals("TOT")){
            periodo="TOT";
            meseButton.setBackground(Style.lightGreen);
            meseButton.setForeground(Style.darkGreen);
            settimanaButton.setBackground(Style.lightGreen);
            settimanaButton.setForeground(Style.darkGreen);

            aggiorna();
        }
        if(e.getSource()==meseButton && !periodo.equals("MM")){
            periodo="MM";
            totaleButton.setBackground(Style.lightGreen);
            totaleButton.setForeground(Style.darkGreen);
            settimanaButton.setBackground(Style.lightGreen);
            settimanaButton.setForeground(Style.darkGreen);

            aggiorna();
        }
        if(e.getSource()==settimanaButton && !periodo.equals("SS")){
            periodo="SS";

            totaleButton.setBackground(Style.lightGreen);
            totaleButton.setForeground(Style.darkGreen);
            meseButton.setBackground(Style.lightGreen);
            meseButton.setForeground(Style.darkGreen);
            aggiorna();
        }

        if(e.getSource()==addMovimento){
            dispose();
            new AddMovimento(this.user);
        }

        if (e.getSource()==modificaObiettivo){
            String newName = JOptionPane.showInputDialog(this, "Inserisci nuovo nome: ", null);

            String newSaldo = JOptionPane.showInputDialog(this, "Inserisci quantità da risparmiare:  ", null);
            try {
                double saldoFinale = Double.parseDouble(newSaldo);

                this.user.obiettivoRisparmio.setName(newName);
                this.user.obiettivoRisparmio.setSaldoFinale(saldoFinale);

                obiettivoLabel.setText(this.user.obiettivoRisparmio.getName()+" - "+decimalFormat.format(this.user.obiettivoRisparmio.getPercent())+"% su: "+decimalFormat.format(this.user.obiettivoRisparmio.getSaldoFinale())+"€");

                downBarLabel.setBounds(578,113,489,45);
                upBarLabel.setBounds(578,113,(int) (489*this.user.obiettivoRisparmio.getPercent())/100,45);
                if (upBarLabel.getWidth()>489) upBarLabel.setBounds(578,113,489,45);

                this.user.salvaSuFile();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "ERRORE!", "Inserimento errato", JOptionPane.ERROR_MESSAGE);
            }




        }

        if(e.getSource()==addObiettivo){
            String newSaldo = JOptionPane.showInputDialog(this, "Quanto desideri aggiungere: ", null);

            try {
                double saldoFinale = Double.parseDouble(newSaldo);

                this.user.obiettivoRisparmio.add(saldoFinale);

                obiettivoLabel.setText(this.user.obiettivoRisparmio.getName()+" - "+decimalFormat.format(this.user.obiettivoRisparmio.getPercent())+"% su: "+decimalFormat.format(this.user.obiettivoRisparmio.getSaldoFinale())+"€");

                downBarLabel.setBounds(578,113,489,45);
                upBarLabel.setBounds(578,113,(int) (489*this.user.obiettivoRisparmio.getPercent())/100,45);
                if (upBarLabel.getWidth()>489) upBarLabel.setBounds(578,113,489,45);

                this.user.salvaSuFile();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "ERRORE!", "Inserimento errato", JOptionPane.ERROR_MESSAGE);
            }

        }



    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==addMovimento){
            addMovimento.setBackground(Style.darkGreen);
            addMovimento.setForeground(Style.lightGreen);
        }

        if (e.getSource()==refreshButton) refreshButton.setBackground(Style.darkGreen);

        if (e.getSource()==movimentiButton){
            movimentiButton.setBackground(Style.darkGreen);
            movimentiButton.setForeground(Style.lightGreen);
        }

        if (e.getSource()==settimanaButton && !periodo.equals("SS")){
            settimanaButton.setBackground(Style.darkGreen);
            settimanaButton.setForeground(Style.lightGreen);
        }
        if (e.getSource()==meseButton && !periodo.equals("MM")){
            meseButton.setBackground(Style.darkGreen);
            meseButton.setForeground(Style.lightGreen);
        }

        if (e.getSource()==totaleButton && !periodo.equals("TOT")){
            totaleButton.setBackground(Style.darkGreen);
            totaleButton.setForeground(Style.lightGreen);
        }

        if (e.getSource()==modificaObiettivo ){
            modificaObiettivo.setBackground(Style.darkGreen);
            modificaObiettivo.setForeground(Style.lightGreen);
        }
        if (e.getSource()==addObiettivo ){
            addObiettivo.setBackground(Style.darkGreen);
            addObiettivo.setForeground(Style.lightGreen);
        }





    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==addMovimento){
            addMovimento.setBackground(Style.lightGreen);
            addMovimento.setForeground(Style.darkGreen);
        }

        if (e.getSource()==refreshButton) refreshButton.setBackground(Style.lightGreen);

        if (e.getSource()==movimentiButton){
            movimentiButton.setBackground(Style.lightGreen);
            movimentiButton.setForeground(Style.darkGreen);
        }

        if (e.getSource()==settimanaButton && !periodo.equals("SS")){
            settimanaButton.setBackground(Style.lightGreen);
            settimanaButton.setForeground(Style.darkGreen);
        }
        if (e.getSource()==meseButton && !periodo.equals("MM")){
            meseButton.setBackground(Style.lightGreen);
            meseButton.setForeground(Style.darkGreen);
        }
        if (e.getSource()==totaleButton && !periodo.equals("TOT")){
            totaleButton.setBackground(Style.lightGreen);
            totaleButton.setForeground(Style.darkGreen);
        }

        if (e.getSource()==modificaObiettivo ){
            modificaObiettivo.setBackground(Style.lightGreen);
            modificaObiettivo.setForeground(Style.darkGreen);
        }
        if (e.getSource()==addObiettivo ){
            addObiettivo.setBackground(Style.lightGreen);
            addObiettivo.setForeground(Style.darkGreen);
        }

    }

}
