package swing;

import backend.Jatek;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
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
        jatekPanel = new JatekPanel(this);
        betoltPanel = new BetoltPanel(this);

        // panelek hozzaadasa a cardPanelhez
        cardPanel.add(fomenuPanel, "fomenuPanel");
        cardPanel.add(jatekosMegadosPanel, "jatekosMegadosPanel");
        cardPanel.add(jatekPanel, "jatekPanel");
        cardPanel.add(betoltPanel, "betoltPanel");

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
    public void infoFrameBekapcs(){
        infoFrame.setVisible(true);
    }

    //betoltPanel bekapcsolasa, hogy ki lehessen valasztani, hogy melyik jatekot akarod folytatni
    public void betoltBekapcs(){
        betoltPanel.update(new String[] {"Jatek1", "Üres", "Üres"}); // később fájlból olvasható
        cardLayout.show(cardPanel, "betoltPanel");
        setSize(500, 300);
        setLocationRelativeTo(null);
    }

    //dicsosegPanel bekapcsolasa
    public void dicsosegBekapcs(){
        dicsosegPanel.setVisible(true);
    }

    //TODO
    public void jatekBetolt(int melyik){
        
    }

    //TODO
    public void jatekPanelBekapcs(){
        cardLayout.show(cardPanel, "jatekPanel");
        setSize(1400, 1000);
        setLocationRelativeTo(null);
    }

    // jatekMenet letrehozasa uj jatek eseten. Parameterkent megkapja az uj jatekosok neveit
    public void jatekIndit(ArrayList<String> jatekosNevek){
        
    }
}
