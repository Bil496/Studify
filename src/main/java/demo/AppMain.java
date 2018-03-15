package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by burak on 3/15/2018.
 */

@SpringBootApplication
public class AppMain {
    public static void main(String args[]){
        Reader.createStash();
        SpringApplication.run(AppMain.class, args);
    }
}
