package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.rehoming.dto.RehomingDto;
import com.smile.petpat.config.comm.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_REHOMING")
public class Rehoming  extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REHOMING_ID")
    private Long rehomingId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "VIEW_CNT")
    private int viewCnt;

    @Column(name = "PET_NAME")
    private String petName;

    @Column(name = "PET_AGE")
    private String petAge;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "REGION")
    private String region;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "IS_COMPLETED")
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


