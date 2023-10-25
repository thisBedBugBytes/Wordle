import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class DictionAry {

    private HashSet<String> words;
    public DictionAry() throws IOException{

        words = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("usa.txt"))) {
            String word = reader.readLine();
            while (word != null) {
                //assert false;
                if (word.length() == 5) {
                    words.add(word.trim().toLowerCase());
                }
                word = reader.readLine();
            }
        }

    }

    //used by wordle class to generate random word for wod
    public String wordEngine(){


        String [] wordList = words.toArray(new String[0]); //converts the hashset to an array
        //generates index of random word
        Random rand = new Random();
        int upperbound = words.size();
        int randNum = rand.nextInt(upperbound);

        String str = wordList[randNum];

        return str;
    }

    //checks if a word is a real word or not
    public boolean containWord(String word){
        return words.contains(word);
    }


}
