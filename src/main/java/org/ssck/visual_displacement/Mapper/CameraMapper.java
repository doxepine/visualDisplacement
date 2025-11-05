package org.ssck.visual_displacement.Mapper;

import org.apache.ibatis.annotations.*;
import org.ssck.visual_displacement.Pojo.Camera;

import java.util.List;

@Mapper
public interface CameraMapper {


    @Insert("INSERT INTO camera(ip, port, username, password) VALUES (#{ip}, #{port}, #{username, jdbcType=VARCHAR}, #{password, jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addCamera(Camera camera);

    @Select("SELECT ip from camera")
    List<String> findAllIps();

    @Select("select id from camera")
    List<Integer> getIdList();

    @Select("select * from camera")
    List<Camera> getCamerasInfo();

    @Delete("delete from camera where id=#{cameraID}")
    int deleteByCameraID(Integer cameraID);
}
