
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class View
{

    Scanner bemenet = InputHandler.getScanner();
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
            case "passz":
                ertek=menet.passzBogarasz(tordel[1], tordel[2]);
                break;
            case "lepes":
                ertek = menet.lepes(tordel[1], tordel[2], tordel[3]);
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
                    System.out.println("Sikerult a muvelet");
                }
                else
                {
                    System.out.println("Sikertelen a muvelet");
                }
                break;
            case "ragas":
                ertek = menet.ragas(tordel[1],tordel[2],tordel[3]);
                if(ertek)
                {
                    System.out.println("Sikerult a muvelet");
                }
                else
                {
                    System.out.println("Sikertelen a muvelet");
                }
                break;
            case "fonalLerak":
                ertek = menet.fonalLerak(tordel[1], tordel[2], tordel[3], tordel[4]);
                if(ertek)
                {
                    System.out.println("Sikerult a muvelet");
                }
                else
                {
                    System.out.println("Sikertelen a muvelet");
                }
                break;
            case "sporaSzor":
                ertek = menet.sporaSzor(tordel[1], tordel[2], tordel[3]);
                if(ertek)
                {
                    System.out.println("Sikerult a muvelet");
                }
                else
                {
                    System.out.println("Sikertelen a muvelet");
                }
                break;
            case "gombaHovaRakhat":
                tektonok = menet.gombaHovaRakhat(tordel[1], tordel[2]);
                System.out.println(tordel[1] +" gombasz, " + tordel[2]+" gombatestbol az alabbi tektonokra rakhat: ");
                for(Tekton a : tektonok)
                {
                    System.out.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaEler":
                tektonok = menet.gombaEler(tordel[1], tordel[2]);
                System.out.println(tordel[1] +" gombasz, " + tordel[2]+" gombatestbol az alabbi tektonokat eri el: ");
                for(Tekton a : tektonok)
                {
                    System.out.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaszEler":
                tektonok = menet.gombaszEler(tordel[1]);
                System.out.println(tordel[1] +" gombasz, az alabbi tektonokat eri el: ");
                for(Tekton a : tektonok)
                {
                    System.out.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "fonallalOsszekotott":
                tektonlista = menet.fonallalOsszekotott(tordel[1]);
                System.out.println(tordel[1] +" tektonrol, az alabbi tektonok erhetoek el fonalakon keresztul: ");
                for(Tekton a : tektonlista)
                {
                    System.out.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaszHovaSzorhat":
               tektonok = menet.gombaszHovaSzorhat(tordel[1], tordel[2]);
               System.out.println(tordel[1] +"gombasz az alabbi tektonokra szorhat: ");
                for(Tekton a : tektonok)
                {
                    System.out.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "tektononFonal":
                fonallista = menet.tektononFonal(tordel[1]);
                System.out.println(tordel[1] +" tektonon az alabbi fonalak vannak: ");
                for(Fonal a : fonallista)
                {
                    System.out.println("fonal"+ menet.tektonFromString(tordel[1]).getOsszekoto().indexOf(a) + " ");
                }
                break;
            case "bogarTekton":
              Tekton t =  menet.bogarTekton(tordel[1], tordel[2]);
              System.out.println(tordel[1] +"-nek a " + tordel[2] + " bogara, a tekton" + menet.getJatekter().getPalya().indexOf(t) + "tektonon all.");
                break;
            case "gombaszok":
                System.out.println("gombasz jatekosok: ");
                for(Gombasz g : menet.getGombaszok())
                {
                    System.out.println("gombasz" + menet.getGombaszok().indexOf(g));
                }
                break;
            case "bogaraszok":
            System.out.println("bogarasz jatekosok: ");
            for(Bogarasz g : menet.getBogaraszok())
            {
                System.out.println("bogarasz" + menet.getBogaraszok().indexOf(g));
            }
                break;
            case "gombatestListazas":
               ArrayList<Gombatest> gt = menet.gombatestListazas(tordel[1]);
                System.out.println(tordel[1] + " gombatestjei: ");
                for(Gombatest g : gt)
                {
                    System.out.println("gombatest" + gt.indexOf(g));
                }
                break;
            case "tektonSzomszedNincsFonal":
                tektonlista = menet.tektonSzomszedNincsFonal(tordel[1]);
                System.out.println(tordel[1] + "tekton szomszedai amikre nem megy fonal: ");
                for(Tekton a : tektonlista)
                {
                    System.out.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) + " ");
                }
                break;
            case "benultBogar":
                ArrayList<Bogar> bogarLista = menet.benultBogar(tordel[1]);
                System.out.println("A " + tordel[1] + " altal elert benitott bogarak: ");
                for(Bogar b : bogarLista)
                {
                    System.out.println("bogar" + bogarLista.indexOf(b));
                }
                break;
            
            default:
                System.out.println("\nnem ismert parancs\n");
        }
    }


}