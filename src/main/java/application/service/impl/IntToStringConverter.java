package application.service.impl;

import application.service.IntToStringService;
import org.springframework.stereotype.Service;

/**
 * Service class to convert String to Integer.
 */
@Service
public class IntToStringConverter implements IntToStringService {

    @Override
    public Integer convertToInteger(String param) {
        try {
            return Integer.parseInt(param);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Given parameter is not a number");
        }
    }
}
