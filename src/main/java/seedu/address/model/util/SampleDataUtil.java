package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.OrderBook;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.order.Order;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.order.Food;

/**
 * Contains utility methods for populating {@code OrderBook} with sample data.
 */
public class SampleDataUtil {
    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(new Name("Alex Yeoh"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getFoodSet("Ice Tea")),
            new Order(new Name("Bernice Yu"), new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getFoodSet("colleagues", "Chicken Rice")),
            new Order(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getFoodSet("Nasi Goreng")),
            new Order(new Name("David Li"), new Phone("91031282"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getFoodSet("Satay")),
            new Order(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                    getFoodSet("Fish and Chips")),
            new Order(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getFoodSet("Sugercane Juice"))
        };
    }

    public static ReadOnlyOrderBook getSampleOrderBook() {
        OrderBook sampleAb = new OrderBook();
        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }


    /**
     * Returns a food set containing the list of strings given.
     */
    public static Set<Food> getFoodSet(String... strings) {
        return Arrays.stream(strings)
                .map(Food::new)
                .collect(Collectors.toSet());
    }
}
