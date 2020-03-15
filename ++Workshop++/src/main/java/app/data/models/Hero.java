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
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int level;

    private int stamina;

    private int strength;

    private int attack;

    private int defense;

    @ManyToMany(mappedBy = "heroes")
    private List<Item> items;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
