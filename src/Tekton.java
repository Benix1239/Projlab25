
import java.util.ArrayList;
import java.util.Random;

public class Tekton
{
	Gombatest gombatest;
	ArrayList<Spora> sporak;
	ArrayList<Fonal> osszekoto;
	ArrayList<Tekton> szomszed;
	int eletkor;
	int pluszPont;

	//Paraméter nélküli konstruktor tesztekhez
	Tekton(Gombatest test, ArrayList<Spora> spo, ArrayList<Fonal> ossze, ArrayList<Tekton> szom /*, int plusz*/ ){
		this.gombatest=test;
		this.sporak=spo;
		this.osszekoto=ossze;
		this.szomszed=szom;
		eletkor=1;
		/*this.pluszPont = plusz 
		Random random = new Random();
		pluszPont=random.nextInt(5);
		*/
	}

	//megnezzuk hogy az esely alapjan tenyleg szettorik-e a tekton
	boolean torikE(int toresarany){
		Random random = new Random();
		int toresEsely=random.nextInt(100);
		if(toresEsely>=toresarany) return true;
		return false;
	}

	void mindenFonalElszakad(){
		ArrayList<Gombasz> torlendoFonalGombaszai=new ArrayList<>();
				for (Fonal elem : osszekoto) {										//Kigyujtjuk hogy mely gombaszokhoz kell meghívni az elszakadas kezelest							
					if(!torlendoFonalGombaszai.contains(elem.getTartozik())) {
						torlendoFonalGombaszai.add(elem.getTartozik());
					}
				}
				//meghivjuk az elszakadas kezelest minden gombasznal
				for (Gombasz elem : torlendoFonalGombaszai) {
					elem.elszakadasDfsKezeles();					//dfs gondolom majd torli a fonalakat a fonalElszakad fuggvenemmel, ami nem latszik a szekvencia diagrammon
				}
	}

	//uj tekton szomszed listaja
	ArrayList<Tekton> ujTektonSzomszedListajanakBeallitasa(){
		ArrayList<Tekton>uj=new ArrayList<>();
		for(Tekton elem:szomszed){
			Random random = new Random();
			int szomszedEsely=random.nextInt(100);
			
			if(szomszedEsely%2==0){
				uj.add(elem);
			}

		}
		return uj;
	}

	void ujSzomszedaimBeallaitasa(){
		ArrayList<Tekton>uj=new ArrayList<>();
		for(Tekton elem:szomszed){
			Random random = new Random();
			int szomszedEsely=random.nextInt(100);
			
			if(szomszedEsely%2==0){
				uj.add(elem);
			}

		}
		szomszed=uj;
	}
		

	Tekton tores(int toresarany)
	{
		//ha szettorik a tekton
		if(torikE(toresarany)){
			//ha a tekton van fonal
			if(osszekoto.size()!=0 ||osszekoto!=null){
				mindenFonalElszakad();
			}
			ArrayList<Spora> ujSporak=new ArrayList<>();
			ujSporak=ujTektonSorakListaja();			
			Tekton ujTekton=new Tekton(null,ujSporak,null,ujTektonSzomszedListajanakBeallitasa());
			sajatSporaimBeallitasa(ujSporak);
			ujSzomszedaimBeallaitasa();
			return ujTekton;	
		}
		return null;
	}

	ArrayList<Spora> ujTektonSorakListaja(){
		ArrayList<Spora>uj=new ArrayList<>();
		Random random = new Random();
		if(sporak.size()!=0){
			int sporaEsely=random.nextInt(sporak.size());
			for(int i=0;i<sporaEsely;i++){
				uj.add(sporak.get(i));	
			}
			return uj;
		}
		return null;
	}

	void sajatSporaimBeallitasa(ArrayList<Spora> ujSporaja){
		ArrayList<Spora>uj=new ArrayList<>();
		Random random = new Random();
		if(sporak.size()!=0){
			int sporaEsely=random.nextInt(sporak.size());
			for(int i=0;i<sporaEsely;i++){
				if(!ujSporaja.contains(sporak.get(i)))
				uj.add(sporak.get(i));	
			}
			sporak= uj;
		}
		
	}

	//elszakít egy fonalat
	void fonalElszakad(Fonal fonal){
		for (Fonal elem : osszekoto) {
			if(elem==fonal || elem.equals(fonal)){
				osszekoto.remove(elem);
			}
		}
	}

	int hanyFonalaVanGombasznak(Gombasz g){
		int db=0;
		for(Fonal elem:osszekoto){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		return db;
	}

	int hanySporajaVanGombasznak(Gombasz g){
		int db=0;
		for(Spora elem:sporak){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		return db;
	}

	boolean tudEpulni(Gombasz g){
		boolean tud=false;
		if(hanyFonalaVanGombasznak(g)>=1 && hanySporajaVanGombasznak(g)>=5 ){
			return tud;
		}
		return tud;
	}


	void addFonal(Fonal f)
	{
		osszekoto.add(f);
		if(tudEpulni(f.getTartozik()) ){
			gombaTestEpul(f.getTartozik());
		}
	}

	//Vissza adja az összes olyan szomszédos tektont ahová megy fonal
	ArrayList<Tekton> fonalKeres()
	{
		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){
			if(!tektonok.contains(elem.getHova())){
				tektonok.add(elem.getHova());
			}
		}
		return tektonok;		
	}

	///vissza adja az összes olyan tektont, ahova egy adott gombasz fonalai mennek
	ArrayList<Tekton> fonalKeres(Gombasz g)
	{
		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){
			if(!tektonok.contains(elem.getHova()) && elem.getTartozik().equals(g)){
				tektonok.add(elem.getHova());
			}
		}
		return tektonok;

	}
	

	void addSpora(Spora s)
	{
		sporak.add(s);
		if(tudEpulni(s.getTartozik()) ){
			gombaTestEpul(s.getTartozik());
		}
	}

	void gombaTestEpul(Gombasz g)
	{
		Gombatest uj=new Gombatest(10 , null , this , g);		///faszom tudja hogyan van
		setGombatest(uj);
		sporak=null;											///az enemy spora is eltunik??
																///szakad el valami ??? 
	}

	void setGombatest(Gombatest g){			//lehet neki null pointert??? 
		this.gombatest=g;
	}

   void addSzomszed(Tekton t)
   {
	szomszed.add(t);
   }

	Spora sporatEszik()
	{
		if(sporak.size()!=0){
			Spora uj=sporak.get(sporak.size()-1);
			sporak.remove(sporak.size()-1);
			return uj;
		}
		return null;
	}

 
}