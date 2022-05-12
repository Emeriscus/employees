package employees;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.zalando.problem.Problem;
//import org.zalando.problem.Status;

import javax.validation.Valid;
//import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Operations on employees")
public class EmployeesController {

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EmployeeDto findEmployeeById(@PathVariable("id") Long id) {
        return employeesService.findEmployeeById(id);
    }

//    Ha nem lenne globális hibakezelőnk, akor így kéne:
//    @GetMapping("/{id}")
//    public ResponseEntity findEmployeeById(@PathVariable("id") Long id){
//        try {
//            return ResponseEntity.ok(employeesService.findEmployeeById(id));
//        } catch (IllegalArgumentException iae){
//            return ResponseEntity.notFound().build();
//        }
//    }

    // @RequestBody ebből tudja a Spring, hogy a request body-jában szereplő adatokból kell létrehozni a Command-ot.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates an employee")
    @ApiResponse(responseCode = "201", description = "employee has been created")
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }


// Ha használjuk a problem-spring-web-startert, akkor ez nem kell, csak ha sima problem-et használunk:
    //    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
//
//        Problem problem = Problem.builder()
//                .withType(URI.create("employees/not-found"))
//                .withTitle("Not found")
//                .withStatus(Status.NOT_FOUND)
//                .withDetail(iae.getMessage())
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
//                .body(problem);
//    }
}
