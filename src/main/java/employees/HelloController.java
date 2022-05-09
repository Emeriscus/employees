package employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController         // Ha ezt használom, úgy veszi a Sping, hogy minden metóduson rajta van a @ResponseBody annotáció.
public class HelloController {

    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String sayHello(){
        return helloService.sayHello();
    }

    @GetMapping("/target")
    public String sayGoodBy(){
        return helloService.sayGoodBy();
    }
}
