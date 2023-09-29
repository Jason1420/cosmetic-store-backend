package com.store.cosmetic.help;

public enum ItemBrand {
    BRAND_1("brand1"),
    BRAND_2("brand2"),
    BRAND_3("brand3"),
    BRAND_4("brand4"),
    BRAND_5("brand5"),
    BRAND_6("brand6");

    public final String label;

    private ItemBrand(String label) {
        this.label = label;
    }
}
