package cc.ridgestone.rpcore.config;

public enum Variable {

    CHAT_FORMAT("&e[%rpcore_age%] &a[%rpcore_role%] &f%rpcore_name%: &7%message%"),
    IT_MESSAGE("&e[!] %message%"),
    IT_SHOUT("&c[S] &e[!] %message%"),
    IT_QUIET("&b[Q] &e[!] %message%"),
    IT_WHISPER("&9[W] &e[!] %message%"),
    OOC_FORMAT("&e%player%: &7%message%"),
    LOOC_FORMAT("&e%player%: &7%message%"),
    WOOC_FORMAT("&e%player%: &7%message%"),
    WHISPER_FORMAT("&9[W] &e[%rpcore_age%] &a[%rpcore_role%] &f%rpcore_name%: &7%message%"),
    WHISPER_COLOR("&7"),
    SHOUT_FORMAT("&c[S] &e[%rpcore_age%] &a[%rpcore_role%] &f%rpcore_name%: &7%message%"),
    SHOUT_COLOR("&7"),
    QUIET_FORMAT("&b[Q] &e[%rpcore_age%] &a[%rpcore_role%] &f%rpcore_name%: &7%message%"),
    QUIET_COLOR("&7"),
    EMOTE_FORMAT("&e[Emote] &6** %rpcore_name% %message%"),
    SHOUT_EMOTE("&c[S] &e[Emote] &6** %rpcore_name% %message%"),
    QUIET_EMOTE("&b[Q] &e[Emote] &6** %rpcore_name% %message%"),
    WHISPER_EMOTE("&9[W] &e[Emote] &6** %rpcore_name% %message%"),
    BIO_FORMAT("&2[&7RC&2] &7>> &7&l%player_name%'s BIO&8:,&7&m---------------------------------------------------,&2&lNAME&8: &f%rpcore_name%,&2&lAGE&8: &f%rpcore_age%,&2&lDESCRIPTION: &f%rpcore_bio%,&2&lROLE&8: &f%uperms_prefixes%,&7&m---------------------------------------------------"),
    QUOTE_FORMAT("&7[%rpcore_name%] %message%"),
    SHOUT_QUOTE_FORMAT("&c[S] &7[%rpcore_name%] %message%"),
    QUIET_QUOTE_FORMAT("&b[Q] &7[%rpcore_name%] %message%"),
    WHISPER_QUOTE_FORMAT("&9[W] &7[%rpcore_name%] %message%"),
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
