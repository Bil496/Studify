package data;

import model.*;

/**
 * Created by ask on 17.02.2018
 */
public class CreateUser {

    public Topic getTopicWithEnrolledUsers(){
        Topic topic = new Topic();
        topic.setId((long) 1);
        topic.setTitle("Software Exam");
        //TODO if necessary, please set grouping times

        SubTopic subTopic = new SubTopic();
        subTopic.setTopic(topic);
        subTopic.setTitle("Python");
        subTopic.setId((long) 1);

        SubTopic subTopic2 = new SubTopic();
        subTopic.setTopic(topic);
        subTopic.setTitle("Java");
        subTopic.setId((long) 2);

        SubTopic subTopic3 = new SubTopic();
        subTopic.setTopic(topic);
        subTopic.setTitle("Android");
        subTopic.setId((long) 3);

        //User 1
        User user = new User();
        user.setId((long) 1);
        user.setUsername("user1");
        user.setPassword("user1pass");
        user.setEmail("user1@somemail.com");
        user.setName("User1");
        user.setSurname("User1");

        //User 1 details
        Talent talent = new Talent();
        talent.setScore(1);
        UserSubTopicId userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 2
        user = new User();
        user.setId((long) 2);
        user.setUsername("user2");
        user.setPassword("user2pass");
        user.setEmail("user2@somemail.com");
        user.setName("User2");
        user.setSurname("User2");

        //User 2 details
        talent = new Talent();
        talent.setScore(2);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 3
        user = new User();
        user.setId((long) 3);
        user.setUsername("user3");
        user.setPassword("user3pass");
        user.setEmail("user3@somemail.com");
        user.setName("User3");
        user.setSurname("User3");

        //User 3 details
        talent = new Talent();
        talent.setScore(4);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 4
        user = new User();
        user.setId((long) 4);
        user.setUsername("user4");
        user.setPassword("user4pass");
        user.setEmail("user4@somemail.com");
        user.setName("User4");
        user.setSurname("User4");

        //User 4 details
        talent = new Talent();
        talent.setScore(1);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 5
        user = new User();
        user.setId((long) 5);
        user.setUsername("user5");
        user.setPassword("user5pass");
        user.setEmail("user5@somemail.com");
        user.setName("User5");
        user.setSurname("User5");

        //User 5 details
        talent = new Talent();
        talent.setScore(5);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 6
        user = new User();
        user.setId((long) 6);
        user.setUsername("user6");
        user.setPassword("user6pass");
        user.setEmail("user6@somemail.com");
        user.setName("User6");
        user.setSurname("User6");

        //User 6 details
        talent = new Talent();
        talent.setScore(2);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 7
        user = new User();
        user.setId((long) 7);
        user.setUsername("user7");
        user.setPassword("user7pass");
        user.setEmail("user7@somemail.com");
        user.setName("User7");
        user.setSurname("User7");

        //User 7 details
        talent = new Talent();
        talent.setScore(4);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 8
        user = new User();
        user.setId((long) 8);
        user.setUsername("user8");
        user.setPassword("user8pass");
        user.setEmail("user8@somemail.com");
        user.setName("User8");
        user.setSurname("User8");

        //User 8 details
        talent = new Talent();
        talent.setScore(3);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 9
        user = new User();
        user.setId((long) 9);
        user.setUsername("user9");
        user.setPassword("user9pass");
        user.setEmail("user9@somemail.com");
        user.setName("User9");
        user.setSurname("User9");

        //User 9 details
        talent = new Talent();
        talent.setScore(1);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        //User 10
        user = new User();
        user.setId((long) 10);
        user.setUsername("user10");
        user.setPassword("user10pass");
        user.setEmail("user10@somemail.com");
        user.setName("User10");
        user.setSurname("User10");

        //User 10 details
        talent = new Talent();
        talent.setScore(2);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        //User 11
        user = new User();
        user.setId((long) 11);
        user.setUsername("user11");
        user.setPassword("user11pass");
        user.setEmail("user11@somemail.com");
        user.setName("User11");
        user.setSurname("User11");

        //User 11 details
        talent = new Talent();
        talent.setScore(0);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 12
        user = new User();
        user.setId((long) 12);
        user.setUsername("user12");
        user.setPassword("user12pass");
        user.setEmail("user12@somemail.com");
        user.setName("User12");
        user.setSurname("User12");

        //User 12 details
        talent = new Talent();
        talent.setScore(2);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 13
        user = new User();
        user.setId((long) 13);
        user.setUsername("user13");
        user.setPassword("user13pass");
        user.setEmail("user13@somemail.com");
        user.setName("User13");
        user.setSurname("User13");

        //User 13 details
        talent = new Talent();
        talent.setScore(2);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 14
        user = new User();
        user.setId((long) 14);
        user.setUsername("user14");
        user.setPassword("user14pass");
        user.setEmail("user14@somemail.com");
        user.setName("User14");
        user.setSurname("User14");

        //User 14 details
        talent = new Talent();
        talent.setScore(1);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 15
        user = new User();
        user.setId((long) 15);
        user.setUsername("user15");
        user.setPassword("user15pass");
        user.setEmail("user15@somemail.com");
        user.setName("User15");
        user.setSurname("User15");

        //User 15 details
        talent = new Talent();
        talent.setScore(3);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(1);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 16
        user = new User();
        user.setId((long) 16);
        user.setUsername("user16");
        user.setPassword("user16pass");
        user.setEmail("user16@somemail.com");
        user.setName("User16");
        user.setSurname("User16");

        //User 16 details
        talent = new Talent();
        talent.setScore(0);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 17
        user = new User();
        user.setId((long) 17);
        user.setUsername("user17");
        user.setPassword("user17pass");
        user.setEmail("user17@somemail.com");
        user.setName("User17");
        user.setSurname("User17");

        //User 17 details
        talent = new Talent();
        talent.setScore(5);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(5);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 18
        user = new User();
        user.setId((long) 18);
        user.setUsername("user18");
        user.setPassword("user18pass");
        user.setEmail("user18@somemail.com");
        user.setName("User18");
        user.setSurname("User18");

        //User 18 details
        talent = new Talent();
        talent.setScore(1);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(4);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 19
        user = new User();
        user.setId((long) 19);
        user.setUsername("user19");
        user.setPassword("user19pass");
        user.setEmail("user19@somemail.com");
        user.setName("User19");
        user.setSurname("User19");

        //User 19 details
        talent = new Talent();
        talent.setScore(1);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(0);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        // User 20
        user = new User();
        user.setId((long) 20);
        user.setUsername("user20");
        user.setPassword("user20pass");
        user.setEmail("user20@somemail.com");
        user.setName("User20");
        user.setSurname("User20");

        //User 20 details
        talent = new Talent();
        talent.setScore(1);
        userSubTopicId  = new UserSubTopicId(user,subTopic);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(2);
        userSubTopicId = new UserSubTopicId(user, subTopic2);
        talent.setUserSubTopicId(userSubTopicId);

        talent = new Talent();
        talent.setScore(3);
        userSubTopicId = new UserSubTopicId(user, subTopic3);
        talent.setUserSubTopicId(userSubTopicId);

        topic.getUsers().add(user);

        return topic;

    }
}
