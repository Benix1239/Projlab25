package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LegendFrame extends JFrame {
    
    private JPanel panel;
    private JTextArea szoveg;

    LegendFrame()
    {
        this.panel = new JPanel();
        szoveg = new JTextArea();
        this.add(this.panel);
        this.panel.add(this.szoveg);
        szoveg.setEditable(false);
        szoveg.setText("B : Bogarak Listazasa\nG : Gombatestek Listazasa\nO : Fonallal Osszekotott szomszedok mutatasa\nI : Info a jelenlegi jatekosrol\nC : A pontok jelenlegi allasat mutatja\nD : Leiras a kivalasztott tektonrol\nP : Passz\nE : Gombasz Eler\nF : Fonal Lerakas\nS : Spora Szoras\nM : Eves\nL : Lepes Bogarral\nR : Ragas Bogarral\nU: Tektonok száma");
        this.pack();
    }
}
