public class UE {
    private String intitule;
    private String codeUE;
    private int nombreInscrit;
    private int[] tab_inscrits;
    private double[] tab_notes;

    public UE(String intitule , String codeUE , int nombreMax)
    {
        this.intitule = intitule;
        this.codeUE = codeUE;
        this.nombreInscrit = 0;
        this.tab_inscrits = new int[nombreMax];
        this.tab_notes = new double[nombreMax];
    }

    public String toString()
    {
        return "intitule : " + this.intitule +"| codeUE : " + this.codeUE ;
    }

    public String getIntitule()
    {
        return this.intitule;
    }

    public String getCodeUE()
    {
        return this.codeUE;
    }

    public int getNombreInscrit()
    {
        return this.nombreInscrit;
    }

    public int getLengthmMax()
    {
        return this.tab_inscrits.length;
    }



    public Boolean estPleine()
    {
        if(this.nombreInscrit == this.tab_inscrits.length) return true;
        return false;
    }

    public int indice( int numero_etudiant )
    {
        int i;
        for( i = 0 ; i < this.tab_inscrits.length ; i++ )
        {
            if(this.tab_inscrits[i] == numero_etudiant)
                return i;
        }
        return -1;
    }

    public void inscrire_etudiant(Etudiant e)
    {
        try{
            if(indice(e.getNumero()) == -1 && !e.estChoisie10())
            {
                this.tab_inscrits[nombreInscrit] = e.getNumero();
                this.nombreInscrit += 1;
                e.ajouterUE(this);
            }
        }
        catch(Exception ex)
        {
            System.out.println("error : depacement de nombre maximale.");
        }
    }

    public void set_notes(int numero_etu , double note )
    {
        try{
            this.tab_notes[indice(numero_etu)] = note;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public double moyenne()
    {
        double moy = 0;
        for(int i = 0 ; i < this.nombreInscrit ; i++)
        {
            moy += this.tab_notes[i];
        }
        moy = moy /this.nombreInscrit;

        return moy;
    }

    public void afficherMoyenne()
    {
        System.out.println(" + la moyenne de UE de codeUE : "+this.getCodeUE()+" moyenne : "+this.moyenne() );
    }
    public double get_note(int numero)
    {
        return this.tab_notes[this.indice(numero)];
    }
}
