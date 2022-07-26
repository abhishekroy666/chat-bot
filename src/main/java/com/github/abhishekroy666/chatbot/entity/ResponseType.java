package com.github.abhishekroy666.chatbot.entity;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Abhishek Roy
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"type"}))
public class ResponseType extends BaseEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, updatable = false)
    @ColumnTransformer(read = "upper(type)", write = "upper(type)", forColumn = "type")
    @Enumerated(EnumType.STRING)
    private SentenceType type;

    @Column(length = 300)
    private String description;
}
