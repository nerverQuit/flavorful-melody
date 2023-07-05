package com.example.flavorfulmelody.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
// JPA Entity Classe 들이 아래 추상클래스를 상속할 경우, 추상클래스 내 멤버 변수를 column 으로 인식시키는 annotation
@EntityListeners(AuditingEntityListener.class)
// Timestamped class 에 auditing 기능을 넣어 줌
public abstract class Timestamped {

	@CreatedDate
	@Column(updatable = false) // Update 가 되지 않도록 막음
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifiedAt;
}