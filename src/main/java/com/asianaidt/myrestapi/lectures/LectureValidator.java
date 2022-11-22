package com.asianaidt.myrestapi.lectures;

import com.asianaidt.myrestapi.lectures.dto.LectureReqDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class LectureValidator {
    public void validate(LectureReqDto lectureReqDto, Errors errors) {
        if(lectureReqDto.getBasePrice() > lectureReqDto.getMaxPrice() &&
                lectureReqDto.getMaxPrice() != 0) {
            //Field Error
            errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong");
            errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is wrong");
            //Global Error
            errors.reject("wrongPrices", "Values for prices are wrong");
        }
        LocalDateTime endLectureDateTime = lectureReqDto.getEndLectureDateTime();
        if(endLectureDateTime.isBefore(lectureReqDto.getBeginLectureDateTime()) ||
                endLectureDateTime.isBefore(lectureReqDto.getCloseEnrollmentDateTime()) ||
                endLectureDateTime.isBefore(lectureReqDto.getBeginEnrollmentDateTime()) ) {
            errors.rejectValue("endLectureDateTime", "wrongValue", "endLectureDateTime is wrong");
        }
    }

}
