package com.asianaidt.myrestapi.lectures;

import com.asianaidt.myrestapi.common.ErrorsResource;
import com.asianaidt.myrestapi.lectures.dto.LectureReqDto;
import com.asianaidt.myrestapi.lectures.dto.LectureResDto;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
            return badRequest(errors);
        }
        //biz logic 입력항목 오류 체크
        lectureValidator.validate(lectureReqDto, errors);
        if(errors.hasErrors()) {
            return badRequest(errors);
        }

        //ReqDTO -> Entity로 매핑
        Lecture lecture = modelMapper.map(lectureReqDto, Lecture.class);
        //free와 offline 필드의 값을 update 함
        lecture.update();
        Lecture addLecture = lectureRepository.save(lecture);
        //Entity -> ResDTO 매핑
        LectureResDto lectureResDto = modelMapper.map(addLecture, LectureResDto.class);

        //http://localhost:8080/api/lectures/10
        WebMvcLinkBuilder selfLinkBuilder = linkTo(LectureController.class).slash(lecture.getId());
        URI createUri = selfLinkBuilder.toUri();
        //Resource와 Link를 모두 전달하기 위해서 ResDTO를  Resource 객체에 저장한다.
        LectureResource lectureResource = new LectureResource(lectureResDto);
        lectureResource.add(linkTo(LectureController.class).withRel("query-lectures"));
        lectureResource.add(selfLinkBuilder.withRel("update-lecture"));

        //created() - 201 code
        return ResponseEntity.created(createUri).body(lectureResource);
    }

    private ResponseEntity badRequest(Errors errors) {

        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }
}
