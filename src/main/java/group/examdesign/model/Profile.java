package group.examdesign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
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
    @Column(name = "Full_Name")
    private String fullName;
    @Column(name = "Worker_Role")
    private String role;
    @Column(name = "Phone_Number")
    private String phone;
    @Column(name = "Date_hired")
    private Date hired;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;

}
