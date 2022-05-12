package com.example.demo.controller;

import com.example.demo.controller.utils.R;
import com.example.demo.domain.Bill;
import com.example.demo.domain.Reserve;
import com.example.demo.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reserves")
public class ReserveController {
    @Autowired
    private IReserveService iReserveService;

    @GetMapping("/car/{CarId}")
    public List<Reserve> getByCarId(@PathVariable String CarId){
        String carId = CarId;

        if(carId==null){
            return null;
        }
        return iReserveService.getByCarId(carId);
    }

    /**
     * 新建预定信息
     * @param reserve
     * @return Boolean
     */
    @PostMapping
    public R save(@RequestBody Reserve reserve){
        boolean flag = iReserveService.saveOrUpdate(reserve);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    /**
     * 查询全部预定信息
     * @return List
     */
    @GetMapping
    public R getAll(){
        return new R(true,iReserveService.list());
    }

    /**
     * 预定信息更新
     * @param reserve
     * @return Boolean
     */
    @PutMapping
    public R update(@RequestBody Reserve reserve){
        boolean flag = iReserveService.updateById(reserve);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    /**
     * 按Id删除预定
     * @param ReserveId
     * @return Boolean
     */
    @DeleteMapping("{ReserveId}")
    public R delete(@PathVariable String ReserveId){
        return new R(iReserveService.removeById(ReserveId));
    }

    /**
     * 按Id查询预定
     * @param ReserveId
     * @return Reserve
     */
    @GetMapping(value = "{ReserveId}")
    public R getById(@PathVariable String ReserveId){
        return new R(true,iReserveService.getById(ReserveId));
    }
}
