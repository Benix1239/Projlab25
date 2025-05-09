package swing;

import backend.Jatek;
import backend.Tekton;
import java.awt.Color;
import java.util.ArrayList;

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
        this.paramaterek = new ArrayList<>();
        executeCommand();
    }

    //TODO
    public void addPAram(String param){
        paramaterek.add(param);
        executeCommand();
    }

    //TODO
    private void executeCommand(){
        jatekPanel.frissit();
        switch(commandNev){
            case "leiras":
                if (paramaterek.size()==0) {
                    jatekPanel.mindentEngedely();
                }
                if(paramaterek.size() == 1){
                    Tekton valasztott = jatekmenet.tektonFromString(paramaterek.get(1));
                    infoFrame.setTektonInfo(valasztott);
                }
                break;
            case "bogarakListazasa":
                jatekPanel.bogarakListazasa();
                break;

            case "gombatestekListazasa":
                jatekPanel.gombatestekListazasa();
                break;

            case "passz":
                jatekmenet.passz();
                break;

            case "gombaszEler":
                jatekPanel.gombaszEler();
                break;

            case "fonalLerak":
                if(jatekmenet.gombaszKoreVanE()){
                    if(paramaterek.size() == 0){
                        jatekPanel.elsoComboboxElemek(jatekmenet.jelenlegiGombaszFonalLerakosTestjei());
                        jatekPanel.elsoComboboxEnabled(true);
                        jatekPanel.tektonGombokEnabled(false);
                        jatekPanel.masodikComboboxEnabled(false);
                    }

                    if(paramaterek.size() == 1){
                        boolean[] hovaRakhat = jatekmenet.gombaHovaRakhat("Koron levo gombasz", paramaterek.get(0));
                        jatekPanel.tektonSzinAllitas(hovaRakhat, Color.GREEN);
                        jatekPanel.tektonEngedelyezes(hovaRakhat);
                    }

                    if(paramaterek.size() == 2){
                        boolean[] honnanRakhat = jatekmenet.gombaHonnanRakhat("Koron levo gombasz", paramaterek.get(0), paramaterek.get(1));
                        int hanyIgaz = 0;
                        int tektonIndex = 0;

                        for(int i = 0; i < honnanRakhat.length; i++){
                            if(honnanRakhat[i]){
                                hanyIgaz++;
                                tektonIndex = i;
                            }
                        }

                        if(hanyIgaz == 1){
                            addPAram("tekton" + tektonIndex);
                        }
                        else{
                            jatekPanel.tektonSzinAllitas(honnanRakhat, Color.GREEN);
                            jatekPanel.tektonEngedelyezes(honnanRakhat);
                        }
                    }

                    if(paramaterek.size() == 3){
                        jatekmenet.fonalLerak(paramaterek.get(0), paramaterek.get(2), paramaterek.get(1));
                        jatekPanel.setCommandNull();
                    }
                }
                break;
            case "sporaSzor":
                if(jatekmenet.gombaszKoreVanE()){
                    if(paramaterek.size() == 0){
                        jatekPanel.elsoComboboxElemek(jatekmenet.jelenlegiGombaszSporaSzorosTestjei());
                        jatekPanel.elsoComboboxEnabled(true);
                        jatekPanel.tektonGombokEnabled(false);
                        jatekPanel.masodikComboboxEnabled(false);
                    }

                    if(paramaterek.size() == 1){
                        boolean[] hovaSzorhat = jatekmenet.gombaszHovaSzorhat("Koron levo gombasz", paramaterek.get(0));
                        jatekPanel.tektonSzinAllitas(hovaSzorhat, Color.GREEN);
                        jatekPanel.tektonEngedelyezes(hovaSzorhat);
                    }

                    if(paramaterek.size() == 2){
                        jatekmenet.sporaSzor(paramaterek.get(0), paramaterek.get(1));
                        jatekPanel.setCommandNull();
                    }
                }
                break;
            case "fonallalOsszekotott":
                if(paramaterek.size() == 0){

                }

                if(paramaterek.size() != 0){
                    
                }
                break;
        }
    }

}
