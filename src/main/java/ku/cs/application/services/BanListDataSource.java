package ku.cs.application.services;

import ku.cs.application.models.Ban;
import ku.cs.application.models.BanList;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;

import java.io.*;

public class BanListDataSource implements DataSource<BanList>{

    private BanList banList;

    public BanListDataSource(boolean doReadData) {
        banList = new BanList();
        checkFileIsExisted();
        if (doReadData) {
            readData();
        }
    }

    private void checkFileIsExisted() {
        String directoryName = "data";
        String fileName = "bans.csv";
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

    public BanList getBanList() {
        return banList;
    }

    public void setBanList(BanList banList) {
        this.banList = banList;
    }

    @Override
    public BanList readData() {
        BanList list = new BanList();
        String filePath = "data" + File.separator + "bans.csv";
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                String bannedID = data[0].trim();
                String user = data[1].trim();
                String bannedReason = data[2].trim()
                        .replace("\\[newline]","\n")
                        .replace("\\[doublequote]","\"")
                        .replace("\\[comma]",",");
                String bannedObjectID = data[3].trim();
                String time = data[4].trim();
                boolean isActive = Boolean.parseBoolean(data[5].trim());
                int tryLogin = Integer.parseInt(data[6].trim());
                String comment = data[7].trim();

                Ban ban = new Ban(bannedID,user,bannedReason,bannedObjectID,time,isActive,tryLogin,comment);
                banList.addBan(ban);
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
        return banList;
    }

    @Override
    public void writeData(BanList banList) {
        String filePath = "data" + File.separator + "bans.csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (Ban ban  : banList.getBanList()) {
                String line = ban.toCSV();
                buffer.append(line);
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
