package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.Manager;

public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "/login";

    public static final String MESSAGE_SUCCESS = "Login Success for %1$s";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }
}
