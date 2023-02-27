package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
class Enroll {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @Column
    private LocalDateTime enrolledIn;

    @Deprecated
    protected Enroll() {
    }

    Enroll(User user, Course course, LocalDateTime enrolledIn) {
        this.user = user;
        this.course = course;
        this.enrolledIn = enrolledIn;
    }

    User getUser() {
        return user;
    }

    Course getCourse() {
        return course;
    }

    LocalDateTime getEnrolledIn() {
        return enrolledIn;
    }
}
