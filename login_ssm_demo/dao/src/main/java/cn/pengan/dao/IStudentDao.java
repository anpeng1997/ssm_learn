package cn.pengan.dao;

import cn.pengan.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
public interface IStudentDao {
    @Select("select * from students")
    public List<Student> findAll();

    @Insert("insert into students values(null,#{name},#{age},#{score})")
    public int saveStudent(Student student);

    @Select("select * from students limit #{pageSize} offset #{offset}")
    public List<Student> findStudentPage(@Param("pageSize") int pageSize,@Param("offset") int offset);

    @Select("select count(*) from students")
    public int findStudentCount();

    @Select("select * from students where id = #{id}")
    public Student findStudentById(@Param("id") int id);

    @Delete({"<script>",
            "delete from students where id in"
            ,"<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    public void deleteStudentById(@Param("ids")Integer[] ids);
}
