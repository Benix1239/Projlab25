import java.util.ArrayList;

public class Felszivo extends Tekton
{
    Felszivo(ArrayList<Tekton> szom){
        super(szom);
    }

    void fonalElszakadKoronkent(){
        if(osszekoto.size()!=0){
           fonalElszakad(osszekoto.get(osszekoto.size()-1));
        }
    }

    
}
