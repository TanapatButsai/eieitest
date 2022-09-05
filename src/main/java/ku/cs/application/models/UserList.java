package ku.cs.application.models;
import java.util.ArrayList;
import ku.cs.application.models.Users;
public class UserList {
    private ArrayList<Users> userList;

    public UserList() {
        userList = new ArrayList<>();}
    public void addUser(Users user) {
        // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
        userList.add(user);
    }
    public ArrayList<Users> getAllCards() {return userList;}


    public Users findUser(String username) {
        Users user = null;
        for (Users temp : userList) {
            if (temp.getUsername().equals(username)) {
                user = temp;
            }
        }
        return user;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "userList=" + userList +
                '}';
    }
}


