package com.dongx.accountbook.note.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户表
 *
 * @author Dongx
 * @date 2019-10-19 23:41:926
 */
public class MultiTenancy implements Serializable {
    /**
     * 租户主键
     */
    private Integer id;

    /**
     * 租户名称
     */
    private String tenancyName;

    /**
     * 租户数据库名称
     */
    private String tenancyDb;

    /**
     * 租户登录账号
     */
    private Integer tenancyAccount;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 标识位
     */
    private Byte flag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public MultiTenancy withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenancyName() {
        return tenancyName;
    }

    public MultiTenancy withTenancyName(String tenancyName) {
        this.setTenancyName(tenancyName);
        return this;
    }

    public void setTenancyName(String tenancyName) {
        this.tenancyName = tenancyName;
    }

    public String getTenancyDb() {
        return tenancyDb;
    }

    public MultiTenancy withTenancyDb(String tenancyDb) {
        this.setTenancyDb(tenancyDb);
        return this;
    }

    public void setTenancyDb(String tenancyDb) {
        this.tenancyDb = tenancyDb;
    }

    public Integer getTenancyAccount() {
        return tenancyAccount;
    }

    public MultiTenancy withTenancyAccount(Integer tenancyAccount) {
        this.setTenancyAccount(tenancyAccount);
        return this;
    }

    public void setTenancyAccount(Integer tenancyAccount) {
        this.tenancyAccount = tenancyAccount;
    }

    public String getDescription() {
        return description;
    }

    public MultiTenancy withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MultiTenancy withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public MultiTenancy withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getFlag() {
        return flag;
    }

    public MultiTenancy withFlag(Byte flag) {
        this.setFlag(flag);
        return this;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenancyName=").append(tenancyName);
        sb.append(", tenancyDb=").append(tenancyDb);
        sb.append(", tenancyAccount=").append(tenancyAccount);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", flag=").append(flag);
        sb.append("]");
        return sb.toString();
    }
}