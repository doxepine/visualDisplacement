package org.ssck.visual_displacement.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssck.visual_displacement.Mapper.ArucoMapper;
import org.ssck.visual_displacement.Mapper.CameraMapper;
import org.ssck.visual_displacement.Mapper.DisplacementMapper;
import org.ssck.visual_displacement.Mapper.RecordMapper;
import org.ssck.visual_displacement.Pojo.Camera;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.ICameraService;

import java.util.List;

@Service
public class CameraServiceimpl implements ICameraService {
    @Autowired
    private CameraMapper cameraMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private DisplacementMapper displacementMapper;
    @Autowired
    private ArucoMapper arucoMapper;

    @Override
    public Result addCamera(Camera camera) {

        List<String> ipList = cameraMapper.findAllIps();
        if (ipList.contains(camera.getIp())) {
            return Result.error("摄像头ip地址重复");
        }

        int flag = cameraMapper.addCamera(camera);
        if (flag == 1) {
            return Result.success();
        } else {
            return Result.error("插入失败");
        }

    }

    @Override
    public Result getIdList() {
        List<Integer> idList = cameraMapper.getIdList();
        return Result.success(idList);
    }

    @Override
    public Result getCameraInfo() {

        List<Camera> cameraList = cameraMapper.getCamerasInfo();
        return Result.success(cameraList);
    }

    @Override
    public Result deleteByCameraID(Integer id) {
        cameraMapper.deleteByCameraID(id);
        recordMapper.deleteRecordByCameraID(id);
        displacementMapper.deleteDisplacementByCameraID(id);
        arucoMapper.deleteArucoByCameraID(id);
        return Result.success();
    }
}
