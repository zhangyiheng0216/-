package com.zyh.sale.controller;

import com.zyh.sale.controller.ex.FileEmptyException;
import com.zyh.sale.controller.ex.FileSizeException;
import com.zyh.sale.service.ex.*;
import com.zyh.sale.utils.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 10:32
 */
//控制层的基础类
public class BaseController {
    // 操作成功的状态码
    public static final int OK = 200;

    //请求处理方法，返回值需要传递给前端
    // 自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中进行处理，并将返回数据交给前端
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用异常");
        }else if (e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("用户数据不存在异常");
        }else if (e instanceof AccessDeniedException){
            result.setState(4002);
            result.setMessage("收获地址数据非法异常");
        }else if (e instanceof AddressNotFoundException){
            result.setState(4003);
            result.setMessage("用户的收获地址数据不存在异常");
        }else if (e instanceof ProductNotFoundException){
            result.setState(4004);
            result.setMessage("商品数据不存在异常");
        }else if (e instanceof CartNotFoundException){
            result.setState(4005);
            result.setMessage("购物车数据不存在异常");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生异常");
            result.setMessage(e.getMessage());
        }else if (e instanceof PasswordNotMatchException){
            result.setState(5001);
            result.setMessage("用户密码错误异常");
        }else if (e instanceof UpdateException){
            result.setState(5002);
            result.setMessage("更新时数据产生异常");
        }else if (e instanceof DeleteException){
            result.setState(5003);
            result.setMessage("删除数据时产生异常");
        }else if(e instanceof AddressCountException){
            result.setState(5003);
            result.setMessage("用户的收获地址超出上限异常");
        }else if (e instanceof FileEmptyException){
            result.setState(6000);
            result.setMessage("上传数据为空异常");
        }else if(e instanceof FileSizeException){
            result.setState(6001);
            result.setMessage("上传数据过大异常");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(6002);
            result.setMessage("新旧密码重复异常");
        }else if(e instanceof CartNotFoundException){
            result.setState(6003);
            result.setMessage("购物车数据不存在异常");
        }
        else if(e instanceof DeleteException){
            result.setState(6004);
            result.setMessage("服务器异常，删除失败");
        }
        else if(e instanceof InsertException){
            result.setState(6005);
            result.setMessage("请不要重复收藏商品");
        }
        else if(e instanceof InsertException){
            result.setState(6006);
            result.setMessage("收藏商品不存在");
        }
        else if(e instanceof DeleteException){
            result.setState(6007);
            result.setMessage("删除取消收藏失败");
        }
        else if(e instanceof DeleteException){
            result.setState(6008);
            result.setMessage("数据非法访问");
        }
        return result;
    }




    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
