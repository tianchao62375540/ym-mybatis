package bat.ke.qq.com;

import bat.ke.qq.com.generator.pojo.UserExample;
import bat.ke.qq.com.mybatis.mapper.UserMapper;
import bat.ke.qq.com.mybatis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 源码学院-Monkey
 * 只为培养BAT程序员而生
 * http://bat.ke.qq.com
 * 往期视频加群:516212256 暗号:6
 */
@Slf4j
public class MybatisTest {
   @Test
   public void test() throws IOException {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      try {
         User user = session.selectOne("bat.ke.qq.com.mybatis.mapper.UserMapper.selectUser", 1);
         log.info("user:{}",user);
      } finally {
         session.close();
      }
   }

   /***
    * 注解
    * @throws IOException
    */
   @Test
   public void test2() throws IOException {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      UserMapper userMapper = session.getMapper(UserMapper.class);
      User user = userMapper.selectUser(1);
      final Date desc = user.getDesc();
      log.info("user;{}",user);
   }

   /***
    * 逆向工程 注解
    * @throws IOException
    */
   @Test
   public void test3() throws IOException {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      bat.ke.qq.com.generator.mapper.UserMapper userMapper = session.getMapper(bat.ke.qq.com.generator.mapper.UserMapper.class);
      UserExample  userExample=new UserExample();
      userExample.createCriteria().andIdEqualTo(1L);
      List<bat.ke.qq.com.generator.pojo.User> userList = userMapper.selectByExample(userExample);
      log.info("user;{}",userList.get(0));
   }

   /***
    * 逆向工程xml
    * @throws IOException
    */
   @Test
   public void test4() throws IOException {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      Object selectOne = session.selectOne("bat.ke.qq.com.generator.mapper.UserMapper.selectByPrimaryKey", 1L);
      log.info("user:{}",selectOne);
   /*   bat.ke.qq.com.generator.mapper.UserMapper userMapper = session.getMapper(bat.ke.qq.com.generator.mapper.UserMapper.class);
      UserExample  userExample=new UserExample();
      userExample.createCriteria().andIdEqualTo(1L);
      List<bat.ke.qq.com.generator.pojo.User> userList = userMapper.selectByExample(userExample);
      log.info("user;{}",userList.get(0));*/
   }
   public static void main(String[] args) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      System.out.println( simpleDateFormat.format(new Date()));;
   }
}
