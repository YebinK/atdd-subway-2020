package wooteco.subway.maps.map.application;

import org.springframework.stereotype.Service;
import wooteco.subway.maps.map.dto.PathResponse;

@Service
public class FareService {

    public int calculateFare(PathResponse pathResponse) {
        int totalDistance = pathResponse.getDistance();
        return fareByDistance(totalDistance);
    }

    //거리별 추가요금
    private int fareByDistance(int totalDistance) {
        return DistanceType.calculateAdditionalFare(totalDistance);
    }

}
