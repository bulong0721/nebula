package io.nebula.net;

import java.net.*;
import java.util.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/3/21
 */
public enum NetworkInterfaceManager {
    /**
     * 单例
     */
    INSTANCE;

    private InetAddress local;
    private InetAddress localhost;

    private NetworkInterfaceManager() {
        this.load();
    }

    public InetAddress findValidateIp(List<InetAddress> addresses) {
        InetAddress local = null;
        int maxWeight = -1;
        Iterator it = addresses.iterator();

        while (it.hasNext()) {
            InetAddress address = (InetAddress) it.next();
            if (address instanceof Inet4Address) {
                int weight = 0;
                if (address.isSiteLocalAddress()) {
                    weight += 8;
                }

                if (address.isLinkLocalAddress()) {
                    weight += 4;
                }

                if (address.isLoopbackAddress()) {
                    weight += 2;
                }

                if (!Objects.equals(address.getHostName(), address.getHostAddress())) {
                    ++weight;
                }

                if (weight > maxWeight) {
                    maxWeight = weight;
                    local = address;
                }
            }
        }

        return local;
    }

    public String getLocalHostAddress() {
        return this.local.getHostAddress();
    }

    public String getLocalHostName() {
        try {
            if (null == this.localhost) {
                this.localhost = InetAddress.getLocalHost();
            }

            return this.localhost.getHostName();
        } catch (UnknownHostException var2) {
            return this.local.getHostName();
        }
    }

    private String getProperty(String name) {
        String value = null;
        value = System.getProperty(name);
        if (value == null) {
            value = System.getenv(name);
        }

        return value;
    }

    private void load() {
        String ip = this.getProperty("host.ip");
        if (ip != null) {
            try {
                this.local = InetAddress.getByName(ip);
                return;
            } catch (Exception var10) {
                System.err.println(var10);
            }
        }

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            List<NetworkInterface> nis = interfaces == null ? Collections.emptyList() : Collections.list(interfaces);
            List<InetAddress> addresses = new ArrayList();
            InetAddress local = null;

            try {
                Iterator it = ((List) nis).iterator();

                while (it.hasNext()) {
                    NetworkInterface ni = (NetworkInterface) it.next();
                    if (ni.isUp() && !ni.isLoopback()) {
                        addresses.addAll(Collections.list(ni.getInetAddresses()));
                    }
                }

                local = this.findValidateIp(addresses);
            } catch (Exception var8) {
            }

            if (local != null) {
                this.local = local;
                return;
            }
        } catch (SocketException var9) {
        }

        this.local = InetAddress.getLoopbackAddress();
    }
}

