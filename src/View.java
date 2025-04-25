import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class View
{

    Scanner bemenet = InputHandler.getScanner();
    PrintStream kimenet = OutputHandler.getKonzol();
    Scanner elvart;
    Scanner eredmeny; 
   
    Jatek menet = new Jatek();

    View()
    {
        kimenet.println("Filebol [f], vagy Konzolrol [k] szeretned beolvasni a parancsokat?");
        String valaszt = bemenet.nextLine();
        if(valaszt.charAt(0) == 'f')
        {
            fileValaszto();
        }
        else
        {
            kimenet.print("Kezdheti a parancsok megadasat:\n");
        }
        
    }

    void bemenetKezeles()
    {
        
        while (true) 
        {
            String beolvas;
            try {
                beolvas = bemenet.nextLine();
            } catch (Exception e) 
            {
                eredmenyHasonlitas();
                beolvas = "kilepes";
            }
            
            
            String tordel[] = beolvas.split(" ");
            boolean ertek = true;
            Set<Tekton> tektonok;
            ArrayList<Tekton> tektonlista;
            ArrayList<Fonal> fonallista;
            String s=null;
            switch (tordel[0]) {
                case "jatekIndit":
                    menet.alapJatekPalya();
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
                    kimenet.println(menet.passz());
                    
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
                    try{
                        s=menet.lepes(tordel[1], tordel[2], tordel[3]);
                        if(s=="Sikeres"){
                            kimenet.println(tordel[1]+"-hez tartozo " + tordel[2] + " bogar a " + tordel[3] + " tektonra lepett");
                        }
                        else{
                            kimenet.println(s);
                        }
                    } catch (IllegalArgumentException e) {
                        kimenet.println(e.getMessage());
                    }
                    break;
                case "eves":
                    try{
                        if(tordel[1].contains("bogarasz"))
                        {
                            s = menet.evesSporat(tordel[1], tordel[2]);
                        }
                        else
                        {
                            ertek = menet.evesBogarat(tordel[1], tordel[2]);
                        }
                        
                        if(s=="Sikeres"){
                            kimenet.println(tordel[1]+"-hez tartozo " + tordel[2] + " megevett egy sporat a tektonon");
                        }else{
                            kimenet.println(s);
                        } 
                    } catch (IllegalArgumentException e) {
                        kimenet.println(e.getMessage());
                    }
                    break;
                case "ragas":
                    try{
                        s = menet.ragas(tordel[1],tordel[2],tordel[3]);
                        if(s=="Sikeres"){
                            kimenet.println(tordel[1]+"-hez tartozo "+ tordel[2] +" elragta a "+tordel[3]+"-t");
                        } 
                        else{
                            kimenet.println(s);
                        }  
                    } catch (IllegalArgumentException e) {
                        kimenet.println(e.getMessage());
                    }
                    break;
            case "fonalLerak":
                try {
                    String kiirniValo = "Hibas bemenet";
                    if(tordel.length == 4){
                        kiirniValo = menet.fonalLerak(tordel[1], tordel[2], tordel[3]);
                    }
                    
                    kimenet.println(kiirniValo);
                } catch (IllegalArgumentException e) {
                    kimenet.println(e.getMessage());
                }
                
                break;
            case "sporaSzor":
                try {
                    String kiirniValo = "Hibas bemenet";
                    ertek = false;
                    if(tordel.length == 3){
                        ertek = menet.sporaSzor(tordel[1], tordel[2]);
                        if(ertek){
                            kiirniValo = "Siker";
                        }
                        else{
                            kiirniValo = "Sikertelen";
                        }
                    }
                    
                    kimenet.println(kiirniValo);

                } catch (IllegalArgumentException e) {
                    kimenet.println(e.getMessage());
                }
                break;

            case "gombaHovaRakhat":
                tektonok = menet.gombaHovaRakhat(tordel[1], tordel[2]);
                kimenet.println(tordel[1] +" gombasz, " + tordel[2]+" gombatestbol az alabbi tektonokra rakhat: ");
                for(Tekton a : tektonok)
                {
                    kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) );
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
                        kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) );
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
                        kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) );
                    }
                    break;
                case "fonallalOsszekotott":
                    tektonlista = menet.fonallalOsszekotott(tordel[1]);
                    kimenet.println(tordel[1] +" tektonrol, az alabbi tektonok erhetoek el fonalakon keresztul: ");
                    for(Tekton a : tektonlista)
                    {
                        kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a));
                    }
                    break;
                case "gombaszHovaSzorhat":
                    try{
                        String atadandoParameter = "Koron levo gombasz";
                        
                        if(tordel.length >= 3){
                            atadandoParameter = tordel[1];
                            tektonok = menet.gombaszHovaSzorhat(atadandoParameter, tordel[2]);
                            kimenet.println(atadandoParameter +" gombasz, " + tordel[2]+" gombatestbol az alabbi tektonokra szorhat: ");
                        }
                        else{
                            tektonok = menet.gombaszHovaSzorhat(atadandoParameter, tordel[1]);
                            kimenet.println(atadandoParameter +", " + tordel[1]+" gombatestbol az alabbi tektonokra szorhat: ");
                        }
                        
                        for(Tekton a : tektonok)
                        {
                            kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a));
                        }
    
                        }catch(IllegalArgumentException e){
                            kimenet.println(e.getMessage());
                        }
                        break;
                case "tektononFonal":
                    fonallista = menet.tektononFonal(tordel[1]);
                    kimenet.println(tordel[1] +" tektonon az alabbi fonalak vannak: ");
                    for(Fonal a : fonallista)
                    {
                        kimenet.println("fonal"+ menet.tektonFromString(tordel[1]).getOsszekoto().indexOf(a));
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
                    try{
                        if(tordel.length != 2){
                            System.out.println("Hibas bemenet");
                            break;
                        }
                        tektonlista = menet.tektonSzomszedNincsFonal(tordel[1]);
                        kimenet.println(tordel[1] + " tekton szomszedai amikre nem megy fonal: ");
                        for(Tekton a : tektonlista)
                        {
                            kimenet.println("tekton"+ menet.getJatekter().getPalya().indexOf(a) );
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
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
                case "teszt":
                    tesztfvek(tordel);
                    break;

                case "jelenlegiJatekos":
                    kimenet.println(menet.getJelenlegiJatekosNev());
                    break;

                case "kilepes":
                    return;

                default:
                    kimenet.println("\nnem ismert parancs\n");
            }
        }
    }

    void tesztfvek(String tordel[])
    {
        switch (tordel[1]) {
            case "BogarLep":
                menet = Tesztpalya.bogarLep();
                break;
            case "BogarEszik":
                menet = Tesztpalya.bogarEszik();
                break;
            case "BogarNemEszik":
                menet = Tesztpalya.bogarNemEszik();
                break;
            case "BogarRagas":
                menet = Tesztpalya.bogarRagas();
                break;
            case "BogarNemRagas":
                menet = Tesztpalya.bogarNemRagas();
                break;
            case "BogarEmeszt":
                menet = Tesztpalya.bogarEmeszt(tordel[2]);
                break;
            case "FonalLerakSima":
                menet = Tesztpalya.fonalLerakSima();
                break;
            case "EgyFonalasHonnan":
                menet = Tesztpalya.egyFonalasHonnan();
                break;
            case "EgyFonalasHova":
                menet = Tesztpalya.egyFonalasHova();
                break;
            case "SporaSzorSima":
                menet = Tesztpalya.sporaSzorSima();
                break;
            case "SporaSzorFejlett":
                menet = Tesztpalya.sporaSzorFejlett();
                break;
            case "GombatestEpitFonallal":
                menet = Tesztpalya.gombatestEpitFonallal();
                break;
            case "GombatestEpitSporaval":
                menet = Tesztpalya.gombatestEpitSporaval();
                break;
            case "GombatestMeghal":
                menet = Tesztpalya.gombatestMeghal();
                break;
            case "FonalElhalGombatestMiatt":
                menet = Tesztpalya.fonalElhalGombatestMiatt();
                break;
            case "FonalEvesBogar":
                menet = Tesztpalya.fonalEvesBogar();
                break;
            case "FonalElhalSzetesesMiatt":
                menet = Tesztpalya.fonalElhalSzetesesMiatt();
                break;
            case "GombatestEpitTestetlenre":
                menet = Tesztpalya.gombatestEpitTestetlenre();
                break;
            case "FonalElhalBogarMiatt":
                menet = Tesztpalya.fonalElhalBogarMiatt();
                break;
            case "EletbenTart":
                menet = Tesztpalya.eletbenTart();
                break;
            case "TektonSzetesesFonalNelkul":
                menet = Tesztpalya.tektonSzetesesFonalNelkul();
                break;
            case "FonalFelsziv":
                menet = Tesztpalya.fonalFelsziv();
                break;
            default:
               kimenet.print("nem ismert teszt");
        }
    }

    void fileValaszto()
    {
        kimenet.print("melyik file-t alkalmaznad?\n");
        File tesztekFolder = new File("tesztek");
        File[] files = tesztekFolder.listFiles();

        if (files != null && files.length > 0) 
        {
            for (File file : files) 
            {
                if (file.isDirectory()) 
                {
                    kimenet.print(file.getName() + "\n");
                }
            }

            String valaszt = bemenet.nextLine();
            for (File file : files) 
            {
                if (file.isDirectory() && valaszt.equals(file.getName())) 
                {
                    Scanner s;
                    try {
                        File[] tartalom = file.listFiles();
                        for(File belso : tartalom)
                        {
                            if(belso.getName().equals("bemenet.txt"))
                            {
                                s = new Scanner(belso);
                                InputHandler.setScanner(s);
                                bemenet = InputHandler.getScanner();
                                InputHandler.setForras(belso);
                            }
                            else if(belso.getName().equals("kimenet.txt"))
                            {
                                OutputHandler.setKimenet(new PrintStream(belso));
                                kimenet = OutputHandler.getKimenet();
                                OutputHandler.setForras(belso);
                                eredmeny = new Scanner(belso);
                            }
                            else if(belso.getName().equals("elvart.txt"))
                            {
                               elvart = new Scanner(belso);
                            }
                        }
                       
                        
                    } catch (FileNotFoundException e) {
                       
                    }
                    break;
                }
            }
        }
        else
        {
            kimenet.print("Nincsenek megfelelo fileok.\nHelyes file struktura:\nTesztek.dir\n\ttesztnev.dir\n\t\tbemenet.txt\n\t\tkimenet.txt\n\t\telvart.txt");
        }
    }

    void eredmenyHasonlitas()
    {
        
        kimenet = OutputHandler.getKonzol();
        List<String> elvartList = new ArrayList<>();
        List<String> eredmenyList = new ArrayList<>();

        while (elvart.hasNextLine()) {
            elvartList.add(elvart.nextLine());
        }

        while (eredmeny.hasNextLine()) {
            eredmenyList.add(eredmeny.nextLine());
        }
        String elvartSor = String.format("%-20s", "Elvart");
        String eredmenySor = String.format("%20s", "Eredmeny");
        kimenet.print(elvartSor + "||" + eredmenySor + "\n");
        boolean egyeznek = true;
        if(elvartList.size() != eredmenyList.size())
        {
            kimenet.print("a ket file merete nem egyezik meg.");
            return;
        }
        for(int i = 0; i < elvartList.size(); i++)
        {
            elvartSor = String.format("%-20s", elvartList.get(i));
            eredmenySor = String.format("%20s", eredmenyList.get(i));
            kimenet.print(elvartSor + "||" + eredmenySor);
            if(!elvartList.get(i).equals(eredmenyList.get(i)))
            {
                egyeznek = false;
                kimenet.print(" *HIBAS*");
            }
            kimenet.print("\n");
        }

        if(egyeznek)
        {
            kimenet.print("\naz eredmeny megegyezik az elvarttal.\n");
        }
        else
        {
            kimenet.print("\naz eredmeny nem egyezik meg az elvarttal\n");
        }
        return;
    }

}