
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
    //private int Id;
    private boolean korVege;
    private Bogarasz tartozik;
   
    /**
     * Alapértelmezett konstruktor, amely beállítja az alapértékeket.
     */
    public Bogar() {
        mozgasok = 1;
        actionRagas = true;
        actionEves = true;
        elozo = null;
    }

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

    public boolean korVegeEmeszt(){
        //bogar.beallit();

        if(this.elozo!=null){
            this.getTartozik().pontok += this.getSpora().getPluszpont();
        }

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
        Bogar uj = new Bogar();
        this.tartozik.bogarHozzaad(uj, this.helyzet);
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

    public boolean megEve(){
        tartozik.bogarRemove(this);
        return true;
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

    public void setKorVege(boolean i){
        this.korVege=i;
    }

    public boolean getKorVege(){
        return korVege;
    }

    public void setTartozik(Bogarasz i){
        this.tartozik=i;
    }

    public Bogarasz getTartozik(){
        return tartozik;
    }
}
