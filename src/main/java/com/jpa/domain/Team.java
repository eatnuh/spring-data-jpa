package com.jpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Team {

    @Id @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Member> members = new HashSet<>();
}
