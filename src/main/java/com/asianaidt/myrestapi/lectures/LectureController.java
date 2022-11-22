package com.asianaidt.myrestapi.lectures;

import com.asianaidt.myrestapi.lectures.dto.LectureReqDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/api/lectures", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class LectureController {
    //@Autowired
    private final LectureRepository lectureRepository;
    private final ModelMapper modelMapper;
    private final LectureValidator lectureValidator;

    //constructor injection
//    public LectureController(LectureRepository lectureRepository) {
//        this.lectureRepository = lectureRepository;
//    }

    @PostMapping
    public ResponseEntity createLecture(@RequestBody @Valid LectureReqDto lectureReqDto, Errors errors)
            throws Exception {
        //입력항목에 오류가 있는지 체크합니다.
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        //biz logic 입력항목 오류 체크
        lectureValidator.validate(lectureReqDto, errors);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

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
