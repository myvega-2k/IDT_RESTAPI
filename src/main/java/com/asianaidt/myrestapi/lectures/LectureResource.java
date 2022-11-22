package com.asianaidt.myrestapi.lectures;

import com.asianaidt.myrestapi.lectures.dto.LectureResDto;
import org.springframework.hateoas.RepresentationModel;

public class LectureResource extends RepresentationModel<LectureResource> {
    private LectureResDto lectureResDto;

    public LectureResource(LectureResDto resDto) {
        this.lectureResDto = resDto;
    }
    public LectureResDto getLectureResDto() {
        return lectureResDto;
    }
}
