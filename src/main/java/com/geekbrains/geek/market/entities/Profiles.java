package com.geekbrains.geek.market.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;


@Entity
    @Data
    @Table(name = "Profiles")
    public class Profiles{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "surname")
        private String surname;

        @Column(name = "email")
        private String email;

        @Column(name = "birthday")
        private String birthday;

        @Column(name = "sex")
        private String sex;

        @Column(name = "cityOfBirth")
        private String cityOfBirth;

    @ManyToMany
    @JoinTable(name = "email",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "profiles_email"))
    private Collection<Profiles> profiles;

}
