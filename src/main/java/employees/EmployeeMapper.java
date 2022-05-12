package employees;

import org.mapstruct.Mapper;

import java.util.List;

// Mapstruct miatt - létre kell hozni egy Mapper interface-t, amihez a Mapstruct fogja implementációt generálni

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDto(List<Employee> employees);
}
