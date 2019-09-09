package ${package}.dto;

import io.nebula.kernel.security.IUser;
import lombok.Data;

@Data
public class User implements IUser {
    private String username;
    private String nickName;

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

}
