package org.ssck.visual_displacement.Service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssck.visual_displacement.Mapper.MonitoringMapper;
import org.ssck.visual_displacement.Pojo.Displacement;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IMonitoringService;
import org.ssck.visual_displacement.Utils.DateTimeFormatUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MonitoringServiceimpl implements IMonitoringService {

    @Autowired
    MonitoringMapper monitoringMapper;

    @Override
    public Result getMonitoringData() {

        /**
         * 从数据库里查询最新一条的数据，封装成json给前端，data里第一个层级是摄像头ID
         * 下面的数据就是，摄像头1下有两个靶标，ArucoID分别为0和1，dx与dy分别是每个靶标的数据
         * {
         *   "datetime": "2025-09-04 09-36-04",
         *   "data": {
         *     "1": {   //此处为摄像头ID
         *       "0": { //此处为靶标ID
         *         "dx": -45.564261807981,
         *         "dy": -36.3570539643828
         *       },
         *       "1": {
         *         "dx": -263.279418366027,
         *         "dy": 42.945723766382
         *       }
         *     }
         *   }
         * }
         */


        try {
            List<Displacement> data = monitoringMapper.findLatestDisplacementData();

            if (data == null || data.isEmpty()) {
                return Result.error("没有找到监测数据");
            }

            JSONObject resultJson = new JSONObject(true);
            String latestDateTime = data.get(0).getDatetime();

            // 构建层级结构
            Map<Integer, List<Displacement>> groupedByCamera = data.stream()
                    .collect(Collectors.groupingBy(Displacement::getCameraID));

            for (Map.Entry<Integer, List<Displacement>> cameraEntry : groupedByCamera.entrySet()) {
                JSONObject cameraJson = new JSONObject(true);

                for (Displacement item : cameraEntry.getValue()) {
                    JSONObject displacement = new JSONObject(true);
                    displacement.put("dx", item.getDx());
                    displacement.put("dy", item.getDy());
                    cameraJson.put(String.valueOf(item.getArucoID()), displacement);
                }

                resultJson.put(String.valueOf(cameraEntry.getKey()), cameraJson);
            }

            JSONObject response = new JSONObject(true);
            response.put("datetime", DateTimeFormatUtil.toChineseFormat(latestDateTime));
            
            response.put("data", resultJson);
            return Result.success(response);

        } catch (Exception e) {
            return Result.error("获取监测数据失败: " + e.getMessage());
        }
    }
}
