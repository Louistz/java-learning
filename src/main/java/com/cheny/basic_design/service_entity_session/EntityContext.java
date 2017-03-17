package com.cheny.basic_design.service_entity_session;

/**
 *
 * 服务/实体/会话 三体分离
 *
 * <p>会话域</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface EntityContext {
    Object get(String key);
    void set(String key, Object value);
}
