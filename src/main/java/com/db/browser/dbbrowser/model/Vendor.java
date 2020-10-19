package com.db.browser.dbbrowser.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="VENDOR")
public class Vendor implements Serializable {

    private static final long serialVersionUID = 2l;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="VENDOR_NAME")
    private String name;

    @Column(name="VERSION")
    private String version;

}
