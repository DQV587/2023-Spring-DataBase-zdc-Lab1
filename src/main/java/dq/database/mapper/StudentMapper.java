package dq.database.mapper;

import dq.database.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public void insertStudent(Student student);
    public List<Student> getAllStudent();
}
