package com.cheny.basic_design.service_entity_session;

/**
 *
 * 服务/实体/会话 三体分离
 * <p>实体</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface Entity {
    void execute(EntityContext context);
}
