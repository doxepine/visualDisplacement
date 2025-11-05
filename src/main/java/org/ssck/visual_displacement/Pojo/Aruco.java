package org.ssck.visual_displacement.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aruco {
    private Integer id;
    private Integer cameraID;
    private float physicalWidth;
    private float pixelWidth;
    private float initX;
    private float initY;
    private float rate;
}
