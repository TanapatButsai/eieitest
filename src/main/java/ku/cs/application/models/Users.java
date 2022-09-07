package ku.cs.application.models;

public class Users {
    private String name;
    private String id;
//    private String email;
    private String username;
    private String password;
    private double lastTimeLogin;
    private String userImage;


    public Users(String name, String id, String username, String password) {
        this.name = name;
        this.id = id;
//        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
//                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //getter-setter---------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getEmail() {
//        return email;
//    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

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

}
