package com.db.browser.dbbrowser.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableStatistic {
    private String tableName;
    private int columnCount;
    private int rowCount;
}
