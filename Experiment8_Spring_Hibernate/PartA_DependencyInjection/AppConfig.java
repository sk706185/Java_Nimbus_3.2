import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course course() {
        return new Course("Spring Framework", "CS101");
    }

    @Bean
    public Student student() {
        Student s = new Student();
        s.setName("Alice");
        s.setCourse(course());
        return s;
    }
}
