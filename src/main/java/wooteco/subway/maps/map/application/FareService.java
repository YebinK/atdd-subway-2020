package wooteco.subway.maps.map.application;

import org.springframework.stereotype.Service;
import wooteco.subway.maps.map.domain.AgeType;
import wooteco.subway.maps.map.domain.DistanceType;

@Service
public class FareService {

    public int calculateFare(int totalDistance, int lineExtraFare, int age) {
        int totalFare = fareByDistance(totalDistance) + fareByLine(lineExtraFare);
        return fareByAge(totalFare, age);
    }

    //거리별 추가 요금
    private int fareByDistance(int totalDistance) {
        return DistanceType.calculateAdditionalFare(totalDistance);
    }

    //노선별 추가 요금
    private int fareByLine(int lineExtraFare) {
        return lineExtraFare * 100;
    }

    //연령별 할인 정책
    private int fareByAge(int totalFare, int age) {
        return AgeType.discountRate(totalFare, age);
    }
}
