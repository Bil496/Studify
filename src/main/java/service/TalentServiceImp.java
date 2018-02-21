package service;

import dao.TalentDao;
import model.Talent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentServiceImp implements TalentService {
    @Autowired
    TalentDao talentDao;

    @Override
    public List<Talent> getTalentsByTopicId(long userId, long topicId) {
        return talentDao.getTalentsByTopicId(userId, topicId);
    }
}
