
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Gombasz extends Jatekos
{
    private ArrayList<Gombatest> testek;
    private ArrayList<Tekton> palya;

    //valtozas: megkapja a palyat is konstruktorban es nincs Tekton kezdo parametere
    public Gombasz(ArrayList<Tekton> palya){
        testek = new ArrayList<>();
        this.palya = palya;
    }

    public void gombatestHozzaad(Gombatest g){
        szkeleton.logMethodEntry(this, "gombatestHozzaad");
        testek.add(g);
        szkeleton.logMethodExit(this, "");
    }

    public void Round(){
     
    }

    //osszegyujti, hogy a hozza tartozo testekbol melyik tektonok erhetoek el es ha talal olyat, ami nem erheto el,
    //de hozza tartozo fonal van rajta, akkor azt a fonalat torli a tektonrol
    public void elszakadasDfsKezeles(){
        szkeleton.logMethodEntry(this, "elszakadasDfsKezeles");
        HashSet<Tekton> elerhetok = new HashSet<>();
        for(Gombatest test : testek){
            Set<Tekton> elerhetoTektonok = test.dfs();
            for(Tekton t : elerhetoTektonok){
                elerhetok.add(t);
            }
        }

        for(Tekton t : palya){
            if(!elerhetok.contains(t)){
               ArrayList<Fonal> fonalak = t.getKoto();
               for(Fonal f : fonalak){
                    if(f.getTartozik() == this){
                        t.fonalElszakad(f);
                    }
               }
            }
        }
        szkeleton.logMethodExit(this, "");
    }

    public void removeGombatest(Gombatest g){
        szkeleton.logMethodEntry(this, "removeGombatest");
        testek.remove(g);
        szkeleton.logMethodExit(this, "");
    }

    public void fonalLerak(){
        szkeleton.logMethodEntry(this, "fonalLerak");
        for(Gombatest gombatest : testek){
            Set<Tekton> honnanLehetosegek = new HashSet<>();
            Set<Tekton> ujMegtalaltak = gombatest.dfs();
            for(Tekton t : ujMegtalaltak){
                honnanLehetosegek.add(t);
            }

            System.out.println("Honnan szeretnel fonalat lerakni? Lehetosegek: ");
            Set<Integer> ervenyesErtekek = new HashSet<>();
            for(Tekton t : honnanLehetosegek){
                System.out.println(t.getID());
                ervenyesErtekek.add(t.getID());
            }
            Scanner scanner = new Scanner(System.in);
            int szam;
            while (true) {
                if(scanner.hasNextInt()){
                    szam = scanner.nextInt();
                    if (ervenyesErtekek.contains(szam)) {
                        break;
                    } else {
                        System.out.println("Hibas bemenet!");
                    }
                }else {
                    System.out.println("Hibas bemenet!");
                    scanner.next();
                }
            }
            scanner.close();

            Tekton honnan = null;
            for(Tekton t : honnanLehetosegek){
                if(t.getID() == szam){
                    honnan = t;
                }
            }

            ArrayList<Tekton> hovaLehetosegek = honnan.getSzomszed();
            ArrayList<Fonal> honnanFonaljai = honnan.getKoto();
            for(Tekton t : hovaLehetosegek){
                for(Fonal f : honnanFonaljai){
                    if(f.getTartozik() == this && f.getHova() == t){
                        hovaLehetosegek.remove(t);
                    }
                }
            }

            System.out.println("Hova szeretnel fonalat lerakni? Lehetosegek: ");
            ervenyesErtekek = new HashSet<>();
            for(Tekton t : hovaLehetosegek){
                System.out.println(t.getID());
                ervenyesErtekek.add(t.getID());
            }
            while (true) {
                if(scanner.hasNextInt()){
                    szam = scanner.nextInt();
                    if (ervenyesErtekek.contains(szam)) {
                        break;
                    } else {
                        System.out.println("Hibas bemenet!");
                    }
                }else {
                    System.out.println("Hibas bemenet!");
                    scanner.next();
                }
            }
            scanner.close();

            Tekton hova = null;
            for(Tekton t : hovaLehetosegek){
                if(t.getID() == szam){
                    hova = t;
                }
            }

            gombatest.elhelyez(honnan, hova);
        }
        szkeleton.logMethodExit(this, "");
    }

    public void sporaSzor(){
        szkeleton.logMethodEntry(this, "sporaSzor");
        for(Gombatest gombatest : testek){
            ArrayList<Tekton> szomszedokList = gombatest.szomszedKeres();
            HashSet<Tekton> szomszedok = new HashSet<>(szomszedokList);
            if(gombatest.getMaradt() < 3){
                for(Tekton t : szomszedokList){
                    szomszedok.addAll(t.getSzomszed());
                }
            }

            System.out.println("Hova szeretnel sporat szorni? Lehetosegek: ");
            Set<Integer> ervenyesErtekek = new HashSet<>();
            for(Tekton t : szomszedok){
                System.out.println(t.getID());
                ervenyesErtekek.add(t.getID());
            }

            Scanner scanner = new Scanner(System.in);
            int szam;
            while (true) {
                if(scanner.hasNextInt()){
                    szam = scanner.nextInt();
                    if (ervenyesErtekek.contains(szam)) {
                        break;
                    } else {
                        System.out.println("Hibas bemenet!");
                    }
                }else {
                    System.out.println("Hibas bemenet!");
                    scanner.next();
                }
            }
            scanner.close();

            Tekton hova = null;
            for(Tekton t : szomszedok){
                if(t.getID() == szam){
                    hova = t;
                }
            }

            gombatest.elszor(hova);
            szkeleton.logMethodExit(this, "");
        }
    }
    
}