package com.example.flow.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Extension {
	
	@Id
	private String extensionName;

	private String check_yn;
}