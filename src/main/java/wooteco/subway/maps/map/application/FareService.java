package wooteco.subway.maps.map.application;

import org.springframework.stereotype.Service;
import wooteco.subway.maps.map.dto.PathResponse;

@Service
public class FareService {

    public int calculateFare(int totalDistance) {
        return fareByDistance(totalDistance);
    }

    //거리별 추가 요금
    private int fareByDistance(int totalDistance) {
        return DistanceType.calculateAdditionalFare(totalDistance);
    }

    //노선별 추가 요금
    private int fareByLine(int fare) {
        return 0;
    }
}
