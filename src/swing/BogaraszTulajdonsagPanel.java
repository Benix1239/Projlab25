package swing;

import backend.Bogar;
import backend.Bogarasz;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BogaraszTulajdonsagPanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel tableModel;

    public BogaraszTulajdonsagPanel() {
        setLayout(new BorderLayout());

        JLabel cimke = new JLabel("Bogarasz lehetseges lepesek", SwingConstants.CENTER);
        add(cimke, BorderLayout.NORTH);

        String[] oszlopNevek = {"Bogár", "Tud enni", "Tud rágni", "Mozgás szám"};
        tableModel = new DefaultTableModel(oszlopNevek, 0);
        tabla = new JTable(tableModel);
        tabla.setEnabled(false); // csak megjelenítésre

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void frissit(Bogarasz b) {
        tableModel.setRowCount(0); // előző sorok törlése
        List<Bogar> bogarak = b.getBogarak();
        int index = 0;
        for (Bogar bogar : bogarak) {
            tableModel.addRow(new Object[]{
                "Bogar" + index,
                bogar.getactionEves() ? "Igen" : "Nem",
                bogar.getactionRagas() ? "Igen" : "Nem",
                bogar.getMozgaspont()
            });
            index++;
        }
        tableModel.fireTableDataChanged();
    }
}
