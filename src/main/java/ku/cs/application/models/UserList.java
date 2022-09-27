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
        System.out.println(userList.size());
        userList.remove(user);
        System.out.println(userList.size());
    }

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
    public void recordTimeLogin(Users users){
        Users usersTemp = findUser(users.getUsername());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss-dd-MM-yy");
        usersTemp.setLastTimeLogin(now.format(formatter));
        userList.remove(users);
        userList.add(usersTemp);
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


