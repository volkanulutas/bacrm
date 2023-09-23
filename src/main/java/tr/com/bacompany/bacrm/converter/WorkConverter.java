package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.converter.user.UserConverter;
import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.entity.user.User;

import java.util.Set;
import java.util.stream.Collectors;

public class WorkConverter {
    public static Work toEntity(WorkDto source) {
        Work target = new Work();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDefinition(source.getDefinition());
        target.setPlanningDate(source.getPlanningDate());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setWorkloadHour(source.getWorkloadHour());
        return target;
    }

    public static WorkDto toDto(Work source) {
        WorkDto target = new WorkDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDefinition(source.getDefinition());
        target.setPlanningDate(source.getPlanningDate());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setWorkloadHour(source.getWorkloadHour());
        return target;
    }
}
