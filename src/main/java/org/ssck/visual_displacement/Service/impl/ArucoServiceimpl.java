package org.ssck.visual_displacement.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssck.visual_displacement.Mapper.ArucoMapper;
import org.ssck.visual_displacement.Pojo.Aruco;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IArucoService;

import java.util.List;

@Service
public class ArucoServiceimpl implements IArucoService {
    @Autowired
    ArucoMapper arucoMapper;


    @Override
    public Result addAruco(Aruco aruco) {
        int count = getArucoByIDAndCamera(aruco.getId(), aruco.getCameraID());
        if (count != 0) {
            return Result.error("插入失败，请勿重复添加");
        }

        aruco.setRate(aruco.getPhysicalWidth() / aruco.getPixelWidth());
        int flag = arucoMapper.addAruco(aruco);

        if (flag == 1) {
            return Result.success();
        } else {
            return Result.error("添加失败");
        }

    }

    @Override
    public Result getAruco() {
        List<Aruco> arucoList = arucoMapper.getAruco();

        return Result.success(arucoList);
    }

    @Override
    public int getArucoByIDAndCamera(Integer arucoID, Integer cameraID) {
        return arucoMapper.getArucoByIDAndCamera(arucoID, cameraID);
    }

    @Override
    public Result deletArucoByID(Integer cameraID, Integer arucoID) {

        arucoMapper.deleteArucoFromRecordTable(cameraID, arucoID);
        arucoMapper.deleteArucoFromArucoTable(cameraID, arucoID);
        arucoMapper.deleteArucoFromDisplacementTable(cameraID, arucoID);

        return Result.success();
    }
}
