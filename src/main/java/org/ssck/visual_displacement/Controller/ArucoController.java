package org.ssck.visual_displacement.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ssck.visual_displacement.Pojo.Aruco;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IArucoService;


//此接口用于靶标的删除与添加操作
@Slf4j
@RestController
@RequestMapping("/aruco")
public class ArucoController {
    @Autowired
    private IArucoService arucoService;


    @PostMapping()
    public Result addAruco(@RequestBody Aruco aruco) {
        log.info("添加靶标，参数为:{}", aruco.toString());
        return arucoService.addAruco(aruco);
    }

    @GetMapping()
    public Result getArUco() {
        log.info("获取所有靶标");
        return arucoService.getAruco();
    }

    @DeleteMapping()
    public Result deleteAruco(@RequestBody Aruco aruco) {
        log.info("删除Aruco，参数为:CameraID={},ArucoID={}", aruco.getCameraID(), aruco.getId());
        return arucoService.deletArucoByID(aruco.getCameraID(), aruco.getId());
    }

    @PutMapping()
    public Result updateAruco(@RequestBody Aruco aruco) {
        log.info("初始化靶标信息");
        return null;
    }
}
