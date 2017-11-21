import annotations.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @ExcelField("First name")
    private String firstname;

    @ExcelField("Last name")
    private String lastname;

    @ExcelField("Age")
    private int age;
}
