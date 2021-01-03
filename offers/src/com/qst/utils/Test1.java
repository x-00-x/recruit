package com.qst.utils;

import com.qst.bean.User;

import java.lang.reflect.Method;

public class Test1 {
    public static void main(String[] args) throws Exception {
        //反射
        User u=new User();
        u.setUser("147258@qq.com");
        //User.java--javac User.class---类加载----（User.class文件）JVM
        //要想操作User，class字节码文件，Class(如String,Integer，代表了虚拟机xxx.class) Class<User> userClass
        //获取到Class<User>就能操作User类中的所有元素 构造器，方法，属性
        //调用构造方法创建对象，对象属性属性赋值，调用对象的方法
        //获取字节码文件是所有反射的入口
        Class<User> userClass = User.class;
        //newInstance调用User类的默认无参构造方法创建User类型的实例
        User user1 = (User)userClass.newInstance();
        //获取到setUser方法
        Method setUser = userClass.getMethod("setUser",String.class);//记得要传两个参数
        //调用setUser方法给User对象赋值
        setUser.invoke(user1,"147369@qq.com");
        System.out.println(u.getUser());
        System.out.println(user1.getUser());
        System.out.println(user1);
    }
}
