package org.ssck.visual_displacement.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ssck.visual_displacement.Mapper.ConfigurationMapper;
import org.ssck.visual_displacement.Pojo.Result;
import org.ssck.visual_displacement.Service.IConfigurationService;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class ConfigurationServiceimpl implements IConfigurationService {
    @Autowired
    ConfigurationMapper configurationMapper;

    @Override
    public Result setBackground(MultipartFile image) {
        try {
            if (image == null || image.isEmpty()) {
                return Result.error("文件为空");
            }

            // 1. 将 MultipartFile 转成 byte[]
            byte[] bytes = image.getBytes();

            // 2. 将 byte[] 转成 Base64 字符串
            String imgStr = Base64.getEncoder().encodeToString(bytes);

            // 3. 调用 Mapper 保存
            configurationMapper.setBackground(imgStr);

            return Result.success("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("上传失败：" + e.getMessage());
        }

    }

    @Override
    public Result getBackground() {
        String img = configurationMapper.getBackground();
        return Result.success(img);
    }

    @Override
    public Result getFrontRefreshingTime() {
        Integer refreshingTime = configurationMapper.getFrontRefreshingTime();
        if (refreshingTime == null) {
            return Result.error("未设定检测周期");
        }
        return Result.success(refreshingTime);
    }

    @Override
    public Result setFrontRefreshingTime(Integer time) {
        Integer flag = configurationMapper.setFrontRefreshingTime(time);
        if (flag == 1) {
            return Result.success();
        } else return Result.error("设置失败");
    }

    @Override
    public Result initSystem() {
        List<Integer> initInfo = new java.util.ArrayList<>(Collections.emptyList());
        initInfo.add(configurationMapper.initArucoTable());
        initInfo.add(configurationMapper.initCameraTable());
        initInfo.add(configurationMapper.initConfigTable());
        initInfo.add(configurationMapper.initDisplacementTable());
        initInfo.add(configurationMapper.initRecordTable());
        initInfo.add(configurationMapper.insertDefaultConfig());


        return Result.success(initInfo);
    }
}
