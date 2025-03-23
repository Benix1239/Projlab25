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
        szkeleton.logMethodEntry(this, "tudEpulni");	///Szkeleton kiiratas fuggveny kezdetekor
        szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen
        return false;
    }

    @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
        return new Testetlen(spo,szom);
    }


}
