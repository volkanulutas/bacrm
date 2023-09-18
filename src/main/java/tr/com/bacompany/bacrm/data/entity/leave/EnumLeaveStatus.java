package tr.com.bacompany.bacrm.data.entity.leave;

public enum EnumLeaveStatus {
    WAITING,
    APPROVED,
    REMOVED_BY_USER, // deleted by user.
    REJECTED; // rejected by managers.
}
