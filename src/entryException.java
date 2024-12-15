public class entryException extends Exception {
    private User user;
    public entryException(String s, User u){
        super();
        this.user = u;
    }
    public entryException(String s){
        super();
    }
    public void WrongName(String s) throws entryException{
        if(!s.equals(this.user.getUser_name())){
            throw new entryException("This username is incorrect.");
        }


    }
    public void WrongPass(String s) throws entryException{
        if(!s.equals(this.user.getPassword())){
            throw new entryException("This password is incorrect.");
        }
    }
}
