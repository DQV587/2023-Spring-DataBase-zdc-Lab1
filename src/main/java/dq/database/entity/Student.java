package dq.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student  {
    private final String S;
    private final String sex;
    private final Integer Sage;
    private final String SClass;
    private final String Sname;
}
