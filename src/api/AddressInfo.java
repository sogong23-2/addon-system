package api;

public class AddressInfo {
        private String ipAddress;
        private int port;

        public AddressInfo(String ipAddress, int port) {
            this.ipAddress = ipAddress;
            this.port = port;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public int getPort() {
            return port;
        }
}
