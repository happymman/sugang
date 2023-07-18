package happyman.sugang.domain;

import lombok.Data;

@Data
public class RoomEntity {

    private Integer roomIdx;
    private String roomBuildingName;
    private Integer roomName; //호수
    private Integer roomOccupancy; //제외

}
