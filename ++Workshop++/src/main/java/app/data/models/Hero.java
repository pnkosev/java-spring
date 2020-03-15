package app.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int stamina;

    @Column(nullable = false)
    private int strength;

    @Column(nullable = false)
    private int attack;

    @Column(nullable = false)
    private int defense;

    @ManyToMany(mappedBy = "heroes")
    private List<Item> items;

    @OneToOne(mappedBy = "hero")
    private User user;
}
