package org.ssck.visual_displacement.Mapper;

import org.apache.ibatis.annotations.*;
import org.ssck.visual_displacement.Pojo.Aruco;

import java.util.List;

@Mapper
public interface ArucoMapper {
    @Insert("insert into aruco(id, camera_id, physical_width, pixel_width, init_x, init_y, rate) VALUES (#{id},#{cameraID},#{physicalWidth},#{pixelWidth},#{initX},#{initY},#{rate})")
    int addAruco(Aruco aruco);

    @Select("SELECT COUNT(*) FROM aruco WHERE ID = #{arucoID} AND camera_id = #{cameraID}")
    int getArucoByIDAndCamera(@Param("arucoID") Integer arucoID, @Param("cameraID") Integer cameraID);

    @Select("SELECT * FROM aruco")
    @Results({
            @Result(property = "id", column = "id"), // 如果一致可以省略
            @Result(property = "cameraID", column = "camera_id"),
            @Result(property = "physicalWidth", column = "physical_width"),
            @Result(property = "pixelWidth", column = "pixel_width"),
            @Result(property = "initX", column = "init_x"),
            @Result(property = "initY", column = "init_y"),
            @Result(property = "rate", column = "rate")
    })
    List<Aruco> getAruco();

    @Delete("DELETE from aruco where camera_id=#{cameraID} and ID=#{arucoID}")
    int deleteArucoFromArucoTable(@Param("cameraID") Integer cameraID, @Param("arucoID") Integer arucoID);

    @Delete("DELETE FROM record where camera_ID=#{cameraID} and aruco_ID=#{arucoID}")
    int deleteArucoFromRecordTable(@Param("cameraID") Integer cameraID, @Param("arucoID") Integer arucoID);

    @Delete("DELETE FROM displacement where camera_id=#{cameraID} and aruco_id=#{arucoID}")
    int deleteArucoFromDisplacementTable(@Param("cameraID") Integer cameraID, @Param("arucoID") Integer arucoID);


    @Delete("DELETE FROM aruco WHERE camera_id=#{cameraID}")
    int deleteArucoByCameraID(@Param("cameraID") Integer cameraID);
}
