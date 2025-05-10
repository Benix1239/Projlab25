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
    public Command(String nev, Jatek jatekmenet, JatekPanel jatekPanel, InfoFrame infoFrame){
        this.commandNev = nev;
        this.jatekmenet = jatekmenet;
        this.jatekPanel = jatekPanel;
        this.infoFrame=infoFrame;
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
                jatekPanel.tektonGombokEnabled(true);
                if (paramaterek.isEmpty()) {   
                    infoFrame.modeValtozas("L");
                } else {
                    Tekton valasztott = jatekmenet.tektonFromString(paramaterek.getLast());
                    infoFrame.setTektonInfo(valasztott);
                    infoFrame.frissit();
                }
                break;
            case "lepes":
                if(jatekmenet.bogaraszKoreVanE()){
                    if (paramaterek.isEmpty()) {   
                        jatekPanel.elsoComboboxElemek(jatekmenet.jelenlegiBogaraszBogaraiTudLepni());
                        jatekPanel.elsoComboboxEnabled(true);
                        jatekPanel.tektonGombokEnabled(false);
                        jatekPanel.masodikComboboxEnabled(false);
                    }
                    else if(paramaterek.size()==1){
                        boolean[] hovaLephet = jatekmenet.bogarHovaLephet("Koron levo bogarasz", paramaterek.get(0));
                        jatekPanel.tektonSzinAllitas(hovaLephet, Color.GREEN);
                        jatekPanel.tektonEngedelyezes(hovaLephet);   
                    }
                    else if(paramaterek.size()==2){
                        jatekmenet.lepes("Koron levo bogarasz",paramaterek.get(0), paramaterek.get(1));
                        jatekPanel.setCommandNull(); 
                    }
                }
                break;
            case "eves":
                if(jatekmenet.bogaraszKoreVanE()){
                    if (paramaterek.isEmpty()) {   
                        jatekPanel.elsoComboboxElemek(jatekmenet.jelenlegiBogaraszBogaraiTudEnni());
                        jatekPanel.elsoComboboxEnabled(true);
                        jatekPanel.tektonGombokEnabled(false);
                        jatekPanel.masodikComboboxEnabled(false);
                    }
                    if(paramaterek.size() == 1){
                        jatekmenet.evesSporat("Koron levo bogarasz",paramaterek.get(0));
                        jatekPanel.setCommandNull();
                    }
                }
                else{
                    if(paramaterek.isEmpty()) {   
                        
                    }
                }
                
                break;
            case "ragas":
                if(jatekmenet.bogaraszKoreVanE()){
                    if (paramaterek.isEmpty()) {   
                        jatekPanel.elsoComboboxElemek(jatekmenet.jelenlegiBogaraszBogaraiTudRagni());
                        jatekPanel.elsoComboboxEnabled(true);
                        jatekPanel.tektonGombokEnabled(false);
                        jatekPanel.masodikComboboxEnabled(false);
                    }
                    else if(paramaterek.size()==1){
                        jatekPanel.elsoComboboxEnabled(false);
                        jatekPanel.tektonGombokEnabled(false);
                        jatekPanel.masodikComboboxElemek(jatekmenet.bogarMitTudElragni("Koron levo bogarasz",paramaterek.get(0)));
                        jatekPanel.masodikComboboxEnabled(true);
                        boolean[] hovamegy = jatekmenet.bogarHovaLephet("Koron levo bogarasz", paramaterek.get(0));
                        jatekPanel.tektonSzovegAllitas(hovamegy, jatekmenet.bogarMitTudElragni("Koron levo bogarasz",paramaterek.get(0)));
                    }
                    else if(paramaterek.size()==2){
                        jatekmenet.ragas("Koron levo bogarasz", paramaterek.get(0),paramaterek.get(1));
                        jatekPanel.setCommandNull();
                    }
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
