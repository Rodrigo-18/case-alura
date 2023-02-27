package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
class EnrollController {

    private final EnrollRepository enrollRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    EnrollController(EnrollRepository enrollRepository,
                     CourseRepository courseRepository, UserRepository userRepository) {
        this.enrollRepository = enrollRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/courses/enroll/report")
    ResponseEntity<List<EnrollReportResponse>> enrollReport() {
        List<EnrollReportResponse> enrollReport = enrollRepository.getEnrollReport().stream().
                map(enroll -> new EnrollReportResponse(enroll)).collect(Collectors.toList());
        if(enrollReport.isEmpty()) throw new ResponseStatusException(NO_CONTENT, format("Does not have any enrollment records"));
        return ResponseEntity.ok(enrollReport);
    }

    @PostMapping("/courses/{courseCode}/enroll")
    ResponseEntity<Void> newEnroll(@RequestBody @Valid NewEnrollRequest newEnrollRequest, @PathVariable("courseCode") String courseCode) {
        String username = newEnrollRequest.getUsername();
        Course course = courseRepository.findByCode(courseCode).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, format("Course with code %s not found", courseCode)));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, format("User with username %s not found", username)));
        if (enrollRepository.existsByUserAndCourse(user, course)) throw new ResponseStatusException(BAD_REQUEST, format("The user is already enrolled in this course"));
        enrollRepository.save(new Enroll(user, course, LocalDateTime.now()));
        URI location = URI.create(format("/courses/%s/enroll/%s", courseCode, username));
        return ResponseEntity.created(location).build();
    }

}