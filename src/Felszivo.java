import java.util.ArrayList;

public class Felszivo extends Tekton
{
    Felszivo(Gombatest test, ArrayList<Spora> spo, ArrayList<Fonal> ossze, ArrayList<Tekton> szom /*, int plusz*/ ){
        super(test, spo, ossze, szom);
    }

    void fonalElszakadKoronkent(){
        if(osszekoto.size()!=0){
           fonalElszakad(osszekoto.get(osszekoto.size()-1));
        }
    }

    
}
