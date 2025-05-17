package swing;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class BetoltPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel focim;
    private JButton[] mentesGombok;
    private JButton vissza;
    private static final String[] MENTES_FAJLOK = {"mentes1.txt", "mentes2.txt", "mentes3.txt"};

    public BetoltPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initializeUI();
        frissitMentesAllapotokat();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Cím létrehozása
        focim = new JLabel("Válaszd ki melyik mentést akarod betölteni:", SwingConstants.CENTER);
        focim.setFont(new Font("SansSerif", Font.PLAIN, 16));
        focim.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(focim, BorderLayout.NORTH);

        // Mentés gombok panelje
        JPanel gombPanel = new JPanel();
        gombPanel.setLayout(new BoxLayout(gombPanel, BoxLayout.Y_AXIS));
        gombPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // 3 mentés gomb létrehozása
        mentesGombok = new JButton[3];
        Dimension gombMeret = new Dimension(400, 40);

        for (int i = 0; i < mentesGombok.length; i++) {
            mentesGombok[i] = new JButton("Üres");
            mentesGombok[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            mentesGombok[i].setMaximumSize(gombMeret);
            
            final int mentesIndex = i;
            mentesGombok[i].addActionListener(e -> kezelMentesBetoltes(mentesIndex));
            
            gombPanel.add(mentesGombok[i]);
            gombPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        add(gombPanel, BorderLayout.CENTER);

        // Vissza gomb
        vissza = new JButton("Vissza");
        vissza.setPreferredSize(new Dimension(100, 40));
        vissza.addActionListener(e -> mainFrame.foMenuBekapcs());

        JPanel alsoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        alsoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        alsoPanel.add(vissza);
        add(alsoPanel, BorderLayout.SOUTH);
    }

    /**
     * Frissíti a mentés gombok állapotát a fájlok alapján
     */
    public void frissitMentesAllapotokat() {
        for (int i = 0; i < MENTES_FAJLOK.length; i++) {
            File mentesFile = new File(MENTES_FAJLOK[i]);
            if (mentesFile.exists()) {
                // Ha van mentés, megjelenítjük a mentés számát és dátumát
                mentesGombok[i].setText(String.format("Mentés %d - %s", 
                    i+1, 
                    new java.util.Date(mentesFile.lastModified()).toString()));
                mentesGombok[i].setEnabled(true);
            } else {
                // Ha nincs mentés, "Üres" marad a felirat
                mentesGombok[i].setText("Üres");
                mentesGombok[i].setEnabled(false);
            }
        }
    }

    /**
     * Kezeli a mentés betöltését
     */
    private void kezelMentesBetoltes(int mentesIndex) {
        File mentesFile = new File(MENTES_FAJLOK[mentesIndex]);
        if (mentesFile.exists()) {
            try {
                mainFrame.jatekBetolt(mentesIndex + 1); // +1 mert 1-től indexelünk
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Hiba történt a mentés betöltése közben: " + e.getMessage(),
                    "Betöltési hiba", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Nincs mentés a kiválasztott helyen",
                "Nincs mentés",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}