
import java.util.ArrayList;
import java.util.Random;

/* 
 * @class Tekton
 * @brief A egy-egy tekton adatainak taroljasa, es a tekton esemenyeinek kezeleset vegzi.
 */
public class Tekton
{
	private Gombatest gombatest;			///Tarolja ha van gombatest az adott tektonon
	private ArrayList<Spora> sporak;		///Tarolja a sporakat a tektonon
	private ArrayList<Fonal> osszekoto;		///Tarolja hogy milyen fonalak vannak a tektonon
	private ArrayList<Tekton> szomszed;		///Tarolja a tektonok szomszedait
	private int eletkor;					///Tarolja a tektonok eletkorat (noveli a tektonok szetesesenek eselyet)
	private int pluszPont;					///Tarolja hogy az adott tektonon epitett gombatest hany plusz pontot er
	private int id;							///Tarolja a tekton id-jat


	public ArrayList<Spora> getSporak(){
		return sporak;
	}

	/*
	 * @brief Konstruktor
	 * @param spo -> A tekton spora listaja (tekton szetesesekor a tektonon maradnak sporak)
	 * @param szom -> A tekton szomszed listaja. Megadja a tekton szomszedait
	 */
	public Tekton(ArrayList<Spora> spo, ArrayList<Tekton> szom ){
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
	public Tekton(){
		gombatest = null;
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
	public void setId(int idd){
		//szkeleton.logMethodEntry(this, "setId");   ///Szkeleton kiiratas fuggveny kezdetekor
		id=idd;
		//szkeleton.logMethodExit(this, "");		///Szkeleton kiiratas fuggveny vegen
	}








	/*
	 * @brief Getter
	 * @return Visszaadja a tekton id-jat
	 */
	public int getId(){

		////szkeleton.logMethodEntry(this, "getId");	///Szkeleton kiiratas fuggveny kezdetekor
		////szkeleton.logMethodExit(this, id);			///Szkeleton kiiratas fuggveny vegen
		return id;
		
	}



	/*
	 * @brief A fuggveny megnezi/eldonti hogy az adott tekton az eletkora, es a random faktor alapjan szetessen-e
	 * @return true -> szetesik
	 * @return false -> nem esik szet 
	 */
	private boolean torikE(){

		////szkeleton.logMethodEntry(this, "torikE");	///Szkeleton kiiratas fuggveny kezdetekor

		int toresarany=eletkor*3+10;	///toresre az esely: (eletkor*5 + kezdeti esely). Azt jelenti hogy kezdeti esely, koronkent 5%-al no.
		Random random = new Random();	
		int toresEsely=random.nextInt(100);	///tenyleges toresei esely: (toresre az esely / 100)
		if(toresEsely<=toresarany){

			////szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen
			
			return true;
		}
		////szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen

		return false;
	}



	/*
	 * @brief Minden tektonon levo fonalat elszakitjuk, szeteseskor
	 */
	private void mindenFonalElszakad(){

		////szkeleton.logMethodEntry(this, "mindenFonalElszakad");	///Szkeleton kiiratas fuggveny kezdetekor
		/*
		for(Fonal elem : osszekoto){		///Minden fonalunkra meghivjuk az elszakadast
			fonalElszakad(elem);
		}
*/
		for(int i=0;i<osszekoto.size();i++){
			fonalElszakad(osszekoto.get(i));
		}

		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



	/*
	 * @brief Letrehozzuk a listat , ami majd a szeteseskor letrejott uj tekton szomszedait fogja tartalmazni. A szomszedokat random valasztjuk ki.
	 * @return uj -> Az uj tektonnak beallitani kivant szomszed lista.
	 */
	private ArrayList<Tekton> ujTektonSzomszedListajanakBeallitasa(){

		////szkeleton.logMethodEntry(this, "ujTektonSzomszedListajanakBeallitasa");	///Szkeleton kiiratas fuggveny kezdetekor

		ArrayList<Tekton>uj=new ArrayList<>();
		for(Tekton elem:szomszed){								///Minden szomszedon vegig megyunk
			Random random = new Random();		
			int szomszedEsely=random.nextInt(100);			
			
			if(szomszedEsely%2==0){							///50% eselyel hozza adja az adott szomszedot az uj tekton szomszed listajaba
				uj.add(elem);
			}
		}

		////szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen

		return uj;											///Ha nem adunk hozza semmit, akkor az ures listat adjuk vissza
	}







	/*
	* @brief Hozzaadunk egy szomszedot a szomszed listahoz
	*/
	public void szomszedHozzaadasa(Tekton a){
		////szkeleton.logMethodEntry(this, "szomszedHozzaadasa");	///Szkeleton kiiratas fuggveny kezdetekor

		szomszed.add(a);

		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}


	/*
	 * @brief A kapott tekton szomszed listajahoz hozza adjuk a kapott uj tekton. Beallitjuk hogy a lista minden elemenek szomszedja legyen az uj tekton.
	 */
	private void ujTektonSzomszedainakListainakBeallitasa(ArrayList<Tekton> szomszedok1, Tekton uj){

		////szkeleton.logMethodEntry(this, "ujTektonSzomszedainakListainakBeallitasa");	///Szkeleton kiiratas fuggveny kezdetekor

		for(Tekton egyszomszed:szomszedok1){
			egyszomszed.szomszedHozzaadasa(uj);
		}

		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}


	/*
	 * @brief Eltavolitja a szomszed listabol a megadott indexu elemet
	 * @param index -> torolni kivan index
	 */
	public void szomszedTorol(int index){
		////szkeleton.logMethodEntry(this, "szomszedTorol");	///Szkeleton kiiratas fuggveny kezdetekor
		szomszed.remove(index);
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}




	/*
	 * @brief Getter
	 * @return szomszed -> vissza adja a szomszed listat
	 */
	public ArrayList<Tekton> getSzomszed(){
		////szkeleton.logMethodEntry(this, "getSzomszed");	///Szkeleton kiiratas fuggveny kezdetekor
		////szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen
		return szomszed;
	}


	/*
	 * @brief Ezen tekton szomszed listajabol veletlenszeruen kivalasztunk elemeket, amik a szomszedaik maradnak, a tobbi szomszedot toroljuk
	 */
	private void ujSzomszedaimBeallaitasa(){
		////szkeleton.logMethodEntry(this, "ujSzomszedaimBeallaitasa");	///Szkeleton kiiratas fuggveny kezdetekor

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
			for(Tekton szomszedSzomszedja:egyszomszed.getSzomszed()){			///Vegig megyunk a tektonok szomszedainak szomszedain
				if(szomszedSzomszedja==this && !uj.contains(egyszomszed)){		///Ha van olyan elem amit torolni szeretnenk, akkor az kigyultjuk
					keresett=index;
				}
				index++;
			}
			if(keresett!=1000){
				egyszomszed.szomszedTorol(keresett);							///A torlendo indexekhez tarozo elemeket toroljuk
			}
		}

		szomszed=uj;														///Beallitjuk az uj szomszedokat

		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}
		



/*
 * @brief Letre hozunk egy uj tektont , a tekton szetesesekor
 * @return Keletkezett uj tekton
 */
private Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
	////szkeleton.logMethodEntry(this, "ujTektonLetrehozasa");	/szkeleton kiiratas fuggveny kezdetekor
	////szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
	return new Tekton(spo,szom);
}




	/*
	 * @brief A tektonok szetesesenek teljes kezelese, amit koronkent meghivhatunk
	 * @return ujTekton -> A kesz uj tekton, ha ez szettorik.
	 */
	public Tekton tores(){
		//////szkeleton.logMethodEntry(this, "tores");	///Szkeleton kiiratas fuggveny kezdetekor

		//ha szettorik a tekton
		if(torikE()){											///Ha szettorik a tekton
			//ha a tekton van fonal
			if(osszekoto.size()!=0 ||osszekoto!=null){
				mindenFonalElszakad();							///Minden fonalat elszakitunk
			}
			ArrayList<Spora> ujSporak=new ArrayList<>();
			ujSporak=ujTektonSporakListaja();						///Uj tektonnak beallitjuk a soporait
			ArrayList<Tekton>ujSzomszedok=new ArrayList<>();
			ujSzomszedok=ujTektonSzomszedListajanakBeallitasa();			///Uj tekton szomszedainak beallitasa

			Tekton ujTekton=ujTektonLetrehozasa(ujSporak,ujSzomszedok);		///Letrehozzuk az uj tektont,megadjuk a szomszedait
			sajatSporaimBeallitasa(ujSporak);														///Beallitom a sajat sporaimat
			ujSzomszedaimBeallaitasa();
			////szkeleton.logMethodEntry(this, "ujTektonSzomszedainakListainakBeallitasa");	///Szkeleton kiiratas fuggveny kezdetekor																///Beallitja az en uj szomszedaimat, mindket oldalról torlom azokat akik nem a szomszedaim
			ujTektonSzomszedainakListainakBeallitasa(ujSzomszedok, ujTekton);						///Az uj tekton szomszedainak szomszed listajahoz hozza adjuk az uj tektont
			////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
			eletkor=1;

			////szkeleton.logMethodExit(this, "Tekton");			///Szkeleton kiiratas fuggveny vegen
			return ujTekton;	
		}

		////szkeleton.logMethodExit(this, "null");			///Szkeleton kiiratas fuggveny vegen
		return null;
	}




	/*
	 * @brief Megcsinalja a listat ami szeteseskor letrejott tekton sporait fogja tartalmazni
	 * @return uj -> Vissza adja a tombot ami a sporakat tartalmazza.
	 */
	private ArrayList<Spora> ujTektonSporakListaja(){
		////szkeleton.logMethodEntry(this, "ujTektonSporakListaja");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Spora>uj=new ArrayList<>();						
		Random random = new Random();								
		if(sporak.size()!=0){
			int sporaEsely=random.nextInt(sporak.size());				///Generalunk egy random szamot, ami max akkora mint ennek a tektonnak a spora listaja
			for(int i=0;i<sporaEsely;i++){								
				uj.add(sporak.get(i));									///Vegig megyunk ezen tekton spora listajan, es elso random szamu elemet kigyujtjuk, hogy atadhassuk
			}
			////szkeleton.logMethodExit(this, "ArrayList<Spora>");			///Szkeleton kiiratas fuggveny vegen
			return uj;
		}
		////szkeleton.logMethodExit(this, "null");			///Szkeleton kiiratas fuggveny vegen
		return null;
	}

	/*
	* @brief Ezen tekton szetesesekor a sporak beallitasa
	* @param ujSporaja -> Lista amit az uj tekton megkap, tehat ami tobbe nem lesz ezen tekton sporaja
	*/
	private void sajatSporaimBeallitasa(ArrayList<Spora> ujSporaja){
		////szkeleton.logMethodEntry(this, "sajatSporaimBeallitasa");	///Szkeleton kiiratas fuggveny kezdetekor

		ArrayList<Spora>uj=new ArrayList<>();
		if(sporak.size()!=0){
			for(Spora elem: sporak){
				if(!ujSporaja.contains(elem)){			///Kigyujtjuk a jelenlegi spora lista azon elemeit, ami az uj tekton spora listajanak nem eleme 
					uj.add(elem);
				}
			}
		}
		sporak=uj;									///Beallitjuk a spora listat
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
		
	}



	/*
	 * @brief  Elszakit egy fonalat
	 * @param fonal -> A fonal amit el akarunk szakitani
	 */
	public void fonalElszakad(Fonal fonal){
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
		////szkeleton.logMethodExit(this, "");
	}


	public void fonalElpusztit(Gombasz t){

		ArrayList<Fonal> tomb=new ArrayList<>();
		tomb=fonalKeres(t);

		for(int i=0;i<tomb.size();i++){
			fonalElszakad(tomb.get(i));
		}
	}






	/*
	 * @brief Meghatarozza hogy az adott tektonon hany daraab fonala van egy adott gombasznak
	 * @param g -> Gombasz akit vizsgalunk
	 * @return db -> Fonalak szama
	 */
	public int hanyFonalaVanGombasznak(Gombasz g){
		////szkeleton.logMethodEntry(this, "hanyFonalaVanGombasznak");	///Szkeleton kiiratas fuggveny kezdetekor
		int db=0;
		for(Fonal elem:osszekoto){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		////szkeleton.logMethodExit(this, db);			///Szkeleton kiiratas fuggveny vegen	
		return db;
	}


	/*
	 * @brief Meghatarozza hogy hany sporaja van az adott tektonon egy gombasznak
	 * @param g -> Gombasz
	 * @return db -> Sporak szama 
	 */
	private int hanySporajaVanGombasznak(Gombasz g){
		////szkeleton.logMethodEntry(this, "hanySporajaVanGombasznak");	///Szkeleton kiiratas fuggveny kezdetekor
		int db=0;
		for(Spora elem:sporak){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		////szkeleton.logMethodExit(this, db);			///Szkeleton kiiratas fuggveny vegen	
		return db;
	}



	/*
	 * @brief Meghatarozza hogy a gombasz tud-e gombatestet epiteni az adott tektonra
	 * @param g -> Gombasz
	 * @return true -> Tud epiteni
	 * @return false -> Nem tud epiteni
	 */
	private boolean tudEpulni(Gombasz g){
		////szkeleton.logMethodEntry(this, "tudEpulni");	///Szkeleton kiiratas fuggveny kezdetekor
		if(hanyFonalaVanGombasznak(g)>=1 && hanySporajaVanGombasznak(g)>=5 && gombatest==null ){	///Tudd epitani ha van legalabb egy fonala, 5 sporaja, es nincs meg gombatest a tektonon
			////szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen	
			return true;
		}
		////szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen	
		return false;
	}



	/*
	 * @brief Fonal lehelyezes a tektonra
	 * @param f -> Lehelyezni kivant fonal
	 * @return true -> Le tud tenni
	 * @return false -> Nem tud letenni
	 */
	public boolean addFonal(Fonal f){
		////szkeleton.logMethodEntry(this, "addFonal");	///Szkeleton kiiratas fuggveny kezdetekor
		for(Fonal elem:osszekoto){
			//szkeleton.logMethodEntry(elem, "getTartozik");	///Szkeleton kiiratas fuggveny kezdetekor
			boolean osszehasonlitas= elem.equals(f);
			//szkeleton.logMethodExit(elem, osszehasonlitas);			///Szkeleton kiiratas fuggveny vegen	
			if(osszehasonlitas){
				////szkeleton.logMethodExit(this, false);			///Szkeleton kiiratas fuggveny vegen	
				return false;									///Ha mar van egy tektonon fonala a gombasznak akkor nem tud tobbet letenni
			}
		}
		
		osszekoto.add(f);
		if(tudEpulni(f.getTartozik()) ){					///Megvizsgaljuk hogy ezutan a fonal letetel utan, meg tud-e ott epulni a gombatest
			gombaTestEpul(f.getTartozik());					///Ha meg tud epulni automatikusan megepul
		}
		////szkeleton.logMethodExit(this, true);			///Szkeleton kiiratas fuggveny vegen	
		return true;
	} 




	/*
	 * @brief Vissza adja hogy az adott tektonrol melyik tektonokra megy fonal
	 * @return tektonok -> Azon tektonok listaja ahova megy fonal a tektonrol
	 */
	public ArrayList<Tekton> fonalKeres(){
		////szkeleton.logMethodEntry(this, "fonalKeres");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){						///Fonalak listabol kigyujtjuk azokat az osszes tektont ahova fonal megy
			if(!tektonok.contains(elem.getHova())){
				tektonok.add(elem.getHova());
			}
		}
		////szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen	
		return tektonok;		
	}








	/*
	 * @brief Vissza adja hogy az adott tektonrol mely tektonokra megy egy adott gombasz fonala
	 * @param g -> Gombasz
	 * @return tektonok -> Tektonok listaja ahova a gombasz fonala megy 
	 */
	public ArrayList<Tekton> fonalKeres(Gombasz g)	{

		////szkeleton.logMethodEntry(this, "fonalKeres");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){
			if(!tektonok.contains(elem.getHova()) && elem.getTartozik().equals(g)){
				tektonok.add(elem.getHova());
			}
		}
		////szkeleton.logMethodExit(this, "ArrayList<Tekton>");			///Szkeleton kiiratas fuggveny vegen	
		return tektonok;

	}


	/*
	 * @brief Getter
	 * @return osszekoto -> Vissza adja a fonal listat
	 */
	public ArrayList<Fonal> getOsszekoto(){
		////szkeleton.logMethodEntry(this, "getOsszekoto");	///Szkeleton kiiratas fuggveny kezdetekor
		////szkeleton.logMethodExit(this, "ArrayList<Fonal>");			///Szkeleton kiiratas fuggveny vegen
		return osszekoto;
			
	}
	





	/*
	 * @brief Spora hozzaadasa a tektonhoz
	 * @param s -> Lehelyezett spora
	 */
	public void addSpora(Spora s){
		////szkeleton.logMethodEntry(this, "addSpora");	///Szkeleton kiiratas fuggveny kezdetekor
		sporak.add(s);
		if(tudEpulni(s.getTartozik()) ){			///Megnezzuk a spora lehelyezessel meg tud-e epulni gombatest
			//szkeleton.logMethodEntry(s, "getTartozik");	///Szkeleton kiiratas fuggveny kezdetekor
			Gombasz temp=s.getTartozik();
			//szkeleton.logMethodExit(s, "Gombasz");			///Szkeleton kiiratas fuggveny vegen
			gombaTestEpul(temp);			///Ha gombatest tud epulni, akkor megepul
		}
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



	/*
	 * @brief Gombatest epites utan a gombaszhoz tartozo sporakbol a legfelso 5 db eltunik a tektonrol
	 * @param g -> Gombasz aki gombatestet epit
	 */
	private void epitoanyagSporaEltunik(Gombasz g){
		//az adott jatekos utolso 5 sporajat levesszuk a tektonrol
		////szkeleton.logMethodEntry(this, "epitoanyagSporaEltunik");	///Szkeleton kiiratas fuggveny kezdetekor
		ArrayList<Integer> indexek=new ArrayList<>();											
		for(int i=sporak.size()-1;i>=0 ;i--){		
			if(sporak.get(i).getTartozik().equals(g)){		
				indexek.add(i);										///Kigyujtjuk hogy a gombaszhoz tartozo 5 spora hol talalhato a spora listaban
			}
		}
		for(int i=0;i<indexek.size();i++){
			sporak.remove(indexek.get(i));							///Kigyujtott indexeken levo sporak eltavolitasa
		}
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen
	}



	/*
	 * @brief Letrehozzuk/megepitjuk a gombatestett a tektonon
	 * @param g -> Melyik gombasz epit gombatestet
	 */
	private void gombaTestEpul(Gombasz g){
		////szkeleton.logMethodEntry(this, "gombaTestEpul");	///Szkeleton kiiratas fuggveny kezdetekor
		Gombatest uj=new Gombatest(this, g);		///Letre hozzuk a gombatestet
		setGombatest(uj);							///Beallitjuk a tekton gombatestjet
		//szkeleton.logMethodEntry(g, "gombatestHozzaad");	///Szkeleton kiiratas fuggveny kezdetekor
		g.gombatestHozzaad(uj);						///Gombasznak oda adjuk az uj gombatestjet
		//szkeleton.logMethodExit(g, "");			///Szkeleton kiiratas fuggveny vegen	
		epitoanyagSporaEltunik(g);					///Eltuntetjuk a sporakat mikbol epult a test
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen														
	}





	/*
	* @brief Setter
	* @param g -> Tektonnak beallitani kivant gombatest
	*/
	public void setGombatest(Gombatest g){			
		////szkeleton.logMethodEntry(this, "setGombatest");	///Szkeleton kiiratas fuggveny kezdetekor
		this.gombatest=g;
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
	}






	/*
	 * @brief Szomszed hozzaadasa a tektonhoz
	 * @param t -> Uj szomszed tekton
	 */
   public void addSzomszed(Tekton t){
	////szkeleton.logMethodEntry(this, "addSzomszed");	///Szkeleton kiiratas fuggveny kezdetekor
	szomszed.add(t);
	////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
   }



   /*
	* @brief A tekton legfelso sporajaval vissz terunk es toroljuk
	* @retrun uj -> Legfelso spora a tektonon
    */
	public Spora sporatEszik(){
		////szkeleton.logMethodEntry(this, "sporatEszik");	///Szkeleton kiiratas fuggveny kezdetekor
		if(sporak.size()!=0){
			Spora uj=sporak.get(sporak.size()-1);		///Legfelso spora kivalasztasa
			sporak.remove(sporak.size()-1);				///Spora torlese a tektonrol
			////szkeleton.logMethodExit(this, "Spora");			///Szkeleton kiiratas fuggveny vegen	
			return uj;									///Kivalasztott spora visszaadasa
		}
		////szkeleton.logMethodExit(this, "null");			///Szkeleton kiiratas fuggveny vegen	
		return null;
	}


	/*
	 * @brief Setter (tekton eletkoranak novelese +1-el)
	 */
	public void setEletkorNoveles(){
		////szkeleton.logMethodEntry(this, "sporatEszik");	///Szkeleton kiiratas fuggveny kezdetekor
		eletkor++;
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
	}


	/*
	 * @brief Setter (tekton eletkoranak beallitasa barmely ertekre (teszteleshez))
	 */
	public void setEletkorMegadas(int szam){
		////szkeleton.logMethodEntry(this, "setEletkorMegadas");	///Szkeleton kiiratas fuggveny kezdetekor
		eletkor=szam;
		////szkeleton.logMethodExit(this, "");			///Szkeleton kiiratas fuggveny vegen	
	}

	/*
	 * @brief Felszivo tektonban felul irva
	 */
	void fonalElszakadKoronkent(){}

	/*
	 * @brief Eletben tarto tektonban felul irva
	 */
	void megSeHalMeg(){}


	/*
	* @brief Eletbentarto tektonban felul irva
	*/
	void nemHalMegListaTorles(){};

	

	/*
	 * @biref Getter (vissza adja a tektonon levo egy gombaszhoz tartozo fonalakat)
	 */
	public ArrayList<Fonal> getOsszekoto(Gombasz a){
			ArrayList<Fonal>uj = new ArrayList();
			for(int i=0;i<osszekoto.size();i++){
				if(osszekoto.get(i).getTartozik()==a){
					uj.add(osszekoto.get(i));
				}
			}
			return uj;

	}





 
}

