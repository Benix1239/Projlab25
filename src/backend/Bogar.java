package backend;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** 
 * A Bogar osztály egy bogarat reprezentál, amely képes mozogni, rágni és enni.
 */
public class Bogar implements Serializable 
{
    private int mozgasok;
    private Boolean actionRagas;
    private Boolean actionEves;
    private Spora elozo;
    private Tekton helyzet;
    //private int Id;
    private boolean korVege;
    private Bogarasz tartozik;
   
    /**
     * Alapértelmezett konstruktor, amely beállítja az alapértékeket.
     */
    public Bogar(Bogarasz tartozik, Tekton helyzet) {
        this.tartozik = tartozik;
        this.helyzet = helyzet;
        mozgasok = 1;
        actionRagas = true;
        actionEves = true;
        elozo = null;
        korVege=false;
    }

    /**
     * Ellenőrzi a kör végét.
     */
    private void checkKorvege(){
        if(mozgasok == 0 && actionEves==false && actionRagas==false){
            korVege = true;
        }

        if(mozgasok <= 1 && helyzet.fonalKeres()==null && actionEves==false && actionRagas==false){
            korVege = true;
        }

        if(actionRagas == true  && helyzet.getOsszekoto()==null && mozgasok==0 && actionEves==false){
            korVege = true;
        }
        if(actionEves == true && helyzet.getSporak()==null && mozgasok==0 && actionRagas==false ){
            korVege = true;
        }
    }

    /**
     * Kör végén emészt.
     */
    public boolean korVegeEmeszt(){
        this.beallit();

        this.sporaMegemesztes();

        if (this.elozo==null) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Visszaállítja az alapértelmezett értékeket.
     */
    public void beallit() {
        mozgasok = 1;
        actionRagas = true;
        actionEves = true;
        korVege=false;
    }

    /**
     * Növeli a mozgási pontok számát.
     */
    public void gyorsul() {
        mozgasok++;
    }

    /**
     * Csökkenti a mozgási pontok számát és letiltja az evést és rágást.
     */
    public void benul() {
        Gombasz gombasz = elozo.getTartozik();
        gombasz.benultBogarHozzaad(this);
        mozgasok--;
        actionEves = false;
        actionRagas = false;
    }

    /**
     * Letiltja a rágási akciót.
     */
    public void ragasBlock() {
        actionRagas = false;
    }

    /**
     * Csökkenti a mozgási pontok számát.
     */
    public void lassul() {
        mozgasok--;
    }

    /**
     * Létrehoz egy új bogarat és eltárolja a bogarásznál
     */
    public void szaporodo() {
        this.tartozik.bogarHozzaad(this.helyzet);
    }

    /**
     * A bogár rágási akciót hajt végre egy adott fonalon.
     * 
     * @param fonal A fonal, amelyet a bogár elrág.
     */
    public void ragas(Fonal fonal) {
        Gombasz gombasz = fonal.getTartozik();
        helyzet.fonalElszakad(fonal);
        helyzet.nemHalMegListaTorles();
        actionRagas = false;
        actionEves = false;
        checkKorvege();
    }

    /**
     * A bogár megeszi az aktuális helyzetén található spórát.
     */
    public void eves() {
        
        elozo = helyzet.sporatEszik();
        this.tartozik.addPoint(elozo.getPluszpont());
        actionRagas = false;
        actionEves = false;
        checkKorvege();
    }

    /**
     * A bogár egy új helyzetbe mozog.
     * 
     * @param t Az új helyzet.
     */
    public void mozgas(Tekton t) {
        helyzet = t;
        mozgasok--;
        checkKorvege();
    }

    /**
     * A bogár megemészti az előzőleg elfogyasztott spórát.
     */
    public void sporaMegemesztes() {
        if (elozo != null) {
            elozo.hatas(this);
            this.elozo = null;
        }
    }

    /**
     * Eltávolítja a Bogarasz listájából ezt a bogarat.
     * @return igazat ad, ha lefutott a bogár eltávolítása
     */
    public boolean megEve(){
        tartozik.bogarRemove(this);
        return true;
    }

    public Set<Tekton> hovaLephet() {
        Set<Tekton> szomszedok=null;
    
        if (this.mozgasok > 0 && helyzet.fonalKeres().size()!=0) { 
            szomszedok=new HashSet<>(helyzet.fonalKeres());
        }
    
        return szomszedok;
    }

    /**
     * Lekéri a bogár jelenlegi helyzetét.
     * 
     * @return A jelenlegi helyzet.
     */
    public Tekton getHelyzet() {
        return helyzet;
    }

    /**
     * Beállítja a bogár helyzetét.
     * 
     * @param t Az új helyzet.
     */
    public void setHelyzet(Tekton t) {
        helyzet = t;
    }

    /**
     * Lekéri a mozgási pontok számát.
     * 
     * @return A mozgási pontok száma.
     */
    public int getMozgaspont() {
        return mozgasok;
    }

    /**
     * Ellenőrzi, hogy a bogár képes-e rágni.
     * 
     * @return Igaz, ha a bogár rághat, egyébként hamis.
     */
    public boolean getactionRagas() {
        return actionRagas;
    }

    /**
     * Ellenőrzi, hogy a bogár képes-e enni.
     * 
     * @return Igaz, ha a bogár ehet, egyébként hamis.
     */
    public boolean getactionEves() {
        return actionEves;
    }
    
    /**
     * Beállítja az előzőleg elfogyasztott spórát.
     * 
     * @param s A spóra, amelyet a bogár elfogyasztott.
     */
    public void setElozo(Spora s) {
        elozo = s;
    }

    /**
     * Lekéri az előzőleg elfogyasztott spórát.
     * 
     * @return Az előző spóra.
     */
    public Spora getSpora() {
        return elozo;
    }

    /*public void setId(int i){
        this.Id=i;
    }

    public int getId(){
        return Id;
    }*/

    /**
     * Beállítja, hogy a kör véget ért-e.
     * @param i true, ha a kör véget ért, false egyébként
     */
    public void setKorVege(boolean i){
        this.korVege=i;
    }

    /**
     * Visszaadja, hogy a kör véget ért-e.
     * @return true, ha a kör véget ért, false egyébként
     */
    public boolean getKorVege(){
        return korVege;
    }

    /**
     * Beállítja, hogy melyik Bogarasz tartozik ehhez a Bogarhoz
     * @param i a Bogarasz, aki tartozik ehhez a Bogarhoz
     */
    public void setTartozik(Bogarasz i){
        this.tartozik=i;
    }

    /**
     * Visszaadja a Bogaraszt.
     * @return a Bogarasz példány
     */
    public Bogarasz getTartozik(){
        return tartozik;
    }

    /**
     * Megállapítja, hogy a megadott halmazban benne van-e a helyzet tekton
     * @param elerhetok halmaz
     * @return true, ha a tekton, amin a bogár van benne van az elérhető Tektonok halmazában, false egyébként
     */
    public boolean elerhetoFonallal(Set<Tekton> elerhetok){
        if(elerhetok.contains(this.helyzet)){
            return true;
        }
        return false;
    }
}
