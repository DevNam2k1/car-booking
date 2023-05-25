package com.project.BookingCar.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@UtilityClass
public class CheckNumberOfImageUtils {

    public static void check(List<MultipartFile> multipartFiles){
        if (multipartFiles.size() > 6) {
            log.info("over image");
            throw new IllegalArgumentException("Sorry, You can upload over 6 images !!!");
        }
    }
}
