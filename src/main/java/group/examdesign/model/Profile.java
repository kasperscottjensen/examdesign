package group.examdesign.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "worker_role")
    private String role;
    @Column(name = "phone_number")
    private String phone;
    @Column(name = "date_hired")
    private Date hired;
    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;

}
