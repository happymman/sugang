package happyman.sugang.domain;

import lombok.Data;

@Data
public class RoomDto {
    private Integer roomOccupancy;

    public RoomDto(){}

    public RoomDto(Integer roomOccupancy){
        this.roomOccupancy = roomOccupancy;
    }
}
