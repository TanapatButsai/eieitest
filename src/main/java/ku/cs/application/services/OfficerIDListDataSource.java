package ku.cs.application.services;

import ku.cs.application.models.OfficerID;
import ku.cs.application.models.OfficerIDList;

import java.io.*;

public class OfficerIDListDataSource implements DataSource<OfficerIDList>{
    private String directoryName;
    private String fileName;

    public OfficerIDListDataSource(String directoryName, String fileName) {
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
    public OfficerIDList readData() {
        OfficerIDList list = new OfficerIDList();
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
                OfficerID officerID = new OfficerID(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim());
                list.addOfficer(officerID);
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
    public void writeData(OfficerIDList officerIDList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (OfficerID officerID : officerIDList.getAllOfficerID()) {
                String line =
                        officerID.getOfficerID() + ","
                        + officerID.getOfficerPassword() + ","
                        + officerID.setRole();
                buffer.append(line);
                buffer.newLine();
            }
//            buffer.append(newUserString);
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
