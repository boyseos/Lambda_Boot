package com.ship.web.person;

import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data @Component @Lazy
public class PersonDTO {
	private Long id;
	private String userid,passwd,name,role;
	private boolean male;
	private int hak,ban,score;
	private Date birthday;
}
