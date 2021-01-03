package com.qst.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import com.qst.bean.Applicant;
import com.qst.bean.ResumeBasicInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class DBUtils {
    public static Connection getConnection() throws Exception {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");
        Properties properties=new Properties();
        properties.load(in);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);//连接池连接
        return dataSource.getConnection();
    }
    public static <T> List<T> getList(Class<T> clazz, String sql, Object...args) throws Exception {
//        Connection connection = getConnection();//数据库连接池连接

        //加载驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Class.forName(driver);
        //获取连接
//        String url="jdbc:mysql://localhost:3306/offers?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
//        String username="root";
//        String password="123456";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> userList=null;
        try {
            conn = DBUtils.getConnection();
            //        String sql="select user,password,status,authority,birthday from user where user=?";
            //        String sql="select user,password,status,authority,birthday from user";
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }

            }

//            ps.setString(1, "123456@qq.com");
            rs = ps.executeQuery();
            //获取结果集的元数据
            /*
                    user    password     status     authority      birthday   ResultSetMetaData(元数据)
                12@qq.com     123        管理员     查看、修改     2002-02-02  ResultSet(数据集）

                Map<String,Object> rowMap(key=列名，value=列值)
                列名转换成类的方法名，给当前属性赋值的方法名user=setUser,password=setPassword……
             */
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int columnCount = rsmd.getColumnCount();
            userList = new ArrayList<>();
            while (rs.next()) {
                //key存放列名，value存放列值，for循环完成之后，rowmap存放了一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    String colName = rsmd.getColumnLabel(i);//getColumnLabel:取得元数据别名
                    Object colValue = rs.getObject(colName);
                    //判断查询出来的类的类型，如果是java.sql.Date就转成java.util.Date
                    if (colValue instanceof java.sql.Date) {
                        java.sql.Date date = (java.sql.Date) colValue;
                        colValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(colName, colValue);
                }
                //创建一个User对象，给这个User对象属性赋值
                T bean = clazz.newInstance();
                //            User user1=new User();
                //            Class<User> userClass=User.class;
                //根据列名，给User对象 user 属性赋值的方法名 setUser ,调用setUser方法给user对象赋值，map当中的value
                //循环rowMap给User的所有属性赋值  entry{key,value}
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    /*String methodName="set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
                    //对象获取自己类对应的字节码文件
                    Method method = clazz.getMethod(methodName,propertyValue.getClass());//两个参数，注意要写参数类型（xxx.class）
                    method.invoke(bean,propertyValue);*/
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }


               /* String user = rs.getString("user");
                String password = rs.getString("password");
                String status = rs.getString("status");
                String authority = rs.getString("authority");
                java.sql.Date birthday=rs.getDate("birthday");

                User u=new User();
                u.setUser(user);
                u.setPassword(password);
                u.setStatus(status);
                u.setAuthority(authority);
                //转换
                java.util.Date birth = new java.util.Date(birthday.getTime());
                u.setBirthday(birth);*/

                userList.add(bean);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,rs);
        }
        return userList;
    }

    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    //保存对象的方法
    public static boolean save(String sql, Object...args){
        Connection conn=null;
        PreparedStatement ps=null;
        Integer count=null;

        try {
            conn = DBUtils.getConnection();
            //        String sql="select user,password,status,authority,birthday from user where user=?";
            //        String sql="select user,password,status,authority,birthday from user";
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }

            }

//            ps.setString(1, "123456@qq.com");
            //返回更新记录数
            count = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,null);
        }
        return count!=null&&count>0?true:false;
    }

    public static <T> T getSingleObj(Class<T> clazz, String sql, Object...args){

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        T bean=null;
        try {
            conn = DBUtils.getConnection();

            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    String colName = rsmd.getColumnLabel(i);//getColumnLabel:取得元数据别名
                    Object colValue = rs.getObject(colName);
                    if (colValue instanceof java.sql.Date) {
                        java.sql.Date date = (java.sql.Date) colValue;
                        colValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(colName, colValue);
                }
                //创建一个User对象，给这个User对象属性赋值
                bean = clazz.newInstance();
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,rs);
        }
        return bean;
    }
    public static <T> T getPrimary(Class<T> clazz, String sql, Object...args){

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        T bean=null;
        try {
            conn = DBUtils.getConnection();

            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    String colName = rsmd.getColumnLabel(i);//getColumnLabel:取得元数据别名
                    Object colValue = rs.getObject(colName);
                    if (colValue instanceof java.sql.Date) {
                        java.sql.Date date = (java.sql.Date) colValue;
                        colValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(colName, colValue);
                }
                //创建一个User对象，给这个User对象属性赋值
                bean = clazz.newInstance();
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,rs);
        }
        return bean;
    }
    //查询记录数量
    public static Integer getCount(String sql, Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Integer count=null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                count=  rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,rs);
        }
        return count;
    }
    //更新操作
    public static boolean update(String sql, Object...args){
        Connection conn=null;
        PreparedStatement ps=null;
        Integer count=0;

        try {
            conn = DBUtils.getConnection();
            //        String sql="select user,password,status,authority,birthday from user where user=?";
            //        String sql="select user,password,status,authority,birthday from user";
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    //判断当前日期类型，是不是java.util.Date,转换成java.sql.Date
                    if(args[i] instanceof java.util.Date){
                        java.util.Date date=(java.util.Date)args[i];
                        //转化成java.sql.Date类
                        args[i]= new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }

            }

//            ps.setString(1, "123456@qq.com");
            //返回更新记录数
            count = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,null);
        }
        return count!=null&&count>0?true:false;
    }
    public static Integer updateForPrimary(String sql, Object...args){
        Connection conn=null;
        PreparedStatement ps=null;
        Integer primaryKey=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.getConnection();
            //        String sql="select user,password,status,authority,birthday from user where user=?";
            //        String sql="select user,password,status,authority,birthday from user";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    //判断当前日期类型，是不是java.util.Date,转换成java.sql.Date
                    if(args[i] instanceof java.util.Date){
                        java.util.Date date=(java.util.Date)args[i];
                        //转化成java.sql.Date类
                        args[i]= new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }

            }

//            ps.setString(1, "123456@qq.com");
            //返回更新记录数
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();

            if (rs.next()) {
                primaryKey=rs.getInt(1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,rs);
        }
        return primaryKey;
    }
}