package io.nebula.socketio.listener;

import lombok.Data;

import java.util.List;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/8
 */
@Data
public class JoinLeaveEvent {
    private List<String> rooms;
}
