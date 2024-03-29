package com.shadow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shadow.domain.MiBaseDataSet;
import com.shadow.pojo.DataSet;

import java.util.List;

/**
* @author CareShadow
* @description 针对表【mi_base_data_set(mi_base_data_set)】的数据库操作Mapper
* @createDate 2024-01-22 19:53:46
* @Entity com.shadow.domain.MiBaseDataSet
*/
public interface MiBaseDataSetMapper extends BaseMapper<MiBaseDataSet> {
    List<DataSet> getDataSetList();
}




