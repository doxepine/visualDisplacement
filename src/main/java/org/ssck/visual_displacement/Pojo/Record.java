package org.ssck.visual_displacement.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    private Integer cameraID;
    private Integer arucoID;
    private float width;
    private float pos_x;
    private float pos_y;
}
