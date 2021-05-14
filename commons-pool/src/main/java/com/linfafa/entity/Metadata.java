package com.linfafa.entity;

import java.util.List;

/**
 * @author linmin
 * @since 1.0
 */
public class Metadata {
    /**
     * The unique primary key of the data source
     */
    private int id;
    /**
     * Data source name,such as mysql,sqlserver,hbase,clickhouse etc.
     */
    private String name;
    /**
     * Data source type,currently only ANSI SQL is supported.
     */
    private String type;
    /**
     * The metadata of the data source,which is stored in JSON format.
     * {@json [{"key":"type","value":"jdbc"},{"key":"username","value":"sa"}]}
     */
    private List<Entry> metadata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Entry> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Entry> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}



