package bat.ke.qq.com.mybatis.hander;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 源码学院-Monkey
 * 只为培养BAT程序员而生
 * http://bat.ke.qq.com
 * 往期视频加群:516212256 暗号:6
 */
public class BatTypeHandler implements TypeHandler<Date> {
   // 设置sql中指定索引的参数，即将javaType转化为jdbcType

   @Override
   public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      ps.setString(i, simpleDateFormat.format(parameter));
   }

   // 根据列名称从结果集获取值，并将jdbcType转换成javaType
   @Override
   public Date getResult(ResultSet rs, String columnName) throws SQLException {
      String columnValue = rs.getString(columnName);
      if (null != columnValue) {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         try {
            return format.parse(columnValue);
         } catch (ParseException e) {
            return  null;
         }
      }
      return null;
   }

   @Override
   // 根据列名称从结果集获取值，并将jdbcType转换成javaType
   public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
      String columnValue = rs.getString(columnIndex);
      if (null != columnValue) {
         return new Date(Long.valueOf(columnValue));
      }
      return null;
   }

   @Override
   public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
      String columnValue = cs.getString(columnIndex);
      if (null != columnValue) {
         return new Date(Long.valueOf(columnValue));
      }
      return null;
   }
}
