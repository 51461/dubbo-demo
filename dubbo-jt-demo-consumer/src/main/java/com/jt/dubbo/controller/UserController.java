package com.jt.dubbo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.UserService;

@RestController
public class UserController {
	/**
	 * @Reference实例化接口
	 */
	@Reference(timeout=3000,check=true,loadbalance="randomLoad")
	private UserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@RequestMapping("/saveUser/{name}/{age}/{sex}")
	public String saveUser(User user) {
		System.out.println(user);
		Class<? extends User> class1 = user.getClass();
		System.out.println(class1);
		userService.saveUser(user);
		return "用户入库成功!!!";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		System.out.println(id);
		userService.deleteById(id);
		return "delete ok!";
	}
}
