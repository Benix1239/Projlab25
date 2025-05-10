package swing;

import backend.Jatek;
import java.awt.CardLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private FomenuPanel fomenuPanel;
    private JatekPanel jatekPanel;
    private BetoltPanel betoltPanel;
    private JatekosMegadosPanel jatekosMegadosPanel;
    private DicsosegPanel dicsosegPanel;
    private InfoFrame infoFrame;
    private LegendFrame legendFrame;

    private Jatek jatekmenet;

    //TODO
    public MainFrame(){
        setTitle("Fungorium");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // CardLayout beallitasa
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // panelek peldanyositasa
        fomenuPanel = new FomenuPanel(this);
        jatekosMegadosPanel = new JatekosMegadosPanel(this);
        infoFrame = new InfoFrame(jatekmenet);
        jatekPanel = new JatekPanel(this,jatekmenet,infoFrame);
        betoltPanel = new BetoltPanel(this);
        dicsosegPanel = new DicsosegPanel(this);
        

        // panelek hozzaadasa a cardPanelhez
        cardPanel.add(fomenuPanel, "fomenuPanel");
        cardPanel.add(jatekosMegadosPanel, "jatekosMegadosPanel");
        cardPanel.add(betoltPanel, "betoltPanel");
        cardPanel.add(dicsosegPanel, "dicsosegPanel");
        //cardPanel.add(infoFrame,"infoFrame");

        // foablak tartalmanak beallitasa
        setContentPane(cardPanel);

        // kezdokepernyo
        foMenuBekapcs();
    }

    //Fomenu bekapcsolasa
    public void foMenuBekapcs(){
        cardLayout.show(cardPanel, "fomenuPanel");
        setSize(300, 300);
        setLocationRelativeTo(null);
    }

    //jatekosMegadosPanel bekapcsolasa, hogy a jatekosokat meg lehessen adni
    public void ujJatekBekapcs(){
        cardLayout.show(cardPanel, "jatekosMegadosPanel");
        setSize(400, 180);
        setLocationRelativeTo(null);
    }

    //infoFrame bekapcsolasa
    public void infoFrameBekapcs() {
        infoFrame.frissit();
        infoFrame.setVisible(true);
    }

    //betoltPanel bekapcsolasa, hogy ki lehessen valasztani, hogy melyik jatekot akarod folytatni
    public void betoltBekapcs(){
        cardLayout.show(cardPanel, "betoltPanel");
        setSize(500, 300); // vagy amit szeretnél
        setLocationRelativeTo(null);
    }

    //dicsosegPanel bekapcsolasa
    public void dicsosegBekapcs() {
        cardLayout.show(cardPanel, "dicsosegPanel");
        dicsosegPanel.betoltEsFrissit();
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    //A megfelelő játékmenetet tölti be
   public void jatekBetolt(int mentesSzam) {
        try {
            String fajlNev = "mentes" + mentesSzam + ".txt";

            // NINCS új példány itt
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fajlNev));
            jatekmenet = (Jatek) in.readObject(); // vagy Jatekmenet
            in.close();

            // új játékpanel példányosítása a betöltött játékmenettel
            jatekPanel = new JatekPanel(this, jatekmenet, infoFrame);
            cardPanel.add(jatekPanel, "jatekPanel");
            cardLayout.show(cardPanel, "jatekPanel");
            cardPanel.revalidate();
            cardPanel.repaint();
            setSize(1400, 1000);
            setLocationRelativeTo(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Hiba történt a játék betöltése közben: " + e.getMessage(),
                    "Betöltési hiba",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        infoFrame = new InfoFrame(jatekmenet);
    }

    //TODO
    public void jatekPanelBekapcs(){
        cardLayout.show(cardPanel, "jatekPanel");
        setSize(1400, 1000);
        setLocationRelativeTo(null);
    }

    // jatekMenet letrehozasa uj jatek eseten. Parameterkent megkapja az uj jatekosok neveit
    public void jatekIndit(ArrayList<String> jatekosNevek){
        if(jatekosNevek.size() == 4){
            jatekmenet = new Jatek(this, jatekosNevek);
            infoFrame = new InfoFrame(jatekmenet);
            jatekPanel = new JatekPanel(this, jatekmenet,infoFrame);
            cardPanel.add(jatekPanel, "jatekPanel");
            

            jatekPanel.frissit();
        }
    }

    // meghivja a tobbi elem frissit() fv-et
    public void frissit(){
        jatekPanel.frissit();
        infoFrame.frissit();
    }

    public InfoFrame getInfoFrame(){
        return infoFrame;
    }
}
