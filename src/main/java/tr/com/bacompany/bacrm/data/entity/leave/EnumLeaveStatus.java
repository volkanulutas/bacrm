package tr.com.bacompany.bacrm.data.entity.leave;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumLeaveStatus {
    WAITING("Beklemede"),
    APPROVED("Onaylandı"),
    REMOVED_BY_USER("Kullanıcı Tarafından Silindi"), // deleted by user.
    REJECTED("Reddedildi"); // rejected by managers.

    private  String label;

    EnumLeaveStatus(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
    @JsonCreator
    public static EnumLeaveStatus forValues(String name) {
        for (EnumLeaveStatus status : EnumLeaveStatus.values()) {
            if(status.name().equals(name)){
                return status;
            }
        }
        return null;
    }
}
