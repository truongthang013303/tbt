package com.world.tbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class CommentEntity extends BaseEntity{
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newid")
    @JsonIgnore
    private NewEntity post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    @JsonIgnore
    private UserEntity user;
}
