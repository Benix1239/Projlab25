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
        szkeleton.logMethodEntry(this, "fonalElszakadKoronkent");	///Szkeleton kiiratas fuggveny kezdetekor
        if(osszekoto.size()!=0){
           fonalElszakad(osszekoto.get(osszekoto.size()-1));
        }
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
    }


    @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen	
        return new Felszivo(spo,szom);
    }




    
}
