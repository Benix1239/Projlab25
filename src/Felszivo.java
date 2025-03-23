import java.util.ArrayList;

public class Felszivo extends Tekton
{
   Felszivo(){
        super();
    }

    Felszivo(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom); 
    }

    void fonalElszakadKoronkent(){
        if(osszekoto.size()!=0){
           fonalElszakad(osszekoto.get(osszekoto.size()-1));
        }
    }


    @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        return new Felszivo(spo,szom);
    }




    
}
