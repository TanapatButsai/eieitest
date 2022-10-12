package ku.cs.application.services;

import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
//import ku.cs.application.models.Officer;
//import ku.cs.application.models.OfficerList;

import java.io.*;

public class OfficerRoleDataSource implements OfficerDataSource<ComplaintList>{
    private String directoryName;
    private String fileName;

    public OfficerRoleDataSource(String directoryName, String fileName){
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

    @Override
    public ComplaintList readData1() {
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
                String officer1 = "normal";
                if (data[3].equals(officer1)){
                    String headComplaint = data[0].trim();
                    String bodyComplaint = data[1].trim();
                    String fixComplaint = data[2].trim();
                    String category = data[3].trim();
                    String nameWriter = data[4].trim();
                    String time = data[5].trim();
                    boolean done = Boolean.parseBoolean(data[6].trim());
                    boolean inProgress = Boolean.parseBoolean(data[7].trim());
                    boolean unmanaged = Boolean.parseBoolean(data[8].trim());
                    int rating = Integer.parseInt(data[9].trim());
                    boolean isBan = Boolean.parseBoolean(data[10].trim());
                    Complaint complaint = new Complaint(headComplaint,bodyComplaint,fixComplaint,category,nameWriter
                            ,time,done,inProgress,unmanaged,rating,isBan);
                    list.add(complaint);
                }
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
    public ComplaintList readData2() {
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
                String officer2 = "teacher";
                if (data[3].equals(officer2)){
                    String headComplaint = data[0].trim();
                    String bodyComplaint = data[1].trim();
                    String fixComplaint = data[2].trim();
                    String category = data[3].trim();
                    String nameWriter = data[4].trim();
                    String time = data[5].trim();
                    boolean done = Boolean.parseBoolean(data[6].trim());
                    boolean inProgress = Boolean.parseBoolean(data[7].trim());
                    boolean unmanaged = Boolean.parseBoolean(data[8].trim());
                    int rating = Integer.parseInt(data[9].trim());
                    boolean isBan = Boolean.parseBoolean(data[10].trim());
                    Complaint complaint = new Complaint(headComplaint,bodyComplaint,fixComplaint,category,nameWriter
                            ,time,done,inProgress,unmanaged,rating,isBan);
                    list.add(complaint);
                }
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
    public ComplaintList readData3() {
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
                String officer3 = "place";
                if (data[3].equals(officer3)){
                    String headComplaint = data[0].trim();
                    String bodyComplaint = data[1].trim();
                    String fixComplaint = data[2].trim();
                    String category = data[3].trim();
                    String nameWriter = data[4].trim();
                    String time = data[5].trim();
                    boolean done = Boolean.parseBoolean(data[6].trim());
                    boolean inProgress = Boolean.parseBoolean(data[7].trim());
                    boolean unmanaged = Boolean.parseBoolean(data[8].trim());
                    int rating = Integer.parseInt(data[9].trim());
                    boolean isBan = Boolean.parseBoolean(data[10].trim());
                    Complaint complaint = new Complaint(headComplaint,bodyComplaint,fixComplaint,category,nameWriter
                            ,time,done,inProgress,unmanaged,rating,isBan);
                    list.add(complaint);
                }
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
    public ComplaintList readData4() {
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
                String officer4 = "enroll";
                if (data[3].equals(officer4)){
                    String headComplaint = data[0].trim();
                    String bodyComplaint = data[1].trim();
                    String fixComplaint = data[2].trim();
                    String category = data[3].trim();
                    String nameWriter = data[4].trim();
                    String time = data[5].trim();
                    boolean done = Boolean.parseBoolean(data[6].trim());
                    boolean inProgress = Boolean.parseBoolean(data[7].trim());
                    boolean unmanaged = Boolean.parseBoolean(data[8].trim());
                    int rating = Integer.parseInt(data[9].trim());
                    boolean isBan = Boolean.parseBoolean(data[10].trim());
                    Complaint complaint = new Complaint(headComplaint,bodyComplaint,fixComplaint,category,nameWriter
                            ,time,done,inProgress,unmanaged,rating,isBan);
                    list.add(complaint);
                }
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
    public ComplaintList readData5() {
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
                String officer5 = "corrupt";
                if (data[3].equals(officer5)){
                    String headComplaint = data[0].trim();
                    String bodyComplaint = data[1].trim();
                    String fixComplaint = data[2].trim();
                    String category = data[3].trim();
                    String nameWriter = data[4].trim();
                    String time = data[5].trim();
                    boolean done = Boolean.parseBoolean(data[6].trim());
                    boolean inProgress = Boolean.parseBoolean(data[7].trim());
                    boolean unmanaged = Boolean.parseBoolean(data[8].trim());
                    int rating = Integer.parseInt(data[9].trim());
                    boolean isBan = Boolean.parseBoolean(data[10].trim());
                    Complaint complaint = new Complaint(headComplaint,bodyComplaint,fixComplaint,category,nameWriter
                            ,time,done,inProgress,unmanaged,rating,isBan);
                    list.add(complaint);
                }
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
        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (Complaint complaint : complaintList.getAllComplaint()) {
                String line = complaint.getHeadComplaint() + ","
                        + complaint.getBodyComplaint() + ","
                        + complaint.getFixComplaint()+","
                        + complaint.getCategory()+","
                        + complaint.getNameWriter()+","
                        + complaint.getTime()+","
                        + complaint.isDone()+","
                        + complaint.isInProgress()+","
                        + complaint.isUnmanaged()+","
                        + complaint.getRating()+","
                        + complaint.isBan();

                buffer.append(line);
                buffer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
