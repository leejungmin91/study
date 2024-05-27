package kr.co.restfulapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "MEMBER")
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    @OneToMany(mappedBy = "member")
    List<Order> memberOrders = new ArrayList<>();

    public void updateMember(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
