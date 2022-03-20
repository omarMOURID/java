import java.io.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Universite {
    private final int NB_max_etudiants;
    private final int NB_max_UE;
    private UE[] tab_UEs;
    private int NB_UEs;
    private Etudiant[] tab_etudiants;
    private int NB_etudiants;


    public Universite(int nb_max_et , int nb_max_ue)
    {
        this.NB_max_etudiants = nb_max_et;
        this.NB_max_UE = nb_max_ue;
        this.NB_UEs = 0;
        this.NB_etudiants = 0;
        this.tab_UEs = new UE[NB_max_UE];
        this.tab_etudiants = new Etudiant[NB_max_etudiants];
    }
    public int getNB_max_etudiants(){   return NB_max_etudiants;}

    public int getNB_etudiants(){   return NB_etudiants;}

    public int getNB_max_UE(){   return NB_max_UE;}

    public int getNB_UEs(){   return NB_UEs;}


    public Universite(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n;
        int nb_max_et = parseInt(br.readLine());
        int nb_max_ue = parseInt(br.readLine());

        this.NB_max_etudiants = nb_max_et;
        this.NB_max_UE = nb_max_ue;
        this.NB_UEs = 0;
        this.NB_etudiants = 0;
        this.tab_UEs = new UE[NB_max_UE];
        this.tab_etudiants = new Etudiant[NB_max_etudiants];

        n=parseInt( br.readLine() );
        for(int i = 0 ; i < n ; i++)
        {
            this.inscrire_etudiant(new Etudiant(br.readLine(), parseInt(br.readLine())));
        }
        n = parseInt( br.readLine() );
        for(int i = 0 ; i < n ; i++)
        {
            this.ajouter_UE(new UE(br.readLine() , br.readLine() , parseInt(br.readLine())));
        }


        n = parseInt( br.readLine() );
        for(int i = 0 ; i < n ; i++)
        {
            this.inscrire_dans_UE(parseInt(br.readLine()) , br.readLine());
        }

        n = parseInt( br.readLine() );
        for(int i = 0 ; i < n ; i++)
        {
            this.attribuer_note(parseInt(br.readLine()) , br.readLine(),  parseDouble(br.readLine()));
        }

        br.close();
    }

    public void universiteSave(String path) throws IOException {
        FileWriter fr = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fr);

        int n = 0;

        bw.write(Integer.toString(this.NB_max_etudiants));
        bw.newLine();
        bw.write(Integer.toString(this.NB_max_UE));
        bw.newLine();
        bw.write(Integer.toString(this.NB_etudiants));
        for(int i = 0; i < this.NB_etudiants ;i++)
        {
            bw.newLine();
            bw.write(this.tab_etudiants[i].getNom());
            bw.newLine();
            bw.write(Integer.toString(this.tab_etudiants[i].getNumero()));
            n += this.tab_etudiants[i].getnUE();
        }

        bw.newLine();
        bw.write(Integer.toString(this.NB_UEs));

        for(int i = 0; i < this.NB_UEs ;i++)
        {
            bw.newLine();
            bw.write(this.tab_UEs[i].getIntitule());
            bw.newLine();
            bw.write(this.tab_UEs[i].getCodeUE());
            bw.newLine();
            bw.write(Integer.toString(this.tab_UEs[i].getLengthmMax()));
        }

        bw.newLine();
        bw.write(Integer.toString(n));

        for(int i = 0; i < this.NB_etudiants ;i++)
        {
            for(int j = 0 ; j < this.tab_etudiants[i].getnUE() ; j++) {
                bw.newLine();
                bw.write(Integer.toString(this.tab_etudiants[i].getNumero()));
                bw.newLine();
                bw.write(this.tab_etudiants[i].getCodeUeEt(j).getCodeUE());
            }
        }

        bw.newLine();
        bw.write(Integer.toString(n));

        for(int i = 0; i < this.NB_etudiants ;i++)
        {
            for(int j = 0 ; j < this.tab_etudiants[i].getnUE() ; j++) {
                bw.newLine();
                bw.write(Integer.toString(this.tab_etudiants[i].getNumero()));
                bw.newLine();
                bw.write(this.tab_etudiants[i].getCodeUeEt(j).getCodeUE());
                bw.newLine();
                bw.write(Double.toString(this.tab_etudiants[i].getCodeUeEt(j).get_note(this.tab_etudiants[i].getNumero())));
            }
        }

        bw.close();
    }

    public void afficheUE()
    {
        String chaine = "";
        chaine = "les UEs :";
        for(int i = 0 ; i < NB_UEs ; i++)
        {
            chaine += "\n" + this.tab_UEs[i].toString() + "| nombre d'inscrits : " + this.tab_UEs[i].getNombreInscrit();
        }
        System.out.println(chaine);
    }

    public void afficheEt()
    {
        for(int i = 0 ; i < NB_etudiants ; i++)
        {
            System.out.println(this.tab_etudiants[i].toString());
            System.out.println("------------------------");
        }
    }

    public int indice_etudiants(int numero)
    {
        for(int i = 0 ; i < NB_etudiants ; i++ )
        {
            if(this.tab_etudiants[i].getNumero() == numero)
                return i;
        }
        return -1;
    }

    public int indice_UE(String codeUE)
    {
        for(int i = 0 ; i < NB_UEs ; i++ )
        {
            if(this.tab_UEs[i].getCodeUE().equals(codeUE))
                return i;
        }
        return -1;
    }

    public void inscrire_etudiant(Etudiant e)
    {
        if( this.indice_etudiants(e.getNumero()) == -1)
        {
            this.tab_etudiants[this.NB_etudiants] = e;
            this.NB_etudiants += 1;
        }
    }

    public void ajouter_UE(UE ue)
    {
        if( this.indice_UE(ue.getCodeUE()) == -1)
        {
            this.tab_UEs[this.NB_UEs] = ue;
            this.NB_UEs += 1;
        }
    }

    public void inscrire_dans_UE(int numero_etu , String codeUE)
    {
        Etudiant e = this.tab_etudiants[this.indice_etudiants(numero_etu)];
        UE u = this.tab_UEs[this.indice_UE(codeUE)];

        u.inscrire_etudiant(e);
    }

    public void attribuer_note(int numero_etu , String codeUE , double note)
    {
        this.tab_UEs[indice_UE(codeUE)].set_notes(numero_etu,note);
    }

    public void afficher_moyennes()
    {
        double moy = 0;
        System.out.println("----------------------------------");
        for(int i = 0 ; i < NB_UEs ; i++)
        {
            moy += this.tab_UEs[i].moyenne();
        }
        moy = moy / NB_UEs;
        System.out.println("la moyenne de m'université est : "+moy);

        System.out.println("les moyennes des uns:");
        for(int i = 0 ; i < NB_UEs ; i++)
        {
            this.tab_UEs[i].afficherMoyenne();
        }

        System.out.println("les moyennes des etudiants:");
        for(int i = 0 ; i < NB_etudiants ; i++)
        {
            this.tab_etudiants[i].moyenne_etudiant();
        }
        System.out.println("----------------------------------");
    }


}
