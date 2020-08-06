package wooteco.subway.maps.map.application;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import wooteco.subway.common.TestObjectUtils;
import wooteco.subway.maps.line.application.LineService;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.line.domain.LineStation;
import wooteco.subway.maps.map.domain.LineStationEdge;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.maps.map.dto.PathResponseAssembler;
import wooteco.subway.maps.station.application.StationService;
import wooteco.subway.maps.station.domain.Station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FareServiceTest {
    private FareService fareService;
    @Mock
    private MapService mapService;
    @Mock
    private LineService lineService;
    @Mock
    private StationService stationService;
    @Mock
    private PathService pathService;

    private Map<Long, Station> stations;
    private List<Line> lines;

    private SubwayPath subwayPath;

    private PathResponse pathResponse;

    @BeforeEach
    void setUp() {
        stations = new HashMap<>();
        stations.put(1L, TestObjectUtils.createStation(1L, "교대역"));
        stations.put(2L, TestObjectUtils.createStation(2L, "강남역"));
        stations.put(3L, TestObjectUtils.createStation(3L, "양재역"));
        stations.put(4L, TestObjectUtils.createStation(4L, "남부터미널역"));

        Line line1 = TestObjectUtils.createLine(1L, "2호선", "GREEN");
        line1.addLineStation(new LineStation(1L, null, 0, 0));
        LineStation lineStation2 = new LineStation(2L, 1L, 2, 2);
        line1.addLineStation(new LineStation(2L, 1L, 2, 2));

        Line line2 = TestObjectUtils.createLine(2L, "신분당선", "RED");
        line2.addLineStation(new LineStation(2L, null, 0, 0));
        line2.addLineStation(new LineStation(3L, 2L, 2, 1));

        Line line3 = TestObjectUtils.createLine(3L, "3호선", "ORANGE");
        line3.addLineStation(new LineStation(1L, null, 0, 0));
        LineStation lineStation6 = new LineStation(4L, 1L, 1, 2);
        LineStation lineStation7 = new LineStation(3L, 4L, 2, 2);
        line3.addLineStation(lineStation6);
        line3.addLineStation(lineStation7);

        lines = Lists.newArrayList(line1, line2, line3);

        List<LineStationEdge> lineStations = Lists.newArrayList(
                new LineStationEdge(lineStation6, line3.getId()),
                new LineStationEdge(lineStation7, line3.getId())
        );
        subwayPath = new SubwayPath(lineStations);

        pathResponse = PathResponseAssembler.assemble(subwayPath, stations);

        mapService = new MapService(lineService, stationService, pathService, fareService);

        fareService = new FareService();
    }


    @DisplayName("거리별 추가요금을 구한다.")
    @Test
    void calculateFare() {
        int additionalFare = fareService.calculateFare(pathResponse);
        System.err.println(additionalFare);
//        assertThat(additionalFare).isEqualTo()
    }
}