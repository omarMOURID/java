import java.io.IOException;
import java.util.Scanner;


public class TestUniversite {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Integer option = 0;
        int num = 0;
        double note = 0;
        int n = 0;

        Universite un = new Universite("/home/omar/TP3/src/fichier.txt");
        genererEtudiant(un);
        genererUE(un);

        while(option != 6)
        {
            System.out.println(" *si vous voulez ajouter quelqu'un 1\n *voir les etudiant 2\n *ajouter un etudiant a un un 3\n *attribuer une note 4\n *affichage des notes 5\n *quitter 6");
            option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    System.out.println("donner le nom de l'etudiant:");
                    if(scanner.hasNext())
                    {
                        num = genererNumero(un);
                        un.inscrire_etudiant( new Etudiant(scanner.next() , num));
                    }
                    System.out.println("donner le nombre de cursus:");
                    if(scanner.hasNextInt())
                    {
                        n = scanner.nextInt();
                    }
                    System.out.println("donner les cursus par codeUE:");
                    for(int i = 0 ; i < n ; i++ )
                    {
                        if (scanner.hasNext())
                        {
                            un.inscrire_dans_UE(num, scanner.next());
                        }
                    }
                    break;

                case 2:
                    un.afficheEt();
                    break;

                case 3:
                    System.out.println("donner le numero de l'etudiant et le codeUE de le UE");
                    if(scanner.hasNextInt())
                    {
                        num = scanner.nextInt();
                        System.out.println("num : "+num);
                    }
                    if(scanner.hasNext())
                    {
                        un.inscrire_dans_UE( num, scanner.next() );
                    }
                    break;

                case 4:
                    System.out.println("donner le numero de l'etudiant et la note ,le codeUE de le UE :");
                    if(scanner.hasNextInt())
                    {
                        num = scanner.nextInt();
                    }
                    if(scanner.hasNextDouble())
                    {
                        note = scanner.nextDouble();
                    }
                    if(scanner.hasNext())
                    {
                        un.attribuer_note( num , scanner.next() ,  note);
                    }
                    break;

                case 5:
                    un.afficher_moyennes();
                    break;
            }


        }
        un.universiteSave("/home/omar/TP3/src/fichier.txt");
        scanner.close();

    }

    public static int genererNumero(Universite un)
    {
        int cp = 1;
        while( un.indice_etudiants(cp) != -1) cp+=1;
        return cp;
    }

    public static void genererUE(Universite un){
        int cp = 1;
        String s = "USPN";
        while ( un.getNB_UEs() < un.getNB_max_UE()) {
            while (un.indice_UE(s + cp) != -1) {
                cp++;
            }

            un.ajouter_UE(new UE(s + cp, s + cp, 30));
        }
    }

    public static void genererEtudiant(Universite un){
        while ( un.getNB_etudiants() < un.getNB_max_etudiants()) {
            un.inscrire_etudiant(new Etudiant((new RandomNameGeneratorImpl()).createName(),genererNumero(un)));
        }
    }
}

