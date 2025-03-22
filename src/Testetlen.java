import java.util.ArrayList;

public class Testetlen extends Tekton
{
    Testetlen(Gombatest test, ArrayList<Spora> spo, ArrayList<Fonal> ossze, ArrayList<Tekton> szom /*, int plusz*/,int nev){
        super(test,spo,ossze,szom,nev);
    }

   
    @Override
    boolean tudEpulni(Gombasz g) {
        return false;
    }

}
