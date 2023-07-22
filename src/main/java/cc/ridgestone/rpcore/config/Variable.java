package cc.ridgestone.rpcore.config;

public enum Variable {

    CHAT_FORMAT("&e[%rpcore_age%] &a[%rpcore_role%] &f%rpcore_name%: &7%message%"),
    OOC_FORMAT("&e%player%: &7%message%"),
    EMOTE_FORMAT("&e[Emote] &6** %rpcore_name% %message%"),
    ME_RANGE("10"),
    OOC_COOLDOWN("60"),
    CHAT_RANGE("7"),
    LOOC_RANGE("10"),
    WOOC_RANGE("3"),
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
