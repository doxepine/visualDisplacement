package org.ssck.visual_displacement.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ssck.visual_displacement.Pojo.Record;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Select("SELECT dbo.record.camera_ID AS cameraID,dbo.record.aruco_ID AS arucoID,avg(dbo.record.width) AS width,avg(dbo.record.pos_x) AS pos_x,avg(dbo.record.pos_y) AS pos_y FROM dbo.record" +
            " GROUP BY record.camera_ID,record.aruco_ID" +
            " ORDER BY record.camera_ID,record.aruco_ID")
    List<Record> getArucoInRecordList();

    @Delete("DELETE FROM record where camera_ID=#{cameraID}")
    int deleteRecordByCameraID(@Param("cameraID") Integer cameraID);
}
