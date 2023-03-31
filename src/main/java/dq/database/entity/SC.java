package dq.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SC {
    private final String S;
    private final String C;
    private final Float Score;
}
