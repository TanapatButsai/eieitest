package ku.cs.application.models;
import java.util.ArrayList;

public class UserList {
    private ArrayList<Users> users_student;

    public UserList() {
        users_student = new ArrayList<>();}
    public void addCard(Users user) {
        // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
        users_student.add(user);
    }
    public ArrayList<Users> getAllCards() {return users_student;}
}

