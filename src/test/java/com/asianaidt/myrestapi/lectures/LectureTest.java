package com.asianaidt.myrestapi.lectures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    @Test
    public void builder() {
        Lecture lecture = Lecture.builder()//LectureBuilder
                .name("REST API구현 과정")
                .description("2일과정으로 열리는 과정")
                .build();

        assertEquals("REST API구현 과정",lecture.getName());
    }
}