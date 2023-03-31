package dq.database.mapper;

import dq.database.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    public void insertCourse(Course course);
    public List<Course> getAllCourses();
}
