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
        nemHalMegLista=new ArrayList<Fonal>();
    }

     /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    public EletbenTarto(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
        nemHalMegLista=new ArrayList<Fonal>();
    }

    /*
     * @brief Elszakit egy fonal osszekottetest ket tekton kozott. Eloszor lementi a torlendo fonalat.
     */
    @Override
    public void fonalElszakad(Fonal fonal){
        nemHalMegLista.add(fonal);
		Tekton hova = fonal.getHova();
        Gombasz gombasz = fonal.getTartozik();
        this.osszekoto.remove(fonal);
        ArrayList<Fonal> fonalak = hova.getOsszekoto();

        for (Fonal fonali : fonalak) {
            if (fonali.getHova() == this && fonal.getTartozik() == gombasz) {
                hova.osszekoto.remove(fonali);
				break;
            }
        }
    	gombasz.elszakadasDfsKezeles();
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
                Tekton hova= nemHalMegLista.get(i).getHova();

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
    
    protected  void mindenFonalElszakad(){
		for(Fonal elem : osszekoto){
			fonalElszakad(elem);
		}
        nemHalMegListaTorles();
	}
     /*
     * @brief Szeteseskor szinten egy Eletbentarto tektont hozz letre
     * @return Letrehozott tekton
     */
  
    protected  Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
      return new EletbenTarto(spo,szom);
    }

}
