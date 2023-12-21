package tr.com.bacompany.bacrm.data.entity.leave;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum EnumLeaveType {
    MAIN_FREE("Ücretsiz İzin", true),
    MAIN_PAID("Ücretli İzin", false),
    FREE_DISABILTY_REPORT("İşgörmezlik Raporu", true),
    FREE_DISABILTY_REPORT_6_WEEKS("İşgörmezlik Raporu (6 Hafta)", true),
    FREE_MATERNITY_LEAVE("Doğum İzni (Ücretsiz)", true),
    FREE_TRANSMISSION("Yol İzni", true),
    FREE_EXCUSE_LEAVE("Mazeret İzni", true),
    FREE_LEAVE("Ücretsiz İzin", true),
    PAID_ANNUAL("Yıllık İzin", false),
    PAID_EXCUSE("Mazeret İzni (Ücretli)", false),
    PAID_MARRIAGE("Evlilik İzni", false),
    PAID_PAID_MATERNITY_LEAVE("Doğum İzni", false),
    PAID_MILK_LEAVE("Süt İzni", false),
    PAID_ADMINISTRATIVE_LEAVE("İdari İzin", false),
    PAID_EDUCATION_LEAVE("Eğitim İzni", false),
    PAID_BEREAVEMENT_LEAVE("Vefat İzni", false),
    PAID_MATERNITY_LEAVE_FATHER("Doğum İzni (Baba)", false),
    PAID_OVERTIME_LEAVE("Fazla Mesai İzni", false);

    private String label;

    private boolean isFree;

    EnumLeaveType(String name, boolean isFree) {
        this.label = name;
        this.isFree = isFree;
    }

    /*
    @JsonValue
    public List<String> getLabel() {
        List<String> list = new ArrayList<>();
        list.add(name());
        list.add(label);
        return list;
    }

     */

    @JsonValue
    public String getLabel() {
        ;
        return label;
    }

    @JsonCreator
    public static EnumLeaveType forValues(String name) {
        for (EnumLeaveType leaveType : EnumLeaveType.values()) {
            if (leaveType.name().equals(name)) {
                return leaveType;
            }
        }
        return null;
    }
}
