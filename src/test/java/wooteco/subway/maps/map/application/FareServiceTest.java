package wooteco.subway.maps.map.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FareServiceTest {
    private FareService fareService = new FareService();

    @DisplayName("추가요금을 구한다. - 추가요금 없음")
    @Test
    void calculateFare1() {
        //거리(7km)에 따른 추가요금: 0원, 노선별 추가요금: 0원, 연령(5세)별 할인 요금: 0원 -> 최종 요금: 1250원
        int additionalFare = fareService.calculateFare(7, 0, 5);

        assertThat(additionalFare).isEqualTo(1250);
    }

    @DisplayName("추가요금을 구한다. - 거리에 따른 추가요금(10-50km)")
    @Test
    void calculateFare2() {
        //거리(15km)에 따른 추가요금: 900원, 노선별 추가요금: 0원, 연령(5세)별 할인 요금: 0원 -> 최종 요금: 1350원
        int additionalFare = fareService.calculateFare(15, 0, 5);

        assertThat(additionalFare).isEqualTo(1350);
    }

    @DisplayName("추가요금을 구한다. - 거리에 따른 추가요금(50km 이상)")
    @Test
    void calculateFare3() {
        //거리(60km)에 따른 추가요금: 900원, 노선별 추가요금: 0원, 연령(5세)별 할인 요금: -> 최종 요금: 2150원
        int additionalFare = fareService.calculateFare(60, 0, 5);

        assertThat(additionalFare).isEqualTo(2150);
    }


    @DisplayName("추가요금을 구한다. - 노선별 추가요금(100원)")
    @Test
    void calculateFare4() {
        //거리(5km)에 따른 추가요금: 0원, 노선별 추가요금: 100원, 연령(5세)별 할인 요금: 0원 -> 최종 요금: 1350원
        int additionalFare = fareService.calculateFare(5, 1, 5);

        assertThat(additionalFare).isEqualTo(1350);
    }

    @DisplayName("추가요금을 구한다. - 노선별 추가요금(300원)")
    @Test
    void calculateFare5() {
        //거리(5km)에 따른 추가요금: 0, 노선별 추가요금: 300원, 연령(5세)별 할인 요금: -> 최종 요금: 1550원
        int additionalFare = fareService.calculateFare(5, 3, 5);

        assertThat(additionalFare).isEqualTo(1550);
    }

    @DisplayName("추가요금을 구한다. - 6세 미만 어린이 할인 없음")
    @Test
    void calculateFare6() {
        //거리(5km)에 따른 추가요금: 0원, 노선별 추가요금: 0원, 연령(5세)별 할인 요금: 0원 -> 최종 요금: 1250원
        int additionalFare = fareService.calculateFare(5, 0, 5);

        assertThat(additionalFare).isEqualTo(1250);
    }

    @DisplayName("추가요금을 구한다. - 6-13세 어린이 할인")
    @Test
    void calculateFare7() {
        //거리(5km)에 따른 추가요금: 0, 노선별 추가요금: 0원, 연령(6세)별 할인 요금: -> 최종 요금: 800원
        int additionalFare = fareService.calculateFare(5, 0, 6);

        assertThat(additionalFare).isEqualTo(800);
    }

    @DisplayName("추가요금을 구한다. - 13-19세 청소년 할인")
    @Test
    void calculateFare8() {
        //거리(5km)에 따른 추가요금: 0, 노선별 추가요금: 0원, 연령(17세)별 할인 요금: -> 최종 요금: 1550원
        int additionalFare = fareService.calculateFare(5, 0, 17);

        assertThat(additionalFare).isEqualTo(1070);
    }

    @DisplayName("추가요금을 구한다. - 19세 이상 성인 할인 없음")
    @Test
    void calculateFare9() {
        //거리(5km)에 따른 추가요금: 0원, 노선별 추가요금: 0원, 연령(25세)별 할인 요금: 0원 -> 최종 요금: 1250원
        int additionalFare = fareService.calculateFare(5, 0, 25);

        assertThat(additionalFare).isEqualTo(1250);
    }
}