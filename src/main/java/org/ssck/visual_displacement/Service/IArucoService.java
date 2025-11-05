package org.ssck.visual_displacement.Service;

import org.ssck.visual_displacement.Pojo.Aruco;
import org.ssck.visual_displacement.Pojo.Result;

public interface IArucoService {

    Result addAruco(Aruco aruco);

    Result getAruco();

    int getArucoByIDAndCamera(Integer arucoID, Integer cameraID);

    Result deletArucoByID(Integer cameraID, Integer arucoID);
}
