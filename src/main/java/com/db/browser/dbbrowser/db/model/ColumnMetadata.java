package com.db.browser.dbbrowser.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ColumnMetadata {
    private List<Column> columns;
    private String primaryKeyColumnName;
    private String primaryKeyName;
    private String pkTableName;
    private String fkTableName;
    private String pkColumnName;
    private String fkColumnName;
}
