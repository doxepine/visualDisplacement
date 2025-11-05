package org.ssck.visual_displacement.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ssck.visual_displacement.Pojo.Displacement;

import java.util.List;

@Mapper
public interface MonitoringMapper {
    @Select("SELECT " +
            "camera_id as cameraId, " +
            "aruco_id as arucoId, " +
            "dx, " +
            "dy, " +
            "datetime, " +
            "img_path as imgPath " +
            "FROM displacement " +
            "WHERE datetime = (SELECT MAX(datetime) FROM displacement) " +
            "ORDER BY camera_id, aruco_id")
    List<Displacement> findLatestDisplacementData();
}
