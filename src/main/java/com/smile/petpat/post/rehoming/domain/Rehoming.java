package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.rehoming.dto.RehomingDto;
import com.smile.petpat.config.comm.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "rehoming")
public class Rehoming  extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rehomingPostId")
    private Long rehomingId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "viewCnt")
    private int viewCnt;

    @Column(name = "petName")
    private String petName;

    @Column(name = "petAge")
    private String petAge;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @Column(name = "gender")
    private String gender;

    @Column(name = "region")
    private String region;

    @Column(name = "price")
    private int price;

    @Column(name = "isCompleted")
    private boolean isCompleted;

    public Rehoming(RehomingDto rehomingDto) {
        this.title = rehomingDto.getTitle();
        this.description = rehomingDto.getDescription();
        this.petName = rehomingDto.getPetName();
        this.petAge = rehomingDto.getPetAge();
        this.category = rehomingDto.getCategory();
        this.type = rehomingDto.getType();
        this.gender = rehomingDto.getGender();
        this.region = rehomingDto.getRegion();
        this.price = rehomingDto.getPrice();
    }
}


