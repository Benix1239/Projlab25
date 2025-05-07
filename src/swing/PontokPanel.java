package swing;

import javax.swing.*;

public class PontokPanel extends JPanel {
    
    private JTextField szoveg;
    private JTable data;
    private DefaultTableModel tableModel;

    public PontokPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        
        // Cím létrehozása
        szoveg = new JTextField("Játékosok pontszámai");
        szoveg.setEditable(false);
        szoveg.setHorizontalAlignment(JTextField.CENTER);
        szoveg.setFont(new Font("Arial", Font.BOLD, 16));
        add(szoveg, BorderLayout.NORTH);
        
        // Táblázat modell létrehozása
        String[] columnNames = {"Játékos", "Pontok száma"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // A táblázat nem szerkeszthető
            }
        };
        
        // Táblázat létrehozása
        data = new JTable(tableModel);
        data.setRowHeight(25);
        data.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Görgetősáv hozzáadása
        JScrollPane scrollPane = new JScrollPane(data);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void update(Map<String, Integer> jatekosPontok) {
        // Táblázat ürítése
        tableModel.setRowCount(0);
        
        // Adatok hozzáadása a táblázathoz
        if (jatekosPontok != null) {
            for (Map.Entry<String, Integer> entry : jatekosPontok.entrySet()) {
                Object[] row = {entry.getKey(), entry.getValue()};
                tableModel.addRow(row);
            }
        }
        
        // Táblázat frissítése
        tableModel.fireTableDataChanged();
    }
}