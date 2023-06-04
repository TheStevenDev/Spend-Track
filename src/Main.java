import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            DashBoard d = new DashBoard(new User());
            d.setVisible(true);
        });

        try {Thread.sleep(100);} catch (InterruptedException e1) {e1.printStackTrace();}

    }
}