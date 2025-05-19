package backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/*
 * @class EletbenTarto class
 * @brief Az EletbenTarto tektonokat kezeli (Ennek a tekton eletben tartja a gombatesthez nem kapcsolodo fonalakat)
 */
public class EletbenTarto extends Tekton {
    private Set<Fonal> nemHalMegLista; ///A majdan feltamasztani kivant fonalak listaja

    /*
     * @brief Parameter nelkuli konstruktor
     */
     public EletbenTarto(){
        super();    ///Az ososztaly parameter nelkuli konstruktorat hivja
        nemHalMegLista=new HashSet<Fonal>();
    }

     /*
     * @brief Parameteres konstruktor
     * @param spo -> A tekton spora listaja
     * @szom -> A tekton szomszed listaja
     */
    public EletbenTarto(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
        nemHalMegLista=new HashSet<Fonal>();
    }

    /*
     * @brief Elszakit egy fonal osszekottetest ket tekton kozott. Eloszor lementi a torlendo fonalat.
     */
    @Override
    public void fonalElpusztit(Gombasz g){

        
		ArrayList<Fonal> tomb=new ArrayList<>();

		for(Fonal f : osszekoto)
		{
			tomb.add(f);
            nemHalMegLista.add(f);
		}
		
		for(Fonal fonal : tomb)
		{
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
		}
		
	}

    /*
     * @brief Vissza allitja az elszakitott fonalakat
     */
    @Override
    public void megSeHalMeg(){

        if(nemHalMegLista.size()>0){

            ArrayList<Fonal> nemHalMegLista2 = new ArrayList<>(nemHalMegLista);

            for(int i = 0 ;i < nemHalMegLista2.size();i++){

                osszekoto.add(nemHalMegLista2.get(i));
                Gombasz g = nemHalMegLista2.get(i).getTartozik();
                Tekton hova= nemHalMegLista2.get(i).getHova();

                hova.addFonal(new Fonal(this, g));
            }
        }
        nemHalMegLista.clear();
    }

    @Override
    public void addMegseHalMeg(Fonal f) {
        nemHalMegLista.add(f);
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
