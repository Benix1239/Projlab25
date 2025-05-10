package backend;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * @class Egyfonalas
 * @brief Az egyfonalas tektonokat kezeli. (Erre a tektonra csak egy fonalt lehet letenni)
 */
public class Egyfonalas extends Tekton implements Serializable 
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
        boolean sikerult = false;
		if(osszekoto.size() == 0)
        {
            osszekoto.add(f);
            sikerult = true;
        }
		Gombasz temp=f.getTartozik();
		if(tudEpulni(temp) ){                    ///Ha tud gombatestet is epit
			gombaTestEpul(f.getTartozik());
		}
		return sikerult;
	} 
    /*
     * @brief Elszakitj a tektonon levo osszes fonalat
     */
    protected  void mindenFonalElszakad(){
		for(Fonal elem : osszekoto){
			fonalElszakad(elem);
		}
	}

    /*
     * @brief Elszakitja a tektonon levo adott fonalat
     * @param fonal -> Elszakitani kivant fonal
     */
    @Override
	public void fonalElszakad(Fonal fonal){
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
        Gombasz gombasz = fonal.getTartozik();
		gombasz.elszakadasDfsKezeles();                          
	}


    /*
     * @brief Szeteseskor szinten egy Egyfonalas tektont hozz letre
     * @return Letrehozott tekton
     */
 
    protected  Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        return new Egyfonalas(spo,szom);
    }

    

}
