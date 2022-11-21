package com.smile.petpat.rehoming.domain;

import com.smile.petpat.rehoming.dto.RehomingDto;
import com.smile.petpat.util.Timestamped;
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

//    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
//    @JoinColumn(name = "userId", updatable = false)
//    @JsonBackReference
//    private User user;

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

//    @Builder
//    public Rehoming(User user, String title, String description, String petName, String petAge, String category,
//                    String type, String gender, String region, int price, Boolean isCompleted) {
//        this.user = user;
//        this.title = title;
//        this.description = description;
//        this.petName = petName;
//        this.petAge = petAge;
//        this.category = category;
//        this.type = type;
//        this.gender = gender;
//        this.region = region;
//        this.price = price;
//        this.isCompleted = isCompleted;
//    }

    public enum Status {

    }

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


