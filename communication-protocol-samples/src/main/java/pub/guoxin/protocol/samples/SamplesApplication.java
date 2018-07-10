package pub.guoxin.protocol.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create by guoxin on 2018/6/14
 */
@SpringBootApplication
public class SamplesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SamplesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
