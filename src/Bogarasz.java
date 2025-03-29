import java.util.ArrayList;

/**
 * A Bogarasz osztály egy játékost reprezentál, aki egy bogarat irányít a játék során.
 * A játékos különböző akciókat hajthat végre, például mozgást, evést és fonalak rágását.
 */
public class Bogarasz extends Jatekos
{
    private Bogar bogar;

    public Bogarasz(){}

    /**
     * A játékos köre, amely során döntéseket hozhat a bogár mozgásáról, evéséről és rágásáról.
     * A kör addig tart, amíg a bogárnak van mozgáspontja, vagy képes enni és rágni.
     */
    public void round()
    {
        //szkeleton.logMethodEntry(this, "round");
        boolean vege=false;
        while((bogar.getMozgaspont()!=0||bogar.getactionEves()==true|| bogar.getactionRagas()==true)&&(!vege)){
            
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
        //szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogarat egy másik Tektonra mozgatja, ha van elérhető mozgáspontja.
     */
    public void lep(){
        //szkeleton.logMethodEntry(this, "lep");
        if(bogar.getMozgaspont()!=0){
            szkeleton.logMethodEntry(bogar, "getHelyzet");
            Tekton helyzet= bogar.getHelyzet();
            szkeleton.logMethodExit(bogar, "Helyzet");
            szkeleton.logMethodEntry(helyzet, "getOsszekoto");
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            szkeleton.logMethodExit(helyzet, "Osszekoto[]");
            ArrayList<Tekton> lehetsegesLepes=new ArrayList<>();
            
            
            for (Fonal fonal : fonalak) {
                szkeleton.logMethodEntry(fonal, "getHova");
                Tekton hova = fonal.getHova();
                szkeleton.logMethodExit(fonal, "Lehetseges helyek");
                lehetsegesLepes.add(hova);
            }
            

            
            System.out.println("Melyik tektonra szeretnél lépni?");
            
            for (int i=0;i<lehetsegesLepes.size();i++) {
                System.out.println((i+1)+". Tekton"+lehetsegesLepes.get(i).getId());
    
            }
            int lepesValasztas = InputHandler.getScanner().nextInt();
            Tekton hova= lehetsegesLepes.get(lepesValasztas-1);
            
            szkeleton.logMethodEntry(bogar, "mozgas");
            bogar.mozgas(hova);
            szkeleton.logMethodExit(bogar, "");
        }

        else{
            System.out.println("Már nem tudsz mozogni a körben");
        }
        //szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár megeszi a jelenlegi Tektonon található legkésőbb lerakot sporát ha van.
     */
    public void eves(){
        //szkeleton.logMethodEntry(this, "eves");
        if(bogar.getactionEves()==true){
            if (bogar.getHelyzet().sporak!=null) {
                szkeleton.logMethodEntry(bogar, "eves");
                bogar.eves();
                szkeleton.logMethodExit(bogar, "");
            }
            else{
                System.out.println("Nincs a tektonon spóra");
            }
        }
        else{
            System.out.println("Már ettél a körben");
        }
        //szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár elrág egy fonalat ami a jelenlegi tektonról elérhető és a játékos kiválasztott.
     */
    public void ragas(){
        //szkeleton.logMethodEntry(this, "ragas");
        if(bogar.getactionRagas()==true){
            Tekton helyzet= bogar.getHelyzet();
            szkeleton.logMethodEntry(helyzet, "getOsszekoto");
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            szkeleton.logMethodExit(helyzet, "Osszekoto[]");
            System.out.println("Melyik fonalat szeretnéd elrágni?");
            for (int i=0;i<fonalak.size();i++) {
                System.out.println((i+1)+". fonal amely a Tekton"+ fonalak.get(i).getHova().getId()+"ra ér");
            }
            int ragasValasztas = InputHandler.getScanner().nextInt();
            szkeleton.logMethodEntry(bogar, "ragas");
            bogar.ragas(fonalak.get(ragasValasztas-1));
            szkeleton.logMethodExit(bogar, "");
        }
        else{
            System.out.println("Már rágtál a körben");
        }
        //szkeleton.logMethodExit(this, "");
    }

     /**
     * Hozzáad egy bogarat a játékoshoz és beállítja annak kezdeti helyzetét.
     * 
     * @param b A hozzáadandó bogár.
     * @param t A kezdeti Tekton, ahol a bogár elhelyezkedik.
     */
    public void bogarHozzaad(Bogar b,Tekton t)
    {
        ////szkeleton.logMethodEntry(this, "bogarHozzaad");
        bogar=b;
        bogar.setHelyzet(t);
        //szkeleton.logMethodExit(this, "");
    }
    
}
