package swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DicsosegPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel cim;
    private JTable tabla;
    private JButton visszaGomb;

    public DicsosegPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 300));

        // Cím létrehozása
        cim = new JLabel("Dicsőségtábla", SwingConstants.CENTER);
        cim.setFont(new Font("Serif", Font.BOLD, 24));
        cim.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(cim, BorderLayout.NORTH);

        // Táblázat adatok
        String[] oszlopNevek = {"Rang", "Név", "Győzelmek száma", "Meccsek száma", "Összes pont"};
        Object[][] adatok = {
            {"01", "Beke", 3, 6, 30},
            {"02", "Abelephant", 2, 6, 24},
            {"03", "SimiDoki", 1, 6, 19}
        };

        // Nem szerkeszthető táblázat modell
        DefaultTableModel model = new DefaultTableModel(adatok, oszlopNevek) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(model);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // Oszlopok középre igazítása
        tabla.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderer());
        tabla.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderer());
        tabla.getColumnModel().getColumn(2).setCellRenderer(new CenterRenderer());
        tabla.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderer());
        tabla.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderer());

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

    // Segédosztály a középre igazításhoz
    private static class CenterRenderer extends DefaultTableCellRenderer {
        public CenterRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }
    }
}