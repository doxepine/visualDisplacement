package org.ssck.visual_displacement.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ssck.visual_displacement.Pojo.Displacement;

import java.util.List;

@Mapper
public interface DisplacementMapper {


    /**
     * 获取历史位移数据
     *
     * @param begin 开始日期，格式：yyyy-MM-dd
     * @param end   结束日期，格式：yyyy-MM-dd
     * @return 位移数据列表
     */
    @Select("SELECT " +
            "   datetime, " +
            "   aruco_id as arucoId, " +
            "   camera_id as cameraId, " +
            "   dx, " +
            "   dy, " +
            "   img_path as imgPath " +
            "FROM displacement " +
            "WHERE (#{begin} IS NULL OR #{begin} = '' OR CONVERT(date, SUBSTRING(datetime, 1, 10)) >= CONVERT(date, #{begin})) " +
            "  AND (#{end} IS NULL OR #{end} = '' OR CONVERT(date, SUBSTRING(datetime, 1, 10)) <= CONVERT(date, #{end})) " +
            "ORDER BY datetime")
    List<Displacement> getHistoryData(
            @Param("begin") String begin,
            @Param("end") String end
    );


    @Delete("DELETE FROM displacement where camera_id=#{cameraID}")
    Integer deleteDisplacementByCameraID(Integer cameraID);
}
