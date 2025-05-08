package swing;

import backend.Jatek;
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
    }

    public void frissit() {
        // Pontok frissítése
        pontokPanel.frissit(jatekmenet.getJatekosPontok());
        //gombaszPanel.frissit(jatekmenet.getAktivGombasz());
        //bogaraszPanel.frissit(jatekmenet.getAktivBogarasz());
        //tektonPanel.frissit(jatekmenet.getAktivTekton());
        
    }

    public void modeValtozas(int mode) {
        switch(mode) {
            case 0: cardLayout.show(cardPanel, "pontok"); break;
            case 1: cardLayout.show(cardPanel, "gombasz"); break;
            case 2: cardLayout.show(cardPanel, "bogarasz"); break;
            case 3: cardLayout.show(cardPanel, "tekton"); break;
            default: cardLayout.show(cardPanel, "pontok");
        }
    }
}