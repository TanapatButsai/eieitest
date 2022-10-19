package ku.cs.application.models;

import ku.cs.application.services.Filterer;

import java.util.*;

public class VoteList {
    private ArrayList<Vote> votes;

    public VoteList() {
        this.votes = new ArrayList<>();
    }
    public void add(Vote vote){
        votes.add(vote);
    }
    public void addVotedUsername(Vote vote,String username){
        Vote found = find(vote.getComplaintObject());
        found.addUsername(username);
    }
    public ArrayList<Vote> getAllVotes() {
        return votes;
    }
    public void remove(String complaintObject){
        Vote vote = find(complaintObject);
        votes.remove(vote);
    }
    public Vote find(String complaintObject){
        for (Vote found:votes){
            if (complaintObject.equals(found.getComplaintObject())){
                return found;
            }
        }
        return null;
    }
    public void add(String object,String newUsername){
        Vote vote = find(object);
        if (vote != null) {
            vote.addUsername(newUsername);
        }
    }
    public Vote findUser(String object) {
        for (Vote vote : votes) {
            if (vote.getComplaintObject().equals(object)) {
                return vote;
            }
        }return null;
    }

    @Override
    public String toString() {
        return "VoteList{" +
                "votes=" + votes +
                '}';
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }
}
