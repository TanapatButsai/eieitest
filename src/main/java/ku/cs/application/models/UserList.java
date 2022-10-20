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
    public ArrayList<String> getAllRoleOfficers(String role, OfficeList officeList){
        ArrayList<String> officerTemp = new ArrayList<>();
        for (Users userTemp:userList) {
            if (userTemp.isOfficer()) {
                Officer officer = new Officer(userTemp.getName(), userTemp.getId(), userTemp.getUsername()
                        , userTemp.getPassword(), userTemp.getLastTimeLogin(), userTemp.getUserImage()
                        , officeList.findOfficerRole(userTemp.getUsername()));
                if (officer.getRole().equals(role)){
                    String officerID = userTemp.getUsername();
                    officerTemp.add(officerID);
                }
            }
        }
        return officerTemp;
    }
    public Users find(String username){
        for (Users temp:userList){
            if (temp.getUsername().equals(username)){
                return temp;
            }
        }
        return null;
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
    public void unban(String username){
        Users user = findUser(username);
        if (user != null) {
            user.setBan(false);
        }
    }
    public void setImageStudent(String username, String imagePath){
        Users user = findUser(username);
        if (user != null) {
            user.setUserImage(imagePath);
        }
    }
    public void setBan(String username){
        Users user = findUser(username);
        if (user != null) {
            user.setBan(true);
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


