package com.project.BookingCar.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class DescriptionCheckerUtils {

    public static void isDescriptionUnder500Words(String description) {
        String[] words = description.split("\\s+");
        if (words.length > 500){
            throw new IllegalArgumentException("Description is over 500 words ");
        }
    }
}
