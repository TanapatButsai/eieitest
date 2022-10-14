package ku.cs.application.models;
import java.util.*;

import ku.cs.application.models.Users;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserList{
    private ArrayList<Users> userList;

    public UserList() {
        userList = new ArrayList<>();
    }

    public void addUser(Users user){

        userList.add(user);
    }

    public ArrayList<Users> getAllCards(){
        return userList;
    }
    public ArrayList<Users> getAllUsers(){
        ArrayList<Users> userListTemp = new ArrayList<>();
        for (Users userTemp:userList) {
            if (!userTemp.isAdmin()) {
                userListTemp.add(userTemp);
            }
        }
        Collections.sort(userListTemp);
        return userListTemp;
    }

    public void removeUser(Users user){
        userList.remove(user);
    }

    public boolean checkUsernameIsExistedInUserList(String username){
        Users user = findUser(username);
        return user != null;
    }
    public void recordTimeLogin(Users users){
        users.updateTimeNow();
    }

    public void recordTimeLogin(String username){
        Users user = findUser(username);
        if (user != null) {
            user.updateTimeNow();
        }
    }
    public void changePassword(String username,String newPassword){
        Users user = findUser(username);
        if (user != null) {
            user.setPassword(newPassword);
        }
    }
    public void setImageStudent(String username, String imagePath){
        Users user = findUser(username);
        if (user != null) {
            user.setUserImage(imagePath);
        }
    }
    public Users findUser(String username) {
        for (Users user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }return null;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "userList=" + userList +
                '}';
    }

    public void vote(String username){
        Users user = findUser(username);
        for (Users users:userList) {

        }
    }
}


