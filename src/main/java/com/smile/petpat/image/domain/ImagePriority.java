package com.smile.petpat.image.domain;

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
}
