package wooteco.subway.maps.map.domain;

import java.util.Arrays;

public enum DistanceType {
    DEFAULT(0, 10, 0, 0),
    NEAR(10, 50, 5, 0),
    FAR(50, Integer.MAX_VALUE, 8, 800);

    private int minDistance;
    private int maxDistance;
    private int additionalFare;
    private int previousDistanceFareSum;

    private static final int DEFAULT_FARE = 1250;
    private static final int FARE_UNIT = 100;

    DistanceType(int minDistance, int maxDistance, int additionalFare, int previousDistanceFareSum) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.additionalFare = additionalFare;
        this.previousDistanceFareSum = previousDistanceFareSum;
    }

    public static int calculateAdditionalFare(int totalDistance) {
        DistanceType distanceType = whichDistanceType(totalDistance);
        if (distanceType == DEFAULT) {
            return DEFAULT_FARE;
        }

        int additionalDistance = totalDistance - distanceType.minDistance;
        int additionalFare = (additionalDistance / distanceType.additionalFare) * FARE_UNIT;

        return DEFAULT_FARE + additionalFare + distanceType.previousDistanceFareSum;
    }

    private static DistanceType whichDistanceType(int totalDistance) {
        return Arrays.stream(values())
                .filter(val -> totalDistance > val.minDistance)
                .filter(val -> totalDistance <= val.maxDistance)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 거리입니다."));
    }
}
