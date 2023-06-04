import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UltimiMovimenti extends JFrame implements MouseListener, ActionListener {
    private User user;

    //Labels
    private JLabel titleLabel = new JLabel("ULTIMI MOVIMENTI");
    private JLabel categoryUpLabel = new JLabel("CATEGORIA");
    private JLabel categoryDownLabel = new JLabel();
    private JLabel importoUpLabel = new JLabel("IMPORTO");
    private JLabel importoDownLabel = new JLabel();
    private JLabel dataUpLabel = new JLabel("DATA");
    private JLabel dataDownLabel = new JLabel();

    //Button
    private JButton backButton = new JButton();



    public UltimiMovimenti(User user) {
        this.user = user;

        this.setLayout(null);
        this.setSize(1100,725);
        this.setTitle("SpendTrack - Movimenti");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Style.mediumGreen);
        this.setVisible(true);

        titleLabel.setBounds(305,70,489,38);
        titleLabel.setFont(Style.textFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Style.darkestGreen);

        categoryUpLabel.setBounds(227, 125,210,36);
        categoryUpLabel.setFont(Style.textFont);
        categoryUpLabel.setForeground(Style.lightGreen);
        categoryUpLabel.setBackground(Style.darkGreen);
        categoryUpLabel.setHorizontalAlignment(JLabel.CENTER);
        categoryUpLabel.setOpaque(true);

        importoUpLabel.setBounds(445, 125,210,36);
        importoUpLabel.setFont(Style.textFont);
        importoUpLabel.setForeground(Style.lightGreen);
        importoUpLabel.setBackground(Style.darkGreen);
        importoUpLabel.setHorizontalAlignment(JLabel.CENTER);
        importoUpLabel.setOpaque(true);

        dataUpLabel.setBounds(663, 125,210,36);
        dataUpLabel.setFont(Style.textFont);
        dataUpLabel.setForeground(Style.lightGreen);
        dataUpLabel.setBackground(Style.darkGreen);
        dataUpLabel.setHorizontalAlignment(JLabel.CENTER);
        dataUpLabel.setOpaque(true);

        //DOWN


        categoryDownLabel.setBounds(227, 168,210,400);
        categoryDownLabel.setFont(Style.buttonsFont);
        categoryDownLabel.setForeground(Style.lightGreen);
        categoryDownLabel.setBackground(Style.darkGreen);
        categoryDownLabel.setHorizontalAlignment(JLabel.CENTER);
        categoryDownLabel.setOpaque(true);

        importoDownLabel.setBounds(445, 168,210,400);
        importoDownLabel.setFont(Style.buttonsFont);
        importoDownLabel.setForeground(Style.lightGreen);
        importoDownLabel.setBackground(Style.darkGreen);
        importoDownLabel.setHorizontalAlignment(JLabel.CENTER);
        importoDownLabel.setOpaque(true);

        dataDownLabel.setBounds(663, 168,210,400);
        dataDownLabel.setFont(Style.buttonsFont);
        dataDownLabel.setForeground(Style.lightGreen);
        dataDownLabel.setBackground(Style.darkGreen);
        dataDownLabel.setHorizontalAlignment(JLabel.CENTER);
        dataDownLabel.setOpaque(true);

        backButton.setBounds(445, 630, 210,36);
        backButton.setText("INDIETRO");
        backButton.setFocusable(false);
        backButton.setOpaque(true);
        backButton.setBorder(null);
        backButton.setFont(Style.buttonsFont);
        backButton.setForeground(Style.darkGreen);
        backButton.setBackground(Style.lightGreen);
        backButton.addMouseListener(this);
        backButton.addActionListener(this);

        //---TESTI----//
        String categorie="<html>";
        String importi="<html>";
        String date="<html>";

        for (Movimento m: this.user.getMovimenti()){
            categorie+= m.getCategoria()+"<br>";
            importi+= m.getImporto()+"<br>";
            date+= m.getDataString()+"<br>";
        }

        categorie+="</html>";
        importi+="</html>";
        date+="</html>";

        categoryDownLabel.setText(categorie);
        importoDownLabel.setText(importi);
        dataDownLabel.setText(date);


        //-------//

        this.add(backButton);

        this.add(categoryDownLabel);
        this.add(importoDownLabel);
        this.add(dataDownLabel);
        this.add(backButton);

        this.add(dataUpLabel);
        this.add(importoDownLabel);
        this.add(importoUpLabel);
        this.add(categoryUpLabel);
        this.add(titleLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==backButton){
            backButton.setBackground(Style.lightGreen);
            backButton.setForeground(Style.darkestGreen);
        }
    }
}
