package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.route.Route;

public class CreateRouteCommand extends RouteCommand {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an empty route.\n"
            + "Example: /route " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Empty route created.";
    public static final String MESSAGE_DUPLICATE_ROUTE = "This route already exists in the address book";

    private final Route toAdd;
    private static final String DEFAULT_SOURCE = "12 Clementi Rd";

    /**
     * Creates an AddCommand to add the specified {@code Route}
     */
    public CreateRouteCommand() {
        Route route = new Route(new Address(DEFAULT_SOURCE));
        toAdd = route;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasRoute(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROUTE);
        }

        model.addRoute(toAdd);
        model.commitRouteList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateRouteCommand // instanceof handles nulls
                && toAdd.equals(((CreateRouteCommand) other).toAdd));
    }

}
