package swing;

import backend.Gombasz;
import backend.Gombatest;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class GombaszTulajdonsagPanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel tableModel;

    public GombaszTulajdonsagPanel() {
        setLayout(new BorderLayout());

        JLabel cimke = new JLabel("Gombász lehetseges lépések", SwingConstants.CENTER);
        add(cimke, BorderLayout.NORTH);

        String[] oszlopNevek = {"Test", "Tud spóra szórni", "Tud fonalat rakni"};
        tableModel = new DefaultTableModel(oszlopNevek, 0);
        tabla = new JTable(tableModel);
        tabla.setEnabled(false); // csak megjelenítésre

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void frissit(Gombasz g) {
        tableModel.setRowCount(0); // előző sorok törlése
        List<Gombatest> testek = g.getTestek();
        int index = 0;
        for (Gombatest test : testek) {
            tableModel.addRow(new Object[]{
                "Test" + index,
                test.tudSporatSzorni() ? "Igen" : "Nem",
                test.tudFonalatRakni() ? "Igen" : "Nem"
            });
            index++;
        }
        tableModel.fireTableDataChanged();
    }
}
