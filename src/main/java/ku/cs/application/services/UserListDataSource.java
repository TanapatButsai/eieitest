package ku.cs.application.services;

import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import java.io.*;
import java.nio.charset.StandardCharsets;

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
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                if (data[4].trim().equals("isAdmin")) continue;
                //String name, String id, String username, String password, boolean isAdmin , String lastTimeLogin, String userImage, boolean isBan
                String name = data[0].trim();
                String id = data[1].trim();
                String username = data[2].trim();
                String password = data[3].trim();
                boolean isAdmin = Boolean.parseBoolean(data[4].trim());
                String lastTimeLogin = data[5].trim();
                String userImage = data[6].trim().replace("\\",File.separator);
                boolean isBan = Boolean.parseBoolean(data[7].trim());
                boolean isOfficer = Boolean.parseBoolean(data[8].trim());
                Users user = new Users(name, id, username, password,
                        isAdmin, lastTimeLogin, userImage, isBan,isOfficer);
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

        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            String header = "fullName,ID,username,password,isAdmin,lastTimeLogin,image,isBan";
            buffer.append(header);
            buffer.newLine();
            for (Users user : userList.getAllCards()) {
                String line = user.getName() + ","
                        + user.getId() + ","
                        + user.getUsername() + ","
                        + user.getPassword() + ","
                        + user.isAdmin()+","
                        + user.getLastTimeLogin()+","
                        + user.getUserImage().replace(File.separator,"\\")+ ","
                        + user.isBan() + ","
                        + user.isOfficer();

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

