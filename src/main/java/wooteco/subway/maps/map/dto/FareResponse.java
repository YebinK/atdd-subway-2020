package wooteco.subway.maps.map.dto;

public class FareResponse {
    private int fare;

    public FareResponse(int fare) {
        this.fare = fare;
    }

    public int getFare() {
        return fare;
    }
}
