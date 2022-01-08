package application.domain;

import java.util.stream.Stream;

/**
 * Enum types for {@link Book} objects.
 */
public enum BookGenre {
    HISTORY("History"),
    CLASSIC("Classics"),
    FICTION("Fiction"),
    DOCUMENTARY("Documentary");

    public final String label;

    BookGenre(String label) {
        this.label = label;
    }

    public static BookGenre getBookGenreValue(final String code) {
        return Stream.of(BookGenre.values()).filter(targetEnum -> targetEnum.label.equals(code)).findFirst().orElse(null);
    }

}
