package com.rhea.common.shiro;

import lombok.Data;
import org.apache.shiro.session.mgt.SimpleSession;

@Data
public class GenericSession extends SimpleSession {
    private String userAgent;
}
