package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.util.Combinations;

import model.Team;
import model.Topic;
import model.User;

public class GreedyStrongNashEquilibriumAlgorithm extends MatchingAlgorithm {
    
    private Set<Team> teams = new HashSet<>();
    
    public GreedyStrongNashEquilibriumAlgorithm(Topic topic, List<User> users) {
	super(topic, users);
    }
    
    private class Candidate implements Comparable<Candidate> {

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

	@Override
	public int compareTo(Candidate o) {
	    return Integer.compare(jointUtility, o.jointUtility);
	}
	
	public boolean intersects(Candidate o) {
	    for (int i: members) {
		for (int j: o.members) {
		    if (i == j) {
			return true;
		    }
		}
	    }
	    return false;
	}
    }
    
    private CandidateBag candidateBag = new CandidateBag();
    
    private class CandidateBag {

	private ArrayList<Candidate> candidates = new ArrayList<>();

	public Candidate getCandidateWithGreatestJointUtility() {
	    return candidates.get(candidates.size() - 1);
	}
	
	public void add(Candidate candidate) {
	    candidates.add(candidate);
	    for (int i = candidates.size() - 1; i > 0 && candidate.compareTo(candidates.get(i - 1)) < 0; i--) {
		Collections.swap(candidates, i, i - 1);
	    }
	}
	
	public void removeCandidatesWithMembersOf(Candidate candidate) {
	    candidates.removeIf(o -> candidate.intersects(o));
	}
	
	
	public boolean isEmpty() {
	    return candidates.isEmpty();
	}

    }

    @Override
    public Set<Team> match() {
	// Add all possible subsets of maximumum size to candidate bag.
	for (int[] combination : new Combinations(users.size(), topic.getMaxSize())) {
	    Candidate candidate = new Candidate(combination);
	    candidateBag.add(candidate);
	}
	
	return teams;
    }

}
