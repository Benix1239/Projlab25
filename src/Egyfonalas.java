import java.util.ArrayList;

/*
 * @class Egyfonalas
 * @brief Az egyfonalas tektonokat kezeli. (Erre a tektonra csak egy fonalt lehet letenni)
 */
public class Egyfonalas extends Tekton
{

    /*
     * @brief Parameter nelkuli konstruktor
     */
    Egyfonalas(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
   
    }

     /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    Egyfonalas(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
    }


    /*
     * @brief Megepiti a fonalat a tektonra.
     * @param f -> Fonal amit a tektonra helyez
     * @return true -> Epites sikeres
     * @retrun false -> Sikertelen epites
     */
    @Override
    boolean addFonal(Fonal f){
        szkeleton.logMethodEntry(this, "addFonal");	///Szkeleton kiiratas fuggveny kezdetekor
        boolean sikerult = false;
		if(osszekoto.size() == 0)
        {
            osszekoto.add(f);
            sikerult = true;
        }
		
		if(tudEpulni(f.getTartozik()) ){                    ///Ha tud gombatestet is epit
			gombaTestEpul(f.getTartozik());
		}
                                
        szkeleton.logMethodExit(this, sikerult);			///Szkeleton kiiratas fuggveny vegen
		return sikerult;

	} 






    /*
     * @brief Elszakitj a tektonon levo osszes fonalat
     */
    @Override
    void mindenFonalElszakad(){
        szkeleton.logMethodEntry(this, "mindenFonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
		for(Fonal elem : osszekoto){
			fonalElszakad(elem);
		}
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



    /*
     * @brief Elszakitja a tektonon levo adott fonalat
     * @param fonal -> Elszakitani kivant fonal
     */
    @Override
	void fonalElszakad(Fonal fonal){
        szkeleton.logMethodEntry(this, "fonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
		for (Fonal elem : osszekoto) {
			if(elem==fonal || elem.equals(fonal)){
				for(Fonal elem1 : elem.getHova().getOsszekoto()){
					if(elem1.getHova()==this){
						elem.getHova().getOsszekoto().remove(elem1);
					}
				}
				osszekoto.remove(elem);
				
			}
		}
		fonal.getTartozik().elszakadasDfsKezeles();                          
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}


    /*
     * @brief Szeteseskor szinten egy Egyfonalas tektont hozz letre
     * @return Letrehozott tekton
     */
  @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
       // szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        //szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
        return new Egyfonalas(spo,szom);
    }



}
