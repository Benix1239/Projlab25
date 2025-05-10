package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Gombatest implements Serializable 
{
    
    private Tekton hely;
    private Spora spora;
    private int maradt;
    private Gombasz tartozik;
    private boolean korVege;
    private int action;
    private static final Random random = new Random();

    /**
     * Konstruktor, beállítja a helyet és a gombászt.
     * @param hely
     * @param tartozik
     */
    public Gombatest(Tekton hely, Gombasz tartozik) {
        this.hely = hely;
        this.tartozik = tartozik;
        maradt = 20;
        spora = null;
        korVege = true;
        action = 0;
        tartozik.addPoint(hely.pluszPont);
    }

    /**
     * Kör eleji inicializálás.
     */
    public void korElejeInicializalas(){
        action = 2;
        korVege = false;
        termel();
        checkKorvege();
    }

    /**
     * Spóra termelése.
     */
    private void termel(){
        int randomSzam = random.nextInt(5);
        switch (randomSzam) {
            case 0:
                spora = new Gyorsito(tartozik);
                break;
            case 1:
                spora = new Benito(tartozik);
                break;
            case 2:
                spora = new Keseru(tartozik);
                break;
            case 3:
                spora = new Lassito(tartozik);
                break;
            case 4:
                spora = new Sima(tartozik);
                break;
            case 5:
                spora = new Szaporodo(tartozik);
                break;     
        }
    }

    /**
     * Vége van-e a körnek.
     */
    private void checkKorvege(){
        if(action == 0){
            korVege = true;
        }

        if(hovaSzorhat().size() == 0 && !tudFonalatRakni()){
            korVege = true;
        }

        if(!tudFonalatRakni() && spora == null){
            korVege = true;
        }

    }

    /**
     * Tud-e fonalat rakni.
     * @return boolean.
     */
    public boolean tudFonalatRakni(){
        Set<Tekton> dfsEredmeny = dfs();
        for(Tekton t : dfsEredmeny){
            if(tartozik.hovaLehetosegek(t).size() != 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Adott tektonra spórát szór.
     * @param c Tekton, amire elszór.
     * @return Sikeres-e.
     */
    public boolean elszor(Tekton c){
        Set<Tekton> szomszedok = hovaSzorhat();
        if(szomszedok != null && szomszedok.contains(c) && spora != null){
            c.addSpora(spora);
            action--;
            maradt--;
            if(maradt == 0){
                this.gombatestMeghal();
            }
            spora = null;
            checkKorvege();
            return true;
        }
        checkKorvege();
        return false;
    }

    /**
     * Gombatest meghalásának kezelése.
     */
    private void gombatestMeghal(){
        hely.setGombatest(null);

        tartozik.removeGombatest(this);
        
        tartozik.elszakadasDfsKezeles();
    }

    //legjobb minta erre az EgyFonalasHova szekvenciadiagram
    /**
     * Fonal elhelyezése
     * @param honnan Tekton.
     * @param hova Tekton.
     * @return Sikeresség.
     */
    public String elhelyez(Tekton honnan, Tekton hova){
        Fonal f1 = new Fonal(honnan, this.tartozik);
        Fonal f2 = new Fonal(hova, this.tartozik);

        boolean vissza = honnan.addFonal(f2);

        if(vissza){
            boolean vissza2 = hova.addFonal(f1);
            
            if(!vissza2){
                honnan.fonalElszakad(f2);
                action--;
                checkKorvege();
                return "Fonal elhelyezese sikertelen, mert a hova tekton egyfonalas es van rajta mar fonal";
            }
            action--;
            checkKorvege();
            return "Siker";
        }
        action--;
        checkKorvege();
        return "Fonal elhelyezese sikertelen, mert a honnan tekton egyfonalas es van rajta mar fonal";
    }

    //ez miert itt van, miert nem a tektonban?
    /**
     * Szomszéd keresés.
     * @return Szomszéd tektonok listája.
     */
    public ArrayList<Tekton> szomszedKeres(){
        return hely.getSzomszed();
    }

    /**
     * DFS rekurzió függvény.
     * @param tekton
     * @param megtalalt
     */
    private void dfsRekurzio(Tekton tekton, Set<Tekton> megtalalt){
        if (megtalalt.contains(tekton)) {
            return;
        }
        megtalalt.add(tekton);

        for(Fonal f : tekton.getOsszekoto()){
            if(f.getTartozik() == tartozik){
                dfsRekurzio(f.getHova(), megtalalt);
            }
        }
    }

    /**
     * dfs függvény.
     * @return Megtalált tektonok.
     */
    public Set<Tekton> dfs(){
        Set<Tekton> megtalalt = new HashSet<>();
        dfsRekurzio(hely, megtalalt);
        return megtalalt;
    }

    /**
     * Visszaadja, hova szórhat a Gombatest.
     * @return Tektonok listája, ahova szórhat.
     */
    public Set<Tekton> hovaSzorhat(){
        Set<Tekton> szomszedok = new HashSet<>(hely.getSzomszed());
        if(this.maradt <= 3){
            Set<Tekton> szomszedokSzomszedai = new HashSet<>();
            for(Tekton t : szomszedok){
                szomszedokSzomszedai.addAll(t.getSzomszed());
            }
            szomszedok.addAll(szomszedokSzomszedai);
        }

        return szomszedok;
        
    }

    /**
     * Visszaadja, mit lehet csinálni.
     * @return String.
     */
    public String mitLehetCsinalni(){
        String returnValue = "";
        if(action == 0){
            return returnValue;
        }

        if(korVege){
            return returnValue;
        }

        if(hovaSzorhat().size() != 0 && spora != null){
            returnValue += "Szoras ";
        }
        
        if(tudFonalatRakni()){
            returnValue += "Fonalrakas";
        }

        returnValue += "\n";

        return returnValue;
    }

    /**
     * Lehet-e sporat szorni a gombatesttel.
     * @return true: lehet szorni, false: nem lehet sporat szorni.
     */
    public boolean tudSporatSzorni(){
        if(spora != null){
            return true;
        }
        return false;
    }

    /**
     * Maradt getter.
     * @return int maradt.
     */
    public int getMaradt(){
        return maradt;
    }

    /**
     * Spora setter.
     * @param s
     */
    public void setSpora(Spora s){
        this.spora = s;
    }

    /**
     * Maradt setter.
     * @param i
     */
    public void setMaradt(int i){
        maradt = i;
    }

    /**
     * Hely getter.
     * @return Tekton.
     */
    public Tekton getHely() {
        return hely;
    }

    /**
     * korVege getter.
     * @return boolean.
     */
    public boolean getKorvege(){
        return korVege;
    }

    /**
     * spora getter.
     * @return Spora.
     */
    public Spora getSpora(){
        return spora;
    }


    public Gombasz getTartozik(){
        return tartozik;
    }
}