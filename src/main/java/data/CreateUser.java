package data;

import model.*;

import java.util.Arrays;

/**
 * Created by ask on 17.02.2018
 */
public class CreateUser {

    public static Topic getTopicWithEnrolledUsers() {
        Topic topic = new Topic();
        topic.setTitle("Software Exam");
        //TODO if necessary, please set grouping times

        SubTopic subTopic = new SubTopic();
        subTopic.setTopic(topic);
        subTopic.setTitle("Python");

        SubTopic subTopic2 = new SubTopic();
        subTopic2.setTopic(topic);
        subTopic2.setTitle("Java");

        SubTopic subTopic3 = new SubTopic();
        subTopic3.setTopic(topic);
        subTopic3.setTitle("Android");

        topic.setSubTopics(Arrays.asList(subTopic, subTopic2, subTopic3));

        //User 1
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("user1pass");
        user1.setEmail("user1@somemail.com");
        user1.setName("User1");
        user1.setSurname("User1");

        //User 1 details
        Talent talent = new Talent();
        talent.setScore(1);
        UserSubTopicId userSubTopicId = new UserSubTopicId(user1, subTopic);
        talent.setUserSubTopicId(userSubTopicId);
        user1.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user1, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);
        user1.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user1, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);
        user1.getTalents().add(talent);
        topic.getUsers().add(user1);

        // User 2
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("user2pass");
        user2.setEmail("user2@somemail.com");
        user2.setName("User2");
        user2.setSurname("User2");

        //User 2 details
        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user2, subTopic);
        talent.setUserSubTopicId(userSubTopicId);
        user2.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user2, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);
        user2.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user2, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);
        user2.getTalents().add(talent);

        topic.getUsers().add(user2);

        // User 3
        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword("user3pass");
        user3.setEmail("user3@somemail.com");
        user3.setName("User3");
        user3.setSurname("User3");

        //User 3 details
        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user3, subTopic);
        talent.setUserSubTopicId(userSubTopicId);
        user3.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user3, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);
        user3.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user3, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);
        user3.getTalents().add(talent);

        topic.getUsers().add(user3);

        // User 4
        User user4 = new User();
        user4.setUsername("user4");
        user4.setPassword("user4pass");
        user4.setEmail("user4@somemail.com");
        user4.setName("User4");
        user4.setSurname("User4");

        //User 4 details
        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user4, subTopic);
        talent.setUserSubTopicId(userSubTopicId);
        user4.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user4, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);
        user4.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user4, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);
        user4.getTalents().add(talent);

        topic.getUsers().add(user4);

        // User 5
        User user5 = new User();
        user5.setUsername("user5");
        user5.setPassword("user5pass");
        user5.setEmail("user5@somemail.com");
        user5.setName("User5");
        user5.setSurname("User5");

        //User 5 details
        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user5, subTopic);
        talent.setUserSubTopicId(userSubTopicId);
        user5.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user5, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);
        user5.getTalents().add(talent);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user5, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);
        user5.getTalents().add(talent);

        topic.getUsers().add(user5);
        return topic;

    }
}
