package swing;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JatekosMegadosPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel cim;
    private JTextField nevField;
    private JButton hozzaAd;
    private JButton vege;
    
   public JatekosMegadosPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        // Cim
        cim = new JLabel("Add meg a jatekosok nevet", SwingConstants.CENTER);

        // Szovegmezo kozepen
        JTextField nevField = new JTextField();
        nevField.setHorizontalAlignment(JTextField.CENTER);

        // Gombok
        hozzaAd = new JButton("Jatekos hozzaadasa");
        vege = new JButton("Mehet");

        // Gombok panel alul
        JPanel gombokPanel = new JPanel();
        gombokPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        gombokPanel.add(hozzaAd);
        gombokPanel.add(vege);

        // Panel elemek elrendezese
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        this.add(cim, BorderLayout.NORTH);
        this.add(nevField, BorderLayout.CENTER);
        this.add(gombokPanel, BorderLayout.SOUTH);
   } 
}
