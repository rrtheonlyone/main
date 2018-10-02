package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROUTES;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

public class ListRouteCommand extends RouteCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all routes.\n"
            + "Example: /route";

    public static final String MESSAGE_SUCCESS = "Listed all routes.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredRouteList(PREDICATE_SHOW_ALL_ROUTES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
