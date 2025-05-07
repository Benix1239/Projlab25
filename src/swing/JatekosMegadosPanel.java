package swing;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JatekosMegadosPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel cim;
    private JTextField nevField;
    private JButton hozzaAd;
    private JButton vege;

    private ArrayList<String> jatekosok = new ArrayList<>();
    
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

        // Kezdetben a hozzaadas es mentes gomb letiltasa
        hozzaAd.setEnabled(false);
        vege.setEnabled(false);

        // Szoveg figyelese, hogy beirt-e vmit a delhasznalo
        nevField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { checkInput(); }
            public void removeUpdate(DocumentEvent e) { checkInput(); }
            public void changedUpdate(DocumentEvent e) { checkInput(); }

            private void checkInput() {
                hozzaAd.setEnabled(!nevField.getText().trim().isEmpty());
            }
        });

        // hozzaAd gomb muvelet
        hozzaAd.addActionListener(e -> {
          String nev = nevField.getText().trim();
          if (!nev.isEmpty()) {
              jatekosok.add(nev);
              nevField.setText("");
              //jelenleg csak 4 jatekoshoz van egy "hasznalhato" palya, ezert csak ennyivel szabad elinditani
              if(jatekosok.size() == 4){
                    vege.setEnabled(true);
              }
              else{
                    vege.setEnabled(false);
              }
          }
        });

        // mentes gomb muvelet
        vege.addActionListener(e -> {
          mainFrame.jatekIndit(jatekosok);
          mainFrame.jatekPanelBekapcs();
        });

        // Panel elemek elrendezese
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        this.add(cim, BorderLayout.NORTH);
        this.add(nevField, BorderLayout.CENTER);
        this.add(gombokPanel, BorderLayout.SOUTH);
   } 
}
