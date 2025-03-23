
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
		id=idd;
	}








	/*
	 * @brief Getter
	 * @return Visszaadja a tekton id-jat
	 */
	int getId(){
		return id;
	}



	/*
	 * @brief A fuggveny megnezi/eldonti hogy az adott tekton az eletkora, es a random faktor alapjan szetessen-e
	 * @return true -> szetesik
	 * @return false -> nem esik szet 
	 */
	boolean torikE(){
		int toresarany=eletkor*5+10;	///toresre az esely: (eletkor*5 + kezdeti esely). Azt jelenti hogy kezdeti esely, koronkent 5%-al no.
		Random random = new Random();	
		int toresEsely=random.nextInt(100);	///tenyleges toresei esely: (toresre az esely / 100)
		if(toresEsely>=toresarany) return true;
		return false;
	}



	/*
	 * @brief Minden tektonon levo fonalat elszakitjuk, szeteseskor
	 */
	void mindenFonalElszakad(){
		for(Fonal elem : osszekoto){		///Minden fonalunkra meghivjuk az elszakadast
			fonalElszakad(elem);
		}
	}



	/*
	 * @brief Letrehozzuk a listat , ami majd a szeteseskor letrejott uj tekton szomszedait fogja tartalmazni. A szomszedokat random valasztjuk ki.
	 * @return uj -> Az uj tektonnak beallitani kivant szomszed lista.
	 */
	ArrayList<Tekton> ujTektonSzomszedListajanakBeallitasa(){
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
	void szomszedHozzaadasa(Tekton a){
		szomszed.add(a);
	}












	/*
	 * @brief A kapott tekton szomszed listajahoz hozza adjuk a kapott uj tekton. Beallitjuk hogy a lista minden elemenek szomszedja legyen az uj tekton.
	 */
	void ujTektonSzomszedainakListainakBeallitasa(ArrayList<Tekton> szomszedok1, Tekton uj){
		for(Tekton egyszomszed:szomszedok1){
			egyszomszed.szomszedHozzaadasa(uj);
		}
	}













	/*
	 * @brief Eltavolitja a szomszed listabol a megadott indexu elemet
	 * @param index -> torolni kivan index
	 */
	void szomszedTorol(int index){
		szomszed.remove(index);
	}




	/*
	 * @brief Getter
	 * @return szomszed -> vissza adja a szomszed listat
	 */
	ArrayList<Tekton> getSzomszed(){
		return szomszed;
	}


	/*
	 * @brief Ezen tekton szomszed listajabol veletlenszeruen kivalasztunk elemeket, amik a szomszedaik maradnak, a tobbi szomszedot toroljuk
	 */
	void ujSzomszedaimBeallaitasa(){
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
	}
		






	




Tekton ujTektonLetrehozasa(ArrayList<Spora> spo, ArrayList<Tekton>szom){
	return new Tekton(spo,szom);
}





	Tekton tores()
	{
		
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
			return ujTekton;	
		}
		return null;
	}















	ArrayList<Spora> ujTektonSporakListaja(){
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
				for(Fonal elem1 : elem.getHova().getFonalLista()){
					if(elem1.getHova()==this){
						elem.getHova().getFonalLista().remove(elem1);
					}
				}
				osszekoto.remove(elem);
				
			}
		}
		fonal.getTartozik().elszakadasDfsKezeles();
		
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
		if(hanyFonalaVanGombasznak(g)>=1 && hanySporajaVanGombasznak(g)>=5 && gombatest==null ){
			return true;
		}
		return false;
	}









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
		return true;
	} 









	//Vissza adja az összes olyan szomszédos tektont ahová megy fonal, gondolom bogarak mozgatasa miatt ////////////////////////////////
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

	ArrayList<Fonal> getFonalLista(){
		return osszekoto;
	}
	






	void addSpora(Spora s)
	{
		sporak.add(s);
		if(tudEpulni(s.getTartozik()) ){
			gombaTestEpul(s.getTartozik());
		}
	}














	void epitoanyagSporaEltunik(Gombasz g){
		//az adott jatekos utolso 5 sporajat levesszuk a tektonrol
		ArrayList<Integer> indexek=new ArrayList<>();											
		for(int i=sporak.size()-1;i>=0 ;i--){
			if(sporak.get(i).getTartozik().equals(g)){
				indexek.add(i);
			}
		}
		for(int i=0;i<indexek.size();i++){
			sporak.remove(indexek.get(i));
		}
	}















	void gombaTestEpul(Gombasz g)
	{
		Gombatest uj=new Gombatest(this, g);		///faszom tudja hogyan van
		setGombatest(uj);
		g.gombatestHozzaad(uj);
		epitoanyagSporaEltunik(g);																
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











	void setEletkorNoveles(){
		eletkor++;
	}










	void setEletkorMegadas(int szam){
		eletkor=szam;
	}



 
}