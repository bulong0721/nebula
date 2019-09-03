/**
 * Copyright (c) 2012-2019 Nikita Koksharov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.corundumstudio.socketio.protocol;

import java.io.Serializable;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/9/3
 */
public class Packet implements Serializable {

    private static final long serialVersionUID = 4560159536486711426L;

    private PacketType type;
    private PacketType subType;
    private Long ackId;
    private String name;
    private String nsp = "";
    private Object data;

    protected Packet() {
    }

    public Packet(PacketType type) {
        super();
        this.type = type;
    }

    public PacketType getSubType() {
        return subType;
    }

    public void setSubType(PacketType subType) {
        this.subType = subType;
    }

    public PacketType getType() {
        return type;
    }

    /**
     * Get packet data
     *
     * @param <T> the type data
     *
     *            <pre>
     *            @return <b>json object</b> for PacketType.JSON type
     *            <b>message</b> for PacketType.MESSAGE type
     *            </pre>
     */
    public <T> T getData() {
        return (T) data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getNsp() {
        return nsp;
    }

    public void setNsp(String endpoint) {
        this.nsp = endpoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAckId() {
        return ackId;
    }

    public void setAckId(Long ackId) {
        this.ackId = ackId;
    }

    public boolean isAckRequested() {
        return getAckId() != null;
    }

    @Override
    public String toString() {
        return "Packet [type=" + type + ", ackId=" + ackId + "]";
    }

}
