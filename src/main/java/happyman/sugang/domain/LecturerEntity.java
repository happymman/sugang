package happyman.sugang.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerEntity {

    private Integer lecturerIdx;

    private Integer majorIdx;
    private MajorEntity majorEntity;

    private String lecturerId; //ex : "2001001001"
    private String lecturerName;
}
