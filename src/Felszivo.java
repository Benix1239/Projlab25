import java.util.ArrayList;
/*
 * @class Felszivo
 * @brief A felszivo tekton kezelese. (A tektonrol minden korben eltunik az utoljara ra helyezett fonal)
 */
public class Felszivo extends Tekton
{
    /*
    * @brief Parameter nelkuli konstruktor
    */
   Felszivo(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
    }

    /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    Felszivo(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);    ///Az ososztaly parameteres konstruktorat hivja
    }

    /*
     * @brief Minden korben meghivjuk. Elszakitja a legutobb erre a tektonra letett fonalat
     */
    @Override
    void fonalElszakadKoronkent(){
        //szkeleton.logMethodEntry(this, "fonalElszakadKoronkent");	///Szkeleton kiiratas fuggveny kezdetekor
        if(osszekoto.size()!=0){
            szkeleton.logMethodEntry(this, "fonalElszakadKoronkent");	///Szkeleton kiiratas fuggveny kezdetekor
           fonalElszakad(osszekoto.get(osszekoto.size()-1));
           szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
    
        }
        //szkeletonlogMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
    }

    /*
     * @brief Ez a tekton szeteseskor, egy masik felszivo tektont hozz letre
     */
    @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
       // szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        //szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen	
        return new Felszivo(spo,szom);
    }




    
}
