package org.ssck.visual_displacement.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssck.visual_displacement.Mapper.DisplacementMapper;
import org.ssck.visual_displacement.Pojo.Displacement;
import org.ssck.visual_displacement.Pojo.PageBean;
import org.ssck.visual_displacement.Service.IDisplacementService;

import java.util.List;

@Service
public class DisplacementServiceimpl implements IDisplacementService {

    @Autowired
    DisplacementMapper displacementMapper;

    @Override
    public PageBean getHistoryData(String begin, String end, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        
        List<Displacement> displacementList = displacementMapper.getHistoryData(begin, end);

        Page<Displacement> p = (Page<Displacement>) displacementList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }
}
