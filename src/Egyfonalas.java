import java.util.ArrayList;

public class Egyfonalas extends Tekton
{
    boolean lehet;

    Egyfonalas(ArrayList<Tekton> szom){
        super(szom);
        lehet=true;
    }

    boolean lehetFonalatEpiteni()
    {
        return lehet;
    }
    
    void setLehet(Boolean leh){
        this.lehet=leh;
    }

    @Override
    boolean  addFonal(Fonal f){
            if(lehet){
            osszekoto.add(f);
            if(hanyFonalaVanGombasznak(f.getTartozik())>=1 && hanySporajaVanGombasznak(f.getTartozik())>=5 ){
                gombaTestEpul(f.getTartozik());
            }
        }
            lehet=false;
            return lehet;
	}

    @Override
    void mindenFonalElszakad(){
		ArrayList<Gombasz> torlendoFonalGombaszai=new ArrayList<>();
				for (Fonal elem : osszekoto) {										//Kigyujtjuk hogy mely gombaszokhoz kell meghívni az elszakadas kezelest							
					if(!torlendoFonalGombaszai.contains(elem.getTartozik())) {
						torlendoFonalGombaszai.add(elem.getTartozik());
					}
				}
				//meghivjuk az elszakadas kezelest minden gombasznal
				for (Gombasz elem : torlendoFonalGombaszai) {
					elem.elszakadasDfsKezeles();
                    lehet=true;					//dfs gondolom majd torli a fonalakat a fonalElszakad fuggvenemmel, ami nem latszik a szekvencia diagrammon
				}
	}

    @Override
    //elszakít egy fonalat
	void fonalElszakad(Fonal fonal){
		for (Fonal elem : osszekoto) {
			if(elem==fonal || elem.equals(fonal)){
				osszekoto.remove(elem);
                lehet=true;
			}
		}
	}




}
