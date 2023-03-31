package dq.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Course {
    private final String C;
    private final String Cname;
    private final Float Credit;
    private final Integer Chours;
}
