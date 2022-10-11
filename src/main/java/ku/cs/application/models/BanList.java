package ku.cs.application.models;

import java.util.ArrayList;

public class BanList {

    private ArrayList<Ban> banList;
    public BanList() {
        banList = new ArrayList<>();
    }
    public  ArrayList<Ban> getBanList() {
        return banList;
    }
    public void addBan(Ban ban) {
        banList.add(ban);
    }
    public void unban(String bannedID) {
        Ban ban = findID(bannedID);
        banList.remove(ban);
    }

    public Ban findID(String bannedID) {
        Ban ban = null;
        for (Ban temp : banList) {
            if (bannedID.equals(temp.getBannedID())){
                ban = temp;
                break;
            }
        }
        return ban;
    }


}
