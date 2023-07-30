package cc.ridgestone.rpcore.config;

public enum Variable {

    CHAT_FORMAT("&e[%rpcore_age%] &a[%rpcore_role%] &f%rpcore_name%: &7%message%"),
    OOC_FORMAT("&e%player%: &7%message%"),
    LOOC_FORMAT("&e%player%: &7%message%"),
    WOOC_FORMAT("&e%player%: &7%message%"),
    WHISPER_FORMAT("&e%player%: &7%message%"),
    WHISPER_COLOR("&7"),
    SHOUT_FORMAT("&e%player%: &7%message%"),
    SHOUT_COLOR("&7"),
    QUIET_FORMAT("&e%player%: &7%message%"),
    QUIET_COLOR("&7"),
    EMOTE_FORMAT("&e[Emote] &6** %rpcore_name% %message%"),
    BIO_FORMAT("&e%rpcore_name%'s bio is &7%rpcore_bio%"),
    QUOTE_FORMAT("&7[%rpcore_name%] %message%"),
    ROLL_FORMAT("&7%rpcore_name% rolled %number% out of %givennumber%"),
    ROLL_RANGE("7"),
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
