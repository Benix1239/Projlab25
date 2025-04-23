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
    @Override
    private boolean tudEpulni(Gombasz g) {
        ////szkeleton.logMethodEntry(this, "tudEpulni");	///Szkeleton kiiratas fuggveny kezdetekor
        ////szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen
        return false;
    }

    /*
     * @brief A tekton szetesesekor egy masik Testetlen tektont hozz letre
     * @return Letrehozott uj tekton
     */
    @Override
    private Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        ////szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        ////szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
        return new Testetlen(spo,szom);
    }


}
