package ku.cs.application.models;
import java.util.ArrayList;
import ku.cs.application.models.Users;
public class UserList {
    private ArrayList<Users> userList;

    public UserList() {
        userList = new ArrayList<>();}

    public void addUser(Users user){

        userList.add(user);} // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
    public ArrayList<Users> getAllCards(){
        return userList;}

    public boolean checkUsernameIsExistedInUserList(String username){
        if (username.isEmpty()){
            return true;
        }else {
            for (Users temp: userList){
                if (temp.getUsername().equals(username)){
                    return true;
                }
            }
        }
        return false;
    }
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


