package group.examdesign.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @OneToOne(cascade = CascadeType.ALL)
    private Authority authority;
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wishes> wishes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Shift> shifts = new ArrayList<>();

}