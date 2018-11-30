package view.util;

public enum MessageType {

    DANGER("alert-danger"),
    WARNING("alert-warning"),
    SUCCESS("alert-success"),
    INFO("alert-info");


    private String reference;

    MessageType(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }
}
