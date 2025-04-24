
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class View
{

    Scanner bemenet = InputHandler.getScanner();
    PrintStream kimenet = OutputHandler.getKimenet();
    Jatek menet = new Jatek();

    void bemenetKezeles()
    {
        String beolvas = bemenet.nextLine();
        String tordel[] = beolvas.split(" ");
        boolean ertek = true;
        Set<Tekton> tektonok;
        ArrayList<Tekton> tektonlista;
        ArrayList<Fonal> fonallista;
        switch (tordel[0]) {
            case "jatekIndit":
                
                break;
            case "ment":
                
                break;
            case "betolt":
                
                break;
            case "jatekosKor":
                
                break;
            case "korVege":
                
                break;
            
            case "info":
                kimenet.println(menet.info());
                break;
            case "passz":
                ertek = menet.passz();
                if(ertek)
                {
                    kimenet.println("Sikerult a muvelet");
                }
                else
                {
                    kimenet.println("Sikertelen a muvelet");
                }
                break;
            case "bogarakListazasa":
                try{
                    String atadandoParameter = "Koron levo bogar";
                    if(tordel.length >= 2){
                        atadandoParameter = tordel[1];
                    }
                    ArrayList<Bogar> bogarak = menet.bogarakListazas(atadandoParameter);
                    kimenet.println(atadandoParameter + " bogarai: ");
                    for(Bogar bogar : bogarak)
                    {
                        kimenet.println("bogar" + bogarak.indexOf(bogar));
                    }
                }catch(IllegalArgumentException e){
                    kimenet.println(e.getMessage());
                }
                break;
            case "lepes":
            
                ertek = menet.lepes(tordel[1], tordel[2], tordel[3]);
                if(ertek){
                    kimenet.println(tordel[1]+"-hez tartozo " + tordel[2] + " bogar a " + tordel[3] + " tektonra lepett");
                }
                else{
                    kimenet.println(tordel[1]+"-hez tartozo " + tordel[2] + " bogar nem tudott a " + tordel[3] + " tektonra lepni");
                }
                break;
            case "eves":
                
                if(tordel[1].contains("bogarasz"))
                {
                    ertek = menet.evesSporat(tordel[1], tordel[2]);
                }
                else
                {
                    ertek = menet.evesBogarat(tordel[1], tordel[2]);
                }
                
                if(ertek)
                {
                    kimenet.println("Sikerult a muvelet");
                }
                else
                {
                    kimenet.println("Sikertelen a muvelet");
                }
                break;
            case "ragas":
                ertek = menet.ragas(tordel[1],tordel[2],tordel[3]);
                if(ertek)
                {
                    kimenet.println("Sikerult a muvelet");
                }
                else
                {
                    kimenet.println("Sikertelen a muvelet");
                }
                break;
            case "fonalLerak":
                ertek = menet.fonalLerak(tordel[2], tordel[3], tordel[4]);
                if(ertek)
                {
                    kimenet.println("Sikerult a muvelet");
                }
                else
                {
                    kimenet.println("Sikertelen a muvelet");
                }
                break;
            case "sporaSzor":
                ertek = menet.sporaSzor(tordel[1], tordel[2], tordel[3]);
                if(ertek)
                {
                    kimenet.println("Sikerult a muvelet");
                }
                else
                {
                    kimenet.println("Sikertelen a muvelet");
                }
                break;
            case "gombaHovaRakhat":
                tektonok = menet.gombaHovaRakhat(tordel[1], tordel[2]);
                kimenet.println(tordel[1] +" gombasz, " + tordel[2]+" gombatestbol az alabbi tektonokra rakhat: ");
                for(Tekton a : tektonok)
                {
                    kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaEler":
                try{
                    String atadandoParameter = "Koron levo gombasz";
                    
                    if(tordel.length >= 3){
                        atadandoParameter = tordel[1];
                        tektonok = menet.gombaEler(atadandoParameter, tordel[2]);
                        kimenet.println(atadandoParameter +" gombasz, " + tordel[2]+" gombatestbol az alabbi tektonokat eri el: ");
                    }
                    else{
                        tektonok = menet.gombaEler(atadandoParameter, tordel[1]);
                        kimenet.println(atadandoParameter +", " + tordel[1]+" gombatestbol az alabbi tektonokat eri el: ");
                    }
                    
                    for(Tekton a : tektonok)
                    {
                        kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                    }

                }catch(IllegalArgumentException e){
                    kimenet.println(e.getMessage());
                }
                break;
            case "gombaszEler":
                tektonok = menet.gombaszEler(tordel[1]);
                kimenet.println(tordel[1] +" gombasz, az alabbi tektonokat eri el: ");
                for(Tekton a : tektonok)
                {
                    kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "fonallalOsszekotott":
                tektonlista = menet.fonallalOsszekotott(tordel[1]);
                kimenet.println(tordel[1] +" tektonrol, az alabbi tektonok erhetoek el fonalakon keresztul: ");
                for(Tekton a : tektonlista)
                {
                    kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaszHovaSzorhat":
               tektonok = menet.gombaszHovaSzorhat(tordel[1], tordel[2]);
               kimenet.println(tordel[1] +"gombasz az alabbi tektonokra szorhat: ");
                for(Tekton a : tektonok)
                {
                    kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "tektononFonal":
                fonallista = menet.tektononFonal(tordel[1]);
                kimenet.println(tordel[1] +" tektonon az alabbi fonalak vannak: ");
                for(Fonal a : fonallista)
                {
                    kimenet.println("fonal"+ menet.tektonFromString(tordel[1]).getOsszekoto().indexOf(a) + " ");
                }
                break;
            case "bogarTekton":
              Tekton t =  menet.bogarTekton(tordel[1], tordel[2]);
              kimenet.println("A " + tordel[1] +"-nek a " + tordel[2] + " bogara, a tekton" + menet.getJatekter().getPalya().indexOf(t) + " tektonon all.");
                break;
            case "gombaszok":
                kimenet.println("gombasz jatekosok: ");
                for(Gombasz g : menet.getGombaszok())
                {
                    kimenet.println("gombasz" + menet.getGombaszok().indexOf(g));
                }
                break;
            case "bogaraszok":
            kimenet.println("bogarasz jatekosok: ");
            for(Bogarasz g : menet.getBogaraszok())
            {
                kimenet.println("bogarasz" + menet.getBogaraszok().indexOf(g));
            }
                break;
            case "gombatestListazas":
                try{
                    String atadandoParameter = "Koron levo gombasz";
                    if(tordel.length >= 2){
                        atadandoParameter = tordel[1];
                    }
                    ArrayList<Gombatest> gt = menet.gombatestListazas(atadandoParameter);
                    kimenet.println(atadandoParameter + " gombatestjei: ");
                    for(Gombatest g : gt)
                    {
                        kimenet.println("gombatest" + gt.indexOf(g));
                    }
                }catch(IllegalArgumentException e){
                    kimenet.println(e.getMessage());
                }
                break;
            case "tektonSzomszedNincsFonal":
                tektonlista = menet.tektonSzomszedNincsFonal(tordel[1]);
                kimenet.println(tordel[1] + "tekton szomszedai amikre nem megy fonal: ");
                for(Tekton a : tektonlista)
                {
                    kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "benultBogar":
                ArrayList<Bogar> bogarLista = menet.benultBogar(tordel[1]);
                kimenet.println("A " + tordel[1] + " altal elert benitott bogarak: ");
                for(Bogar b : bogarLista)
                {
                    kimenet.println("bogar" + bogarLista.indexOf(b));
                }
                break;
            
            default:
                kimenet.println("\nnem ismert parancs\n");
        }
    }


}