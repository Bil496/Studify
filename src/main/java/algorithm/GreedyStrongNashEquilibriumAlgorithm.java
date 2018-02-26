package algorithm;

import java.util.List;

import model.Team;
import model.Topic;
import model.User;

public class GreedyStrongNashEquilibriumAlgorithm extends MatchingAlgorithm {

    public GreedyStrongNashEquilibriumAlgorithm(Topic topic, List<User> users) {
	super(topic, users);
    }
    
    private class Candidate {

	private int[] members;
	private int jointUtility;

	public Candidate(int[] members) {
	    this.members = members;

	    // Initialize joint talent vector
	    Integer[] jointTalent = new Integer[topic.getMaxSize()];
	    for (int i = 0; i < topic.getMaxSize(); i++) {
		jointTalent[i] = 0;
	    }

	    // Calculate joint talent vector
	    for (int i : members) {
		for (int j = 0; j < topic.getMaxSize(); j++) {
		    User user = users.get(i);
		    //List<Talent> talents = user.getTalents();
		    int[] talents = user.getTalents().stream().mapToInt(t -> t.getScore()).toArray();
		    
		    if (talents[i] > jointTalent[j]) {
			jointTalent[j] = talents[i]; 
		    }
		}
	    }

	    // Calculate joint utility
	    jointUtility = 0;
	    for (int i = 0; i < topic.getMaxSize(); i++) {
		jointUtility += jointTalent[i];
	    }
	}

    }

    @Override
    public List<Team> match() {
	return null;
    }

}
