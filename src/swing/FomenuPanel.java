package swing;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FomenuPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel focim;
    private JButton ujJatek;
    private JButton betolto;
    private JButton dicsoseg;
    private JButton kilepes;
    

    //TODO
    public FomenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        //Cim
        focim = new JLabel("Fungorium", SwingConstants.CENTER);
        focim.setFont(new Font("Serif", Font.BOLD, 28));
        focim.setAlignmentX(Component.CENTER_ALIGNMENT);
        focim.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        //Gombok panelje kozepen
        JPanel kozepsoGombPanel = new JPanel();
        kozepsoGombPanel.setLayout(new BoxLayout(kozepsoGombPanel, BoxLayout.Y_AXIS));

        //Gombok letrehozasa
        ujJatek = new JButton("Uj jatek");
        betolto = new JButton("Jatek betoltese");
        dicsoseg = new JButton("Dicsosegtabla");
        kilepes = new JButton("Kilepes");

        kozepsoGombPanel.add(Box.createVerticalGlue()); // kozepen tartja a gombokat

        // Gombok egységes beállítása
        JButton[] gombok = {ujJatek, betolto, dicsoseg, kilepes};
        for (JButton gomb : gombok) {
            gomb.setAlignmentX(Component.CENTER_ALIGNMENT);
            gomb.setMaximumSize(new Dimension(200, 40));
            gomb.setFont(new Font("SansSerif", Font.PLAIN, 16));
            kozepsoGombPanel.add(gomb);
            kozepsoGombPanel.add(Box.createRigidArea(new Dimension(0, 10))); // tavolsag
        }

        kozepsoGombPanel.add(Box.createVerticalGlue()); // kozepen tartja a gombokat

        // Kilepes gomb mukodese
        kilepes.addActionListener((ActionEvent e) -> System.exit(0));

        // ujJatek gomb mukodese
        ujJatek.addActionListener((ActionEvent e) -> mainFrame.ujJatekBekapcs());

        // Panel felépítése
        this.setLayout(new BorderLayout());
        this.add(focim, BorderLayout.NORTH);
        this.add(kozepsoGombPanel, BorderLayout.CENTER);
    }
}
