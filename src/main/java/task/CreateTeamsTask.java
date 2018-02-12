package task;

import org.springframework.stereotype.Component;

@Component
public class CreateTeamsTask implements Runnable {

    @Override
    public void run() {
	System.out.println(System.currentTimeMillis());
	// Implement Studfy Algorithm here...
    }

}
