package org.ssck.visual_displacement.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IRecordService;


//用于获取所有记录
@Slf4j
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    IRecordService recordService;


    //添加靶标时，需要从数据库的历史记录中求靶标的平均值，便是这个接口。
    @GetMapping()
    public Result getRecord() {
        log.info("获取所有记录");
        return recordService.getRecordList();
    }
}
