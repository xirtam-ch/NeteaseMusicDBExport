/**
 * Copyright 2020 bejson.com
 */
package fuck;

import java.util.List;

/**
 * Auto-generated: 2020-03-24 19:34:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Album {

    private int id;
    private String name;
    private long picId;
    private String picUrl;
    private List<String> alias;
    private List<String> transNames;

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

    public long getPicId() {
        return picId;
    }

    public void setPicId(long picId) {
        this.picId = picId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public List<String> getTransNames() {
        return transNames;
    }

    public void setTransNames(List<String> transNames) {
        this.transNames = transNames;
    }

}