package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.OrderBook;
import seedu.address.model.deliveryman.DeliverymenList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class XmlFoodZoomStorage {
    private static final Logger logger = LogsCenter.getLogger(XmlFoodZoomStorage.class);

    private Path foodZoomFilePath;
    private Optional<DeliverymenList> deliverymenList;
    private Optional<OrderBook> orderBook;

    public XmlFoodZoomStorage(Path foodZoomFilePath) {
        this.foodZoomFilePath = foodZoomFilePath;
    }


    public Path getFoodZoomFilePath() {
        return foodZoomFilePath;
    }

    public void readFoodZoomStorage() throws DataConversionException, IOException {
        readFoodZoomStorage(foodZoomFilePath);
    }

    public void readFoodZoomStorage(Path filePath) throws DataConversionException {

        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("FoodZoom file " + filePath + " not found");
        }

    }

    public void saveFoodZoom(DeliverymenList deliverymenList, OrderBook orderBook) throws IOException {
        saveFoodZoom(deliverymenList, orderBook, foodZoomFilePath);
    }

    public void saveFoodZoom(DeliverymenList deliverymenList, OrderBook orderBook, Path filePath)
            throws IOException {
        requireNonNull(deliverymenList);
        requireNonNull(orderBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        XmlFileStorage.saveFoodZoomDataToFile(filePath, new XmlFoodZoom(orderBook, deliverymenList));
    }

    public Optional<DeliverymenList> getDeliverymenList() {
        return deliverymenList;
    }

    public Optional<OrderBook> getOrderBook() {
        return orderBook;
    }

}
