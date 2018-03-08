package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.util.Combinations;

import model.Team;
import model.Topic;
import model.User;
import util.RandomStringGenerator;

public class GreedyStrongNashEquilibriumAlgorithm extends MatchingAlgorithm {

    private Set<Team> teams = new HashSet<>();
    
    private Set<Integer> unmatchedUserIndices;
    
    public GreedyStrongNashEquilibriumAlgorithm(Topic topic, List<User> users) {
	super(topic, users);
	unmatchedUserIndices = IntStream.range(0, users.size()).boxed().collect(Collectors.toCollection(HashSet::new));
	
	// Add all possible subsets of maximumum size to candidate bag.
	for (int[] combination : new Combinations(users.size(), topic.getMaxSize())) {
	    Candidate candidate = new Candidate(combination);
	    candidateBag.add(candidate);
	}

	while (!candidateBag.isEmpty()) {
	    Candidate candidate = candidateBag.getCandidateWithGreatestJointUtility();
	    formTeam(candidate.memberIndices);
	    removeFromUnmatchedUserIndices(candidate.memberIndices);
	    candidateBag.removeCandidatesWithMembersOf(candidate);
	}
	
	if (unmatchedUserIndices.size() >= topic.getMinSize()) {
	    int[] userIndices = unmatchedUserIndices.stream().mapToInt(i -> i).toArray();
	    formTeam(userIndices);
	    removeFromUnmatchedUserIndices(userIndices);
	}
	
    }

    private class Candidate implements Comparable<Candidate> {

	private int[] memberIndices;
	private int jointUtility;

	public Candidate(int[] memberIndices) {
	    this.memberIndices = memberIndices;

	    // Initialize joint talent vector
	    Integer[] jointTalent = new Integer[topic.getMaxSize()];
	    for (int i = 0; i < topic.getMaxSize(); i++) {
		jointTalent[i] = 0;
	    }

	    // Calculate joint talent vector
	    for (int i : memberIndices) {
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
	    for (int i: memberIndices) {
		for (int j: o.memberIndices) {
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
    public Set<Team> getTeams() {	
	return teams;
    }
    
    @Override
    public Set<User> getUnmatchedUsers() {
	return unmatchedUserIndices.stream().map(i -> users.get(i)).collect(Collectors.toCollection(HashSet::new));
    }
    
    @Override
    public boolean leftUnmatchedUsers() {
	return !unmatchedUserIndices.isEmpty();
    }
    
    private static final int TEAM_NAME_WORD_COUNT = 3;
    
    private void formTeam(int[] membersIndices) {
	Team team = new Team();
	team.setTopic(topic);
        team.setSize(membersIndices.length);
        team.setName(RandomStringGenerator.getSentence(TEAM_NAME_WORD_COUNT));
        Set<User> members = new HashSet<>();
        for (int i: membersIndices) {
            users.add(users.get(i));
        }
        team.setUsers(members);
    }
    
    private void removeFromUnmatchedUserIndices(int[] userIndices) {
	for (int i: userIndices) {
	    unmatchedUserIndices.remove(i);
	}
    }

}
