package com.store.cosmetic.help;

public enum ItemType {
    TYPE_1("type1"),
    TYPE_2("type2"),
    TYPE_3("type3"),
    TYPE_4("type4"),
    TYPE_5("type5"),
    TYPE_6("type6");

    public final String label;

    private ItemType(String label) {
        this.label = label;
    }
}
