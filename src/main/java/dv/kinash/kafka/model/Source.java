package dv.kinash.kafka.model;

public enum Source {
    wiki("Wikipedia"),
    rest("REST API");

    private String name;

    Source(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
