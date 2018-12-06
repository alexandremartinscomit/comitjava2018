package view.util;

public class Message {

    private String text;
    private MessageType type;

    public Message(String text, MessageType type) {
        this.text = text;
        this.type = type;
    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public MessageType getType() {
        return type;
    }

    public static Message buildSuccessMessage(String text){
        return new Message(text, MessageType.SUCCESS);
    }

    public static Message buildWarningMessage(String text){
        return new Message(text, MessageType.WARNING);
    }

    public static Message buildInfoMessage(String text){
        return new Message(text, MessageType.INFO);
    }

    public static Message buildDangerMessage(String text){
        return new Message(text, MessageType.DANGER);
    }

}
