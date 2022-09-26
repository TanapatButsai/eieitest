package ku.cs.application.services;

import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;

import java.io.*;

public class ComplaintListDataSource implements DataSource<ComplaintList>{
    private String directoryName;
    private String fileName;
    public ComplaintListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

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

    public ComplaintListDataSource() {
        ComplaintList ComplaintList = new ComplaintList();
        readData();
    }
    @Override
    public ComplaintList readData() {
        ComplaintList list = new ComplaintList();
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
                Complaint complaint = new Complaint(data[0].trim(),
                        data[1].trim()
                        , data[2].trim()
                        , data[3].trim()
                        , data[4].trim());
                list.add(complaint);
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
    public void writeData(ComplaintList complaintList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
//        newUser = new Users("jaja123","456","780","123");
//        String newUserString = newUser.getName()+","+newUser.getId()+","+ newUser.getUsername()+","+ newUser.getPassword();
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (Complaint complaint : complaintList.getAllComplaint()) {
                String line = complaint.getHeadComplaint() + ","
                        + complaint.getBodyComplaint() + ","
                        + complaint.getCATEGORY()+","
                        + complaint.getNameWriter()+","
                        + complaint.getTime();

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
        return "ComplaintListDataSource{" +
                "directoryName='" + directoryName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
    //--------

}
