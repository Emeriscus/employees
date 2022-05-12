package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;
    private String name;
    private String address;

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
