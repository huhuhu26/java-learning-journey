public class Student1 {
    private String username;
    private String identity;
    private String phone;
    private String passcode;

    public Student1(){
    }

    public Student1(String username,String identity,String phone,String passcode){
        this.username = username;
        this.identity = identity;
        this.phone = phone;
        this.passcode = passcode;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getIdentity(){
        return identity;
    }
    
    public void setIdentity(String identity){
        this.identity = identity;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPasscode(){
        return passcode;
    }

    public void setPasscode(String passcode){
        this.passcode = passcode;
    }
}
