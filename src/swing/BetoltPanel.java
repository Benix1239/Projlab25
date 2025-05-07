package swing;

import java.awt.*;
import javax.swing.*;

public class BetoltPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel focim;
    private JButton elso;
    private JButton masodik;
    private JButton harmadik;
    private JButton vissza;

    public BetoltPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        focim = new JLabel("Válaszd ki melyik mentést akarod betölteni:", SwingConstants.CENTER);
        focim.setFont(new Font("SansSerif", Font.PLAIN, 16));
        focim.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(focim, BorderLayout.NORTH);

        JPanel gombPanel = new JPanel();
        gombPanel.setLayout(new BoxLayout(gombPanel, BoxLayout.Y_AXIS));
        gombPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        elso = new JButton("Jatek1");
        masodik = new JButton("Üres");
        harmadik = new JButton("Üres");

        Dimension gombMeret = new Dimension(400, 40);
        JButton[] gombok = {elso, masodik, harmadik};

        for (JButton gomb : gombok) {
            gomb.setAlignmentX(Component.CENTER_ALIGNMENT);
            gomb.setMaximumSize(gombMeret);
            gombPanel.add(gomb);
            gombPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        add(gombPanel, BorderLayout.CENTER);

        vissza = new JButton("Vissza");
        vissza.setPreferredSize(new Dimension(100, 40));
        JPanel alsoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        alsoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        alsoPanel.add(vissza);
        add(alsoPanel, BorderLayout.SOUTH);

        // Vissza gomb művelete
        vissza.addActionListener(e -> mainFrame.foMenuBekapcs());

        // Mentések betöltése – teszteléshez a 3 közül csak az első aktív
        elso.addActionListener(e -> mainFrame.jatekBetolt(1));
        masodik.addActionListener(e -> JOptionPane.showMessageDialog(this, "Nincs mentés."));
        harmadik.addActionListener(e -> JOptionPane.showMessageDialog(this, "Nincs mentés."));
    }

    // Frissíti a mentés nevek megjelenítését
    public void frissit(String[] mentesek) {
        elso.setText(mentesek[0]);
        masodik.setText(mentesek[1]);
        harmadik.setText(mentesek[2]);
    }
}
