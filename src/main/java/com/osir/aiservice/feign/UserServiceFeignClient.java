package com.osir.aiservice.feign;

import com.osir.takeoutpojo.entity.AddressBook;
import com.osir.takeoutpojo.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "user-service", path = "/user/user/addressBook")
public interface UserServiceFeignClient {

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return
     */
    @GetMapping("/list")
    Result list();

    /**
     * 新增地址
     *
     * @param addressBook
     * @return
     */
    @PostMapping
    Result save(@RequestBody AddressBook addressBook);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result getById(@PathVariable("id") Long id);

    /**
     * 根据id修改地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping
    Result update(@RequestBody AddressBook addressBook);

    /**
     * 设置默认地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    Result setDefault(@RequestBody AddressBook addressBook);

    /**
     * 根据id删除地址
     *
     * @param id
     * @return
     */
    @DeleteMapping
    Result deleteById(Long id);

    /**
     * 查询默认地址
     */
    @GetMapping("/default")
    Result getDefault();

}
