package com.gemini.util.components.table.model;

import java.util.Objects;

public class SortedColumn {

    private String name;
    private SortDirection sortDirection;

    private SortedColumn(String name, SortDirection sortDirection) {
        this.name = name;
        this.sortDirection = sortDirection;
    }

    public static SortedColumn createSortedColumn(String name, SortDirection sortDirection) {
        return new SortedColumn(name, sortDirection);
    }

    public String getName() {
        return name;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortedColumn that = (SortedColumn) o;
        return Objects.equals(name, that.name) &&
                sortDirection == that.sortDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sortDirection);
    }

    @Override
    public String toString() {
        return "SortedColumn{" +
                "name='" + name + '\'' +
                ", sortDirection=" + sortDirection +
                '}';
    }
}
