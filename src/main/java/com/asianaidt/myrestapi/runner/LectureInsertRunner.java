package com.asianaidt.myrestapi.runner;

import com.asianaidt.myrestapi.lectures.Lecture;
import com.asianaidt.myrestapi.lectures.LectureRepository;
import com.asianaidt.myrestapi.lectures.LectureStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
public class LectureInsertRunner implements ApplicationRunner {
    @Autowired
    LectureRepository lectureRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //IntStream.range(0, 15).forEach(this::generateLecture);
        IntStream.range(0, 15) //IntStream
                  //forEach(Consumer) Consumer의 추상메서드 void accept(T t)
                 .forEach(index -> generateLecture(index));
    }

    private Lecture generateLecture(int index) {
        Lecture lecture = buildLecture(index);
        return this.lectureRepository.save(lecture);
    }

    private Lecture buildLecture(int index) {
        return Lecture.builder()  //LectureBuilder
                .name(index + " lecture ")
                .description("test lecture")
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 11, 23, 14, 21))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 11, 24, 14, 21))
                .beginLectureDateTime(LocalDateTime.of(2022, 11, 25, 14, 21))
                .endLectureDateTime(LocalDateTime.of(2022, 11, 26, 14, 21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location(index + " 강의장")
                .free(false)
                .offline(true)
                .lectureStatus(LectureStatus.DRAFT)
                .build();  //Lecture
    }
}