package com.VenkateshManvi.MoEngage.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseCodeService {

    public List<String> resolveCodes(String filter) {
        List<String> codes = new ArrayList<>();

        if (filter == null || filter.isBlank()) {
            return codes;
        }

        // Normalize pattern to lowercase
        filter = filter.toLowerCase();

        try {
            if (filter.matches("\\d{3}")) {
                // Exact match like 203
                codes.add(filter);
            } else if (filter.matches("[\\dx]{3}")) {
                // e.g., 2xx, 21x, x0x, xx0, xxx
                for (int i = 0; i <= 999; i++) {
                    String code = String.format("%03d", i);
                    if (matchesPattern(code, filter)) {
                        codes.add(code);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return codes;
    }

    // Custom pattern match where 'x' is a wildcard
    private boolean matchesPattern(String code, String pattern) {
        for (int i = 0; i < 3; i++) {
            char p = pattern.charAt(i);
            if (p != 'x' && p != code.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
