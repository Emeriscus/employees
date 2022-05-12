package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

//    ha a pom.xml-ben a ModelMapper plugint használjuk, akkor:
//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }

    //    ha a pom.xml-ben a MapStruct plugint használjuk, akkor:

}
