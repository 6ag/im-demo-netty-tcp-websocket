package com.kefu.netty.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Session会话（用户信息）
 * 实际生产环境中 Session 中的字段可能较多，比如头像 url，年龄，性别等等
 *
 * @author feng
 * @date 2019-04-21
 */
@Data
@NoArgsConstructor
public class Session {

    /**
     * 用户唯一标识
     */
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
