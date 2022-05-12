package employees;

//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

//    private ModelMapper modelMapper;

    private EmployeeMapper employeeMapper;

    //AtomicLong - hogy szálbiztos legyen, ennek a incrementAndGet() metódusa generálja az ID-t egyedileg
    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(idGenerator.incrementAndGet(), "John Doe"),
            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
    )));    // Collections.synchronizedList - több szálon is lehet hívni

//    public EmployeesService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    public EmployeesService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    //  Metódusok MapStruct-tal:
    //
    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        List<Employee> filtered = employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        return employeeMapper.toDto(filtered);
    }

    public EmployeeDto findEmployeeById(Long id) {
        return employeeMapper.toDto(employees.stream()
                .filter(employee -> employee.getId() == id).findAny()
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setName(command.getName());
        return employeeMapper.toDto(employee);
    }

    public void deleteEmployee(long id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employees.remove(employee);
    }

    public void deleteAllEmployees() {
        idGenerator = new AtomicLong();
        employees.clear();
    }


//    Metódusok modelMapper-rel:
//
//    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
//        Type targetListType = new TypeToken<List<EmployeeDto>>() {
//        }.getType();
//        List<Employee> filtered = employees.stream()
//                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
//                .collect(Collectors.toList());
//        return modelMapper.map(filtered, targetListType);
//    }
//
//    public EmployeeDto findEmployeeById(Long id) {
//
//        return modelMapper.map(employees.stream()
//                        .filter(employee -> employee.getId() == id).findAny()
//                        .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
//                EmployeeDto.class);
//    }
//
//    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
//        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
//        employees.add(employee);
//        return modelMapper.map(employee, EmployeeDto.class);
//    }
//
//    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
//        Employee employee = employees.stream()
//                .filter(e -> e.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
//        employee.setName(command.getName());
//        return modelMapper.map(employee, EmployeeDto.class);
//    }
//
//    public void deleteEmployee(long id) {
//        Employee employee = employees.stream()
//                .filter(e -> e.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
//        employees.remove(employee);
//    }
}
