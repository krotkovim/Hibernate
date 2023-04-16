import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "linked_purchaselist")
public class LinkedPurchaseList {
    @EmbeddedId
    private KeyForLinkedPurchaseList keyForLinkedPurchaseList;

    @JoinColumn(name = "student_id", insertable = false, updatable = false )
    private int studentId;

    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private int courseId;


    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(KeyForLinkedPurchaseList keyForLinkedPurchaseList, int studentId, int courseId) {
        this.keyForLinkedPurchaseList = keyForLinkedPurchaseList;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public KeyForLinkedPurchaseList getKeyForLinkedPurchaseList() {
        return keyForLinkedPurchaseList;
    }

    public void setKeyForLinkedPurchaseList(KeyForLinkedPurchaseList keyForLinkedPurchaseList) {
        this.keyForLinkedPurchaseList = keyForLinkedPurchaseList;
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
}
