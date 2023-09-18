package tr.com.bacompany.bacrm.data.entity.leave;

public enum EnumLeaveType {
    FREE_DISABILTY_REPORT("İşgörmezlik Raporu", true),
    FREE_DISABILTY_REPORT_6_WEEKS("İşgörmezlik Raporu (6 Hafta)", true),
    FREE_MATERNITY_LEAVE("Doğum İzni (Ücretsiz)", true),
    FREE_TRANSMISSION("Yol İzni", true),
    FREE_EXCUSE_LEAVE("Mazeret İzni", true),
    FREE_LEAVE("Ücretsiz İzin", true),
    PAID_ANNUAL("Yıllık İzin", false),
    PAID_EXCUSE("Mazeret İzni (Ücretli)", false),
    PAID_MARRIAGE("Evnlenme İzni", false),
    PAID_PAID_MATERNITY_LEAVE("Doğum İzni", false),
    PAID_MILK_LEAVE("Süt İzni", false),
    PAID_ADMINISTRATIVE_LEAVE("İdari İzin", false),
    PAID_EDUCATION_LEAVE("Eğitim İzni", false),
    PAID_BEREAVEMENT_LEAVE("Vefat İzni", false),
    PAID_MATERNITY_LEAVE_FATHER("Doğum İzni (Baba)", false),
    PAID_OVERTIME_LEAVE("Fazla Mesai İzni", false);

    private String name;

    private boolean isFree;

    EnumLeaveType(String name, boolean isFree) {
        this.name = name;
        this.isFree = isFree;
    }
}
