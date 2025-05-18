package swing;

import backend.Bogarasz;
import backend.Gombasz;
import backend.Jatek;
import backend.Jatekos;
import backend.Tekton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InfoFrame extends JFrame {
    
    private GombaszTulajdonsagPanel gombaszPanel;
    private BogaraszTulajdonsagPanel bogaraszPanel;
    private TektonTulajdonsagPanel tektonPanel;
    private PontokPanel pontokPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Jatek jatekmenet;
    private String mode = "pontok";
    private Tekton kivalasztott;

    public String getMode(){
        return mode;
    }

    public void setJatekmenet(Jatek j)
    {
        this.jatekmenet = j;
    }
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
        tektonPanel.frissit(kivalasztott,jatekmenet.getGombaszok(),jatekmenet.getBogaraszok());
        modeValtozas(this.mode);
        
    }

    public void modeValtozas(String mode) {
        this.mode=mode;
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
            case "C":
                cardLayout.show(cardPanel, "pontok");
                break;
            default:
                cardLayout.show(cardPanel, "pontok");
                break;
        }
    }
    
    public void setTektonInfo(Tekton t){
        kivalasztott=t;
        if(mode=="L"){
            frissit();
        }
    }
}