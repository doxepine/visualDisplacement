package org.ssck.visual_displacement.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssck.visual_displacement.Mapper.RecordMapper;
import org.ssck.visual_displacement.Pojo.Record;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IRecordService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecordServiceimpl implements IRecordService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public Result getRecordList() {
        List<Record> recordList = recordMapper.getArucoInRecordList();

        Map<Integer, Map<Integer, Map<String, Object>>> result = recordList.stream()
                .collect(Collectors.groupingBy(
                        Record::getCameraID,
                        Collectors.toMap(
                                Record::getArucoID,
                                record -> {
                                    Map<String, Object> data = new HashMap<>();
                                    data.put("width", record.getWidth());
                                    data.put("pos_x", record.getPos_x());
                                    data.put("pos_y", record.getPos_y());
                                    return data;
                                },
                                (existing, replacement) -> existing
                        )
                ));
        return Result.success(result);
    }
}
