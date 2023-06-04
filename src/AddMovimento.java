import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddMovimento extends JFrame implements ActionListener, MouseListener {
    User user;
    //Labels
    private JLabel titleLabel = new JLabel("AGGIUNGI MOVIMENTO:");
    private JLabel importoLabel = new JLabel("IMPORTO: ");
    private JLabel categoriaLabel = new JLabel("CATEGORIA: ");
    private JLabel dataLabel = new JLabel("DATA: ");

    //Buttons e field
    private JTextField importoFiled = new JTextField();
    private JTextField categoryField = new JTextField();
    private JTextField dataField = new JTextField();
    private JButton backButton = new JButton();
    private JButton confirmButton = new JButton();
    private JButton todayButton = new JButton();
    private LocalDate dataScelta;


    public AddMovimento(User user){
        this.user=user;

        this.setLayout(null);
        this.setSize(1100,725);
        this.setTitle("SpendTrack - Movimenti");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Style.mediumGreen);
        this.setVisible(true);

        titleLabel.setBounds(304, 108, 489, 38);
        titleLabel.setFont(Style.textFont);
        titleLabel.setForeground(Style.darkestGreen);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        importoLabel.setBounds(304, 213, 189, 36);
        importoLabel.setFont(Style.textFont);
        importoLabel.setForeground(Style.darkestGreen);
        importoLabel.setHorizontalAlignment(JLabel.RIGHT);

        categoriaLabel.setBounds(260, 267, 233, 36);
        categoriaLabel.setFont(Style.textFont);
        categoriaLabel.setForeground(Style.darkestGreen);
        categoriaLabel.setHorizontalAlignment(JLabel.RIGHT);

        dataLabel.setBounds(304, 327, 189, 36);
        dataLabel.setFont(Style.textFont);
        dataLabel.setForeground(Style.darkestGreen);
        dataLabel.setHorizontalAlignment(JLabel.RIGHT);

        importoFiled.setBounds(506,209, 333, 45);
        importoFiled.setBackground(Style.darkGreen);
        importoFiled.setBorder(null);
        importoFiled.setFont(Style.textFont);
        importoFiled.setForeground(Color.WHITE);
        importoFiled.setCaretColor(Color.WHITE);

        categoryField.setBounds(506,262, 333, 45);
        categoryField.setBackground(Style.darkGreen);
        categoryField.setBorder(null);
        categoryField.setFont(Style.textFont);
        categoryField.setForeground(Color.WHITE);
        categoryField.setCaretColor(Color.WHITE);


        backButton.setBounds(496, 460, 104,45);
        backButton.setText("ANNULLA");
        backButton.setFocusable(false);
        backButton.setOpaque(true);
        backButton.setBorder(null);
        backButton.setFont(Style.buttonsFont);
        backButton.setForeground(Style.darkGreen);
        backButton.setBackground(Style.lightGreen);
        backButton.addMouseListener(this);
        backButton.addActionListener(this);

        confirmButton.setBounds(506, 518, 85,45);
        confirmButton.setText("SALVA");
        confirmButton.setFocusable(false);
        confirmButton.setOpaque(true);
        confirmButton.setBorder(null);
        confirmButton.setFont(Style.buttonsFont);
        confirmButton.setForeground(Style.darkGreen);
        confirmButton.setBackground(Style.lightGreen);
        confirmButton.addMouseListener(this);
        confirmButton.addActionListener(this);

        todayButton.setBounds(687, 317, 120,45);
        todayButton.setText("OGGI");
        todayButton.setFocusable(false);
        todayButton.setOpaque(true);
        todayButton.setBorder(null);
        todayButton.setFont(Style.buttonsFont);
        todayButton.setForeground(Style.lightGreen);
        todayButton.setBackground(Style.darkGreen);
        todayButton.addMouseListener(this);
        todayButton.addActionListener(this);

        dataField.setBounds(507,317,167,45);
        dataField.setBackground(Style.darkGreen);
        dataField.setBorder(null);
        dataField.setForeground(Color.WHITE);
        dataField.setCaretColor(Color.WHITE);



        this.add(dataField);
        this.add(todayButton);
        this.add(confirmButton);
        this.add(backButton);
        this.add(categoryField);
        this.add(importoFiled);
        this.add(dataLabel);
        this.add(categoriaLabel);
        this.add(importoLabel);
        this.add(titleLabel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==todayButton){
            this.dataScelta = LocalDate.now();

            String temp = dataScelta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dataField.setText(temp);

        }

        if(e.getSource()==confirmButton){
            dispose();
            double importo = Double.parseDouble(importoFiled.getText());
            String cat = categoryField.getText();
            dataScelta = LocalDate.parse(dataField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            this.user.movimenti.add(new Movimento(cat, importo, dataScelta));
            this.user.salvaSuFile();

            SwingUtilities.invokeLater(() -> {
                DashBoard d = new DashBoard(this.user);
                d.setVisible(true);
            });

            try {Thread.sleep(100);} catch (InterruptedException e1) {e1.printStackTrace();}
        }


        if(e.getSource()==backButton){
            dispose();
            SwingUtilities.invokeLater(() -> {
                DashBoard d = new DashBoard(this.user);
                d.setVisible(true);
            });

            try {Thread.sleep(100);} catch (InterruptedException e1) {e1.printStackTrace();}
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
        if (e.getSource()==backButton){
            backButton.setBackground(Style.darkGreen);
            backButton.setForeground(Style.lightGreen);
        }

        if (e.getSource()==confirmButton){
            confirmButton.setBackground(Style.darkGreen);
            confirmButton.setForeground(Style.lightGreen);
        }

        if (e.getSource()==todayButton){
            todayButton.setBackground(Style.lightGreen);
            todayButton.setForeground(Style.darkGreen);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==backButton){
            backButton.setBackground(Style.lightGreen);
            backButton.setForeground(Style.darkestGreen);
        }
        if (e.getSource()==confirmButton){
            confirmButton.setBackground(Style.lightGreen);
            confirmButton.setForeground(Style.darkestGreen);
        }

        if (e.getSource()==todayButton){
            todayButton.setBackground(Style.darkGreen);
            todayButton.setForeground(Style.lightGreen);
        }

    }



}
