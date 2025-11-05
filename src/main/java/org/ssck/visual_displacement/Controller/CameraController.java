package org.ssck.visual_displacement.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ssck.visual_displacement.Pojo.Camera;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.ICameraService;


//此接口用于相机操作
@Slf4j
@RestController
@RequestMapping("/camera")
public class CameraController {

    @Autowired
    private ICameraService cameraService;

    @PostMapping()
    public Result addCamera(@RequestBody Camera camera) {
        log.info("添加摄像头,参数为：{}", camera.toString());
        return cameraService.addCamera(camera);
    }

    @GetMapping("/getList")
    public Result getCameraIDs() {
        log.info("获取所有摄像头ID");
        return cameraService.getIdList();
    }


    @GetMapping()
    public Result getCameraInfo() {
        log.info("获取所有摄像头信息");
        return cameraService.getCameraInfo();
    }

    @DeleteMapping("/{id}")
    public Result deleteCamera(@PathVariable Integer id) {
        log.info("删除摄像头，删除参数为:{}", id);
        return cameraService.deleteByCameraID(id);
    }


}

