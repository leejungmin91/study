package kr.co.restfulApi.model;

import lombok.*;
import javax.persistence.*;

@Data
@Table(name = "MEMBER")
@NoArgsConstructor
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mbrNo;
	
	private String id;
	
	private String name;
	
	@Builder
	public Member(String id, String name) {
		this.id = id;
		this.name = name;
	}
	

}
