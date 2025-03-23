import java.util.ArrayList;

public class Egyfonalas extends Tekton
{
    boolean lehet;

    Egyfonalas(){
        super();
        lehet=true;
    }

    Egyfonalas(ArrayList<Spora> spo, ArrayList<Tekton> szom){
        super(spo,szom);
        lehet=true;
    }



    boolean getLehetFonalatEpiteni(){
        szkeleton.logMethodEntry(this, "getLehetFonalatEpiteni");	///Szkeleton kiiratas fuggveny kezdetekor
        szkeleton.logMethodExit(this, lehet);			///Szkeleton kiiratas fuggveny vegen	
        return lehet;
    }
    
    void setLehet(Boolean leh){
        szkeleton.logMethodEntry(this, "setLehet");	///Szkeleton kiiratas fuggveny kezdetekor
        this.lehet=leh;
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
    }



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
		if(tudEpulni(f.getTartozik()) ){
			gombaTestEpul(f.getTartozik());
		}
        lehet=false;

        szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen
		return true;

	} 







    @Override
    void mindenFonalElszakad(){
        szkeleton.logMethodEntry(this, "mindenFonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
		for(Fonal elem : osszekoto){
			fonalElszakad(elem);
		}
        lehet=true;
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



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
		lehet=true;
        szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



  @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
        szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
        return new Egyfonalas(spo,szom);
    }



}
