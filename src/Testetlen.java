import java.util.ArrayList;

public class Testetlen extends Tekton
{
    Testetlen(){
        super();
    }

    Testetlen(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
    }

   
    @Override
    boolean tudEpulni(Gombasz g) {
        return false;
    }

    @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        return new Testetlen(spo,szom);
    }


}
