package swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.Jatek;

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

        // panelek hozzaadasa a cardPanelhez
        cardPanel.add(fomenuPanel, "fomenu");
        cardPanel.add(jatekosMegadosPanel, "jatekos");

        // foablak tartalmanak beallitasa
        setContentPane(cardPanel);

        // kezdokepernyo
        foMenuBekapcs();
    }

    //Fomenu bekapcsolasa
    public void foMenuBekapcs(){
        cardLayout.show(cardPanel, "fomenu");
        setSize(300, 300);
        setLocationRelativeTo(null);
    }

    //jatekosMegadosPanel bekapcsolasa, hogy a jatekosokat meg lehessen adni
    public void ujJatekBekapcs(){
        cardLayout.show(cardPanel, "jatekos");
        setSize(400, 180);
        setLocationRelativeTo(null);
    }

    //infoFrame bekapcsolasa
    public void infoFrameBekapcs(){
        infoFrame.setVisible(true);
    }

    //betoltPanel bekapcsolasa, hogy ki lehessen valasztani, hogy melyik jatekot akarod folytatni
    public void betoltBekapcs(){
        betoltPanel.setVisible(true);
    }

    //dicsosegPanel bekapcsolasa
    public void dicsosegBekapcs(){
        dicsosegPanel.setVisible(true);
    }

    //TODO
    public void jatekBetolt(int melyik){
        
    }

}
