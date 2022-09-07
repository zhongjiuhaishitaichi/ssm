package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Employee;
import com.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 查询所有的员工信息-->/employee-->get
 * 查询员工的分页信息-->/employee/page/1-->get
 * 根据id查询员工信息-->/employee/1-->get
 * 跳转到添加页面-->/to/add-->get
 * 添加员工信息-->/employee-->post
 * 修改员工信息-->/employee-->put
 * 删除员工信息-->/employee/1-->delete
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {
        //查询所有的员工信息
        List<Employee> employeeList = employeeService.getAllEmployee();
        //向请求域共享
        model.addAttribute("employeeList", employeeList);
        //跳转页面
        return "employee_list";
    }

    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum") Integer pageNum, Model model) {
        //获取员工的分页信息
        PageInfo<Employee> pageInfo = employeeService.getEmployeePage(pageNum);
        //把分页数据 共享请求域
        model.addAttribute("page", pageInfo);
        //资源跳转
        return "employee_list_ByPage";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("simpleEmployee",employee);
        return "simple_employee";
    }
    @RequestMapping(value = "/employee/addIndex",method = RequestMethod.GET)
    public String toAddIndex(){
        return "/addEmployee";
    }
    @RequestMapping(value = "/employee/to/add",method = RequestMethod.POST)
    public String addEmployee(Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/employee";
    }
    @RequestMapping(value = "/employee/update/{id}",method = RequestMethod.GET)
    public String ToUpdateEmployee(@PathVariable("id")Integer id,Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "/updateEmployee";
    }
    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public String updateEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }
    @RequestMapping(value = "/employee/delete/{id}",method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmployee(id);
        return "redirect:/employee";
    }
}
