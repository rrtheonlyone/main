package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.model.deliveryman.Deliveryman;

/**
 * A utility class for Deliveryman testing.
 */
public class DeliverymanUtil {

    /**
     * Returns the part of the command string for the given {@code deliveryman}'s details.
     */
    public static String getDeliverymanDetails(Deliveryman deliveryman) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + deliveryman.getName().fullName + " ");
        return sb.toString();
    }
}
