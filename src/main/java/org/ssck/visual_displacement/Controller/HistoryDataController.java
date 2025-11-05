package org.ssck.visual_displacement.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ssck.visual_displacement.Pojo.PageBean;
import org.ssck.visual_displacement.Service.IDisplacementService;


//历史数据，分页查询
@Slf4j
@RestController
@RequestMapping("/history")
public class HistoryDataController {
    @Autowired
    IDisplacementService displacementService;

    @GetMapping()
    PageBean historyData(@RequestParam("begin")
                         @DateTimeFormat(pattern = "yyyy-MM-dd") String begin,
                         @RequestParam("end")
                         @DateTimeFormat(pattern = "yyyy-MM-dd") String end,
                         @RequestParam("pageNum") Integer pageNum,
                         @RequestParam("pageSize") Integer pageSize) {

        log.info("查询历史数据，参数为：begin={}, end={}, pageNum={}, pageSize={}", begin, end, pageNum, pageSize);
        return displacementService.getHistoryData(begin, end, pageNum, pageSize);
    }
}
