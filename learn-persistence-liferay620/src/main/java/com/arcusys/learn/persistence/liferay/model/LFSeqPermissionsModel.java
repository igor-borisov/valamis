package com.arcusys.learn.persistence.liferay.model;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the LFSeqPermissions service. Represents a row in the &quot;Learn_LFSeqPermissions&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.arcusys.learn.persistence.liferay.model.impl.LFSeqPermissionsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.arcusys.learn.persistence.liferay.model.impl.LFSeqPermissionsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFSeqPermissions
 * @see com.arcusys.learn.persistence.liferay.model.impl.LFSeqPermissionsImpl
 * @see com.arcusys.learn.persistence.liferay.model.impl.LFSeqPermissionsModelImpl
 * @generated
 */
public interface LFSeqPermissionsModel extends BaseModel<LFSeqPermissions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a l f seq permissions model instance should use the {@link LFSeqPermissions} interface instead.
     */

    /**
     * Returns the primary key of this l f seq permissions.
     *
     * @return the primary key of this l f seq permissions
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this l f seq permissions.
     *
     * @param primaryKey the primary key of this l f seq permissions
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the ID of this l f seq permissions.
     *
     * @return the ID of this l f seq permissions
     */
    public long getId();

    /**
     * Sets the ID of this l f seq permissions.
     *
     * @param id the ID of this l f seq permissions
     */
    public void setId(long id);

    /**
     * Returns the sequencing i d of this l f seq permissions.
     *
     * @return the sequencing i d of this l f seq permissions
     */
    public Integer getSequencingID();

    /**
     * Sets the sequencing i d of this l f seq permissions.
     *
     * @param sequencingID the sequencing i d of this l f seq permissions
     */
    public void setSequencingID(Integer sequencingID);

    /**
     * Returns the choice for children of this l f seq permissions.
     *
     * @return the choice for children of this l f seq permissions
     */
    public boolean getChoiceForChildren();

    /**
     * Returns <code>true</code> if this l f seq permissions is choice for children.
     *
     * @return <code>true</code> if this l f seq permissions is choice for children; <code>false</code> otherwise
     */
    public boolean isChoiceForChildren();

    /**
     * Sets whether this l f seq permissions is choice for children.
     *
     * @param choiceForChildren the choice for children of this l f seq permissions
     */
    public void setChoiceForChildren(boolean choiceForChildren);

    /**
     * Returns the choice for non descendants of this l f seq permissions.
     *
     * @return the choice for non descendants of this l f seq permissions
     */
    public boolean getChoiceForNonDescendants();

    /**
     * Returns <code>true</code> if this l f seq permissions is choice for non descendants.
     *
     * @return <code>true</code> if this l f seq permissions is choice for non descendants; <code>false</code> otherwise
     */
    public boolean isChoiceForNonDescendants();

    /**
     * Sets whether this l f seq permissions is choice for non descendants.
     *
     * @param choiceForNonDescendants the choice for non descendants of this l f seq permissions
     */
    public void setChoiceForNonDescendants(boolean choiceForNonDescendants);

    /**
     * Returns the flow for children of this l f seq permissions.
     *
     * @return the flow for children of this l f seq permissions
     */
    public boolean getFlowForChildren();

    /**
     * Returns <code>true</code> if this l f seq permissions is flow for children.
     *
     * @return <code>true</code> if this l f seq permissions is flow for children; <code>false</code> otherwise
     */
    public boolean isFlowForChildren();

    /**
     * Sets whether this l f seq permissions is flow for children.
     *
     * @param flowForChildren the flow for children of this l f seq permissions
     */
    public void setFlowForChildren(boolean flowForChildren);

    /**
     * Returns the forward only for children of this l f seq permissions.
     *
     * @return the forward only for children of this l f seq permissions
     */
    public boolean getForwardOnlyForChildren();

    /**
     * Returns <code>true</code> if this l f seq permissions is forward only for children.
     *
     * @return <code>true</code> if this l f seq permissions is forward only for children; <code>false</code> otherwise
     */
    public boolean isForwardOnlyForChildren();

    /**
     * Sets whether this l f seq permissions is forward only for children.
     *
     * @param forwardOnlyForChildren the forward only for children of this l f seq permissions
     */
    public void setForwardOnlyForChildren(boolean forwardOnlyForChildren);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(LFSeqPermissions lfSeqPermissions);

    @Override
    public int hashCode();

    @Override
    public CacheModel<LFSeqPermissions> toCacheModel();

    @Override
    public LFSeqPermissions toEscapedModel();

    @Override
    public LFSeqPermissions toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}