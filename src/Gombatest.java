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
        int randomSzam = random.nextInt(4);
        switch (randomSzam) {
            case 0:
                spora = new Gyorsito();
                break;
            case 1:
                spora = new Benito();
                break;
            case 2:
                spora = new Keseru();
                break;
            case 3:
                spora = new Lassito();
                break;
            case 4:
                spora = new Sima();     
        }
    }

    public void elszor(Tekton c){
        c.addSpora(spora);
        maradt--;
        if(maradt == 0){
            this.gombatestMeghal();
        }
        spora = null;
    }

    private void gombatestMeghal(){
        hely.setGombatest(null);
        tartozik.removeGombatest(this);
        tartozik.elszakadasDfsKezeles();
    }

    //legjobb minta erre az EgyFonalasHova szekvenciadiagram
    public boolean elhelyez(Tekton honnan, Tekton hova){
        Fonal f1 = new Fonal(honnan, this.tartozik);
        Fonal f2 = new Fonal(hova, this.tartozik);
        if(honnan.addFonal(f2)){
            if(!hova.addFonal(f1)){
                honnan.fonalElszakad(f2);
                System.out.println("Sikertelen lerakas, mert a hova tekton EgyFonalas");
                return false;
            }
            System.out.println("Sikeres lerakas!");
            return true;
        }
        System.out.println("Sikertelen lerakas, mert a honnan tekton EgyFonalas");
        return false;
    }

    //ez miert itt van, miert nem a tektonban?
    public ArrayList<Tekton> szomszedKeres(){
        return hely.getSzomszed();
    }

    private void dfsRekurzio(Tekton tekton, Set<Tekton> megtalalt){
        if (megtalalt.contains(tekton)) {
            return;
        }
         megtalalt.add(tekton);

         for(Fonal f : tekton.getKoto()){
            if(f.getTartozik() == tartozik){
                dfsRekurzio(f.getHova(), megtalalt);
            }
        }
    }

    public Set<Tekton> dfs(){
        Set<Tekton> megtalalt = new HashSet<>();
        dfsRekurzio(hely, megtalalt);
        return megtalalt;
    }

    public int getMaradt(){
        return maradt;
    }
    
    //vmi
}