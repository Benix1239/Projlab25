import java.util.ArrayList;
import java.util.HashSet;
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
        testek.add(g);
    }

    public void Round(){
     
    }

    //osszegyujti, hogy a hozza tartozo testekbol melyik tektonok erhetoek el es ha talal olyat, ami nem erheto el,
    //de hozza tartozo fonal van rajta, akkor azt a fonalat torli a tektonrol
    public void elszakadasDfsKezeles(){
        HashSet<Tekton> elerhetok = new HashSet<>();
        for(Gombatest test : testek){
            Set<Tekton> elerhetoTektonok = test.dfs();
            for(Tekton t : elerhetoTektonok){
                elerhetok.add(t);
            }
        }

        for(Tekton t : palya){
            if(!elerhetok.contains(t)){
               ArrayList<Fonal> fonalak = new ArrayList<>();
               for(Fonal f : t.getOsszekoto())
               {
                    fonalak.add(f);
               }
               for(Fonal f : fonalak){
                    if(f.getTartozik() == this){
                        t.getOsszekoto().remove(f);
                    }
               }
            }
        }
    }

    public void removeGombatest(Gombatest g){
        testek.remove(g);
    }

    public void fonalLerak(){
        ArrayList<Gombatest> temptestek = new ArrayList<>();
        for(Gombatest gombatest : testek){
            temptestek.add(gombatest);
        }

        for(Gombatest gombatest : temptestek){
            Set<Tekton> honnanLehetosegek = new HashSet<>();

            Set<Tekton> ujMegtalaltak = gombatest.dfs();

            for(Tekton t : ujMegtalaltak){
                honnanLehetosegek.add(t);
            }

            System.out.println("Honnan szeretnel fonalat lerakni? Lehetosegek: ");
            Set<Integer> ervenyesErtekek = new HashSet<>();
            for(Tekton t : honnanLehetosegek){
                System.out.println(t.getId());
                ervenyesErtekek.add(t.getId());
            }
            int szam;
            while (true) {
                if(InputHandler.getScanner().hasNextInt()){
                    szam = InputHandler.getScanner().nextInt();
                    if (ervenyesErtekek.contains(szam)) {
                        break;
                    } else {
                        System.out.println("Hibas bemenet!");
                    }
                }else {
                    System.out.println("Hibas bemenet!");
                    InputHandler.getScanner().next();
                }
            }

            Tekton honnan = null;
            for(Tekton t : honnanLehetosegek){
                if(t.getId() == szam){
                    honnan = t;
                }
            }

            ArrayList<Tekton> hovaLehetosegek = new ArrayList<>();
            for(Tekton f : honnan.getSzomszed())
            {

                hovaLehetosegek.add(f);
            }

            ArrayList<Fonal> honnanFonaljai = new ArrayList<>();
            for(Fonal f : honnan.getOsszekoto())
            {
                honnanFonaljai.add(f);
            }

            ArrayList<Tekton> torlendo = new ArrayList<>();
            for(Tekton t : hovaLehetosegek){
                for(Fonal f : honnanFonaljai){
                    if(f.getTartozik() == this && f.getHova() == t){
                        torlendo.add(t);
                    }
                }
            }

            hovaLehetosegek.removeAll(torlendo);

            System.out.println("Hova szeretnel fonalat lerakni? Lehetosegek: ");
            ervenyesErtekek = new HashSet<>();
            for(Tekton t : hovaLehetosegek){
                System.out.println(t.getId());
                ervenyesErtekek.add(t.getId());
            }
            while (true) {
                if(InputHandler.getScanner().hasNextInt()){
                    szam = InputHandler.getScanner().nextInt();
                    if (ervenyesErtekek.contains(szam)) {
                        break;
                    } else {
                        System.out.println("Hibas bemenet!");
                    }
                }else {
                    System.out.println("Hibas bemenet!");
                    InputHandler.getScanner().next();
                }
            }

            Tekton hova = null;
            for(Tekton t : hovaLehetosegek){
                if(t.getId() == szam){
                    hova = t;
                }
            }

            boolean vissza = gombatest.elhelyez(honnan, hova);

        }
    }

    public void sporaSzor(){
        ArrayList<Gombatest> temptestek = new ArrayList<>();
        for(Gombatest gombatest : testek){
            temptestek.add(gombatest);
        }

        for(Gombatest gombatest : temptestek){

            ArrayList<Tekton> szomszedokList = gombatest.szomszedKeres();

            HashSet<Tekton> szomszedok = new HashSet<>(szomszedokList);
            if(gombatest.getMaradt() < 3){
                for(Tekton t : szomszedokList){

                    ArrayList<Tekton> vmi = t.getSzomszed();

                    szomszedok.addAll(vmi);
                }
            }

            System.out.println("Hova szeretnel sporat szorni? Lehetosegek: ");
            Set<Integer> ervenyesErtekek = new HashSet<>();
            for(Tekton t : szomszedok){
                int id = t.getId();
                System.out.println(id);
                ervenyesErtekek.add(id);
            }

            int szam = 1;
            while (true) {
                if(InputHandler.getScanner().hasNextInt()){
                    szam = InputHandler.getScanner().nextInt();
                    if (ervenyesErtekek.contains(szam)) {
                        break;
                    } else {
                        System.out.println("Hibas bemenet!");
                    }
                }else {
                    System.out.println("Hibas bemenet!");
                    InputHandler.getScanner().next();
                }
            }

            Tekton hova = null;
            for(Tekton t : szomszedok){
                if(t.getId() == szam){
                    hova = t;
                }
            }

            gombatest.elszor(hova);
        }
    }
    
}