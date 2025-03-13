package com.lyr.busticketsystemdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 公告信息表
 * @TableName announcement
 */
@TableName(value ="announcement")
@Data
public class Announcement implements Serializable {
    /**
     * 公告ID（主键）
     */
    @TableId(type = IdType.AUTO)
    private Long announcementId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date publishTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Announcement other = (Announcement) that;
        return (this.getAnnouncementId() == null ? other.getAnnouncementId() == null : this.getAnnouncementId().equals(other.getAnnouncementId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAnnouncementId() == null) ? 0 : getAnnouncementId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", announcementId=").append(announcementId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}