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
    public Ban findObjectID(String user, String objectID) {
        Ban ban = null;
        for (Ban temp : banList) {
            if (objectID.equals(temp.getObjectID()) && user.equals(temp.getUser())) {
                ban = temp;
                break;
            }
        }
        return ban;
    }
    public void setRequestUnban(String username,String request){
        Ban ban = findBanByUsername(username);
        if (ban != null) {
            ban.setRequest(request);
        }
    }
    public Ban findBanByUsername(String username){
        for (Ban ban : banList) {
            if (username.equals(ban.getUser())) {
                return ban;
            }
        }
        return null;
    }
    public void tryLogin(String username){
        Ban ban = findBanByUsername(username);
        if (!(ban == null)){ban.setTryLogin(ban.getTryLogin()+1);}
    }
    @Override
    public String toString() {
        return "BanList{" +
                "banList=" + banList +
                '}';
    }

    public int getSize() {
        return banList.size();
    }
}
