import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A Bogarasz osztály egy játékost reprezentál, aki több bogarat irányít a játék során.
 * A játékos különböző akciókat hajthat végre, például mozgást, evést és fonalak rágását.
 */
public class Bogarasz extends Jatekos
{
    private ArrayList<Bogar> bogarak;
    private ArrayList<String> azonosito;
    private int Id;

    public void setId(int i){
        this.Id=i;
    }

    public int getId(){
        return Id;
    }

    public Bogarasz(){
        this.bogarak = new ArrayList<Bogar>();
    }

    /**
     * A játékos köre, amely során döntéseket hozhat a bogár mozgásáról, evéséről és rágásáról.
     * A kör addig tart, amíg a bogárnak van mozgáspontja, vagy képes enni és rágni.
     */
    /*public void round()
    {

        ArrayList<Bogar> tempbogarak = new ArrayList<>();
        for(Bogar bogar : tempbogarak){
            tempbogarak.add(bogar);
        }
        
        boolean vege=false;
        for(Bogar bogar: tempbogarak){
            while((bogar.getMozgaspont()!=0||bogar.getactionEves()==true||bogar.getactionRagas()==true)&&(!vege)){
            
                ArrayList<Fonal> fonalak= null;
                Tekton helyzet= null;
                System.out.println("Mit szeretnél csinálni");
                System.out.println("1. Mozgás");
                System.out.println("2. Evés");
                System.out.println("3. Rágni");
                System.out.println("4. Vége a körömnek");
                
                int actionValasztas =InputHandler.getScanner().nextInt();
                
                switch (actionValasztas) {
                    case 1:
                        lep(bogar);
                        break;
                    case 2:
                        eves(bogar);
                        break;
                    case 3:
                        ragas(bogar);
                        break;
                    case 4:
                        vege=true;
                        break;
                    default:
                        System.out.println("Érvénytelen menüpontot választottál");
                        break;
                }
            }
    
            szkeleton.logMethodEntry(bogar, "beallit");
            bogar.beallit();
            szkeleton.logMethodExit(bogar, "");
    
            if(bogar.getSpora()!=null){
                pontok += bogar.getSpora().getPluszpont();
            }
            szkeleton.logMethodEntry(bogar, "sporaMegemesztes");
            bogar.sporaMegemesztes();
            szkeleton.logMethodExit(bogar, "");
        }
        //szkeleton.logMethodExit(this, "");
    }
 */
    
    /**
     * Megpróbálja a megadott bogarat átléptetni egy másik Tektonra, ha van még mozgáspontja
     * és a cél Tekton elérhető a jelenlegi helyzetéből kiindulva.
     *
     * @param bogar A bogár, amelyet mozgatni szeretnénk.
     * @param tekton A cél Tekton, ahova a bogarat mozgatni szeretnénk.
     * @return true, ha a lépés sikeres volt, különben false.
     */
    public boolean lep(Bogar bogar, Tekton tekton){
        //szkeleton.logMethodEntry(this, "lep");
        if(bogar.getMozgaspont()!=0){
            //szkeleton.logMethodEntry(bogar, "getHelyzet");
            Tekton helyzet= bogar.getHelyzet();
            //szkeleton.logMethodExit(bogar, "Helyzet");
            //szkeleton.logMethodEntry(helyzet, "getOsszekoto");
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            //szkeleton.logMethodExit(helyzet, "Osszekoto[]");
            ArrayList<Tekton> lehetsegesLepes=new ArrayList<>();
            
            
            for (Fonal fonal : fonalak) {
                //szkeleton.logMethodEntry(fonal, "getHova");
                Tekton hova = fonal.getHova();
                //szkeleton.logMethodExit(fonal, "Lehetseges helyek");
                lehetsegesLepes.add(hova);
            }
            

            
            /*System.out.println("Melyik tektonra szeretnél lépni?");
            
            for (int i=0;i<lehetsegesLepes.size();i++) {
                System.out.println((i+1)+". Tekton"+lehetsegesLepes.get(i).getId());
    
            }
            int lepesValasztas = InputHandler.getScanner().nextInt();
            Tekton hova= lehetsegesLepes.get(lepesValasztas-1);*/
            
            //szkeleton.logMethodEntry(bogar, "mozgas");
            int mozgas=bogar.getMozgaspont();
            if(lehetsegesLepes.contains(tekton)){
                bogar.mozgas(tekton);
            }
            if(bogar.getMozgaspont()==(mozgas-1) && bogar.getHelyzet()==tekton){
                return true;
            }
            else{
                return false;
            }
            //szkeleton.logMethodExit(bogar, "");
        }

        else{
            return false;
            //System.out.println("Már nem tudsz mozogni a körben");
        }
        //szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár megeszi a jelenlegi Tektonon található legutoljára lerakott spórát, ha van,
     * és ha a körben még nem evett.
     *
     * @param bogar A bogár, amelyik enni próbál.
     * @return true, ha a bogár sikeresen evett egy spórát, különben false.
     */
    public boolean eves(Bogar bogar){
        //szkeleton.logMethodEntry(this, "eves");
        if(bogar.getactionEves()==true){
            if (bogar.getHelyzet().sporak!=null) {
                //szkeleton.logMethodEntry(bogar, "eves");
                bogar.eves();
                if(bogar.getactionEves()==false && bogar.getSpora()!=null){
                    return true;
                }
                else{
                    return false;
                }
                //szkeleton.logMethodExit(bogar, "");
            }
            else{
                return false;
                //System.out.println("Nincs a tektonon spóra");
            }
        }
        else{
            return false;
            //System.out.println("Már ettél a körben");
        }
        //szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár megpróbál elrágni egy fonalat, ami a jelenlegi Tektonról indul, 
     * ha ebben a körben még nem rágott, és a megadott fonal ténylegesen kapcsolódik a jelenlegi helyzetéhez.
     *
     * @param bogar A bogár, amely rágni próbál.
     * @param fonal Az a fonal, amelyet el szeretne rágni.
     * @return true, ha a fonalat sikeresen elrágta, különben false.
     */
    public boolean ragas(Bogar bogar,Fonal fonal){
        //szkeleton.logMethodEntry(this, "ragas");
        if(bogar.getactionRagas()==true){
            Tekton helyzet= bogar.getHelyzet();
            //szkeleton.logMethodEntry(helyzet, "getOsszekoto");
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            //szkeleton.logMethodExit(helyzet, "Osszekoto[]");
            /*System.out.println("Melyik fonalat szeretnéd elrágni?");
            for (int i=0;i<fonalak.size();i++) {
                System.out.println((i+1)+". fonal amely a Tekton"+ fonalak.get(i).getHova().getId()+"ra ér");
            }
            int ragasValasztas = InputHandler.getScanner().nextInt();
            //szkeleton.logMethodEntry(bogar, "ragas");
            bogar.ragas(fonalak.get(ragasValasztas-1));*/
            if(fonalak.contains(fonal)){
                bogar.ragas(fonal);
            }
            
            if (bogar.getactionRagas()==false && (!bogar.getHelyzet().getOsszekoto().contains(fonal))) {
                return true;
            }
            else{
                return false;
            }
            //szkeleton.logMethodExit(bogar, "");
        }
        else{
            return false;
            //System.out.println("Már rágtál a körben");
        }
        //szkeleton.logMethodExit(this, "");
    }

    /**
     * Hozzáad egy bogarat a játékos bogaraihoz, és beállítja annak kezdeti helyzetét a megadott Tektonra.
     *
     * @param b A hozzáadandó bogár.
     * @param t A kezdeti Tekton, ahol a bogár elhelyezkedik.
     * @return true, ha a bogár sikeresen hozzá lett adva és a helyzete is be lett állítva, különben false.
     */
    public boolean bogarHozzaad(Bogar b,Tekton t)
    {
        ////szkeleton.logMethodEntry(this, "bogarHozzaad");
        bogarak.add(b);
        bogarak.get(bogarak.size()-1).setHelyzet(t);
        b.beallit();
        b.setId(bogarak.size());
        azonosito.add("Bogar"+b.getId());
        if(bogarak.contains(b) && b.getHelyzet()==t){
            return true;
        }
        else{
            return false;
        }
        //szkeleton.logMethodExit(this, "");
    }
    
    /**
     * Eltávolít egy bogarat a játékos bogarai közül.
     *
     * @param b A bogár, amelyet el szeretnénk távolítani.
     * @return true, ha a bogár sikeresen eltávolításra került, különben false.
     */
    public boolean bogarRemove(Bogar b)
    {
        ////szkeleton.logMethodEntry(this, "bogarHozzaad");
        if(b!=null){
            bogarak.remove(b);
        }
        if(bogarak.contains(b)){
            return false;
        }
        else{
            return true;
        }
        //szkeleton.logMethodExit(this, "");
    }
}
