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

    boolean getLehetFonalatEpiteni()
    {
        return lehet;
    }
    
    void setLehet(Boolean leh){
        this.lehet=leh;
    }





    @Override
    boolean addFonal(Fonal f)
	{
	
		for(Fonal elem:osszekoto){
			if(elem.getTartozik().equals(f.getTartozik())){
				return false;
			}
		}
		
		osszekoto.add(f);
		if(tudEpulni(f.getTartozik()) ){
			gombaTestEpul(f.getTartozik());
		}
        lehet=false;
		return true;

	} 







    @Override
    void mindenFonalElszakad(){
		for(Fonal elem : osszekoto){
			fonalElszakad(elem);
		}
        lehet=true;
	}



    @Override
	void fonalElszakad(Fonal fonal){
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
	}



  @Override
    Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
        return new Egyfonalas(spo,szom);
    }



}
