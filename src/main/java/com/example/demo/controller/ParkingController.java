package com.example.demo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.utils.R;
import com.example.demo.domain.Parking;
import com.example.demo.service.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    @Autowired
    private IParkingService iParkingService;

    /**
     分页
     */
    @GetMapping("/page")
    public IPage<Parking> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String parkingName,
                                @RequestParam(defaultValue = "") String parkingAddress,
                                @RequestParam(defaultValue = "") String parkingFee) {
        IPage<Parking> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Parking> queryWrapper = new QueryWrapper<>();
        if (!"".equals(parkingName)) {
            queryWrapper.like("Parking_name", parkingName);
        }
        if (!"".equals(parkingAddress)) {
            queryWrapper.like("Parking_address", parkingAddress);
        }
        if (!"".equals(parkingFee)) {
            queryWrapper.like("Parking_fee", parkingFee);
        }
        return iParkingService.page(page, queryWrapper);
    }

    /**
     * 删除
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return iParkingService.removeByIds(ids);
    }


    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Parking> list = iParkingService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("ParkingId", "ParkingId");
        writer.addHeaderAlias("ParkingName", "ParkingName");
        writer.addHeaderAlias("ParkingAddress", "ParkingAddress");
        writer.addHeaderAlias("ParkingSpace", "ParkingSpace");
        writer.addHeaderAlias("ParkingAvailable", "ParkingAvailable");
        writer.addHeaderAlias("ParkingLatitude", "ParkingLatitude");
        writer.addHeaderAlias("ParkingLongitude", "ParkingLongitude");
        writer.addHeaderAlias("ParkingFee", "ParkingFee");
        writer.addHeaderAlias("IsDeleted", "IsDeleted");
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("ParkingInformation", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    /**
     * excel 导入
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//         方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Parking> list = reader.readAll(Parking.class);
        iParkingService.saveBatch(list);
        return true;
    }

    /**
     * 新建停车场信息
     */
    @PostMapping
    public R save(@RequestBody Parking parking){
        boolean flag = iParkingService.save(parking);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    /**
     * 查询全部停车场信息
     */
    @GetMapping
    public R getAll(@RequestParam(defaultValue = "") String parkingName){
        QueryWrapper<Parking> queryWrapper = new QueryWrapper<>();
        if (!"".equals(parkingName)) {
            queryWrapper.like("Parking_name", parkingName);
        }
        return new R(true,iParkingService.list(queryWrapper));
    }












    /**
     * 停车场信息更新
     * @param parking
     * @return
     */
    @PutMapping
    public R update(@RequestBody Parking parking){
        boolean flag = iParkingService.updateById(parking);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    /**
     * 按Id删除停车场
     * @param ParkingId
     * @return
     */
    @DeleteMapping("{ParkingId}")
    public R delete(@PathVariable Integer ParkingId){
        return new R(iParkingService.removeById(ParkingId));
    }

    /**
     * 按Id查询停车场
     * @param ParkingId
     * @return
     */
    @GetMapping(value = "{ParkingId}")
    public R getById(@PathVariable Integer ParkingId){
        return new R(true,iParkingService.getById(ParkingId));
    }
}
