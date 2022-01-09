package application.service;

/**
 * Interface to convert Integer to String.
 */
public interface IntToStringService {

    /**
     * Convert String to Integer;
     * @param param will be converted.
     * @return the value in Integer, if conversion possible.
     */
    Integer convertToInteger(String param);
}
