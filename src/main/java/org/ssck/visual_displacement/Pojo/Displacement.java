package org.ssck.visual_displacement.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Displacement {
    private String datetime;
    private Integer arucoID;
    private Integer cameraID;
    private float dx;
    private float dy;
    private String imgPath;
}
