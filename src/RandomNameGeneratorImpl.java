import java.util.Random;
public class RandomNameGeneratorImpl {
    Random r;
    String letters;

    public RandomNameGeneratorImpl()
    {
        r = new Random();
        letters = "abcdefghijklmnopqrstuvwxyz";

    }

    public String createName()
    {
        StringBuilder nom = new StringBuilder("crazy") ;
        for(int i = 0; i < 8; i++)
        {
            nom.append(letters.charAt(r.nextInt(letters.length())));
        }

        return nom.toString();
    }

}
