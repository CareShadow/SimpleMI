package com.shadow.controller;

import cn.hutool.core.lang.ObjectId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.context.UserContext;
import com.shadow.domain.MiBaseDataSet;
import com.shadow.enums.ResultCode;
import com.shadow.service.MiBaseDataSetService;
import com.shadow.vo.ResultBuilder;
import com.shadow.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 数据集的列表接口
     * @return
     */
    @GetMapping("list")
    public ResultVO<Map> dataSetList() {
        Map<String, Object> result = new HashMap<>();
        List<MiBaseDataSet> list = miBaseDataSetService
                .list(new QueryWrapper<MiBaseDataSet>()
                        .select("id", "name", "type", "source_id", "create_user", "create_date", "create_user", "create_date"));
        int count = miBaseDataSetService.count();
        result.put("count", count);
        result.put("list", list);
        return ResultBuilder.ok(result);
    }

    /**
     * 新增数据集
     *
     * @param miBaseDataSet
     * @return
     */
    @PostMapping("/insert")
    public ResultVO<String> insertDataSet(@RequestBody MiBaseDataSet miBaseDataSet) {
        miBaseDataSet.setId(ObjectId.next());
        miBaseDataSet.setCreateUser(UserContext.getCurrentUserName());
        miBaseDataSet.setCreateDate(new Date());
        boolean isSave = miBaseDataSetService.save(miBaseDataSet);
        String msg = isSave ? "保存成功" : "保存失败";
        return ResultBuilder.create(ResultCode.SUCCESS, msg);
    }

    /**
     * 修改数据集
     *
     * @param miBaseDataSet
     * @return
     */
    @PostMapping("/edit")
    public ResultVO<String> editDataSet(@RequestBody MiBaseDataSet miBaseDataSet) {
        boolean isUpdate = miBaseDataSetService.updateById(miBaseDataSet);
        String msg = isUpdate ? "修改成功" : "修改失败";
        return ResultBuilder.create(ResultCode.SUCCESS, msg);
    }

    /**
     * 删除数据集
     *
     * @param dataSetId
     * @return
     */
    @GetMapping("/delete")
    public ResultVO<String> deleteDataSet(String dataSetId) {
        boolean isDelete = miBaseDataSetService.removeById(dataSetId);
        String msg = isDelete ? "删除成功" : "删除失败";
        return ResultBuilder.create(ResultCode.SUCCESS, msg);
    }
}
