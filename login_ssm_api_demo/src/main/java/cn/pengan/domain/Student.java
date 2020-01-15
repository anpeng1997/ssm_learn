package cn.pengan.domain;

public class Student {
    private Integer Id;
    private String name;
    private Integer age;
    private Integer score;

    public Student() {
    }

    public Student(Integer id, String name, Integer age, Integer score) {
        this.Id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
