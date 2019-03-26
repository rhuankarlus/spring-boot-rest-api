package br.com.rk.repositories.specifications.string;

/**
 * @author Rhuan Karlus
 * @since 26/03/19
 */
public enum Operation {

    /**
     * Less than operation
     */
    LT,

    /**
     * Less than or equals operation
     */
    LTE,

    /**
     * Greater than operation
     */
    GT,

    /**
     * Greater than or equals operation
     */
    GTE,

    /**
     * Equals operation
     */
    EQ,

    /**
     * Like both sides operation
     */
    LK,

    /**
     * Like before operation
     */
    LK_BEFORE,

    /**
     * Like after operation
     */
    LK_AFTER;

}
