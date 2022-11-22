package com.asianaidt.myrestapi.lectures;

import com.asianaidt.myrestapi.lectures.dto.LectureResDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class LectureResource extends RepresentationModel<LectureResource> {
    @JsonUnwrapped
    private LectureResDto lectureResDto;

    public LectureResource(LectureResDto resDto) {
        this.lectureResDto = resDto;
        ///Self Link 추가
        add(linkTo(LectureController.class).slash(resDto.getId()).withSelfRel());
    }
    public LectureResDto getLectureResDto() {
        return lectureResDto;
    }
}
