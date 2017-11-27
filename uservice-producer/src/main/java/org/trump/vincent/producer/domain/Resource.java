package org.trump.vincent.producer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Created by Vincent on 2017/11/13 0013.
 */

@Entity(name = "DEMO_RESOURCE")
public class Resource implements Serializable{
    protected final static long serialVersionUID = 10000123L;
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String iconUrl;

    @Column
    private Boolean usable;

    @Column
    private String created;

    @Version
    private Long version;

    @Column
    private String creatorId;

    @Column
    private String creatorName;

    @Column
    private String ccid;

    @Column
    private String aclContent;

    public String getId() {
        return id;
    }

    public Resource setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public Resource setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public Boolean getUsable() {
        return usable;
    }

    public Resource setUsable(Boolean usable) {
        this.usable = usable;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public Resource setCreated(String created) {
        this.created = created;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Resource setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public Resource setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Resource setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getCcid() {
        return ccid;
    }

    public Resource setCcid(String ccid) {
        this.ccid = ccid;
        return this;
    }

    public String getAclContent() {
        return aclContent;
    }

    public Resource setAclContent(String aclContent) {
        this.aclContent = aclContent;
        return this;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public Resource setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    @Column
    private Integer deleteFlag;


}
