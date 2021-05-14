package com.linfafa.conf;

import com.linfafa.util.FileUtils;
import org.apache.metamodel.util.BooleanComparator;
import org.apache.metamodel.util.NumberComparator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.linfafa.conf.Constant.*;

/**
 * @author linmin
 * @since 1.0
 */
public class DtoConf implements Cloneable {

    private boolean loadDefault;

    private ConcurrentHashMap<String, Object> settings = new ConcurrentHashMap<>();

    public DtoConf() {
        this(true);
        init(true);
    }

    public DtoConf(boolean loadDefault) {
        this.loadDefault = loadDefault;
        init(loadDefault);
    }

    private void init(boolean loadDefault) {
        if (loadDefault) {
            FileUtils.getSystemProperties().forEach((k, v) -> {
                set(k, v);
            });
        }
    }

    public DtoConf set(String key, Object value) {
        if (key == null) {
            throw new NullPointerException("null key");
        }
        if (value == null) {
            throw new NullPointerException("null value for " + key);
        }
        settings.put(key, value);
        return this;
    }

    public DtoConf setAll(Map<String, Object> map) {
        map.forEach((k, v) -> set(k, v));
        return this;
    }


    public Object get(String key) {
        return settings.get(key);
    }

    public Map<String, Object> getAll() {
        Map<String, Object> var1 = new HashMap<>();
        var1.putAll(settings);
        return var1;
    }

    public DtoConf setMetadataId(int metadataId) {
        set(DTO_METADATA_ID, metadataId);
        return this;
    }

    public DtoConf setMaxTotalConnection(int maxTotalConnection) {
        set(DTO_MAX_TOTAL_CONNECTION_NUM, maxTotalConnection);
        return this;

    }

    public DtoConf setMaxActiveConnection(int maxActiveConnection) {
        set(DTO_MAX_ACTIVE_CONNECTION_NUM, maxActiveConnection);
        return this;
    }

    public DtoConf setMaxConnectionWaitTime(long maxConnectionWaitTime) {
        set(DTO_MAX_CONNECTION_WAIT_TIME, maxConnectionWaitTime);
        return this;
    }

    public DtoConf setMaxQueryWaitTime(long maxQueryWaitTime) {
        set(DTO_MAX_QUERY_WAIT_TIME, maxQueryWaitTime);
        return this;
    }

    public DtoConf setMaxQueryRows(int maxQueryRows) {
        set(DTO_MAX_QUERY_ROWS, maxQueryRows);
        return this;
    }

    public DtoConf setMaxIdleConnection(int maxIdleConnection) {
        set(DTO_MAX_IDLE_CONNECTION_NUM, maxIdleConnection);
        return this;
    }

    public DtoConf setMinIdleConnection(int minIdleConnection) {
        set(DTO_MIN_IDLE_CONNECTION_NUM, minIdleConnection);
        return this;
    }

    public DtoConf setTimeBetweenEvictionRuns(long time, TimeUnit timeUnit) {
        set(DTO_TIME_BETWEEN_EVICTION_RUNS_TIME, timeUnit.toMillis(time));
        return this;
    }

    public DtoConf setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        set(DTO_NUM_TESTS_PER_EVICTION_RUN, numTestsPerEvictionRun);
        return this;
    }

    public DtoConf setMinEvictableIdleTime(long time, TimeUnit timeUnit) {
        set(DTO_MIN_EVICTABLE_IDLE_TIME, timeUnit.toMillis(time));
        return this;
    }

    public DtoConf setSoftMinEvictableIdleTime(long time, TimeUnit timeUnit) {
        set(DTO_SOFT_MIN_EVICTABLE_IDLE_TIME, timeUnit.toMillis(time));
        return this;
    }

    public Integer getMetadataId() {
        return getInt(DTO_METADATA_ID);
    }

    public Integer getMaxActiveConnection() {
        return getInt(DTO_MAX_ACTIVE_CONNECTION_NUM);
    }

    public Integer getMaxTotalConnection() {
        return getInt(DTO_MAX_TOTAL_CONNECTION_NUM);
    }

    public Long getMaxConnectionWaitTime() {
        return getLong(DTO_MAX_CONNECTION_WAIT_TIME);
    }

    public Long getMaxQueryWaitTime() {
        return getLong(DTO_MAX_QUERY_WAIT_TIME);
    }

    public Integer getMaxQueryRows() {
        return getInt(DTO_MAX_QUERY_ROWS);
    }

    public Integer getMaxIdleConnection() {
        return getInt(DTO_MAX_IDLE_CONNECTION_NUM);
    }

    public Integer getMinIdleConnection() {
        return getInt(DTO_MIN_IDLE_CONNECTION_NUM);
    }

    public Long getTimeBetweenEvictionRuns() {
        return getLong(DTO_TIME_BETWEEN_EVICTION_RUNS_TIME);
    }

    public Integer getNumTestsPerEvictionRun() {
        return getInt(DTO_NUM_TESTS_PER_EVICTION_RUN);
    }

    public Long getMinEvictableIdleTime() {
        return getLong(DTO_MIN_EVICTABLE_IDLE_TIME);
    }

    public Long setSoftMinEvictableIdleTime() {
        return getLong(DTO_SOFT_MIN_EVICTABLE_IDLE_TIME);
    }

    public Integer getInt(String key) {
        Object obj = this.get(key);
        return obj == null ? null : NumberComparator.toNumber(obj).intValue();
    }

    public Long getLong(String key) {
        Object obj = this.get(key);
        return obj == null ? null : NumberComparator.toNumber(obj).longValue();
    }

    public Boolean getBoolean(String key) {
        Object obj = this.get(key);
        return obj == null ? null : BooleanComparator.toBoolean(obj).booleanValue();
    }

    @Override
    public DtoConf clone() {
        return new DtoConf().setAll(getAll());
    }
}
