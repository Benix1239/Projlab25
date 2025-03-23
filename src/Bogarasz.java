import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Bogarasz osztály egy játékost reprezentál, aki egy bogarat irányít a játék során.
 * A játékos különböző akciókat hajthat végre, például mozgást, evést és fonalak rágását.
 */
public class Bogarasz extends Jatekos
{
    Bogar bogar;

    Bogarasz() {}

    /**
     * A játékos köre, amely során döntéseket hozhat a bogár mozgásáról, evéséről és rágásáról.
     * A kör addig tart, amíg a bogárnak van mozgáspontja, vagy képes enni és rágni.
     */
    void round()
    {
        szkeleton.logMethodEntry(this, "round");
        boolean vege=false;
        while((bogar.getMozgaspont()!=0||bogar.getactionEves()==true|| bogar.getactionRagas()==true)&&(!vege)){
            Scanner bemenet = new Scanner(System.in);
            ArrayList<Fonal> fonalak= null;
            Tekton helyzet= null;
            System.out.println("Mit szeretnél csinálni");
            System.out.println("1. Mozgás");
            System.out.println("2. Evés");
            System.out.println("3. Rágni");
            System.out.println("4. Vége a körömnek");
            
            int actionValasztas =bemenet.nextInt();
            
            switch (actionValasztas) {
                case 1:
                    lep();
                    break;
                case 2:
                    eves();
                    break;
                case 3:
                    ragas();
                    break;
                case 4:
                    vege=true;
                    break;
                default:
                    System.out.println("Érvénytelen menüpontot választottál");
                    break;
            }
            bemenet.close();
        }

        bogar.beallit();

        if(bogar.getSpora()!=null){
            pontok += bogar.getSpora().getPluszpont();
        }

        bogar.sporaMegemesztes();
        szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogarat egy másik Tektonra mozgatja, ha van elérhető mozgáspontja.
     */
    void lep(){
        szkeleton.logMethodEntry(this, "lep");
        if(bogar.getMozgaspont()!=0){
            Scanner bemenet = new Scanner(System.in);
            Tekton helyzet= bogar.getHelyzet();
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            ArrayList<Tekton> lehetsegesLepes=null;
            for (Fonal fonal : fonalak) {
                lehetsegesLepes.add(fonal.getHova());
            }

            
            System.out.println("Melyik tektonra szeretnél lépni?");
            
            for (int i=0;i<lehetsegesLepes.size();i++) {
                System.out.println(i+". Tekton"+lehetsegesLepes.get(i).getId());
    
            }
            int lepesValasztas = bemenet.nextInt();
            Tekton hova= lehetsegesLepes.get(lepesValasztas-1);
            bogar.mozgas(hova);
            bemenet.close();
        }
        else{
            System.out.println("Már nem tudsz mozogni a körben");
        }
        szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár megeszi a jelenlegi Tektonon található legkésőbb lerakot sporát ha van.
     */
    void eves(){
        szkeleton.logMethodEntry(this, "eves");
        if(bogar.getactionEves()==true){
            if (bogar.getHelyzet().sporak!=null) {
                bogar.eves();
            }
            else{
                System.out.println("Nincs a tektonon spóra");
            }
        }
        else{
            System.out.println("Már ettél a körben");
        }
        szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár elrág egy fonalat ami a jelenlegi tektonról elérhető és a játékos kiválasztott.
     */
    void ragas(){
        szkeleton.logMethodEntry(this, "ragas");
        if(bogar.getactionRagas()==true){
            Scanner bemenet = new Scanner(System.in);
            Tekton helyzet= bogar.getHelyzet();
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            System.out.println("Melyik fonalat szeretnéd elrágni?");
            for (int i=0;i<fonalak.size();i++) {
                System.out.println(i+". fonal amely a Tekton"+ fonalak.get(i).getHova().getId()+"ra ér");
            }
            int ragasValasztas = bemenet.nextInt();
            bogar.ragas(fonalak.get(ragasValasztas-1));
            bemenet.close();
        }
        else{
            System.out.println("Már rágtál a körben");
        }
        szkeleton.logMethodExit(this, "");
    }

     /**
     * Hozzáad egy bogarat a játékoshoz és beállítja annak kezdeti helyzetét.
     * 
     * @param b A hozzáadandó bogár.
     * @param t A kezdeti Tekton, ahol a bogár elhelyezkedik.
     */
    void bogarHozzaad(Bogar b,Tekton t)
    {
        szkeleton.logMethodEntry(this, "bogarHozzaad");
        bogar=b;
        bogar.setHelyzet(t);
        szkeleton.logMethodExit(this, "");
        szkeleton.logMethodEntry(this, "bogarHozzaad");
        bogar=b;
        bogar.setHelyzet(t);
        szkeleton.logMethodExit(this, "");
    }
    
}
