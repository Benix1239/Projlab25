import java.util.ArrayList;
/*
 * @class EletbenTarto class
 * @brief Az EletbenTarto tektonokat kezeli (Ennek a tekton eletben tartja a gombatesthez nem kapcsolodo fonalakat)
 */
public class EletbenTarto extends Tekton {
    private ArrayList<Fonal> nemHalMegLista; ///A majdan feltamasztani kivant fonalak listaja

    /*
     * @brief Parameter nelkuli konstruktor
     */
     public EletbenTarto(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
        nemHalMegLista=new ArrayList<>(Fonal);
    }

     /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    public EletbenTarto(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
        nemHalMegLista=new ArrayList<>(Fonal);
    }

    /*
     * @brief Elszakit egy fonal osszekottetest ket tekton kozott. Eloszor lementi a torlendo fonalat.
     */
    @Override
    public void fonalElszakad(Fonal fonal){
        nemHalMegLista.add(fonal);
		////szkeleton.logMethodEntry(this, "fonalElszakad");
		//szkeleton.logMethodEntry(fonal, "getHova");
		Tekton hova = fonal.getHova();
		//szkeleton.logMethodExit(fonal, "Hova");
		//szkeleton.logMethodEntry(fonal, "getTartozik");
        Gombasz gombasz = fonal.getTartozik();
        //szkeleton.logMethodExit(fonal, "Gombasz");
        this.osszekoto.remove(fonal);
		//szkeleton.logMethodEntry(hova, "getOsszekoto");
        ArrayList<Fonal> fonalak = hova.getOsszekoto();
		//szkeleton.logMethodExit(hova, "Osszekoto[]");

        for (Fonal fonali : fonalak) {
            if (fonali.getHova() == this && fonal.getTartozik() == gombasz) {
                hova.osszekoto.remove(fonali);
				break;
            }
        }
		//szkeleton.logMethodEntry(gombasz, "elszakadasDfsKezeles");
    	gombasz.elszakadasDfsKezeles();
		//szkeleton.logMethodExit(gombasz, "");
		

	}

    /*
     * @brief Vissza allitja az elszakitott fonalakat
     */
    @Override
    public void megSeHalMeg(){

        if(nemHalMegLista.size()>0){

            for(int i = 0 ;i < nemHalMegLista.size();i++){

                osszekoto.add(nemHalMegLista.get(i));
                Gombasz g = nemHalMegLista.get(i).getTartozik();
                Tekton hova= nemHalMeglista.get(i).getHova();

                hova.addFonal(new Fonal(this, g));
                
            
            }

         }
         nemHalMegLista.clear();
    }

    /*
     * @brief Torli a visszaallitando fonalak listajat
     */
    @Override
    void nemHalMegListaTorles(){
        nemHalMegLista.clear();
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
        nemHalMegListaTorles()

	}




     /*
     * @brief Szeteseskor szinten egy Eletbentarto tektont hozz letre
     * @return Letrehozott tekton
     */
  @Override
  private Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
       //szkeletonlogMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
      ////szkeletonlogMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
      return new EletbenTarto(spo,szom);
  }


}
