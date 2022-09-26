package ku.cs.application.models;

public class Users implements Comparable<Users> {
    private String fullName;
    private String id;
//    private String email;
    private String username;
    private String password;
    private String lastTimeLogin;
    private String userImage;
    private boolean isAdmin;



    public Users(String fullName, String id, String username, String password, boolean isAdmin, String lastTimeLogin) {
        this.fullName = fullName;
        this.id = id;
//        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.lastTimeLogin = lastTimeLogin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + fullName + '\'' +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastTimeLogin='" + lastTimeLogin + '\'' +
                ", userImage='" + userImage + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public void recordTimeLogin(){

    }

    //getter-setter---------------
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getLastTimeLogin() {return lastTimeLogin;}
    public double getLastTimeLoginToSecond(){
        String[] timeArr = new String[lastTimeLogin.length()];
        timeArr = lastTimeLogin.split("-");
        return (Double.parseDouble(timeArr[0])*3600)+(Double.parseDouble(timeArr[1])*60)
                +Double.parseDouble(timeArr[2])+(Double.parseDouble(timeArr[3])*86400)
                +(Double.parseDouble(timeArr[4])*2629743);
    }

    public void setLastTimeLogin(String lastTimeLogin) {this.lastTimeLogin = lastTimeLogin;}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Users o) {
        return Double.compare(o.getLastTimeLoginToSecond(), getLastTimeLoginToSecond());
    }
}
