
import java.util.ArrayList;
import java.io.Serializable;

public class Palya implements Serializable;
{
    private ArrayList<Tekton> palya;

    Palya(int kezdomeret)
    {
        letrehoz(kezdomeret);
    }

    public void tektonHozzaad(Tekton t)
    {
        if (t != null)) {
            palya.add(t);
        }
        
    }

    public void letrehoz(int kezdomeret)
    {
        for (int i = 0; i < kezdomeret; i++) {
            tektonHozzaad(new Tekton());
        }
    }

    public void round(Jatekos karakterek)
    {

    }

    public void tores()
    {
        for (Tekton t : palya) {
            Tekton torott = t.tores();
            if (torott != null) {
                tektonHozzaad(torott);
            }
        }
    }

    

    public void felszivodik()
    {
        for (Tekton t : palya) {
            if (t instanceof Testetlen) {
                t.fonalElszakadKoronkent();
            }
        }
    }
}