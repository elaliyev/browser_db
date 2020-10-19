package com.db.browser.dbbrowser.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColumnStatistic {
    private String min;
    private String max;
    private String avg;
}
