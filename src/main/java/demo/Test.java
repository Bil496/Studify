package demo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import demo.model.SubTopic;
import demo.model.Team;
import demo.model.Topic;
import demo.model.User;

public class Test {

    public static void main(String[] args) throws JSONException, JsonGenerationException, JsonMappingException, IOException {
        Reader.createStash();
        Stash stash = Stash.getInstance();

//        User ferdem = stash.findUserByUsername("ferdem");
//        Team team1 = stash.findTeamByName("Kurucu Tim");
//
//        System.out.println(ferdem.toJSONObject());
//        System.out.println(team1.toJSONObject());
//        
//        ferdem.quitCurrentTeam();
//        ferdem.quitCurrentTopic();
//        ferdem.setCurrentTopic(stash.getTopic(5));
//        
//        ferdem.setTalentLevelOfSubTopic(stash.getSubTopic(14), Float.floatToIntBits(4f));
//        ferdem.setTalentLevelOfSubTopic(stash.getSubTopic(15), Float.floatToIntBits(4f));
//        ferdem.setTalentLevelOfSubTopic(stash.getSubTopic(16), Float.floatToIntBits(4f));
//        ferdem.setTalentLevelOfSubTopic(stash.getSubTopic(17), Float.floatToIntBits(4f));
//        
//        Team team = new Team();
//        team.setTopic(ferdem.getCurrentTopic());
//        team.setName(RandomStringGenerator.getSentence(2));
//        System.out.println(team.getJointUtility());
//        
//        team.addMember(ferdem);
//        System.out.println(team.getJointUtility());
        
        System.out.println("USERS");
        System.out.println("------------------------------------------------------------");
        for (User user : stash.getUsers()) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Location: " + user.getCurrentLocation().getTitle());
            System.out.println("Topic: " + user.getCurrentTopic().getTitle());
            if (user.getCurrentTeam() != null) {
                System.out.println("Team:" + user.getCurrentTeam().getName());
            } else {
                System.out.println("Not in a team!");
            }
            System.out.println();
        }

        System.out.println("TOPICS");
        System.out.println("------------------------------------------------------------");
        for (Topic topic : stash.getTopics()) {
            System.out.println("Title: " + topic.getTitle());
            System.out.println("Location: " + topic.getLocation().getTitle());
            System.out.println("User Count:" + topic.getUserCount());
            System.out.println("Sub-Topics: ");
            for (SubTopic subTopic : topic.getSubTopics()) {
                System.out.println(subTopic.getTitle());
            }
            System.out.println("Teams: ");
            for (Team team : topic.getTeams()) {
                System.out.println(team.getName());
            }
            System.out.println();
        }

        System.out.println("TEAMS");
        System.out.println("------------------------------------------------------------");
        for (Team team : stash.getTeams()) {
            System.out.println("Name: " + team.getName());
            System.out.println("Topic: " + team.getTopic().getTitle());
            System.out.println("Location: " + team.getTopic().getLocation().getTitle());
            System.out.println("Joint Utility: " + team.getJointUtility());
            System.out.println("Total Utility: " + team.getTotalUtility());
            System.out.println("Members: ");
            for (User user : team.getMembers()) {
                System.out.println(user.getUsername());
            }
            System.out.println();
        }
    }
}
