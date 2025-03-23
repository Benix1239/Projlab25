import java.util.ArrayList;
import java.util.Scanner;

public class Bogarasz extends Jatekos
{
    Bogar bogar;

    Bogarasz() {}

    void round()
    {
        szkeleton.logMethodEntry(this, "round");
        while(bogar.getMozgaspont()!=0||bogar.getactionEves()==true|| bogar.getactionRagas()==true){
            Scanner bemenet = new Scanner(System.in);
            ArrayList<Fonal> fonalak= null;
            Tekton helyzet= null;
            System.out.println("Mit szeretnél csinálni");
            System.out.println("1. Mozgás");
            System.out.println("2. Evés");
            System.out.println("3. Rágni");
            
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
                default:
                    System.out.println("Érvénytelen menüpontot választottál");
                    break;
            }
        }

        bogar.beallit();

        if(bogar.getSpora()!=null){
            pontok += bogar.getSpora().getPluszpont();
        }

        bogar.sporaMegemesztes();
        szkeleton.logMethodExit(this, "");
    }

    void lep(){
        szkeleton.logMethodEntry(this, "lep");
        if(bogar.getMozgaspont()!=0){
            Scanner bemenet = new Scanner(System.in);
            Tekton helyzet= bogar.getHelyzet();
            ArrayList<Fonal> fonalak = helyzet.fonalKeres();
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
        }
        else{
            System.out.println("Már nem tudsz mozogni a körben");
        }
        szkeleton.logMethodExit(this, "");
    }

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

    void ragas(){
        szkeleton.logMethodEntry(this, "ragas");
        if(bogar.getactionRagas()==true){
            Scanner bemenet = new Scanner(System.in);
            Tekton helyzet= bogar.getHelyzet();
            ArrayList<Fonal> fonalak = helyzet.fonalKeres();
            System.out.println("Melyik fonalat szeretnéd elrágni?");
            for (int i=0;i<fonalak.size();i++) {
                System.out.println(i+". fonal amely a Tekton"+ fonalak.get(i).getHova().getId()+"ra ér");
            }
            int ragasValasztas = bemenet.nextInt();
            bogar.ragas(fonalak.get(ragasValasztas-1));
        }
        else{
            System.out.println("Már rágtál a körben");
        }
        szkeleton.logMethodExit(this, "");
    }

    void bogarHozzaad(Bogar b,Tekton t)
    {
        szkeleton.logMethodEntry(this, "bogarHozzaad");
        bogar=b;
        bogar.setHelyzet(t);
        szkeleton.logMethodExit(this, "");
    }
    
}
