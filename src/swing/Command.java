package swing;

import java.util.ArrayList;

import backend.Jatek;

public class Command {
    
    private ArrayList<String> paramaterek;
    private String commandNev;
    private JatekPanel jatekPanel;
    private InfoFrame infoFrame;

    private Jatek jatekmenet;

    //TODO
    public Command(String nev, Jatek jatekmenet, JatekPanel jatekPanel){
        this.commandNev = nev;
        this.jatekmenet = jatekmenet;
        this.jatekPanel = jatekPanel;
        executeCommand();
    }

    //TODO
    public void addPAram(String param){}

    //TODO
    private void executeCommand(){
        switch(commandNev){
            case "bogarakListazasa":
                jatekPanel.bogarakListazasa();
                break;

            case "gombatestekListazasa":
                jatekPanel.gombatestekListazasa();
                break;

            case "passz":
                jatekmenet.passz();
                break;
        }
    }

}
