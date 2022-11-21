package com.smile.petpat.Image;

import com.smile.petpat.util.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Image")
public class Photo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageId")
    private Long id;
    
//    @ManyToOne
//    @JoinColumn(name = "postId")
//    private Rehoming rehoming;

    @Column(nullable = false)
    private String imageRealName;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public Photo(String imageRealName, String filePath) {
        this.imageRealName = imageRealName;
        this.filePath = filePath;
    }
}
