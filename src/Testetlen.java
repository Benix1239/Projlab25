import java.util.ArrayList;

public class Testetlen extends Tekton
{
    Testetlen(Gombatest test, ArrayList<Spora> spo, ArrayList<Fonal> ossze, ArrayList<Tekton> szom /*, int plusz*/){
        super(test,spo,ossze,szom);
    }

   
    @Override
    boolean tudEpulni(Gombasz g) {
        return false;
    }

}
