package org.ssck.visual_displacement.Service;

import org.ssck.visual_displacement.Pojo.PageBean;

public interface IDisplacementService {
    PageBean getHistoryData(String begin, String end, Integer pageNum, Integer pageSize);
}
