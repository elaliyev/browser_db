package com.db.browser.dbbrowser.model;

import lombok.*;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CONNECTION")
public class Connection  implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name="NAME", unique = true)
    private String name;

    @Column(name="HOSTNAME")
    private String hostname;

    @Column(name="PORT")
    private int port;

    @Column(name="DB_NAME")
    private String dbName;

    @Column(name="USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="URL")
    private String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Vendor vendor;

}
