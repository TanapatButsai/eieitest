package ku.cs.application.services;

import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import java.io.*;

public class UserListDataSource implements DataSource<UserList> {
    private String directoryName;
    private String fileName;

    public UserListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public UserListDataSource() {
        userList = new UserList();
        readData();
    }

    //check that file is exist or not
    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public UserList readData() {
        UserList list = new UserList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                Users user = new Users(data[0].trim(),
                        data[1].trim()
                        , data[2].trim()
                        , data[3].trim());
                list.addUser(user);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }


    @Override
    public void writeData(UserList userList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
//        newUser = new Users("jaja123","456","780","123");
//        String newUserString = newUser.getName()+","+newUser.getId()+","+ newUser.getUsername()+","+ newUser.getPassword();
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (Users user : userList.getAllCards()) {
                String line = user.getName() + ","
                        + user.getId() + ","
                        + user.getUsername() + ","
                        + user.getPassword();

                buffer.append(line);
                buffer.newLine();
            }
//            buffer.append(newUserString);
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
    @Override
    public String toString() {
        return "UserListDataSource{" +
                "directoryName='" + directoryName + '\'' +
                ", fileName='" + fileName + '\'';
    }
    private UserList userList;
    public UserList getUserList() {
        return userList;
    }
}

//    public boolean loginUser(String username,String password){
//        String filePath = directoryName;
//        File file = new File(filePath);
//        FileReader reader = null;
//        BufferedReader buffer = null;
//        try {
//            reader = new FileReader(file);
//            buffer = new BufferedReader(reader);
//            String line = "";
//            while ( ( line = buffer.readLine() ) != null ){
//                String[] data = line.split(",");
//                if (Objects.equals(data[2],username) && Objects.equals(data[3],password)){
//                    return true;
//                }
//            }
//            return false;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }finally {
//            try {
//                buffer.close();
//                reader.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

