package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EnrollControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final User user = new User("rodrigo", "rodrigo@gmail.com");
    private final Course course = new Course("spring-1", "Spring Boot", "Microservices with SpringBoot");
    private final User user2 = new User("ruan", "ruan@gmail.com");
    private final Course course2 = new Course("java-1", "Java basics", "Basics concepts about Java");
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void should_add_new_enroll() throws Exception {
        userRepository.save(user);
        courseRepository.save(course);

        NewEnrollRequest newEnrollRequest = new NewEnrollRequest("rodrigo");

        mockMvc.perform(post("/courses/spring-1/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newEnrollRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_not_allow_username_nonexistent() throws Exception {
        courseRepository.save(course);

        NewEnrollRequest newEnrollRequest = new NewEnrollRequest("rodrigo");

        mockMvc.perform(post("/courses/spring-1/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newEnrollRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_allow_course_code_nonexistent() throws Exception {
        userRepository.save(user);

        NewEnrollRequest newEnrollRequest = new NewEnrollRequest("rodrigo");

        mockMvc.perform(post("/courses/spring-1/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newEnrollRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_allow_user_enrolled_in_course() throws Exception {
        userRepository.save(user);
        courseRepository.save(course);
        enrollRepository.save(new Enroll(user, course, LocalDateTime.now()));

        NewEnrollRequest newEnrollRequest = new NewEnrollRequest("rodrigo");

        mockMvc.perform(post("/courses/spring-1/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newEnrollRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}