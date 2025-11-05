package org.ssck.visual_displacement.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IConfigurationService;

import java.io.IOException;


//此接口用于系统的配置
@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigurationController {

    @Autowired
    IConfigurationService configurationService;

    @GetMapping("/background")
    public Result getBackground() {
        log.info("获取背景");
        return configurationService.getBackground();
    }

    @PostMapping("/background")
    public Result setBackground(@RequestParam("image") MultipartFile image) throws IOException {
        log.info("上传背景");
        return configurationService.setBackground(image);
    }


    @GetMapping("/frontRefreshingTime")
    public Result getFrontRefreshingTime() {
        log.info("获取前端实时监测刷新时间");
        return configurationService.getFrontRefreshingTime();
    }

    @PostMapping
    public Result setFrontRefreshingTime(@RequestParam("frontRefreshTime") Integer time) {
        log.info("设置前端实时监测刷新时间");
        return configurationService.setFrontRefreshingTime(time);
    }

    @GetMapping("/init")
    public Result initSystem() {
        log.info("初始化系统");
        return configurationService.initSystem();
    }

}
