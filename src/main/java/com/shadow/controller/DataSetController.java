package com.shadow.controller;

import com.shadow.domain.MiBaseDataSet;
import com.shadow.service.MiBaseDataSetService;
import com.shadow.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName DataSetController
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/12/10 21:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/dataset")
public class DataSetController {

    @Resource
    private MiBaseDataSetService miBaseDataSetService;

    /**
     * 新增数据集
     * @param miBaseDataSet
     * @return
     */
    @PostMapping("/insert")
    public ResultVO<String> insertDataSet(@RequestBody MiBaseDataSet miBaseDataSet) {
        boolean isSave = miBaseDataSetService.save(miBaseDataSet);
        String msg = "";
        if (isSave) {
            msg = "保存成功";
        } else {
            msg = "保存失败";
        }
        return new ResultVO<>(msg);
    }

    /**
     * 修改数据集
     * @param miBaseDataSet
     * @return
     */
    @PostMapping("/edit")
    public ResultVO<String> editDataSet(@RequestBody MiBaseDataSet miBaseDataSet) {
        boolean isUpdate = miBaseDataSetService.updateById(miBaseDataSet);
        String msg = "";
        if (isUpdate) {
            msg = "修改成功";
        } else {
            msg = "修改失败";
        }
        return new ResultVO<>(msg);
    }

    /**
     * 删除数据集
     * @param dataSetId
     * @return
     */
    public ResultVO<String> deleteDataSet(String dataSetId) {
        boolean isDelete = miBaseDataSetService.removeById(dataSetId);
        String msg = "";
        if (isDelete) {
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }
        return new ResultVO<>(msg);
    }
}
