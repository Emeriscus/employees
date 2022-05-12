package employees;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(Student student);

    List<StudentDto> toDto(List<Student> students);
}
