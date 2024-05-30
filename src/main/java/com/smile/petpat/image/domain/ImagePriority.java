package com.smile.petpat.image.domain;

import java.util.List;

public enum ImagePriority {
    PRIORITY_1(1),
    PRIORITY_2(2),
    PRIORITY_3(3),
    PRIORITY_4(4),
    PRIORITY_5(5);

    private final int priority;

    ImagePriority(int priority){
        this.priority =priority;
    }

    public int getPriority(){
        return priority;
    }

    public static ImagePriority fromIndexToPriority(int index){
        for(ImagePriority p : ImagePriority.values()){
            if(p.getPriority()==index) return p;
        }
        throw new IllegalArgumentException("Invalid index "+ index);
    }

    public static List<Image> setPriority(List<Image> images){
        for(int i=0; i<images.size(); i++){
            ImagePriority priority = ImagePriority.fromIndexToPriority(i+1);
            images.get(i).setImagePriority(priority);
        }
        return images;
    }
}
