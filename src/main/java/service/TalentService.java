package service;

import model.Talent;

import java.util.List;

public interface TalentService {
    List<Talent> getTalentsByTopicId(long userId, long topicId);
}
