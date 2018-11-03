package seedu.address.logic.commands.order;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Mark an order as COMPLETED.
 */
public class DoneCommand extends OrderCommand {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = OrderCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Marks an order as COMPLETED \n"
            + "Parameters: INDEX (must be a positive integer) ";


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }
}
