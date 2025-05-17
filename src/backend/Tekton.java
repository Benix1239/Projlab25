package backend;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/* 
 * @class Tekton
 * @brief A egy-egy tekton adatainak taroljasa, es a tekton esemenyeinek kezeleset vegzi.
 */
public class Tekton implements Serializable 
{
	protected Gombatest gombatest;			///Tarolja ha van gombatest az adott tektonon
	protected  ArrayList<Spora> sporak;		///Tarolja a sporakat a tektonon
	protected  ArrayList<Fonal> osszekoto;		///Tarolja hogy milyen fonalak vannak a tektonon
	protected  ArrayList<Tekton> szomszed;		///Tarolja a tektonok szomszedait
	protected  int eletkor;					///Tarolja a tektonok eletkorat (noveli a tektonok szetesesenek eselyet)
	protected  int pluszPont;					///Tarolja hogy az adott tektonon epitett gombatest hany plusz pontot er
	protected  int id;							///Tarolja a tekton id-jat


	public ArrayList<Spora> getSporak(){
		return sporak;
	}

	public Gombatest getGombatest(){
		return gombatest;
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
		//eletkor=1;
		eletkor=0;
		pluszPont=0;
		id=-1;

	}

	/*
 	* @brief Setter
	* @param idd -> A beallitani kivant id
 	*/
	public void setId(int idd){
		id=idd;
	}

	/*
	 * @brief Getter
	 * @return Visszaadja a tekton id-jat
	 */
	public int getId(){
		return id;
	}

	/*
	 * @brief A fuggveny megnezi/eldonti hogy az adott tekton az eletkora, es a random faktor alapjan szetessen-e
	 * @return true -> szetesik
	 * @return false -> nem esik szet 
	 */
	protected boolean torikE(){

		int toresarany=eletkor*10/*+10*/;	///toresre az esely: (eletkor*5 + kezdeti esely). Azt jelenti hogy kezdeti esely, koronkent 5%-al no.
		Random random = new Random();	
		int toresEsely=random.nextInt(100);	///tenyleges toresei esely: (toresre az esely / 100)
		if(toresEsely<=toresarany){
			return true;
		}

		return false;
	}

	/*
	 * @brief Minden tektonon levo fonalat elszakitjuk, szeteseskor
	 */
	protected void mindenFonalElszakad(){
		for(int i=0;i<osszekoto.size();i++){
			fonalElszakad(osszekoto.get(i));
		}
	}

	/*
	 * @brief Letrehozzuk a listat , ami majd a szeteseskor letrejott uj tekton szomszedait fogja tartalmazni. A szomszedokat random valasztjuk ki.
	 * @return uj -> Az uj tektonnak beallitani kivant szomszed lista.
	 */
	protected ArrayList<Tekton> ujTektonSzomszedListajanakBeallitasa(){

		ArrayList<Tekton>uj=new ArrayList<>();
		for(Tekton elem:szomszed){								///Minden szomszedon vegig megyunk
			Random random = new Random();		
			int szomszedEsely=random.nextInt(100);			
			
			if(szomszedEsely%2==0){							///50% eselyel hozza adja az adott szomszedot az uj tekton szomszed listajaba
				uj.add(elem);
			}
		}

		return uj;											///Ha nem adunk hozza semmit, akkor az ures listat adjuk vissza
	}
	/*
	* @brief Hozzaadunk egy szomszedot a szomszed listahoz
	*/
	public void szomszedHozzaadasa(Tekton a){
		szomszed.add(a);
	}

	/*
	 * @brief A kapott tekton szomszed listajahoz hozza adjuk a kapott uj tekton. Beallitjuk hogy a lista minden elemenek szomszedja legyen az uj tekton.
	 */
	protected void ujTektonSzomszedainakListainakBeallitasa(ArrayList<Tekton> szomszedok1, Tekton uj){

		for(Tekton egyszomszed:szomszedok1){
			egyszomszed.szomszedHozzaadasa(uj);
		}
	}

	/*
	 * @brief Eltavolitja a szomszed listabol a megadott indexu elemet
	 * @param index -> torolni kivan index
	 */
	public void szomszedTorol(int index){
		szomszed.remove(index);
	}

	/*
	 * @brief Getter
	 * @return szomszed -> vissza adja a szomszed listat
	 */
	public ArrayList<Tekton> getSzomszed(){
		return szomszed;
	}

	/*
	 * @brief Ezen tekton szomszed listajabol veletlenszeruen kivalasztunk elemeket, amik a szomszedaik maradnak, a tobbi szomszedot toroljuk
	 */
	protected void ujSzomszedaimBeallaitasa(){

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
	}
	
	/*
	* @brief Letre hozunk egy uj tektont , a tekton szetesesekor
	* @return Keletkezett uj tekton
	*/
	protected Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
		return new Tekton(spo,szom);
	}


	/*
	 * @brief A tektonok szetesesenek teljes kezelese, amit koronkent meghivhatunk
	 * @return ujTekton -> A kesz uj tekton, ha ez szettorik.
	 */
	public Tekton tores(){

		//ha szettorik a tekton
		if(torikE()){											///Ha szettorik a tekton
			//ha a tektonon van fonal
			if(osszekoto.size()!=0 ||osszekoto!=null){
				mindenFonalElszakad();							///Minden fonalat elszakitunk
			}
			ArrayList<Spora> ujSporak=new ArrayList<>();
			ujSporak=ujTektonSporakListaja();						///Uj tektonnak beallitjuk a soporait
			ArrayList<Tekton>ujSzomszedok=new ArrayList<>();
			ujSzomszedok=ujTektonSzomszedListajanakBeallitasa();			///Uj tekton szomszedainak beallitasa

			Tekton ujTekton=ujTektonLetrehozasa(ujSporak,ujSzomszedok);		///Letrehozzuk az uj tektont,megadjuk a szomszedait
			sajatSporaimBeallitasa(ujSporak);														///Beallitom a sajat sporaimat
			ujSzomszedaimBeallaitasa();																///Beallitja az en uj szomszedaimat, mindket oldalról torlom azokat akik nem a szomszedaim
			ujTektonSzomszedainakListainakBeallitasa(ujSzomszedok, ujTekton);						///Az uj tekton szomszedainak szomszed listajahoz hozza adjuk az uj tektont
			eletkor=1;

			return ujTekton;	
		}

		return null;
	}

	/*
	 * @brief Megcsinalja a listat ami szeteseskor letrejott tekton sporait fogja tartalmazni
	 * @return uj -> Vissza adja a tombot ami a sporakat tartalmazza.
	 */
	protected ArrayList<Spora> ujTektonSporakListaja(){
		ArrayList<Spora>uj=new ArrayList<>();						
		Random random = new Random();								
		if(sporak.size()!=0){
			int sporaEsely=random.nextInt(sporak.size());				///Generalunk egy random szamot, ami max akkora mint ennek a tektonnak a spora listaja
			for(int i=0;i<sporaEsely;i++){								
				uj.add(sporak.get(i));									///Vegig megyunk ezen tekton spora listajan, es elso random szamu elemet kigyujtjuk, hogy atadhassuk
			}
		}
		return uj;
	}

	/*
	* @brief Ezen tekton szetesesekor a sporak beallitasa
	* @param ujSporaja -> Lista amit az uj tekton megkap, tehat ami tobbe nem lesz ezen tekton sporaja
	*/
	protected void sajatSporaimBeallitasa(ArrayList<Spora> ujSporaja){

		ArrayList<Spora>uj=new ArrayList<>();
		if(sporak.size()!=0){
			for(Spora elem: sporak){
				if(!ujSporaja.contains(elem)){			///Kigyujtjuk a jelenlegi spora lista azon elemeit, ami az uj tekton spora listajanak nem eleme 
					uj.add(elem);
				}
			}
		}
		sporak=uj;									///Beallitjuk a spora listat
		
	}

	/*
	 * @brief  Elszakit egy fonalat
	 * @param fonal -> A fonal amit el akarunk szakitani
	 */
	public void fonalElszakad(Fonal fonal){
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

	/**
	 * Fonal elpusztítás.
	 * @param g Gombasz.
	 */
	public void fonalElpusztit(Gombasz g){

		ArrayList<Fonal> tomb=new ArrayList<>();

		for(Fonal f : osszekoto)
		{
			tomb.add(f);
		}
		
		for(Fonal fonal : tomb)
		{
			Tekton hova = fonal.getHova();
			if(fonal.getTartozik() == g){
				this.osszekoto.remove(fonal);
			}
			
			ArrayList<Fonal> fonalak = hova.getOsszekoto();

			for (Fonal fonali : fonalak) {
				if (fonali.getHova() == this && fonal.getTartozik() == g) {
					hova.osszekoto.remove(fonali);
					break;
				}
			}
		}
		
	}

	/*
	 * @brief Meghatarozza hogy az adott tektonon hany daraab fonala van egy adott gombasznak
	 * @param g -> Gombasz akit vizsgalunk
	 * @return db -> Fonalak szama
	 */
	public int hanyFonalaVanGombasznak(Gombasz g){
		int db=0;
		for(Fonal elem:osszekoto){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		return db;
	}

	/*
	 * @brief Meghatarozza hogy hany sporaja van az adott tektonon egy gombasznak
	 * @param g -> Gombasz
	 * @return db -> Sporak szama 
	 */
	protected int hanySporajaVanGombasznak(Gombasz g){
		int db=0;
		for(Spora elem:sporak){
			if(elem.getTartozik().equals(g)){
				db++;
			}
		}
		return db;
	}

	/*
	 * @brief Meghatarozza hogy a gombasz tud-e gombatestet epiteni az adott tektonra
	 * @param g -> Gombasz
	 * @return true -> Tud epiteni
	 * @return false -> Nem tud epiteni
	 */
	protected boolean tudEpulni(Gombasz g){
		if(hanyFonalaVanGombasznak(g)>=1 && hanySporajaVanGombasznak(g)>=5 && gombatest==null ){	///Tudd epitani ha van legalabb egy fonala, 5 sporaja, es nincs meg gombatest a tektonon
			return true;
		}
		return false;
	}

	/*
	 * @brief Fonal lehelyezes a tektonra
	 * @param f -> Lehelyezni kivant fonal
	 * @return true -> Le tud tenni
	 * @return false -> Nem tud letenni
	 */
	public boolean addFonal(Fonal f){
		for(Fonal elem:osszekoto){
			boolean osszehasonlitas= elem.equals(f);
			if(osszehasonlitas){
				return false;									///Ha mar van egy tektonon fonala a gombasznak akkor nem tud tobbet letenni
			}
		}
		
		osszekoto.add(f);
		if(tudEpulni(f.getTartozik()) ){					///Megvizsgaljuk hogy ezutan a fonal letetel utan, meg tud-e ott epulni a gombatest
			gombaTestEpul(f.getTartozik());					///Ha meg tud epulni automatikusan megepul
		}
		return true;
	} 

	/*
	 * @brief Vissza adja hogy az adott tektonrol melyik tektonokra megy fonal
	 * @return tektonok -> Azon tektonok listaja ahova megy fonal a tektonrol
	 */
	public ArrayList<Tekton> fonalKeres(){
		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){						///Fonalak listabol kigyujtjuk azokat az osszes tektont ahova fonal megy
			if(!tektonok.contains(elem.getHova())){
				tektonok.add(elem.getHova());
			}
		}	
		return tektonok;		
	}

	/*
	 * @brief Vissza adja hogy az adott tektonrol mely tektonokra megy egy adott gombasz fonala
	 * @param g -> Gombasz
	 * @return tektonok -> Tektonok listaja ahova a gombasz fonala megy 
	 */
	public ArrayList<Tekton> fonalKeres(Gombasz g)	{

		ArrayList<Tekton> tektonok=new ArrayList<>();
		for(Fonal elem:osszekoto){
			if(!tektonok.contains(elem.getHova()) && elem.getTartozik().equals(g)){
				tektonok.add(elem.getHova());
			}
		}
		return tektonok;

	}

	/*
	 * @brief Getter
	 * @return osszekoto -> Vissza adja a fonal listat
	 */
	public ArrayList<Fonal> getOsszekoto(){
		return osszekoto;
	}
	
	/*
	 * @brief Spora hozzaadasa a tektonhoz
	 * @param s -> Lehelyezett spora
	 */
	public void addSpora(Spora s){
		sporak.add(s);
		if(tudEpulni(s.getTartozik()) ){			///Megnezzuk a spora lehelyezessel meg tud-e epulni gombatest
			Gombasz temp=s.getTartozik();
			gombaTestEpul(temp);			///Ha gombatest tud epulni, akkor megepul
		}
	}

	/*
	 * @brief Gombatest epites utan a gombaszhoz tartozo sporakbol a legfelso 5 db eltunik a tektonrol
	 * @param g -> Gombasz aki gombatestet epit
	 */
	private void epitoanyagSporaEltunik(Gombasz g){
		//az adott jatekos utolso 5 sporajat levesszuk a tektonrol
		ArrayList<Integer> indexek=new ArrayList<>();											
		for(int i=sporak.size()-1;i>=0 ;i--){		
			if(sporak.get(i).getTartozik().equals(g)){		
				indexek.add(i);										///Kigyujtjuk hogy a gombaszhoz tartozo 5 spora hol talalhato a spora listaban
			}
		}
		for(int i=0;i<indexek.size();i++){
			int index = indexek.get(i);
			sporak.remove(index);							///Kigyujtott indexeken levo sporak eltavolitasa
		}
	}

	/*
	 * @brief Letrehozzuk/megepitjuk a gombatestett a tektonon
	 * @param g -> Melyik gombasz epit gombatestet
	 */
	protected void gombaTestEpul(Gombasz g){
		Gombatest uj=new Gombatest(this, g);		///Letre hozzuk a gombatestet
		setGombatest(uj);							///Beallitjuk a tekton gombatestjet
		g.gombatestHozzaad(uj);						///Gombasznak oda adjuk az uj gombatestjet
		epitoanyagSporaEltunik(g);					///Eltuntetjuk a sporakat mikbol epult a test													
	}

	/*
	* @brief Setter
	* @param g -> Tektonnak beallitani kivant gombatest
	*/
	public void setGombatest(Gombatest g){			
		this.gombatest=g;
	}

	/*
	 * @brief Szomszed hozzaadasa a tektonhoz
	 * @param t -> Uj szomszed tekton
	 */
   public void addSzomszed(Tekton t){
	szomszed.add(t);
   }

   /*
	* @brief A tekton legfelso sporajaval vissz terunk es toroljuk
	* @retrun uj -> Legfelso spora a tektonon
    */
	public Spora sporatEszik(){
		if(sporak.size()!=0){
			Spora uj=sporak.get(sporak.size()-1);		///Legfelso spora kivalasztasa
			sporak.remove(sporak.size()-1);				///Spora torlese a tektonrol
			return uj;									///Kivalasztott spora visszaadasa
		}
		return null;
	}

	/*
	 * @brief Setter (tekton eletkoranak novelese +1-el)
	 */
	public void setEletkorNoveles(){
		eletkor++;
	}

	/*
	 * @brief Setter (tekton eletkoranak beallitasa barmely ertekre (teszteleshez))
	 */
	public void setEletkorMegadas(int szam){
		eletkor=szam;
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

	/**
	 * Visszaadja a fonal nélküli szomszédokat.
	 * @param g Gombasz.
	 * @return Tekton lista.
	 */
	public ArrayList<Tekton> fonalNelkuliSzomzed(Gombasz g)
	{
		ArrayList<Tekton> eredmeny = new ArrayList<>();
		ArrayList<Tekton> temp = fonalKeres(g);
		for(Tekton t : szomszed)
		{
			if(!temp.contains(t))
			{
				eredmeny.add(t);
			}
		}

		return eredmeny;
	}

	/**
	 * Visszaadja, hány spóra van a tektonon egy adott Gombasz-nak.
	 * @param g Gombász.
	 * @return int.
	 */
	public int hanySporajaVan(Gombasz g){
		int szam = 0;
		for(Spora s : sporak){
			if(s.getTartozik() == g){
				szam++;
			}
		}
		return szam;
	}

	/**
	 * 
	 * Megnezi, hogy a kapott tekton benne van-e a szomszedok listaban
	 * @param sz a vizsgalt tekton
	 * @return igazat ad vissza, ha szomszedok, hamisat ha nem
	 */
	public boolean szomszedE(Tekton sz){
		return szomszed.contains(sz);
	}
}