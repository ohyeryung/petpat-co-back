package com.smile.petpat.post.rehoming.dto;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RehomingPagingDto {
    private List<?> rehomigList = new ArrayList<>();
    private int totalPage;
    private boolean islastPage;

    public RehomingPagingDto(Page<?> rehomigListDto, boolean islastPage) {

        this.rehomigList = rehomigListDto.getContent();
        this.totalPage = rehomigListDto.getTotalPages();
        this.islastPage = islastPage;
    }

    public RehomingPagingDto(List<Rehoming> rehoming) {
        this.rehomigList = rehoming;
    }
}
