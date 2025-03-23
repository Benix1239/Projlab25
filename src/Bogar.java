import java.util.ArrayList;

/**
 * A Bogar osztály egy bogarat reprezentál, amely képes mozogni, rágni és enni.
 */
public class Bogar 
{
    private int mozgasok;
    private Boolean actionRagas;
    private Boolean actionEves;
    private Spora elozo;
    private Tekton helyzet;
   
    /**
     * Alapértelmezett konstruktor, amely beállítja az alapértékeket.
     */
    public Bogar() {
        mozgasok = 1;
        actionRagas = true;
        actionEves = true;
        elozo = null;
    }

    /**
     * Visszaállítja az alapértelmezett értékeket.
     */
    public void beallit() {
        szkeleton.logMethodEntry(this, "beallit");
        mozgasok = 1;
        actionRagas = true;
        actionEves = true;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Növeli a mozgási pontok számát.
     */
    public void gyorsul() {
        szkeleton.logMethodEntry(this, "gyorsul");
        mozgasok++;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Csökkenti a mozgási pontok számát és letiltja az evést és rágást.
     */
    public void benul() {
        szkeleton.logMethodEntry(this, "benul");
        mozgasok--;
        actionEves = false;
        actionRagas = false;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Letiltja a rágási akciót.
     */
    public void ragasBlock() {
        szkeleton.logMethodEntry(this, "ragasBlock");
        actionRagas = false;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Csökkenti a mozgási pontok számát.
     */
    public void Lassul() {
        szkeleton.logMethodEntry(this, "ro");
        mozgasok--;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár rágási akciót hajt végre egy adott fonalon.
     * 
     * @param fonal A fonal, amelyet a bogár elrág.
     */
    public void ragas(Osszekoto fonal) {
        szkeleton.logMethodEntry(this, "ragas");
        Tekton hova = fonal.getHova();
        Gombasz gombasz = fonal.getTartozik();
        
        helyzet.fonalElszakad(fonal);
        ArrayList<Fonal> fonalak = hova.fonalKeres();

        for (Fonal fonali : fonalak) {
            if (fonali.getHova() == helyzet && fonal.getTartozik() == gombasz) {
                hova.fonalElszakad();
            }
        }

        gombasz.elszakadasDfsKezeles();
        actionRagas = false;
        actionEves = false;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár megeszi az aktuális helyzetén található spórát.
     */
    public void eves() {
        szkeleton.logMethodEntry(this, "eves");
        elozo = helyzet.sporatEszik();
        actionRagas = false;
        actionEves = false;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár egy új helyzetbe mozog.
     * 
     * @param t Az új helyzet.
     */
    public void mozgas(Tekton t) {
        szkeleton.logMethodEntry(this, "mozgas");
        helyzet = t;
        mozgasok--;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * A bogár megemészti az előzőleg elfogyasztott spórát.
     */
    public void sporaMegemesztes() {
        szkeleton.logMethodEntry(this, "sporaMegemesztes");
        if (elozo != null) {
            elozo.hatas(this);
            this.elozo = null;
        }
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Lekéri a bogár jelenlegi helyzetét.
     * 
     * @return A jelenlegi helyzet.
     */
    public Tekton getHelyzet() {
        szkeleton.logMethodEntry(this, "getHelyzet");
        szkeleton.logMethodExit(this, "Tekton"+helyzet.getId());
        return helyzet;
    }

    /**
     * Beállítja a bogár helyzetét.
     * 
     * @param t Az új helyzet.
     */
    public void setHelyzet(Tekton t) {
        szkeleton.logMethodEntry(this, "setHelyzet");
        helyzet = t;
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Lekéri a mozgási pontok számát.
     * 
     * @return A mozgási pontok száma.
     */
    public int getMozgaspont() {
        szkeleton.logMethodEntry(this, "getMozgaspont");
        szkeleton.logMethodExit(this, mozgasok);
        return mozgasok;
    }

    /**
     * Ellenőrzi, hogy a bogár képes-e rágni.
     * 
     * @return Igaz, ha a bogár rághat, egyébként hamis.
     */
    public boolean getactionRagas() {
        szkeleton.logMethodEntry(this, "getactionRagas");
        szkeleton.logMethodExit(this, boolean);
        return actionRagas;
    }

    /**
     * Ellenőrzi, hogy a bogár képes-e enni.
     * 
     * @return Igaz, ha a bogár ehet, egyébként hamis.
     */
    public boolean getactionEves() {
        szkeleton.logMethodEntry(this, "getactionEves");
        szkeleton.logMethodExit(this, boolean);
        return actionEves;
    }
    
    /**
     * Beállítja az előzőleg elfogyasztott spórát.
     * 
     * @param s A spóra, amelyet a bogár elfogyasztott.
     */
    public void setElozo(Spora s) {
        szkeleton.logMethodEntry(this, "setElozo");
        szkeleton.logMethodExit(this, "");
        elozo = s;
    }

    /**
     * Lekéri az előzőleg elfogyasztott spórát.
     * 
     * @return Az előző spóra.
     */
    public Spora getSpora() {
        szkeleton.logMethodEntry(this, "setElozo");
        szkeleton.logMethodExit(this, "spora");
        return elozo;
    }
}
