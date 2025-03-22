
import java.util.ArrayList;

public class Bogar 
{
    int mozgasok;
    Boolean actionRagas;
    Boolean actionEves;
    Spora elozo;
    Tekton helyzet;
   

    Bogar()
    {
        mozgasok=1;
        actionRagas=true;
        actionEves=true;
        elozo=null;
    }

    void beallit()
    {
        mozgasok=1;
        actionRagas=true;
        actionEves=true;
        
    }

    void gyorsul()
    {
        mozgasok++;
    }

    void benul()
    {
        mozgasok--;
        actionEves=false;
        actionRagas=false;
    }

    void ragasBlock()
    {
        actionRagas=false;
    }

    void Lassul()
    {
        mozgasok--;
    }

    void ragas(Osszekoto fonal) //Megvan
    {
        Tekton hova= fonal.getHova();

        Gombasz gombasz=fonal.getTartozik();

        helyzet.fonalElszakad(fonal);// Ez amin éppen áll a bogár

        ArrayList<Fonal> fonalak = hova.fonalKeres();

        for (Fonal fonali : fonalak) {
            if (fonali.getHova()==helyzet && fonal.getTartozik()==gombasz) {
                hova.fonalElszakad();
            }
        }

        gombasz.elszakadasDfsKezeles();

        actionRagas=false;
        actionEves=false;
    }

    void eves() //Megvan
    {
        elozo=helyzet.sporatEszik();
        actionRagas=false;
        actionEves=false;
    }

    void mozgas(Tekton t) //Megvan
    {
        helyzet=t;
        mozgasok--;
    }

    
    void sporaMegemesztes()
    {
        elozo.hatas(this); 
        this.elozo=null;
    }

    Tekton getHelyzet()
    {
        return helyzet;
    }

    void setHelyzet(Tekton t)
    {
        helyzet=t;
    }

    int getMozgaspont(){
        return mozgasok;
    }

    boolean getactionRagas(){
        return actionRagas;
    }

    boolean getactionEves(){
        return actionEves;
    }
    
    void setElozo(Spora s){
        elozo=s;
    }

    Spora getSpora(){
        return elozo;
    }
}
