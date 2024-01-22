package com.shadow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.domain.MiBaseDataSet;
import com.shadow.mapper.MiBaseDataSetMapper;
import com.shadow.pojo.DataSet;
import com.shadow.service.MiBaseDataSetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author CareShadow
* @description 针对表【mi_base_data_set(mi_base_data_set)】的数据库操作Service实现
* @createDate 2024-01-22 19:53:46
*/
@Service
public class MiBaseDataSetServiceImpl extends ServiceImpl<MiBaseDataSetMapper, MiBaseDataSet>
    implements MiBaseDataSetService{

    @Resource
    private MiBaseDataSetMapper miBaseDataSetMapper;

    @Override
    public List<DataSet> getDataSetList() {
        return miBaseDataSetMapper.getDataSetList();
    }
}




