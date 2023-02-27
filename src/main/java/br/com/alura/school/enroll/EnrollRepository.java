package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface EnrollRepository extends JpaRepository<Enroll, Long> {
    boolean existsByUserAndCourse(User user, Course course);

    @Query("SELECT new br.com.alura.school.enroll.EnrollReportRecord(u.email, COUNT(course_id)) FROM Enroll e JOIN e.user u GROUP BY u.email ORDER BY COUNT(course_id) DESC")
    List<EnrollReportRecord> getEnrollReport();

}
