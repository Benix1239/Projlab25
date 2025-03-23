import java.util.ArrayList;

/*
 * @class Egyfonalas
 * @brief Az egyfonalas tektonokat kezeli. (Erre a tektonra csak egy fonalt lehet letenni)
 */
public class Egyfonalas extends Tekton
{
    boolean lehet;  ///Van-e mar a tektonon fonal

    /*
     * @brief Parameter nelkuli konstruktor
     */
    Egyfonalas(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
        lehet=true;
    }

     /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    Egyfonalas(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
        lehet=true; ///Az ososztaly parameteres konstruktorat hivja
    }


    /*
     * @brief Getter
     * @return true -> Lehet fonalat epiteni
     * @return false -> Nem lehet fonalat epiteni
     */
    boolean getLehetFonalatEpiteni(){
        szkeleton.logMethodEntry(this, "getLehetFonalatEpiteni");	///Szkeleton kiiratas fuggveny kezdetekor
        szkeleton.logMethodExit(this, lehet);			///Szkeleton kiiratas fuggveny vegen	
        return lehet;
    }
    
    /*
     * @brief Setter
     * @param leh -> Lehet -e epiteni
     */
    void setLehet(Boolean leh){
        szkeleton.logMethodEntry(this, "setLehet");	///Szkeleton kiiratas fuggveny kezdetekor
        this.lehet=leh;
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
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
		for(Fonal elem:osszekoto){
			if(elem.getTartozik().equals(f.getTartozik())){

                szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen

				return false;
			}
		}
		osszekoto.add(f);
		if(tudEpulni(f.getTartozik()) ){                    ///Ha tud gombatestet is epit
			gombaTestEpul(f.getTartozik());
		}
        lehet=false;                                    ///Tobbet mar ne lehessen letenni

        szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen
		return true;

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
        lehet=true;                                 ///Ujra lehet a tektonra fonalat tenni
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
				for(Fonal elem1 : elem.getHova().getFonalLista()){
					if(elem1.getHova()==this){
						elem.getHova().getFonalLista().remove(elem1);
					}
				}
				osszekoto.remove(elem);
				
			}
		}
		fonal.getTartozik().elszakadasDfsKezeles();
		lehet=true;                                 ///Ismet lehet fonalat epiteni a tektonra
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}


    /*
     * @brief Szeteseskor szinten egy Egyfonalas tektont hozz letre
     * @return Letrehozott tekton
     */
  @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
        return new Egyfonalas(spo,szom);
    }



}
