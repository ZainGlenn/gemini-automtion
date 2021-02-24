package com.gemini.util.components;

import com.thoughtworks.gauge.TableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Usage:
 * In spec File, create a table like example:
 * | Key        | Value     |
 * | Name       | Jack      |
 */
public class GaugeTable {
    private final com.thoughtworks.gauge.Table table;

    public GaugeTable(com.thoughtworks.gauge.Table table) {
        super();
        this.table = table;
    }

    /**
     * In your Steps / page class, use like this:
     * GaugeTable gTable = new GaugeTable(table); //this table argument comes from the spec file
     * String name = gTable.getValue("Name");
     */
    public String getValue(String key) {
        List<TableRow> rows = table.getTableRows();

        if (rows.stream().noneMatch(s -> s.getCellValues().get(0).equals(key))) {
            return null;
        }

        List<TableRow> rowList = rows.stream().filter(s -> s.getCellValues().get(0).equals(key)).collect(Collectors.toList());
        if (rowList.size() < 1) {
            return null;
        }
        TableRow row = rowList.get(0);

        if (row.getCellValues().size() < 1) {
            return null;
        }
        return row.getCellValues().get(1);
    }

    public String getValue(String key, String defaultValue) {
        List<TableRow> rows = table.getTableRows();

        if (rows.stream().noneMatch(s -> s.getCellValues().get(0).equals(key))) {
            return defaultValue;
        }

        List<TableRow> rowList = rows.stream().filter(s -> s.getCellValues().get(0).equals(key)).collect(Collectors.toList());
        if (rowList.size() < 1) {
            return defaultValue;
        }
        TableRow row = rowList.get(0);

        if (row.getCellValues().size() < 1) {
            return defaultValue;
        }
        String value = row.getCellValues().get(1);
        value = (value == null || value.isEmpty()) ? defaultValue : value;
        return value;
    }

    public boolean getValue(String key, boolean defaultValue) {
        List<TableRow> rows = table.getTableRows();

        if (rows.stream().noneMatch(s -> s.getCellValues().get(0).equals(key))) {
            return defaultValue;
        }

        List<TableRow> rowList = rows.stream().filter(s -> s.getCellValues().get(0).equals(key)).collect(Collectors.toList());
        if (rowList.size() < 1) {
            return defaultValue;
        }
        TableRow row = rowList.get(0);

        if (row.getCellValues().size() < 1) {
            return defaultValue;
        }
        String value = row.getCellValues().get(1);
        value = (value == null || value.isEmpty()) ? String.valueOf(defaultValue) : value;
        return Boolean.parseBoolean(value);
    }

    public List<String> getKeyListFromGaugeTable() {
        List<String> keyList = new ArrayList<>();

        List<TableRow> rows = table.getTableRows();
        for (TableRow s : rows) {
            String cellValue = s.getCellValues().get(0);
            if (cellValue.trim().replaceAll("-", "").isEmpty()) {
                continue;
            }
            keyList.add(cellValue);
        }

        if (keyList.size() == 0) {
            assertThat("Failed to retrieve Keys from Gauge Table", false);
        }
        return keyList;
    }

    public List<String> getKeyListFromGaugeTable(int index) {
        List<String> keyList = new ArrayList<>();
        List<TableRow> rows = table.getTableRows();
        rows.forEach(s -> keyList.add(s.getCellValues().get(index)));
        if (keyList.size() < 1) {
            assertThat("Failed to retrieve Keys from Gauge Table", false);
        }
        return keyList;
    }

    public List<String> getValueList() {
        List<String> valueList = new ArrayList<>();
        getKeyListFromGaugeTable().forEach(s -> valueList.add(getValue(s)));
        return valueList;
    }
}
