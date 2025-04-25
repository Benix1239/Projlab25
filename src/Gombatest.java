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
    private boolean korVege;
    private int action;
    private static final Random random = new Random();

    public Gombatest(Tekton hely, Gombasz tartozik) {
        this.hely = hely;
        this.tartozik = tartozik;
        maradt = 20;
        spora = null;
        korVege = false;
        action = 2;
        tartozik.addPoint(hely.pluszPont);
    }

    public void korElejeInicializalas(){
        action = 2;
        korVege = false;
        termel();
        checkKorvege();
    }

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

    private void checkKorvege(){
        if(action == 0){
            korVege = true;
        }

        if(hovaSzorhat().size() == 0 && tartozik.hovaLehetosegek(hely).size() == 0){
            korVege = true;
        }

        if(action == 1 && tartozik.hovaLehetosegek(hely).size() == 0 && spora == null){
            korVege = true;
        }
    }

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

    private void gombatestMeghal(){
        hely.setGombatest(null);

        tartozik.removeGombatest(this);
        
        tartozik.elszakadasDfsKezeles();
    }

    //legjobb minta erre az EgyFonalasHova szekvenciadiagram
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
    public ArrayList<Tekton> szomszedKeres(){
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
        Set<Tekton> megtalalt = new HashSet<>();
        dfsRekurzio(hely, megtalalt);
        return megtalalt;
    }

    public Set<Tekton> hovaSzorhat(){
        Set<Tekton> szomszedok = new HashSet<>(hely.getSzomszed());
        if(this.maradt <= 3){
            Set<Tekton> szomszedokSzomszedai = new HashSet<>();
            for(Tekton t : szomszedok){
                szomszedokSzomszedai.addAll(t.getSzomszed());
            }
            szomszedok.addAll(szomszedokSzomszedai);
        }

        if(szomszedok.size() != 0){
            return szomszedok;
        }else{
            return null;
        }
    }

    public int getMaradt(){
        return maradt;
    }

    public void setSpora(Spora s){
        this.spora = s;
    }

    public void setMaradt(int i){
        maradt = i;
    }

    public Tekton getHely() {
        return hely;
    }

    public boolean getKorvege(){
        return korVege;
    }
}