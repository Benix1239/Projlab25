package backend;


import java.util.ArrayList;

/*
 * @class Testetlen
 * @brief Testetlen tekton kezelese. (Erre a tektonra nem tud gombatest epulni)
 */
public class Testetlen extends Tekton
{
     /*
    * @brief Parameter nelkuli konstruktor
    */
    public Testetlen(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
    }

    /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    public Testetlen(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom); ///Az ososztaly parameteres konstruktorat hivja
    }

   /*
    * @brief Meghatarozza hogy tud -e gombatest epulni az adott tektonra
    * @retrun Mindig false
    */
    protected  boolean tudEpulni(Gombasz g) {
        return false;
    }

    /*
     * @brief A tekton szetesesekor egy masik Testetlen tektont hozz letre
     * @return Letrehozott uj tekton
     */
    protected  Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        return new Testetlen(spo,szom);
    }


}
