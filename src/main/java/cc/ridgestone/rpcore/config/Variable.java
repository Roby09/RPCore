package cc.ridgestone.rpcore.config;

public enum Variable {

    CHAT_FORMAT("&e[%rpcore_age%] &a[%rpcore_role%] &f%rpcore_name%: &7%message%"),
    CHAT_RANGE("7"),
    SHOUT_RANGE("15"),
    WHISPER_RANGE("3"),
    QUIET_RANGE("1");

    private String value;

    Variable(String value) {
        this.value = value;
    }

    public void setVariable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
