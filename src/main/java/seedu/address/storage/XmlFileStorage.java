package seedu.address.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.XmlUtil;
import seedu.address.storage.user.XmlSerializableUsersList;
import seedu.address.storage.deliveryman.XmlSerializableDeliverymenList;

/**
 * Stores orderbook data in an XML file
 */
public class XmlFileStorage {
    /**
     * Saves the given addressbook data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableOrderBook addressBook)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, addressBook);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns address book in the file or an empty address book
     */
    public static XmlSerializableOrderBook loadDataFromSaveFile(Path file) throws DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableOrderBook.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

    /**
     * Saves the given users list data to the specified file.
     */
    public static void saveUsersDataToFile(Path file, XmlSerializableUsersList usersList)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, usersList);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns users list in the file or an empty users list.
     */
    public static XmlSerializableUsersList loadUsersDataFromSaveFile(Path file) throws DataConversionException,
                                                                                       FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableUsersList.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

    /**
     * Saves the given deliverymen data to the specified file.
     */
    public static void saveDeliverymenDataToFile(Path file, XmlSerializableDeliverymenList deliverymenList)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, deliverymenList);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns deliverymen list in the file or an empty deliverymen list
     */
    public static XmlSerializableDeliverymenList loadDeliverymenDataFromSaveFile(Path file) throws DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableDeliverymenList.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }
}
