package org.ssck.visual_displacement.Service;

import org.ssck.visual_displacement.Pojo.Camera;
import org.ssck.visual_displacement.Pojo.Result;

public interface ICameraService {
    Result addCamera(Camera camera);

    Result getIdList();

    Result getCameraInfo();

    Result deleteByCameraID(Integer id);


}
