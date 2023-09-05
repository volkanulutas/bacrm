package tr.com.bacompany.bacrm.data.dto;

import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.user.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class WorkDto implements Serializable {
    private Long id;


    private String name;

    private long startDate;


    private long endDate;

    private double workloadHour;

    private Set<UserDto> users = new HashSet<>();
}
