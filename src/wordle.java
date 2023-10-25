import java.io.IOException;

public class wordle extends DictionAry{
    private final String wod;
    private final int tries = 5;

    public wordle() throws IOException {
        super();
        this.wod = super.wordEngine();
    }

    public  String getWod() {
        return wod;
    }

    public int getTries() {
        return tries;
    }

    public boolean isWordle(char ch){
        if(wod.contains(Character.toString(ch))){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isIdx(char ch, String s, int idx){
        if(s.charAt(idx)==wod.charAt(idx)){
            return true;
        }
        else{
            return false;
        }
    }
    public int isCount(char ch, String s, int i) {
        int count = 0;
        int index = 0;
        while (index <= i) {
            if (s.charAt(index) == ch) {
                count++;
            }
            index++;
        }
        return count;
    }

}
