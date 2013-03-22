/**
 * 
 */
package com.parsons.dao.util;

import java.util.UUID;

/**
 * @author Tim
 *
 */

public class UUIDConverter {
    public static UUID parse(String s) {
        return UUID.fromString(s);
    }

    public static String print(UUID uuid) {
        return uuid.toString();
    }
}
