package service;

import dao.TalentDao;
import model.Talent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentServiceImp implements TalentService {
    TalentDao talentDao;

    @Override
    public List<Talent> getTalentsByTopicId(long userId, long topicId) {
        return talentDao.getTalentsByTopicId(userId, topicId);
    }

    @Override
    public void save(Talent talent) {
        talentDao.save(talent);
    }
}
