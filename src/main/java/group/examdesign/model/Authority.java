package group.examdesign.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "authority", nullable = false)
    private String authority;
    @OneToOne(mappedBy = "authority")
    @JsonIgnore
    private User user;

}