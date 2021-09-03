package com.lookie.toy1_back.tome.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lookie.toy1_back.tome.role.UserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Table(name = "tome_user")
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Question> questionList = new ArrayList<Question>();;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Answer> answerList = new ArrayList<Answer>();

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String name, String phone, String username, String password, UserRole role){
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof User)){
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.name, user.name) &&
                Objects.equals(this.phone, user.phone) &&
                Objects.equals(this.username, user.username) &&
                Objects.equals(this.password, user.password);

    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.phone,
                this.username, this.password);
    }
}