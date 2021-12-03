package application.domain;

/**
 * Enum types for {@link Book} objects.
 */
public enum BookGenre {
    HIS("History"),
    CLA("Classics"),
    FIC("Fiction"),
    DOC("Documentary");

    public final String label;

    private BookGenre(String label) {
        this.label = label;
    }
}
