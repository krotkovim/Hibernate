import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private KeyForLinkedPurchaseList id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @Column(name = "subscription_date", insertable = false, updatable = false)
    private Date subscriptionDate;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    public KeyForLinkedPurchaseList getId() {
        return id;
    }

    public void setId(KeyForLinkedPurchaseList id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
