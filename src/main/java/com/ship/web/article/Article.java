package com.ship.web.article;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.ship.web.person.Person;
import com.ship.web.proxy.Proxy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@Table(name = "ARTICLE")
							 
public class Article extends Proxy implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ARTICLEID") 
	@NotNull private Long articleid;
	@Column (name="USERID")
	@NotNull private String userid;
	@Column(name="COMMENTS",length = 64) 
	private String comments;
	@Column(name="TITLE",length = 64) 
	@NotNull private String title;
	@Column(name="CONTENTS",length = 1024) 
	@NotNull private String content;
	@Column(name="IMG",length = 64) 
	private String img;
	@Column(name="BOARDTYPE",length = 64) 
	@NotNull private String boardtype;
	@Column(name="RATING",length = 64) 
	private String rating;
	@Temporal(TemporalType.DATE)
	@Column(name="REGDATE") 
	@NotNull private Date regdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person personid;
	
	@Builder
	private Article(String userid,String comments,String title,
			String content, String img, String boardtype, String rating,
			Date regdate) {
		this.userid = userid;
		this.comments = comments;
		this.title = title;
		this.content = content;
		this.img = img;
		this.boardtype = boardtype;
		this.rating = rating;
		this.regdate = regdate;
	}
}