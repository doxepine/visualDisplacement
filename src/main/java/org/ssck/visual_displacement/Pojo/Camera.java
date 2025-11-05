package org.ssck.visual_displacement.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Camera {
    private Integer id;
    private String ip;
    private String port;
    private String username;
    private String password;
}
