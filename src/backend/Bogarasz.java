package backend;

import java.util.ArrayList;

/**
 * A Bogarasz osztály egy játékost reprezentál, aki több bogarat irányít a játék során.
 * A játékos különböző akciókat hajthat végre, például mozgást, evést és fonalak rágását.
 */
public class Bogarasz extends Jatekos
{
    private ArrayList<Bogar> bogarak;

    /**
     * Konstruktor, létrehozza a bogarak-at.
     */
    public Bogarasz(){
        this.bogarak = new ArrayList<Bogar>();
    }

    
    public void korElejeInicializalas(){
        /*for(Bogar bogar : bogarak){
            bogar.beallit();
        }*/
    }

    /**
     * Vége a bogarak körének, emésztenek.
     * @return ha tényleg vége a körnek, true
     */
    private boolean mindenBogarVege(){
        for(Bogar bogar : bogarak){
            if(!bogar.getKorVege()){
                return false;
            }
        }
        for(Bogar bogar : bogarak){
            bogar.korVegeEmeszt();
        }
        return true;
    }

    /**
     * Megadja, mit lehet még csinálni a bogarakkal
     */
    public String mitLehetCsinalni() {
        String returnValue = "";
        for (int i = 0; i < bogarak.size(); i++) {
            Bogar bogar = bogarak.get(i);
            returnValue += "Bogar" + i + ":\nMegmaradt lepesek szama: " + bogar.getMozgaspont() + "\n";
            
            if (bogar.getactionEves()) {
                returnValue += "Tud meg enni\n";
            } else {
                returnValue += "Nem tud meg enni\n";
            }
    
            if (bogar.getactionRagas()) {
                returnValue += "Tud meg ragni";
            } else {
                returnValue += "Nem tud meg ragni";
            }
    
            if (i < bogarak.size() - 1) {
                returnValue += "\n"; // Csak akkor adunk új sort, ha van még bogár
            }
        }
        return returnValue;
    }
    

    /**
     * Megpróbálja a megadott bogarat átléptetni egy másik Tektonra, ha van még mozgáspontja
     * és a cél Tekton elérhető a jelenlegi helyzetéből kiindulva.
     *
     * @param bogar A bogár, amelyet mozgatni szeretnénk.
     * @param tekton A cél Tekton, ahova a bogarat mozgatni szeretnénk.
     * @return true, ha a lépés sikeres volt, különben false.
     */
    public String lep(Bogar bogar, Tekton tekton){
        if(bogar.getMozgaspont()!=0){
            Tekton helyzet= bogar.getHelyzet();
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            ArrayList<Tekton> lehetsegesLepes=new ArrayList<>();
            
            
            for (Fonal fonal : fonalak) {
                Tekton hova = fonal.getHova();
                lehetsegesLepes.add(hova);
            }
      
            int mozgas=bogar.getMozgaspont();
            if(lehetsegesLepes.contains(tekton)){
                bogar.mozgas(tekton);
            }
            if(bogar.getMozgaspont()==(mozgas-1) && bogar.getHelyzet()==tekton){
                korVege=mindenBogarVege();
                return "Sikeres";
            }
            else{
                korVege= mindenBogarVege();
                return "Nem elerehto az a tekton amire lepni akarasz";
            }
        }
        else{
            korVege=mindenBogarVege();
            return "Mar nem tudsz többet mozogni ebben a körben";
        }
    }

    /**
     * A bogár megeszi a jelenlegi Tektonon található legutoljára lerakott spórát, ha van,
     * és ha a körben még nem evett.
     *
     * @param bogar A bogár, amelyik enni próbál.
     * @return true, ha a bogár sikeresen evett egy spórát, különben false.
     */
    public String eves(Bogar bogar){
        if(bogar.getactionEves()==true){
            if (bogar.getHelyzet().getSporak().size()>0) {
                    bogar.eves();
                if(bogar.getactionEves()==false && bogar.getSpora()!=null){
                    korVege=mindenBogarVege();
                    return "Sikeres";
                }
                else{
                    korVege=mindenBogarVege();
                    return "Nem sikerült megenni a tektonon levo sporat";
                }
            }
            else{
                korVege=mindenBogarVege();
                return "Nincs spora azon a tektonon amirol a bogar enni szeretne";
            }
        }
        else{
            korVege=mindenBogarVege();
            return "Mar nem tudsz többet enni ebben a körben";
        }
    }

    /**
     * A bogár megpróbál elrágni egy fonalat, ami a jelenlegi Tektonról indul, 
     * ha ebben a körben még nem rágott, és a megadott fonal ténylegesen kapcsolódik a jelenlegi helyzetéhez.
     *
     * @param bogar A bogár, amely rágni próbál.
     * @param fonal Az a fonal, amelyet el szeretne rágni.
     * @return true, ha a fonalat sikeresen elrágta, különben false.
     */
    public String ragas(Bogar bogar,Fonal fonal){
        if(bogar.getactionRagas()==true){
            Tekton helyzet= bogar.getHelyzet();
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            if(fonalak.contains(fonal)){
                bogar.ragas(fonal);
            }
            
            if (bogar.getactionRagas()==false && (!bogar.getHelyzet().getOsszekoto().contains(fonal))) {
                korVege=mindenBogarVege();
                return "Sikeres";
            }
            else{
                korVege=mindenBogarVege();
                return "Nem sikerult elragni az adott fonalat";
            }
        }
        else{
            korVege=mindenBogarVege();
            return "Mar nem tudsz többet ragni ebben a körben";
        }
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
        bogarak.add(b);
        bogarak.get(bogarak.size()-1).setHelyzet(t);
        b.beallit();
        b.setTartozik(this);
        if(bogarak.contains(b) && b.getHelyzet()==t){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Eltávolít egy bogarat a játékos bogarai közül.
     *
     * @param b A bogár, amelyet el szeretnénk távolítani.
     * @return true, ha a bogár sikeresen eltávolításra került, különben false.
     */
    public boolean bogarRemove(Bogar b)
    {
        if(b!=null){
            bogarak.remove(b);
        }
        if(bogarak.contains(b)){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Név setter.
     * @param nev A beállítandó név
     */
    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * Visszaadja a bogarakat.
     * @return bogarak lista.
     */
    public ArrayList<Bogar> getBogarak() {
        return bogarak;
    }

    /**
     * Korvege setter.
     * @param ertek
     */
    public void setKorvege(boolean ertek){
        korVege = ertek;
        if(ertek==true){
            ArrayList<Bogar> regi = new ArrayList<>();
            for (Bogar bogar : bogarak) {
                regi.add(bogar);
            }
            for(Bogar bogar : regi){
                bogar.korVegeEmeszt();
            }
        }
    }
}
