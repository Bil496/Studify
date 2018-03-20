package dao;

import model.Talent;

import java.util.List;

public interface TalentDao {
    List<Talent> getTalentsByTopicId(long userId, long topicId);

    void save(Talent talent);
}
