package org.ssck.visual_displacement.Service;

import org.springframework.web.multipart.MultipartFile;
import org.ssck.visual_displacement.Pojo.Result;

import java.io.IOException;

public interface IConfigurationService {
    Result setBackground(MultipartFile image) throws IOException;

    Result getBackground();

    Result getFrontRefreshingTime();

    Result setFrontRefreshingTime(Integer time);

    Result initSystem();
}
