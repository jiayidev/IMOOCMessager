package net.brian.italker.factory.model;

import java.util.Date;

/**
 * 基础用户接口
 * Created by with_you on 2017/8/30.
 */

public interface Author {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getPortrait();

    void setPortrait(String portrait);

}
