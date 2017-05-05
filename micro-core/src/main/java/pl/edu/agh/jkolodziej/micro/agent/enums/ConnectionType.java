package pl.edu.agh.jkolodziej.micro.agent.enums;

/**
 * @author - Jakub Kołodziej
 */
public enum ConnectionType {
    NONE("none"), WIFI("wifi"), LTE_4G("4g"), UMTS_3G("3g"), CDMA_2G("2g");

    private final String typeName;

    ConnectionType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static ConnectionType parse(String connectionTypeName) {
        String name = connectionTypeName.trim().toLowerCase();
        if (name.equalsIgnoreCase("UNKNOWN")) {
            return NONE;
        } else if (name.equalsIgnoreCase("4g") || name.equalsIgnoreCase("LTE")) {
            return LTE_4G;
        } else if (name.equalsIgnoreCase("3g")
                || name.equalsIgnoreCase("HSPA+")
                || name.equalsIgnoreCase("HSPA")) {
            return UMTS_3G;
        } else if (name.equalsIgnoreCase("2g")
                || name.equalsIgnoreCase("EDGE")
                || name.equalsIgnoreCase("GPRS")) {
            return CDMA_2G;
        }
        throw new IllegalStateException("Incorrect connection type name: " + connectionTypeName);
    }

    @Override
    public String toString() {
        return typeName;
    }
}
