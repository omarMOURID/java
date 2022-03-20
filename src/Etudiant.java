public class Etudiant {
    private String nom;
    private int numero;
    private int nUE;
    private UE[] UEs;
    static final int NB_gabarit = 10;

    public Etudiant(String nom , int numero)
    {
        this.nom = nom;
        this.numero = numero;
        this.nUE = 0;
        this.UEs = new UE[NB_gabarit];
    }

    public String toString()
    {
        String chaine = "";
        chaine += "nom : " + this.nom;
        chaine += "\nnuméro : " + this.numero;
        chaine += "\nliste  des UEs suivis:";

        if(nUE == 0) chaine += "\npas d'UE";

        for(int i = 0 ; i < nUE ; i++)
        {
            chaine += "\n+ " + this.UEs[i].toString();
        }
        return chaine += "\n";
    }

    public String getNom() {    return  this.nom;}

    public int getNumero() {    return this.numero;}

    public int getnUE() {   return this.nUE;}

    public UE getCodeUeEt(int indice){ return this.UEs[indice];}

    public Boolean estChoisie10()
    {
        if(this.nUE == NB_gabarit) return true;
        return false;
    }

    public void ajouterUE(UE u)
    {
        this.UEs[nUE] = u;
        this.nUE += 1;
    }

    public void moyenne_etudiant()
    {
        double moy = 0;
        for(int i = 0 ; i < nUE ; i++)
        {
            moy += this.UEs[i].get_note(this.numero);
        }
        moy = moy / nUE;
        System.out.println(" + etudiant de numero : "+this.getNumero()+" | moyenne : "+moy);
    }
}
