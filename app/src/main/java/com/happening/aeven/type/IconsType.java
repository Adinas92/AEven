package com.happening.aeven.type;


public enum IconsType {
    MAKI_ICON_HARBOR("harbor-15"),
    MAKI_ICON_VOLLEYBALL("volleyball-15"),
    MAKI_ICON_MUSEUM("museum-15"),
    MAKI_ICON_LIBRARY("library-15"),
    MAKI_ICON_MONUMENT("monument-15"),
    MAKI_ICON_CAFE("cafe-15");

    private final String icon;

    IconsType(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return icon;
    }
}
