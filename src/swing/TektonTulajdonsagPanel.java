package swing;

import backend.Bogarasz;
import backend.Gombasz;
import backend.Tekton;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TektonTulajdonsagPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public TektonTulajdonsagPanel() {
        setLayout(new BorderLayout());

        // Fejléc
        JLabel cimke = new JLabel("Tekton tulajdonságai", SwingConstants.CENTER);
        cimke.setFont(new Font("Arial", Font.BOLD, 16));
        add(cimke, BorderLayout.NORTH);

        // Táblázat modell
        tableModel = new DefaultTableModel(new Object[]{"", "Sporak", "Gombatest", "Bogarak"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ne legyen szerkeszthető
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void frissit(Tekton tekton, List<Gombasz> gombaszok, List<Bogarasz> bogaraszok) {
        tableModel.setRowCount(0); // előző sorok törlése

        // Gombászok adatai
        if (tekton != null) {
            for (int i = 0; i < gombaszok.size(); i++) {
                Gombasz g = gombaszok.get(i);
                String sporak = tekton.getSporak() != null  ? String.valueOf(tekton.getSporak().size()) : "";
                String gombatest = tekton.getGombatest() != null&&tekton.getGombatest().getTartozik()==g ? "Van" : "-";
                tableModel.addRow(new Object[]{g.getNev(), sporak, gombatest, "-"});
            }
        } else {
            for (Gombasz g : gombaszok) {
                tableModel.addRow(new Object[]{g.getNev(), "-", "-", "-"});
            }
        }

        // Bogarászok adatai
        /*for (Bogarasz b : bogaraszok) {
            List<Bogar> bogarak = b.getBogarak(); 
            List<Tekton> bogarakHelyei = b.bogarakHelyei();
    
            StringBuilder ottLevoBogarak = new StringBuilder();
            for (int i = 0; i < bogarakHelyei.size(); i++) {
                Tekton t = bogarakHelyei.get(i);
                if (t.equals(tekton)) {
                    if (ottLevoBogarak.length() > 0) ottLevoBogarak.append(", ");
                    ottLevoBogarak.append(bogarak.get(i)); 
                }
            }
    
            String bogarakStr = ottLevoBogarak.length() > 0 ? ottLevoBogarak.toString() : "-";
            tableModel.addRow(new Object[]{b.getNev(), "-", "-", bogarakStr});
        }*/
    }
}
