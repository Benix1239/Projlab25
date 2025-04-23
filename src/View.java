
import java.util.Scanner;

public class View
{

    Scanner bemenet = InputHandler.getScanner();
    Jatek menet = new Jatek();

    void bemenetKezeles()
    {
        String beolvas = bemenet.nextLine();
        String tordel[] = beolvas.split(" ");

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
                menet.lepes(tordel[1], tordel[2], tordel[3]);
                break;
            case "eves":
                
                if(tordel[1].contains("bogarasz"))
                {
                    menet.evesSporat(tordel[1], tordel[2]);
                }
                else
                {
                    menet.evesBogarat(tordel[1], tordel[2]);
                }

                break;
            case "ragas":
                menet.ragas(tordel[1],tordel[2],tordel[3]);
                break;
            case "fonalLerak":
                menet.fonalLerak(tordel[1], tordel[2], tordel[3], tordel[4]);
                break;
            case "sporaSzor":
                menet.sporaSzor(tordel[1], tordel[2], tordel[3]);
                break;
            case "gombaHovaRakhat":
                menet.gombaHovaRakhat(tordel[1], tordel[2]);
                break;
            case "gombaEler":
                menet.gombaEler(tordel[1], tordel[2]);
                break;
            case "gombaszEler":
                menet.gombaszEler(tordel[1]);
                break;
            case "fonallalOsszekotott":
                menet.fonallalOsszekotott(tordel[1]);
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