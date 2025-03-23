import java.util.ArrayList;

public class Testetlen extends Tekton
{
    Testetlen(ArrayList<Tekton> szom){
        super(szom);
    }

   
    @Override
    boolean tudEpulni(Gombasz g) {
        return false;
    }

}
