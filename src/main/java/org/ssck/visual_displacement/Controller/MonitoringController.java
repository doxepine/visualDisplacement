package org.ssck.visual_displacement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IMonitoringService;


//首页实时数据监测
@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    IMonitoringService monitoringService;


    @GetMapping
    public Result getMonitoringData() {
        return monitoringService.getMonitoringData();
    }

}
