package co.com.yourcompany.certification.nameproject.model.enumerables;

public enum License {

    NONE("None"),
    APACHE_2("Apache license 2.0"),
    MIT("MIT License"),
    MOZILLA("Mozilla Public License 2.0");

    private final String licenseName;

    License(final String licenseName) {
        this.licenseName = licenseName;
    }

    @Override
    public String toString() {
        return licenseName;
    }

}
