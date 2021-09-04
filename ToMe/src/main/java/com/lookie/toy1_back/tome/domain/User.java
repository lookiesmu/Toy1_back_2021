package com.lookie.toy1_back.tome.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lookie.toy1_back.tome.role.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Table
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long u_num;

    @NotBlank(message = "이름을 입력해주세요.")
    @Column
    private String name;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Column
    private String phone;

    @NotBlank(message = "ID를 입력해주세요.")
    @Column
    private String username;

    // @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Column
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
        return Objects.equals(this.u_num, user.u_num) &&
                Objects.equals(this.name, user.name) &&
                Objects.equals(this.phone, user.phone) &&
                Objects.equals(this.username, user.username) &&
                Objects.equals(this.password, user.password);

    }

    @Override
    public int hashCode(){
        return Objects.hash(this.u_num, this.name, this.phone,
                this.username, this.password);
    }
}