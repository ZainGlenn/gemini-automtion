package com.gemini.util.components.table.model;

public enum SortDirection {

    UNSORTED,
    ASCENDING,
    DESCENDING;

    public static SortDirection fromCssClass(String cssSortClass) {
        if (cssSortClass.equals("asc")) {
            return ASCENDING;
        } else if (cssSortClass.equals("desc")) {
            return DESCENDING;
        }

        return UNSORTED;
    }
}
