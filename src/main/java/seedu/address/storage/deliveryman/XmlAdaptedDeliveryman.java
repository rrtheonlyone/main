package seedu.address.storage.deliveryman;

import static seedu.address.model.TaggedObject.MESSAGE_INVALID_ID;

import java.util.Objects;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Name;
import seedu.address.model.deliveryman.Deliveryman;

/**
 * Represents the XML for storage of Deliveryman
 */
public class XmlAdaptedDeliveryman {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deliveryman's %s field is missing!";

    @XmlAttribute
    @XmlID
    private String tag;

    @XmlElement(required = true)
    private String name;

    public XmlAdaptedDeliveryman() {
    }

    /**
     * Constructs an {@code XmlAdapterDeliveryman} with the given common details.
     */
    public XmlAdaptedDeliveryman(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    /**
     * Constructs an {@code XmlAdapterDeliveryman} with the given common details.
     */
    public XmlAdaptedDeliveryman(String name) {
        this.tag = UUID.randomUUID().toString();
        this.name = name;
    }

    /**
     * Converts a given deliveryman into this class for JAXB use.
     *
     * @param source
     */
    public XmlAdaptedDeliveryman(Deliveryman source) {
        tag = source.getTag().toString();
        name = source.getName().fullName;
    }

    /**
     * Converts this jaxb-friendly adapted deliveryman object into the model's Deliveryman object.
     *
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public Deliveryman toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }

        UUID modelTag;

        try {
            modelTag = UUID.fromString(tag);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(MESSAGE_INVALID_ID);
        }

        final Name modelName = new Name(name);

        return new Deliveryman(modelTag, modelName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedDeliveryman)) {
            return false;
        }

        XmlAdaptedDeliveryman otherDman = (XmlAdaptedDeliveryman) other;
        return tag.equals(otherDman.tag)
                && Objects.equals(name, otherDman.name);
    }
}
