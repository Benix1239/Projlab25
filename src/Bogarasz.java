import java.util.ArrayList;

/**
 * A Bogarasz osztály egy játékost reprezentál, aki több bogarat irányít a játék során.
 * A játékos különböző akciókat hajthat végre, például mozgást, evést és fonalak rágását.
 */
public class Bogarasz extends Jatekos
{
    private ArrayList<Bogar> bogarak;

    public Bogarasz(){
        this.bogarak = new ArrayList<Bogar>();
    }

    
    public void korElejeInicializalas(){
        for(Bogar bogar : bogarak){
            bogar.beallit();
        }
    }
    
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

    public String mitLehetCsinalni(){
        String returnValue = null;
        for(Bogar bogar : bogarak){
            returnValue += "Bogar"+bogarak.indexOf(bogar) + ":\nMegmaradt lepesek száma: " + bogar.getMozgaspont()+ "\n";
            if (bogar.getactionEves()) {
                returnValue += "Tud meg enni\n";
            } else {
                returnValue += "Nem tud meg enni\n";
            }
            if (bogar.getactionRagas()) {
                returnValue += "Tud meg ragni\n\n";
            } else {
                returnValue += "Nem tud meg ragni\n\n";
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
            return "Mar nem tudsz többet vele mozogni ebben a körben";
        }
    }

    /**
     * A bogár megeszi a jelenlegi Tektonon található legutoljára lerakott spórát, ha van,
     * és ha a körben még nem evett.
     *
     * @param bogar A bogár, amelyik enni próbál.
     * @return true, ha a bogár sikeresen evett egy spórát, különben false.
     */
    public boolean eves(Bogar bogar){
        if(bogar.getactionEves()==true){
            bogar.eves();
            if(bogar.getactionEves()==false && bogar.getSpora()!=null){
                korVege=mindenBogarVege();
                return true;
            }
            else{
                korVege=mindenBogarVege();
                return false;
            }
        }
        else{
            korVege=mindenBogarVege();
            return false;
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
    public boolean ragas(Bogar bogar,Fonal fonal){
        if(bogar.getactionRagas()==true){
            Tekton helyzet= bogar.getHelyzet();
            ArrayList<Fonal> fonalak = helyzet.getOsszekoto();
            if(fonalak.contains(fonal)){
                bogar.ragas(fonal);
            }
            
            if (bogar.getactionRagas()==false && (!bogar.getHelyzet().getOsszekoto().contains(fonal))) {
                korVege=mindenBogarVege();
                return true;
            }
            else{
                korVege=mindenBogarVege();
                return false;
            }
        }
        else{
            korVege=mindenBogarVege();
            return false;
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

    public void setNev(String nev) {
        this.nev = nev;
    }

    public ArrayList<Bogar> getBogarak() {
        return bogarak;
    }
}
