package tr.com.bacompany.bacrm.data.dto.leave;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LeaveTypeDto {
    private String label;

    private String value;

    @JsonProperty("isLeaf")
    private boolean isLeaf = true;

    public LeaveTypeDto(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public LeaveTypeDto(String label, String value, boolean isLeaf) {
        this.label = label;
        this.value = value;
        this.isLeaf = isLeaf;
    }
}
