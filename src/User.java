import java.io.*;
import java.util.Scanner;

public class User {
    private String user_name, password;
    private int rAttempt, wAttempt;
    BufferedWriter writer;
    Scanner reader;
    File info;
    public User(String user_name, String password) throws IOException {

        this.user_name = user_name;
        this.password = password;
        this.rAttempt = 0;
        this.wAttempt = 0;
        info = new File("Info.txt");


    }

    public int getrAttempt() {
        return rAttempt;
    }

    public void setrAttempt(int rAttempt) {
        this.rAttempt = rAttempt;
    }

    public int getwAttempt() {
        return wAttempt;
    }

    public void setwAttempt(int wAttempt) {
        this.wAttempt = wAttempt;
    }

    //incase yes is pressed
    public void Writer(String s, int r, int w) throws IOException{
        if(info.exists()){
          try{  this.writer = new BufferedWriter(new FileWriter(info, true));
            writer.write(s + " " + r + " " + w + "\n");}
          finally {
              writer.close();
          }


        }
        else{
            info.createNewFile();
            this.writer = new BufferedWriter(new FileWriter(info, true));
            writer.write(s + " " + r + " " + w );
            writer.close();

        }
    }
    public void reWrite(String str, int r, int w) throws IOException {
        BufferedReader Reader = null;


        StringBuilder line = new StringBuilder();
        try {
            Reader = new BufferedReader(new FileReader(info));
            String s = Reader.readLine();
            int check = 0;
            while (s != null) {

                if (s.startsWith(str)) {
                    int space = s.indexOf(' ');
                    //String subs = "";
                    if (space != -1) {
                        String name = s.substring(0, space);
                        s = name + " " + r + " " + w;

                    }
                }
                line.append(s).append(System.lineSeparator());
                check++;
                s = Reader.readLine();
            }
        }
        catch (RuntimeException ex){
            throw new NullPointerException();
        }
        finally {
            Reader.close();
        }

        try{
            this.writer = new BufferedWriter(new FileWriter(info, false));
            writer.write(line.toString());}
        finally
        {
            writer.close();
        }



    }
    public int[] upDate(String str, int r, int w) throws FileNotFoundException {
        this.reader = new Scanner(info);
        if(info.exists()){
            String temp = reader.next();
            while(reader.hasNext()){
                if(temp.equals(str)){
                    r = reader.nextInt();
                    w = reader.nextInt();
                    break;
                }
                temp = reader.next();
            }
        }
        return new int[]{r,w};
    }
    //incase no is pressed
    public boolean Verify(String s) throws IOException{
        reader = null;

        try{
            if(info.exists()){
                reader = new Scanner(info);
                String str = reader.next();
                boolean result = false;
                if(str.equals(s))  {result = true;}
                else{
                    while(reader.hasNext()){
                        if(str.equals(s)){
                            result = true;
                            break;
                        }
                        str = reader.next();

                    }}

                return result;}
            else{
                info.createNewFile();
                reader = new Scanner(info);
                String str = reader.next();
                boolean result = false;
                if(str.equals(s))  {result = true;}
                else{
                    while(reader.hasNext()){
                        if(str.equals(s)){
                            result = true;
                            break;
                        }
                        str = reader.next();

                    }}

                return result;
            }
        }

        finally {
            if(reader!=null)      reader.close();
        }
    }


    public User(){
        this.password = "";
        this.rAttempt = 0;
        this.wAttempt = 0;
        this.info = new File("Info.txt");
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }



}
