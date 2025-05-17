package swing;

import backend.Jatek;
import backend.Jatekos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class JatekPanel extends JPanel{
    
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
    private Jatek jatekmenet;

    public JatekPanel(MainFrame mainFrame, Jatek jatekmenet,InfoFrame infoFrame ){
        this.mainFrame = mainFrame;
        this.jatekmenet = jatekmenet;
        this.infoFrame=infoFrame;

        // palya merete
        final int SOR = 25;
        final int OSZLOP = 25;

        // felso panel
        felsoPanel = new JPanel();
        felsoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        mentes = new JButton("Mentes");
        kilepes = new JButton("Kilepes");

        jelenlegiJatekos = new JLabel("Jelenlegi gombasz");

        elsoComboBox = new JComboBox<String>();
        masodikComboBox = new JComboBox<String>();
        elsoComboBox.setEnabled(false);
        masodikComboBox.setEnabled(false);

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
                gomb.setEnabled(false);
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

        // mentes gomb mukodese
        mentes.addActionListener(e -> {
            int valasz = JOptionPane.showConfirmDialog(this, "Szeretné menteni a játékot?", "Mentés megerősítése", JOptionPane.YES_NO_OPTION);
            if (valasz == JOptionPane.YES_OPTION) {
                try {
                    FileOutputStream fileOut = new FileOutputStream("mentes3.txt");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(jatekmenet);  
                    out.close();
                    fileOut.close();

                    // 2. Dicsőséglista betöltése
                    List<Jatekos> dicsoseglista = new ArrayList<>();
                    File dicsosegFajl = new File("dicsoseglista.txt");
                    if (dicsosegFajl.exists()) {
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dicsosegFajl))) {
                            dicsoseglista = (List<Jatekos>) ois.readObject();
                        } catch (Exception ex) {
                            ex.printStackTrace(); // hibás fájlformátum esetén
                        }
                    }

                    // 3. Frissítés vagy hozzáadás
                    List<Jatekos> osszesJatekos = new ArrayList<>();
                    osszesJatekos.addAll(jatekmenet.getGombaszok());
                    osszesJatekos.addAll(jatekmenet.getBogaraszok());

                    for (Jatekos aktualis : osszesJatekos) {
                        boolean megtalalt = false;

                        for (Jatekos j : dicsoseglista) {
                            if (j.getNev().equals(aktualis.getNev())) {
                                j.setGyozelmekSzama(j.getGyozelmekSzama() + aktualis.getGyozelmekSzama());
                                j.setMeccsekSzama(j.getMeccsekSzama() + aktualis.getMeccsekSzama());
                                j.setOsszesPont(j.getOsszesPont() + aktualis.getOsszesPont());
                                megtalalt = true;
                                break;
                            }
                        }

                        if (!megtalalt) {
                            dicsoseglista.add(aktualis);
                        }
                    }

                    // 4. Dicsőséglista mentése
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dicsosegFajl))) {
                        oos.writeObject(dicsoseglista);
                    }
                    JOptionPane.showMessageDialog(this, "A játék sikeresen elmentve.", "Mentés kész", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Hiba történt a mentés során: " + ex.getMessage(), "Mentés hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Billentyuzet beallitas
        this.billentyuBeallitas();

        // comboboxok mukodese
        elsoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) elsoComboBox.getSelectedItem();
        
                if (selected != null && !selected.isEmpty() && command != null) {
                    command.addPAram(selected);
                }
            }
        });

        masodikComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) masodikComboBox.getSelectedItem();
        
                if (selected != null && !selected.isEmpty() && command != null) {
                    command.addPAram(selected);
                }
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

            JButton tgomb = mat[r][c];

            // Add current element to result list
            res.add(mat[r][c]);

            // tektonGombok megnyomasakor a jelenlegi parancshoz adodik hozza egy parameter: tekton+gom indexe a tombben
            tgomb.addActionListener(e->{
                if(command != null){
                    command.addPAram("tekton" + tektonGombok.indexOf(tgomb));
                }
            });

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

    private void billentyuBeallitas(){
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("B"), "bogarakListazasa");
        this.getActionMap().put("bogarakListazasa", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                new Command("bogarakListazasa", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released B"), "felengedveB");
        this.getActionMap().put("felengedveB", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frissit();
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("G"), "gombatestekListazasa");
        this.getActionMap().put("gombatestekListazasa", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Command("gombatestekListazasa", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released G"), "felengedveG");
        this.getActionMap().put("felengedveG", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frissit();
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "passz");
        this.getActionMap().put("passz", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Command("passz", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "leiras");
        this.getActionMap().put("leiras", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("leiras", jatekmenet, sajatMaga(),infoFrame);
                
            }
        });
        
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("I"), "info");
        this.getActionMap().put("info", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getInfoFrame().modeValtozas("I");

            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "gombaszEler");
        this.getActionMap().put("gombaszEler", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Command("gombaszEler", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released E"), "felengedveE");
        this.getActionMap().put("felengedveE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frissit();
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F"), "fonalLerak");
        this.getActionMap().put("fonalLerak", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("fonalLerak", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "sporaSzor");
        this.getActionMap().put("sporaSzor", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("sporaSzor", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("L"), "lepes");
        this.getActionMap().put("lepes", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("lepes", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("M"), "eves");
        this.getActionMap().put("eves", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("eves", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "ragas");
        this.getActionMap().put("ragas", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("ragas", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("O"), "fonallalOsszekotott");
        this.getActionMap().put("fonallalOsszekotott", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("fonallalOsszekotott", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("U"), "tektonHelyeTombben");
        this.getActionMap().put("tektonHelyeTombben", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = new Command("tektonHelyeTombben", jatekmenet, sajatMaga(),infoFrame);
            }
        });

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released U"), "felengedveU");
        this.getActionMap().put("felengedveU", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frissit();
            }
        });
    }

    

    // letiltja a tektonGombokat es a comboboxokat
    public void mindentLetilt(){
        tektonGombokEnabled(false);
        elsoComboBox.setEnabled(false);
        masodikComboBox.setEnabled(false);
    }

    private JatekPanel sajatMaga(){
        return this;
    }

    public void frissit(){

        mindentLetilt();

        // gombok frissitese
        int palyaMeret = jatekmenet.palyaMeret();
        for(int i = 0; i < palyaMeret; i++){
            tektonGombok.get(i).setBackground(Color.ORANGE);
            tektonGombok.get(i).setText("");
        }

        // jelenlegi jatekos frissitese
        jelenlegiJatekos.setText(jatekmenet.jelenlegiJatekosNeve());
    }

    public void bogarakListazasa(){
        int[] sajatBogarak = jatekmenet.sajatBogarakHelyei();
        int[] mindenBogar = jatekmenet.mindenBogarHelyei();
        for(int i = 0; i < mindenBogar.length; i++){
            if(jatekmenet.bogaraszKoreVanE()){
                tektonGombok.get(i).setText(sajatBogarak[i] + ", " + mindenBogar[i]);
            }
            else{
                tektonGombok.get(i).setText(""+ mindenBogar[i]);
            }
        }
    }

    public void gombatestekListazasa(){
        int[] sajatGombatestek = jatekmenet.sajatGombatestekHelyei();
        int[] mindenGombatest = jatekmenet.mindenGombatestHelyei();

        for(int i = 0; i < mindenGombatest.length; i++){
            if(jatekmenet.gombaszKoreVanE() && sajatGombatestek[i] != 0){
                tektonGombok.get(i).setBackground(Color.GREEN);
            }
            if(mindenGombatest[i] != 0){
                tektonGombok.get(i).setText("G");
            }
            
        }
    }

    public void gombaszEler(){
        try{
            boolean[] elerhetoTektonok = jatekmenet.gombaszEler("Koron levo gombasz");
            for(int i = 0; i < elerhetoTektonok.length; i++){
                if(elerhetoTektonok[i]){
                    tektonGombok.get(i).setBackground(Color.GREEN);
                }
            }
        }catch(IllegalArgumentException e){

        }
    }

    public void tektonGombokEnabled(boolean ertek){
        if(ertek){
            for(int i = 0; i < jatekmenet.palyaMeret(); i++){
            tektonGombok.get(i).setEnabled(ertek);
            }
        }
        else{
            for(JButton gomb : tektonGombok){
            gomb.setEnabled(ertek);
            }
        }
    }

    public void elsoComboboxEnabled(boolean ertek){
        elsoComboBox.setEnabled(ertek);
    }

    public void elsoComboboxElemek(ArrayList<String> elemek){
        elsoComboBox.setModel(new DefaultComboBoxModel<>(elemek.toArray(new String[0])));
        elsoComboBox.setSelectedItem(null);
    }

    public void masodikComboboxEnabled(boolean ertek){
        masodikComboBox.setEnabled(ertek);
    }

    public void masodikComboboxElemek(ArrayList<String> elemek){
        masodikComboBox.setModel(new DefaultComboBoxModel<>(elemek.toArray(new String[0])));
        masodikComboBox.setSelectedItem(null);
    }

    public void tektonSzinAllitas(boolean[] tektonok, Color color){
        for(int i = 0; i < tektonok.length; i++){
            if(tektonok[i]){
                tektonGombok.get(i).setBackground(color);
            }
        }
    }

    public void tektonSzovegAllitas(boolean[] tektonok, ArrayList<String> a ){
        int j=0;
        for(int i = 0; i < tektonok.length; i++){
            if(tektonok[i]){
                tektonGombok.get(i).setText(a.get(j));
                j++;
            }
        }
    }

    public void tektonEngedelyezes(boolean[] tektonok){
        for(int i = 0; i < tektonok.length; i++){
            if(tektonok[i]){
                tektonGombok.get(i).setEnabled(true);;
            }
        }
    }

    public void setCommandNull(){
        command = null;
    }

    public void tektonGombokSzama(){
        for(int i = 0; i < jatekmenet.palyaMeret(); i++){
            tektonGombok.get(i).setText("" + i);
        }
    }

}
