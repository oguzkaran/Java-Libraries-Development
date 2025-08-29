package org.csystem.util.string;

/**
 * Specifies options for splitting strings.
 * <p>
 * This enum is typically used to control the behavior of string splitting methods,
 * such as whether to include or remove empty entries from the result.
 * </p>
 */
public enum StringSplitOptions {
    /**
     * No special splitting options. All entries, including empty ones, are included in the result.
     */
    NONE,

    /**
     * Removes empty entries from the result of the split operation.
     */
    REMOVE_EMPTY_ENTRIES
}