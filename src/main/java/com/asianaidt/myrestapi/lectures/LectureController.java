package com.asianaidt.myrestapi.lectures;

import com.asianaidt.myrestapi.lectures.dto.LectureReqDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value="/api/lectures", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class LectureController {
    //@Autowired
    private final LectureRepository lectureRepository;
    private final ModelMapper modelMapper;

    //constructor injection
//    public LectureController(LectureRepository lectureRepository) {
//        this.lectureRepository = lectureRepository;
//    }

    @PostMapping
    public ResponseEntity createLecture(@RequestBody LectureReqDto lectureReqDto) {
        //ReqDTO -> Entity로 매핑
        Lecture lecture = modelMapper.map(lectureReqDto, Lecture.class);
        Lecture addLecture = lectureRepository.save(lecture);

        //http://localhost:8080/api/lectures/10
        WebMvcLinkBuilder selfLinkBuilder = WebMvcLinkBuilder.linkTo(LectureController.class).slash(lecture.getId());
        URI createUri = selfLinkBuilder.toUri();
        //created() - 201 code
        return ResponseEntity.created(createUri).body(addLecture);
    }
}
