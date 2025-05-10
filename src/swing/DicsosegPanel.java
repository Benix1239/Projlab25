package swing;

import backend.Jatekos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DicsosegPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel cim;
    private JTable tabla;
    private JButton visszaGomb;
    private DefaultTableModel model;
    private static final String FALJ_UTVONAL = "dicsoseglista.txt";

    public DicsosegPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initializeUI();
        betoltEsFrissit();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 300));

        // Cím létrehozása
        cim = new JLabel("Dicsőségtábla", SwingConstants.CENTER);
        cim.setFont(new Font("Serif", Font.BOLD, 24));
        cim.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(cim, BorderLayout.NORTH);

        // Táblázat modell létrehozása
        String[] oszlopNevek = {"Rang", "Név", "Győzelmek száma", "Meccsek száma", "Összes pont"};
        model = new DefaultTableModel(oszlopNevek, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(model);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // Oszlopok középre igazítása
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(new CenterRenderer());
        }

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Vissza gomb
        visszaGomb = new JButton("Vissza");
        visszaGomb.setFont(new Font("SansSerif", Font.PLAIN, 16));
        visszaGomb.setPreferredSize(new Dimension(100, 40));
        visszaGomb.addActionListener((ActionEvent e) -> {
            mainFrame.foMenuBekapcs();
        });

        JPanel gombPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gombPanel.add(visszaGomb);
        gombPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(gombPanel, BorderLayout.SOUTH);
    }

    /**
     * Automatikusan betölti a dicsőséglistát fájlból és frissíti a táblázatot
     */
    public void betoltEsFrissit() {
        List<Jatekos> jatekosok = new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FALJ_UTVONAL))) {
            jatekosok = (List<Jatekos>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ha nem létezik a fájl, üres listával dolgozunk
            System.out.println("Dicsőséglista fájl még nem létezik.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Hiba történt a dicsőséglista betöltésekor", 
                "Hiba", JOptionPane.ERROR_MESSAGE);
        }

        frissitTablazat(jatekosok);
    }

    /**
     * Frissíti a táblázatot a kapott játékos lista alapján
     */
    private void frissitTablazat(List<Jatekos> jatekosok) {
        model.setRowCount(0); // Táblázat ürítése

        if (jatekosok != null && !jatekosok.isEmpty()) {
            jatekosok.sort((j1, j2) -> Integer.compare(j2.getGyozelmekSzama(), j1.getGyozelmekSzama()));
            
            int rang = 1;
            for (Jatekos jatekos : jatekosok) {
                Object[] sor = {
                    String.format("%02d", rang++),
                    jatekos.getNev(),
                    jatekos.getGyozelmekSzama(),
                    jatekos.getMeccsekSzama(),
                    jatekos.getOsszesPont()
                };
                model.addRow(sor);
            }
        }
    }

    // Segédosztály a középre igazításhoz
    private static class CenterRenderer extends DefaultTableCellRenderer {
        public CenterRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }
    }
}