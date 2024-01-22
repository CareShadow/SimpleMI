package com.shadow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shadow.domain.MiBaseDataSet;
import com.shadow.pojo.DataSet;

import java.util.List;

/**
* @author CareShadow
* @description 针对表【mi_base_data_set(mi_base_data_set)】的数据库操作Service
* @createDate 2024-01-22 19:53:46
*/
public interface MiBaseDataSetService extends IService<MiBaseDataSet> {
    List<DataSet> getDataSetList();
}
