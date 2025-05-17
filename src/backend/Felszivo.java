package backend;

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
   public Felszivo(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
    }

    /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    public Felszivo(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);    ///Az ososztaly parameteres konstruktorat hivja
    }

    /*
     * @brief Minden korben meghivjuk. Elszakitja a legutobb erre a tektonra letett fonalat
     */
    @Override
    public void fonalElszakadKoronkent(){
        if(osszekoto.size()!=0){
            Fonal szakitjuk=osszekoto.get(osszekoto.size()-1);
           fonalElszakad(szakitjuk);
        }
    }

    /*
     * @brief Ez a tekton szeteseskor, egy masik felszivo tektont hozz letre
     */
    
    protected  Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){	
        return new Felszivo(spo,szom);
    }

}
