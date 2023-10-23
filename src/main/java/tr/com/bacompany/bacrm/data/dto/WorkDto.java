package tr.com.bacompany.bacrm.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkDto implements Serializable {
    private Long id;

    private String name;

    private String definition;

    private long planningDate;

    private long startDate;

    private long endDate;

    private Float workloadHour;

    private Set<UserDto> users = new HashSet<>();
}
