package employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
    void sayHelloTest() {
        HelloService helloService= new HelloService();
        String message=helloService.sayHello();

        assertThat(message).startsWith("Hello");
    }

    @Test
    void sayGoodByTest(){
        HelloService helloService= new HelloService();
        String message=helloService.sayGoodBy();

        assertThat(message).startsWith("Good by");
    }
}