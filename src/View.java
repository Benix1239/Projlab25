
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
                    System.out.println("tekton"+ menet.getjatekter.getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaEler":
                tektonok = menet.gombaEler(tordel[1], tordel[2]);
                System.out.println(tordel[1] +" gombasz, " + tordel[2]+" gombatestbol az alabbi tektonokat eri el: ");
                for(Tekton a : tektonok)
                {
                    System.out.println("tekton"+ menet.getjatekter.getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaszEler":
                tektonok = menet.gombaszEler(tordel[1]);
                System.out.println(tordel[1] +" gombasz, az alabbi tektonokat eri el: ");
                for(Tekton a : tektonok)
                {
                    System.out.println("tekton"+ menet.getjatekter.getPalya().indexOf(a) + " ");
                }
                break;
            case "fonallalOsszekotott":
                tektonlista = menet.fonallalOsszekotott(tordel[1]);
                System.out.println(tordel[1] +" tektonrol, az alabbi tektonok erhetoek el fonalakon keresztul: ");
                for(Tekton a : tektonlista)
                {
                    System.out.println("tekton"+ menet.getjatekter.getPalya().indexOf(a) + " ");
                }
                break;
            case "gombaszHovaSzorhat":
                
                break;
            case "tektononFonal":
                
                break;
            case "bogarTekton":
                
                break;
            case "palyaKor":
                
                break;
            case "gombaszHozzad":
                
                break;
            case "bogaraszHozzad":
                
                break;
            case "tetkonHozzad":
                
                break;
            case "gombatestHozzaad":
                
                break;
            case "bogarHozzad":
                
                break;
            case "sporaHozzad":
                
                break;
            case "gombaszok":
                
                break;
            case "bogaraszok":
                
                break;
            case "helyzet":
                
                break;
            case "megemeszt":
                
                break;
            case "gombatestListazas":
                
                break;
            case "tektonSzomszedNincsFonal":
                
                break;
            case "benultBogar":
                
                break;
            
            default:
                System.out.println("\nnem ismert parancs\n");
        }
    }


}