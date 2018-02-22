package task;

import org.springframework.stereotype.Component;

@Component
public class CreateTeamsTask implements Runnable {
    private long topicId;

    public CreateTeamsTask(long topicId) {
        this.topicId = topicId;
    }
    
    @Override
    public void run() {
        //System.out.println(System.currentTimeMillis());
        // Implement Studfy Algorithm here...
    }

}
