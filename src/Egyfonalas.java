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
    public Egyfonalas(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
   
    }

     /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    public Egyfonalas(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
    }


    /*
     * @brief Megepiti a fonalat a tektonra.
     * @param f -> Fonal amit a tektonra helyez
     * @return true -> Epites sikeres
     * @retrun false -> Sikertelen epites
     */
    @Override
    public boolean addFonal(Fonal f){
        //szkeletonlogMethodEntry(this, "addFonal");	///Szkeleton kiiratas fuggveny kezdetekor
        boolean sikerult = false;
		if(osszekoto.size() == 0)
        {
            osszekoto.add(f);
            sikerult = true;
        }
        //szkeleton.logMethodEntry(f, "getTartozik");	///Szkeleton kiiratas fuggveny kezdetekor
		Gombasz temp=f.getTartozik();
        //szkeleton.logMethodExit(f, "Gombasz");			///Szkeleton kiiratas fuggveny vegen
		if(tudEpulni(temp) ){                    ///Ha tud gombatestet is epit
			gombaTestEpul(f.getTartozik());
		}
                                
        //szkeletonlogMethodExit(this, sikerult);			///Szkeleton kiiratas fuggveny vegen
		return sikerult;

	} 






    /*
     * @brief Elszakitj a tektonon levo osszes fonalat
     */
    @Override
    private void mindenFonalElszakad(){
        //szkeletonlogMethodEntry(this, "mindenFonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
		for(Fonal elem : osszekoto){
            //szkeleton.logMethodEntry(this, "fonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
			fonalElszakad(elem);
            //szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
		}
        //szkeletonlogMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



    /*
     * @brief Elszakitja a tektonon levo adott fonalat
     * @param fonal -> Elszakitani kivant fonal
     */
    @Override
	public void fonalElszakad(Fonal fonal){
        //szkeletonlogMethodEntry(this, "fonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
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
        //szkeleton.logMethodEntry(fonal, "getTartozik");
        Gombasz gombasz = fonal.getTartozik();
        //szkeleton.logMethodExit(fonal, "Gombasz");
		gombasz.elszakadasDfsKezeles();                          
        //szkeletonlogMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}


    /*
     * @brief Szeteseskor szinten egy Egyfonalas tektont hozz letre
     * @return Letrehozott tekton
     */
  @Override
    private Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
       // //szkeletonlogMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        ////szkeletonlogMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
        return new Egyfonalas(spo,szom);
    }

    

}
