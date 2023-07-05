package cc.ridgestone.rpcore.config;

public enum Variable {

    TEST("");

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
