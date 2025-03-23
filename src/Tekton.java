
import java.util.ArrayList;
import java.util.Random;

/*
 * @class Tekton
 * @brief A egy-egy tekton adatainak taroljasa, es a tekton esemenyeinek kezeleset vegzi.
 */
public class Tekton
{
	Gombatest gombatest;			///Tarolja ha van gombatest az adott tektonon
	ArrayList<Spora> sporak;		///Tarolja a sporakat a tektonon
	ArrayList<Fonal> osszekoto;		///Tarolja hogy milyen fonalak vannak a tektonon
	ArrayList<Tekton> szomszed;		///Tarolja a tektonok szomszedait
	int eletkor;					///Tarolja a tektonok eletkorat (noveli a tektonok szetesesenek eselyet)
	int pluszPont;					///Tarolja hogy az adott tektonon epitett gombatest hany plusz pontot er
	int id;							///Tarolja a tekton id-jat


	/*
	 * @brief Konstruktor
	 * @param spo -> A tekton spora listaja (tekton szetesesekor a tektonon maradnak sporak)
	 * @param szom -> A tekton szomszed listaja. Megadja a tekton szomszedait
	 */
	Tekton(ArrayList<Spora> spo, ArrayList<Tekton> szom ){
		this.gombatest=null;
		this.sporak=spo;
		this.osszekoto=new ArrayList<Fonal>();
		this.szomszed=szom;
		eletkor=1;
		this.id=-1;
		Random random = new Random();
		pluszPont=random.nextInt(5);
		
	}


	/*
	* @brief Parameter nelkuli konstruktor teszteleshez
	*/
	Tekton(){
		gombatest=new Gombatest();
		sporak=new ArrayList<Spora>();
		osszekoto=new ArrayList<Fonal>();
		szomszed=new ArrayList<Tekton>();
		eletkor=1;
		pluszPont=0;
		id=-1;

	}






	/*
 	* @brief Setter
	* @param idd -> A beallitani kivant id
 	*/
	void setId(int idd){
		szkeleton.logMethodEntry(this, "setId");   ///Szkeleton kiiratas fuggveny kezdetekor
		id=idd;
		szkeleton.logMethodExit(this, "");		///Szkeleton kiiratas fuggveny vegen
	}








	/*
	 * @brief Getter
	 * @return Visszaadja a tekton id-jat
	 */
	int getId(){

		szkeleton.logMethodEntry(this, "getId");	///Szkeleton kiiratas fuggveny kezdetekor
		szkeleton.logMethodExit(this, id);			///Szkeleton kiiratas fuggveny vegen
		return id;
		
	}



	/*
	 * @brief A fuggveny megnezi/eldonti hogy az adott tekton az eletkora, es a random faktor alapjan szetessen-e
	 * @return true -> szetesik
	 * @return false -> nem esik szet 
	 */
	boolean torikE(){

		szkeleton.logMethodEntry(this, "torikE");	///Szkeleton kiiratas fuggveny kezdetekor

		int toresarany=eletkor*5+10;	///toresre az esely: (eletkor*5 + kezdeti esely). Azt jelenti hogy kezdeti esely, koronkent 5%-al no.
		Random random = new Random();	
		int toresEsely=random.nextInt(100);	///tenyleges toresei esely: (toresre az esely / 100)
		if(toresEsely>=toresarany){

			szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen
			
			return true;
		}
		szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen

		return false;
	}



	/*
	 * @brief Minden tektonon levo fonalat elszakitjuk, szeteseskor
	 */
	void mindenFonalElszakad(){

		szkeleton.logMethodEntry(this, "mindenFonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
		
		for(Fonal elem : osszekoto){		///Minden fonalunkra meghivjuk az elszakadast
			fonalElszakad(elem);
		}

		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



	/*
	 * @brief Letrehozzuk a listat , ami majd a szeteseskor letrejott uj tekton szomszedait fogja tartalmazni. A szomszedokat random valasztjuk ki.
	 * @return uj -> Az uj tektonnak beallitani kivant szomszed lista.
	 */
	ArrayList<Tekton> ujTektonSzomszedListajanakBeallitasa(){

		szkeleton.logMethodEntry(this, "ujTektonSzomszedListajanakBeallitasa");	///Szkeleton kiiratas fuggveny kezdetekor

		ArrayList<Tekton>uj=new ArrayList<>();
		for(Tekton elem:szomszed){								///Minden szomszedon vegig megyunk
			Random random = new Random();		
			int szomszedEsely=random.nextInt(100);			
			
			if(szomszedEsely%2==0){							///50% eselyel hozza adja az adott szomszedot az uj tekton szomszed listajaba
				uj.add(elem);
			}
		}

		szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen

		return uj;											///Ha nem adunk hozza semmit, akkor az ures listat adjuk vissza
	}







	/*
	* @brief Hozzaadunk egy szomszedot a szomszed listahoz
	*/
	void szomszedHozzaadasa(Tekton a){
		szkeleton.logMethodEntry(this, "szomszedHozzaadasa");	///Szkeleton kiiratas fuggveny kezdetekor

		szomszed.add(a);

		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}


	/*
	 * @brief A kapott tekton szomszed listajahoz hozza adjuk a kapott uj tekton. Beallitjuk hogy a lista minden elemenek szomszedja legyen az uj tekton.
	 */
	void ujTektonSzomszedainakListainakBeallitasa(ArrayList<Tekton> szomszedok1, Tekton uj){

		szkeleton.logMethodEntry(this, "ujTektonSzomszedainakListainakBeallitasa");	///Szkeleton kiiratas fuggveny kezdetekor

		for(Tekton egyszomszed:szomszedok1){
			egyszomszed.szomszedHozzaadasa(uj);
		}

		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}


	/*
	 * @brief Eltavolitja a szomszed listabol a megadott indexu elemet
	 * @param index -> torolni kivan index
	 */
	void szomszedTorol(int index){
		szkeleton.logMethodEntry(this, "szomszedTorol");	///Szkeleton kiiratas fuggveny kezdetekor
		szomszed.remove(index);
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}




	/*
	 * @brief Getter
	 * @return szomszed -> vissza adja a szomszed listat
	 */
	ArrayList<Tekton> getSzomszed(){
		szkeleton.logMethodEntry(this, "getSzomszed");	///Szkeleton kiiratas fuggveny kezdetekor
		szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen
		return szomszed;
	}


	/*
	 * @brief Ezen tekton szomszed listajabol veletlenszeruen kivalasztunk elemeket, amik a szomszedaik maradnak, a tobbi szomszedot toroljuk
	 */
	void ujSzomszedaimBeallaitasa(){
		szkeleton.logMethodEntry(this, "ujSzomszedaimBeallaitasa");	///Szkeleton kiiratas fuggveny kezdetekor

		ArrayList<Tekton>uj=new ArrayList<>();
		for(Tekton elem:szomszed){									///Kivalasztunk a szomszedok kozul veletlenszeruen elemeket
			Random random = new Random();
			int szomszedEsely=random.nextInt(100);
			if(szomszedEsely%2==0){
				uj.add(elem);										///A kivalasztott elemeket hozza eltaroljuk
			}

		}
		for(Tekton egyszomszed:szomszed){								///Vegig megyunk a szomszedos tektonokon
			int index=0;
			int keresett=1000;
			for(Tekton szomszedSzomszedja:egyszomszed.getSzomszed()){			///
				if(szomszedSzomszedja==this && !uj.contains(egyszomszed)){
					keresett=index;
				}
				index++;
			}
			if(keresett!=1000){
				egyszomszed.szomszedTorol(keresett);
			}
		}

		szomszed=uj;

		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}
		






	




Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
	szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	///Szkeleton kiiratas fuggveny kezdetekor
	szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
	return new Tekton(spo,szom);
}





	Tekton tores(){
		szkeleton.logMethodEntry(this, "tores");	///Szkeleton kiiratas fuggveny kezdetekor

		//ha szettorik a tekton
		if(torikE()){
			//ha a tekton van fonal
			if(osszekoto.size()!=0 ||osszekoto!=null){
				mindenFonalElszakad();
			}
			ArrayList<Spora> ujSporak=new ArrayList<>();
			ujSporak=ujTektonSporakListaja();			///uj tektonnak beallitjuk a soporait
			ArrayList<Tekton>ujSzomszedok=new ArrayList<>();
			ujSzomszedok=ujTektonSzomszedListajanakBeallitasa();			//uj tekton szomszedainak beallitasa

			Tekton ujTekton=ujTektonLetrehozasa(ujSporak,ujSzomszedok);	//letrehozzuk az uj tektont, szomszedainak megadasa
			sajatSporaimBeallitasa(ujSporak);														//beallitom a sajat sporaimat
			ujSzomszedaimBeallaitasa();																//beallitja az en uj szomszedaimat, mindket oldalról torlom azokat akik nem a szomszedaim
			ujTektonSzomszedainakListainakBeallitasa(ujSzomszedok, ujTekton);						//az uj tekton szomszedainak szomszed listajahoz hozza adjuk az uj tektont
			eletkor=1;

			szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
			return ujTekton;	
		}

		szkeleton.logMethodExit(this, "null");			///Szkeleton kiiratas fuggveny vegen
		return null;
	}















	ArrayList<Spora> ujTektonSporakListaja(){
		szkeleton.logMethodEntry(this, "ujTektonSporakListaja");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Spora>uj=new ArrayList<>();
		Random random = new Random();
		if(sporak.size()!=0){
			int sporaEsely=random.nextInt(sporak.size());
			for(int i=0;i<sporaEsely;i++){
				uj.add(sporak.get(i));	
			}
			szkeleton.logMethodExit(this, "ArrayList<Spora>");			///Szkeleton kiiratas fuggveny vegen
			return uj;
		}
		szkeleton.logMethodExit(this, "null");			///Szkeleton kiiratas fuggveny vegen
		return null;
	}


	void sajatSporaimBeallitasa(ArrayList<Spora> ujSporaja){
		szkeleton.logMethodEntry(this, "sajatSporaimBeallitasa");	///Szkeleton kiiratas fuggveny kezdetekor

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
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
		
	}







	//elszakít egy fonalat
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
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
	}




	int hanyFonalaVanGombasznak(Gombasz g){
		szkeleton.logMethodEntry(this, "hanyFonalaVanGombasznak");	///Szkeleton kiiratas fuggveny kezdetekor
		int db=0;
		for(Fonal elem:osszekoto){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		szkeleton.logMethodExit(this, db);			///Szkeleton kiiratas fuggveny vegen	
		return db;
	}



	int hanySporajaVanGombasznak(Gombasz g){
		szkeleton.logMethodEntry(this, "hanySporajaVanGombasznak");	///Szkeleton kiiratas fuggveny kezdetekor
		int db=0;
		for(Spora elem:sporak){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		szkeleton.logMethodExit(this, db);			///Szkeleton kiiratas fuggveny vegen	
		return db;
	}




	boolean tudEpulni(Gombasz g){
		szkeleton.logMethodEntry(this, "tudEpulni");	///Szkeleton kiiratas fuggveny kezdetekor
		if(hanyFonalaVanGombasznak(g)>=1 && hanySporajaVanGombasznak(g)>=5 && gombatest==null ){
			szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen	
			return true;
		}
		szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen	
		return false;
	}




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
		szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen	
		return true;
	} 




	//Vissza adja az összes olyan szomszédos tektont ahová megy fonal, gondolom bogarak mozgatasa miatt ////////////////////////////////
	ArrayList<Tekton> fonalKeres(){
		szkeleton.logMethodEntry(this, "fonalKeres");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){
			if(!tektonok.contains(elem.getHova())){
				tektonok.add(elem.getHova());
			}
		}
		szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen	
		return tektonok;		
	}








	///vissza adja az összes olyan tektont, ahova egy adott gombasz fonalai mennek
	ArrayList<Tekton> fonalKeres(Gombasz g)	{

		szkeleton.logMethodEntry(this, "fonalKeres");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){
			if(!tektonok.contains(elem.getHova()) && elem.getTartozik().equals(g)){
				tektonok.add(elem.getHova());
			}
		}
		szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen	
		return tektonok;

	}



	ArrayList<Fonal> getFonalLista(){
		szkeleton.logMethodEntry(this, "getFonalLista");	///Szkeleton kiiratas fuggveny kezdetekor
		return osszekoto;
		szkeleton.logMethodExit(this, "ArrayList<Fonal>");			///Szkeleton kiiratas fuggveny vegen	
	}
	






	void addSpora(Spora s){
		szkeleton.logMethodEntry(this, "addSpora");	///Szkeleton kiiratas fuggveny kezdetekor
		sporak.add(s);
		if(tudEpulni(s.getTartozik()) ){
			gombaTestEpul(s.getTartozik());
		}
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}




	void epitoanyagSporaEltunik(Gombasz g){
		//az adott jatekos utolso 5 sporajat levesszuk a tektonrol
		szkeleton.logMethodEntry(this, "epitoanyagSporaEltunik");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Integer> indexek=new ArrayList<>();											
		for(int i=sporak.size()-1;i>=0 ;i--){
			if(sporak.get(i).getTartozik().equals(g)){
				indexek.add(i);
			}
		}
		for(int i=0;i<indexek.size();i++){
			sporak.remove(indexek.get(i));
		}
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}




	void gombaTestEpul(Gombasz g){
		szkeleton.logMethodEntry(this, "gombaTestEpul");	///Szkeleton kiiratas fuggveny kezdetekor
		Gombatest uj=new Gombatest(this, g);		///faszom tudja hogyan van
		setGombatest(uj);
		g.gombatestHozzaad(uj);
		epitoanyagSporaEltunik(g);		
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen														
	}






	void setGombatest(Gombatest g){			//lehet neki null pointert??? 
		szkeleton.logMethodEntry(this, "setGombatest");	///Szkeleton kiiratas fuggveny kezdetekor
		this.gombatest=g;
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
	}







   void addSzomszed(Tekton t){
	szkeleton.logMethodEntry(this, "addSzomszed");	///Szkeleton kiiratas fuggveny kezdetekor
	szomszed.add(t);
	szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
   }




	Spora sporatEszik(){
		szkeleton.logMethodEntry(this, "sporatEszik");	///Szkeleton kiiratas fuggveny kezdetekor
		if(sporak.size()!=0){
			Spora uj=sporak.get(sporak.size()-1);
			sporak.remove(sporak.size()-1);
			szkeleton.logMethodExit(this, "Spora");			///Szkeleton kiiratas fuggveny vegen	
			return uj;
		}
		szkeleton.logMethodExit(this, "null");			///Szkeleton kiiratas fuggveny vegen	
		return null;
	}



	void setEletkorNoveles(){
		szkeleton.logMethodEntry(this, "sporatEszik");	///Szkeleton kiiratas fuggveny kezdetekor
		eletkor++;
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
	}



	void setEletkorMegadas(int szam){
		szkeleton.logMethodEntry(this, "setEletkorMegadas");	///Szkeleton kiiratas fuggveny kezdetekor
		eletkor=szam;
		szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
	}



 
}