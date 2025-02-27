

public class Jatekos 
{
    int id;
    String nev;
    int pontok;

    Jatekos(int NID, String NNev)
    {
        this.id = NID;
        this.nev = NNev;
        pontok = 0;

    }

    void SetID(int NID)
    {
        this.id = NID;
    }

    int GetID()
    {
        return this.id;
    }

    void SetPont(int pont)
    {
        this.pontok = pont;
    }

    int GetPont()
    {
        return this.pontok;
    }

    void NovelPont(int n)
    {
        this.pontok += n;
    }

    void SetNev(String NNev)
    {
        this.nev = NNev;
    }

    String GetNev()
    {
        return this.nev;
    }
}
