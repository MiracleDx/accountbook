package com.dongx.accountbook.note.mapper;

import com.dongx.accountbook.note.model.MultiTenancy;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface MultiTenancyMapper {
    @Delete({
        "delete from multi_tenancy",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into multi_tenancy (tenancy_name, tenancy_db, ",
        "tenancy_account, description, ",
        "create_time, update_time, ",
        "flag)",
        "values (#{tenancyName,jdbcType=VARCHAR}, #{tenancyDb,jdbcType=VARCHAR}, ",
        "#{tenancyAccount,jdbcType=INTEGER}, #{description,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{flag,jdbcType=TINYINT})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(MultiTenancy record);

    @Select({
        "select",
        "id, tenancy_name, tenancy_db, tenancy_account, description, create_time, update_time, ",
        "flag",
        "from multi_tenancy",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tenancy_name", property="tenancyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tenancy_db", property="tenancyDb", jdbcType=JdbcType.VARCHAR),
        @Result(column="tenancy_account", property="tenancyAccount", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="flag", property="flag", jdbcType=JdbcType.TINYINT)
    })
    MultiTenancy selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, tenancy_name, tenancy_db, tenancy_account, description, create_time, update_time, ",
        "flag",
        "from multi_tenancy"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tenancy_name", property="tenancyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tenancy_db", property="tenancyDb", jdbcType=JdbcType.VARCHAR),
        @Result(column="tenancy_account", property="tenancyAccount", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="flag", property="flag", jdbcType=JdbcType.TINYINT)
    })
    List<MultiTenancy> selectAll();

    @Update({
        "update multi_tenancy",
        "set tenancy_name = #{tenancyName,jdbcType=VARCHAR},",
          "tenancy_db = #{tenancyDb,jdbcType=VARCHAR},",
          "tenancy_account = #{tenancyAccount,jdbcType=INTEGER},",
          "description = #{description,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "flag = #{flag,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MultiTenancy record);
}