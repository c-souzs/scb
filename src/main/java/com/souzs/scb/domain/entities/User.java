package com.souzs.scb.domain.entities;

import com.souzs.scb.domain.enums.EUserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @Setter(AccessLevel.NONE)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Member member;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Library library;

    public void setMember(Member member) {
        if(library != null) throw new RuntimeException("Usuário já é uma biblioteca.");

        Role roleMember = new Role();
        roleMember.setId(EUserRole.ROLE_MEMBER.getId());

        this.member = member;
        this.role = roleMember;
    }

    public void setLibrary(Library library) {
        if(member != null) throw new RuntimeException("Usuário já é um membro.");

        Role roleLibrary = new Role();
        roleLibrary.setId(EUserRole.ROLE_LIBRARY.getId());

        this.library = library;
        this.role = roleLibrary;
    }

    public void setAdmin() {
        if(member != null || library != null) throw new RuntimeException("Usuário já é um(a) biblioteca ou membro.");

        Role roleAdmin = new Role();
        roleAdmin.setId(EUserRole.ROLE_ADMIN.getId());

        this.role = roleAdmin;
    }
}
