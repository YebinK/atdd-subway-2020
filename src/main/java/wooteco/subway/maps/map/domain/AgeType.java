package wooteco.subway.maps.map.domain;

import java.util.Arrays;

public enum AgeType {
    KID(0, 5, 0, 0.0),
    CHILD(6, 12, 350, 0.5),
    STUDENT(13, 18, 350, 0.2),
    ADULT(19, Integer.MAX_VALUE, 0, 0.0);

    private int minAge;
    private int maxAge;
    private int discountFare;
    private double discountRate;

    AgeType(int minAge, int maxAge, int discountFare, double discountRate) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.discountFare = discountFare;
        this.discountRate = discountRate;
    }

    public static int discountRate(int fare, int age) {
        AgeType ageType = whichAgeType(age);
        double discountedFare = fare - ((fare - ageType.discountFare) * ageType.discountRate);
        return (int) discountedFare;
    }

    private static AgeType whichAgeType(int age) {
        return Arrays.stream(values())
                .filter(val -> age >= val.minAge)
                .filter(val -> age <= val.maxAge)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("로그인을 해주세요."));
    }
}
