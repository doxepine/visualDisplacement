package org.ssck.visual_displacement.Mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ConfigurationMapper {
    // 查询
    @Select("SELECT background_image FROM config WHERE id = 1")
    @Results({
            @Result(column = "background_image", property = "backgroundImage", jdbcType = JdbcType.VARCHAR)
    })
    String getBackground();

    // 保存
    @Update("UPDATE config SET background_image = #{imgStr} WHERE id = 1")
    Integer setBackground(@Param("imgStr") String imgStr);


    @Select("SELECT frontend_refresh_time from config where id= 1")
    Integer getFrontRefreshingTime();

    @Update("UPDATE config set frontend_refresh_time=#{time} where id = 1")
    Integer setFrontRefreshingTime(Integer time);

    org.ssck.visual_displacement.Pojo.Result initSystem();


    // 清空
    @Update("TRUNCATE TABLE aruco")
    Integer initArucoTable();

    @Update("TRUNCATE TABLE camera")
    Integer initCameraTable();

    @Update("TRUNCATE TABLE config")
    Integer initConfigTable();

    @Update("TRUNCATE TABLE displacement")
    Integer initDisplacementTable();

    @Update("TRUNCATE table record")
    Integer initRecordTable();

    @Insert("INSERT INTO config(id,backend_refresh_time)values(1,60)")
    Integer insertDefaultConfig();
}
