package com.asianaidt.myrestapi.lectures;

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
public class LectureController {
    @PostMapping
    public ResponseEntity createLecture(@RequestBody Lecture lecture) {
        lecture.setId(10);

        //http://localhost:8080/api/lectures/10
        WebMvcLinkBuilder selfLinkBuilder = WebMvcLinkBuilder.linkTo(LectureController.class).slash(lecture.getId());
        URI createUri = selfLinkBuilder.toUri();
        //created() - 201 code
        return ResponseEntity.created(createUri).body(lecture);
    }
}
