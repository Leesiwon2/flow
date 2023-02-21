package com.example.flow.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Extension {
	
	@Id @GeneratedValue

    @Column(name = "extensionName")
	private String extensionName;

    @Column(name = "check_yn")
	private String check_yn;
}