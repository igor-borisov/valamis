package com.arcusys.learn.persistence.liferay.model.impl;

import com.arcusys.learn.persistence.liferay.model.LFRequiredActivity;
import com.arcusys.learn.persistence.liferay.model.LFRequiredActivityModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the LFRequiredActivity service. Represents a row in the &quot;Learn_LFRequiredActivity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.arcusys.learn.persistence.liferay.model.LFRequiredActivityModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LFRequiredActivityImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFRequiredActivityImpl
 * @see com.arcusys.learn.persistence.liferay.model.LFRequiredActivity
 * @see com.arcusys.learn.persistence.liferay.model.LFRequiredActivityModel
 * @generated
 */
public class LFRequiredActivityModelImpl extends BaseModelImpl<LFRequiredActivity>
    implements LFRequiredActivityModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a l f required activity model instance should use the {@link com.arcusys.learn.persistence.liferay.model.LFRequiredActivity} interface instead.
     */
    public static final String TABLE_NAME = "Learn_LFRequiredActivity";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", Types.BIGINT },
            { "achievementId", Types.INTEGER },
            { "activityClassName", Types.VARCHAR },
            { "numberActivitiesRequired", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table Learn_LFRequiredActivity (id_ LONG not null primary key,achievementId INTEGER null,activityClassName VARCHAR(75) null,numberActivitiesRequired INTEGER null)";
    public static final String TABLE_SQL_DROP = "drop table Learn_LFRequiredActivity";
    public static final String ORDER_BY_JPQL = " ORDER BY lfRequiredActivity.id ASC";
    public static final String ORDER_BY_SQL = " ORDER BY Learn_LFRequiredActivity.id_ ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.arcusys.learn.persistence.liferay.model.LFRequiredActivity"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.arcusys.learn.persistence.liferay.model.LFRequiredActivity"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.arcusys.learn.persistence.liferay.model.LFRequiredActivity"),
            true);
    public static long ACHIEVEMENTID_COLUMN_BITMASK = 1L;
    public static long ID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.com.arcusys.learn.persistence.liferay.model.LFRequiredActivity"));
    private static ClassLoader _classLoader = LFRequiredActivity.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            LFRequiredActivity.class
        };
    private long _id;
    private Integer _achievementId;
    private Integer _originalAchievementId;
    private boolean _setOriginalAchievementId;
    private String _activityClassName;
    private Integer _numberActivitiesRequired;
    private long _columnBitmask;
    private LFRequiredActivity _escapedModel;

    public LFRequiredActivityModelImpl() {
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Class<?> getModelClass() {
        return LFRequiredActivity.class;
    }

    @Override
    public String getModelClassName() {
        return LFRequiredActivity.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("achievementId", getAchievementId());
        attributes.put("activityClassName", getActivityClassName());
        attributes.put("numberActivitiesRequired", getNumberActivitiesRequired());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Integer achievementId = (Integer) attributes.get("achievementId");

        if (achievementId != null) {
            setAchievementId(achievementId);
        }

        String activityClassName = (String) attributes.get("activityClassName");

        if (activityClassName != null) {
            setActivityClassName(activityClassName);
        }

        Integer numberActivitiesRequired = (Integer) attributes.get(
                "numberActivitiesRequired");

        if (numberActivitiesRequired != null) {
            setNumberActivitiesRequired(numberActivitiesRequired);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;
    }

    @Override
    public Integer getAchievementId() {
        return _achievementId;
    }

    @Override
    public void setAchievementId(Integer achievementId) {
        _columnBitmask |= ACHIEVEMENTID_COLUMN_BITMASK;

        if (!_setOriginalAchievementId) {
            _setOriginalAchievementId = true;

            _originalAchievementId = _achievementId;
        }

        _achievementId = achievementId;
    }

    public Integer getOriginalAchievementId() {
        return _originalAchievementId;
    }

    @Override
    public String getActivityClassName() {
        return _activityClassName;
    }

    @Override
    public void setActivityClassName(String activityClassName) {
        _activityClassName = activityClassName;
    }

    @Override
    public Integer getNumberActivitiesRequired() {
        return _numberActivitiesRequired;
    }

    @Override
    public void setNumberActivitiesRequired(Integer numberActivitiesRequired) {
        _numberActivitiesRequired = numberActivitiesRequired;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            LFRequiredActivity.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public LFRequiredActivity toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (LFRequiredActivity) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        LFRequiredActivityImpl lfRequiredActivityImpl = new LFRequiredActivityImpl();

        lfRequiredActivityImpl.setId(getId());
        lfRequiredActivityImpl.setAchievementId(getAchievementId());
        lfRequiredActivityImpl.setActivityClassName(getActivityClassName());
        lfRequiredActivityImpl.setNumberActivitiesRequired(getNumberActivitiesRequired());

        lfRequiredActivityImpl.resetOriginalValues();

        return lfRequiredActivityImpl;
    }

    @Override
    public int compareTo(LFRequiredActivity lfRequiredActivity) {
        long primaryKey = lfRequiredActivity.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LFRequiredActivity)) {
            return false;
        }

        LFRequiredActivity lfRequiredActivity = (LFRequiredActivity) obj;

        long primaryKey = lfRequiredActivity.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public void resetOriginalValues() {
        LFRequiredActivityModelImpl lfRequiredActivityModelImpl = this;

        lfRequiredActivityModelImpl._originalAchievementId = lfRequiredActivityModelImpl._achievementId;

        lfRequiredActivityModelImpl._setOriginalAchievementId = false;

        lfRequiredActivityModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<LFRequiredActivity> toCacheModel() {
        LFRequiredActivityCacheModel lfRequiredActivityCacheModel = new LFRequiredActivityCacheModel();

        lfRequiredActivityCacheModel.id = getId();

        lfRequiredActivityCacheModel.achievementId = getAchievementId();

        lfRequiredActivityCacheModel.activityClassName = getActivityClassName();

        String activityClassName = lfRequiredActivityCacheModel.activityClassName;

        if ((activityClassName != null) && (activityClassName.length() == 0)) {
            lfRequiredActivityCacheModel.activityClassName = null;
        }

        lfRequiredActivityCacheModel.numberActivitiesRequired = getNumberActivitiesRequired();

        return lfRequiredActivityCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", achievementId=");
        sb.append(getAchievementId());
        sb.append(", activityClassName=");
        sb.append(getActivityClassName());
        sb.append(", numberActivitiesRequired=");
        sb.append(getNumberActivitiesRequired());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append(
            "com.arcusys.learn.persistence.liferay.model.LFRequiredActivity");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>achievementId</column-name><column-value><![CDATA[");
        sb.append(getAchievementId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activityClassName</column-name><column-value><![CDATA[");
        sb.append(getActivityClassName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>numberActivitiesRequired</column-name><column-value><![CDATA[");
        sb.append(getNumberActivitiesRequired());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}