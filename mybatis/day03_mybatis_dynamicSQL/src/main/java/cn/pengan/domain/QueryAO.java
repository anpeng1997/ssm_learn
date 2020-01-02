package cn.pengan.domain;

import java.util.List;

public class QueryAO {
    private Student student;
    private List<Integer> ids;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
