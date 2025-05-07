package swing;

import backend.Jatek;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JatekPanel extends JPanel {
    
    private MainFrame mainFrame;
    private InfoFrame infoFrame;
    private JButton mentes;
    private JButton kilepes;
    private JLabel jelenlegiJatekos;
    private JLabel visszajelzes;
    private JComboBox<String> elsoComboBox;
    private JComboBox<String> masodikComboBox;
    private JPanel palyaPanel;
    private JPanel felsoPanel;
    private ArrayList<JButton> tektonGombok;
    private Command command;
    private Jatek jatekMenet;

    public JatekPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        // palya merete
        final int SOR = 25;
        final int OSZLOP = 25;

        // felso panel
        felsoPanel = new JPanel();
        felsoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        mentes = new JButton("Mentes");
        kilepes = new JButton("Kilepes");

        jelenlegiJatekos = new JLabel("Jelenlegi gombasz");

        elsoComboBox = new JComboBox<>(new String[]{"bogar1", "bogar2"});
        masodikComboBox = new JComboBox<>(new String[]{"fonal1", "fonal2"});

        Dimension szurkeMeret = new Dimension(80, 30);
        mentes.setPreferredSize(szurkeMeret);
        kilepes.setPreferredSize(szurkeMeret);
        mentes.setBackground(Color.LIGHT_GRAY);
        kilepes.setBackground(Color.LIGHT_GRAY);

        felsoPanel.add(mentes);
        felsoPanel.add(jelenlegiJatekos);
        felsoPanel.add(elsoComboBox);
        felsoPanel.add(masodikComboBox);
        felsoPanel.add(kilepes);

        // kozepso gomb panel
        JButton[][] gombokMatrix = new JButton[SOR][OSZLOP];
        palyaPanel = new JPanel(new GridLayout(SOR, OSZLOP));
        for (int i = 0; i < SOR; i++) {
            for (int j = 0; j < OSZLOP; j++) {
                JButton gomb = new JButton();
                gomb.setPreferredSize(new Dimension(40, 40));
                gomb.setMargin(new Insets(0, 0, 0, 0));
                gomb.setOpaque(true);
                gomb.setBorderPainted(true);
                gomb.setBackground(Color.WHITE); // Alap háttér
                gombokMatrix[i][j] = gomb;
                palyaPanel.add(gomb);
            }
        }
        spiralGombBejaras(gombokMatrix);

        //also visszajelzo mezo
        visszajelzes = new JLabel("Visszajelzes");
        visszajelzes.setFont(new Font("SansSerif", Font.PLAIN, 32));
        visszajelzes.setHorizontalAlignment(JTextField.CENTER);
        visszajelzes.setPreferredSize(new Dimension(10000, 100));

        // kilepes gomb mukodese
        kilepes.addActionListener(e -> {
            int valasz = JOptionPane.showConfirmDialog(this, "Biztos, hogy ki akar lepni?", "Kilépés megerősítése", JOptionPane.YES_NO_OPTION);
            if (valasz == JOptionPane.YES_OPTION) {
                mainFrame.foMenuBekapcs();
            }
        });

        // JatekPanel elrendezese
        this.setLayout(new BorderLayout());
        this.add(felsoPanel, BorderLayout.NORTH);
        this.add(palyaPanel, BorderLayout.CENTER);
        this.add(visszajelzes, BorderLayout.SOUTH);
    }

     // gombok spiralis bejarasa. Az algoritmus alapja a https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/ linkrol szarmazik
    private void spiralGombBejaras(JButton[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        ArrayList<JButton> res = new ArrayList<>();
        boolean[][] vis = new boolean[m][n];

        // Change in row index for each direction
        int[] dr = { 0, 1, 0, -1 };

        // Change in column index for each direction
        int[] dc = { 1, 0, -1, 0 };

        // Initial position in the matrix
        int r = 0, c = 0;

        // Initial direction index (0 corresponds to 'right')
        int idx = 0;

        for (int i = 0; i < m * n; ++i) {

            // Add current element to result list
            res.add(mat[r][c]);

            // Mark current cell as visited
            vis[r][c] = true;

            // Calculate the next cell coordinates based on
            // current direction
            int newR = r + dr[idx];
            int newC = c + dc[idx];

            // Check if the next cell is within bounds and not
            // visited
            if (0 <= newR && newR < m && 0 <= newC && newC < n
                    && !vis[newR][newC]) {

                // Move to the next row
                r = newR;

                // Move to the next column
                c = newC;
            } else {

                // Change direction (turn clockwise)
                idx = (idx + 1) % 4;

                // Move to the next row according to new
                // direction
                r += dr[idx];

                // Move to the next column according to new
                // direction
                c += dc[idx];
            }
        }

        Collections.reverse(res);
        tektonGombok = res;
    }
}
