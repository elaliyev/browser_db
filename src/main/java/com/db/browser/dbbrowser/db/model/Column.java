package com.db.browser.dbbrowser.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Column {
    private final String columnName;
    private final String columnSize;
    private final String datatype;
    private final String isNullable;
    private final String isAutoIncrement;
}
