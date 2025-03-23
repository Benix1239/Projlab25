import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Gombatest
{
    
    private Tekton hely;
    private Spora spora;
    private int maradt;
    private Gombasz tartozik;
    private static final Random random = new Random();

    public Gombatest(Tekton hely, Gombasz tartozik) {
        this.hely = hely;
        this.tartozik = tartozik;
        maradt = 20;
        spora = null;
    }

    public void Round(){
       
    }

    public void termel(){
        //szkeleton.logMethodEntry(this, "termel");
        int randomSzam = random.nextInt(4);
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
        }
        //szkeleton.logMethodExit(this, "");
    }

    public void elszor(Tekton c){
        //szkeleton.logMethodEntry(this, "elszor");
        szkeleton.logMethodEntry(c, "addSpora");
        c.addSpora(spora);
        szkeleton.logMethodExit(this, "");	
        maradt--;
        if(maradt == 0){
            this.gombatestMeghal();
        }
        spora = null;
        //szkeleton.logMethodExit(this, "");
    }

    private void gombatestMeghal(){
        //szkeleton.logMethodEntry(this, "gombatestMeghal");
        hely.setGombatest(null);
        tartozik.removeGombatest(this);
        tartozik.elszakadasDfsKezeles();
        //szkeleton.logMethodExit(this, "");
    }

    //legjobb minta erre az EgyFonalasHova szekvenciadiagram
    public boolean elhelyez(Tekton honnan, Tekton hova){
        //szkeleton.logMethodEntry(this, "elhelyez");
        Fonal f1 = new Fonal(honnan, this.tartozik);
        Fonal f2 = new Fonal(hova, this.tartozik);
        if(honnan.addFonal(f2)){
            if(!hova.addFonal(f1)){
                honnan.fonalElszakad(f2);
                System.out.println("Sikertelen lerakas, mert a hova tekton EgyFonalas");
                //szkeleton.logMethodExit(this, "false");
                return false;
            }
            System.out.println("Sikeres lerakas!");
            //szkeleton.logMethodExit(this, "true");
            return true;
        }
        System.out.println("Sikertelen lerakas, mert a honnan tekton EgyFonalas");
        //szkeleton.logMethodExit(this, "false");
        return false;
    }

    //ez miert itt van, miert nem a tektonban?
    public ArrayList<Tekton> szomszedKeres(){
        //szkeleton.logMethodEntry(this, "szomszedKeres");
        //szkeleton.logMethodExit(this, "szomszedok");
        return hely.getSzomszed();
    }

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

    public Set<Tekton> dfs(){
        //szkeleton.logMethodEntry(this, "dfs");
        Set<Tekton> megtalalt = new HashSet<>();
        dfsRekurzio(hely, megtalalt);
        //szkeleton.logMethodExit(this, "megtalalt");
        return megtalalt;
    }

    public int getMaradt(){
        //szkeleton.logMethodEntry(this, "getMaradt");
        //szkeleton.logMethodExit(this, "maradt");
        return maradt;
    }

    public void setSpora(Spora s){
        this.spora = s;
    }

    public void setMaradt(int i){
        maradt = i;
    }
}