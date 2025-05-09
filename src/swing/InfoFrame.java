package swing;

import backend.Bogarasz;
import backend.Gombasz;
import backend.Jatek;
import backend.Jatekos;
import java.awt.*;
import javax.swing.*;

public class InfoFrame extends JFrame {
    
    private GombaszTulajdonsagPanel gombaszPanel;
    private BogaraszTulajdonsagPanel bogaraszPanel;
    private TektonTulajdonsagPanel tektonPanel;
    private PontokPanel pontokPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Jatek jatekmenet;

    public InfoFrame(Jatek jatek) {
        this.jatekmenet = jatek;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Játék jelenlegi állása");
        setSize(500, 400);
        setLayout(new BorderLayout());
        
        // Gombok felülre
        /*JPanel gombPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton pontokGomb = new JButton("Pontok");
        JButton gombaszGomb = new JButton("Gombász");
        JButton bogaraszGomb = new JButton("Bogarász");
        JButton tektonGomb = new JButton("Tekton");

        // Gomb eseménykezelők
        pontokGomb.addActionListener(e -> modeValtozas(0));
        gombaszGomb.addActionListener(e -> modeValtozas(1));
        bogaraszGomb.addActionListener(e -> modeValtozas(2));
        tektonGomb.addActionListener(e -> modeValtozas(3));

        // Gombok hozzáadása a panelhez
        gombPanel.add(pontokGomb);
        gombPanel.add(gombaszGomb);
        gombPanel.add(bogaraszGomb);
        gombPanel.add(tektonGomb);

        // Gombpanel hozzáadása felülre
        add(gombPanel, BorderLayout.NORTH);*/

        // CardLayout inicializálása
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Panelek létrehozása
        gombaszPanel = new GombaszTulajdonsagPanel();
        bogaraszPanel = new BogaraszTulajdonsagPanel();
        tektonPanel = new TektonTulajdonsagPanel();
        pontokPanel = new PontokPanel();
        
        // Panelek hozzáadása
        cardPanel.add(pontokPanel, "pontok");
        cardPanel.add(gombaszPanel, "gombasz");
        cardPanel.add(bogaraszPanel, "bogarasz");
        cardPanel.add(tektonPanel, "tekton");
        
       
        add(cardPanel, BorderLayout.CENTER);
        
        // Kezdeti frissítés
        //frissit();
    }

    public void frissit() {
        // Pontok frissítése
        pontokPanel.frissit(jatekmenet.getJatekosPontok());
        Jatekos aktualis = jatekmenet.jelenlegiJatekos();

        if (aktualis instanceof Gombasz g) {
            gombaszPanel.frissit(g);
        } else if (aktualis instanceof Bogarasz b) {
                bogaraszPanel.frissit(b);
        }
        //tektonPanel.frissit(,jatekmenet.getGombaszok(),jatekmenet.getBogaraszok());
        
    }

    public void modeValtozas(String mode) {
        switch(mode) {
            case "I":
                Jatekos j = jatekmenet.jelenlegiJatekos();
    
                if (j instanceof backend.Gombasz) {
                    cardLayout.show(cardPanel, "gombasz");
                } else if (j instanceof backend.Bogarasz) {
                    cardLayout.show(cardPanel, "bogarasz");
                }
                break;
    
            case "L":
                cardLayout.show(cardPanel, "tekton");
                break;
    
            default:
                cardLayout.show(cardPanel, "pontok");
                break;
        }
    
        frissit();
    }
    
}