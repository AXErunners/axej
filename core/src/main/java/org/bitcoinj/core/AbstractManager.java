package org.bitcoinj.core;


import org.bitcoinj.store.FlatDB;

import java.io.OutputStream;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by Hash Engineering on 6/21/2016.
 *
 * Base class for all Axe Manager objects.  Derived classes must implement
 * {@link #parse()} and {@link #bitcoinSerializeToStream(OutputStream)} to
 * to serialize data to a file with FlatDB.
 */
public abstract class AbstractManager extends Message {

    /**
     * The Context.
     */
    protected Context context;
    /**
     * The Default file name.
     * The default value is the name of the derived class + ".dat"
     */
    protected String defaultFileName;
    /**
     * The Default magic message.
     * The default value is the name of the derived class.
     */
    protected String defaultMagicMessage;
    /**
     * The Magic message.
     * The default value is the name of the derived class.
     */
    protected String magicMessage;
    /**
     * The Format version.  The default value is 1.
     */
    protected int formatVersion;
    /**
     * The constant defaultExtension.
     */
    public static String defaultExtension = ".dat";

    /**
     * The Filename.
     */
    public String filename;

    /**
     * Instantiates a new AbstractManager.
     *
     * @param context the context
     */
    public AbstractManager(Context context) {
        super(context.getParams());
        this.context = context;
        this.formatVersion = 1;
        String fullClassName = this.getClass().getCanonicalName();
        this.defaultMagicMessage = fullClassName.substring(fullClassName.lastIndexOf('.')+1);
        this.magicMessage = defaultMagicMessage;
        this.defaultFileName = this.magicMessage.toLowerCase() + defaultExtension;
    }

    /**
     * Instantiates a new Abstract manager.
     *
     * @param params  the params
     * @param payload the payload
     * @param cursor  the cursor
     */
    public AbstractManager(NetworkParameters params, byte [] payload, int cursor) {
        super(params, payload, cursor);
        this.context = Context.get();
        this.formatVersion = 1;
        String fullClassName = this.getClass().getCanonicalName();
        this.defaultMagicMessage = fullClassName.substring(fullClassName.lastIndexOf('.')+1);
        this.magicMessage = defaultMagicMessage;
        this.defaultFileName = fullClassName + defaultExtension;
    }

    /**
     * Calculates size in bytes of this object.
     *
     * @return the size
     */
    public abstract int calculateMessageSizeInBytes();

    /**
     * Check and remove.  Called to check the validity of all objects
     * and remove the expired objects.
     */
    public abstract void checkAndRemove();

    /**
     * Clear.  Removes all objects and settings.
     */
    public abstract void clear();

    /**
     * Loads a payload into the object
     *
     * @param payload the payload
     * @param offset  the offset
     */
    public void load(byte [] payload, int offset)
    {
        this.protocolVersion = params.getProtocolVersionNum(NetworkParameters.ProtocolVersion.CURRENT);
        this.payload = payload;
        this.cursor = this.offset = offset;
        this.length = payload.length;

        if (this.length == UNKNOWN_LENGTH)
            checkState(false, "Length field has not been set in constructor for %s after %s parse. " +
                            "Refer to Message.parseLite() for detail of required Length field contract.",
                    getClass().getSimpleName(), /*parseLazy ? "lite" :*/ "full");
        parse();
    }

    /**
     * Create empty abstract manager.
     *
     * @return the abstract manager
     */
    public abstract AbstractManager createEmpty();

    /**
     * Gets default file name.
     *
     * @return the default file name
     */
    public String getDefaultFileName() {
        return defaultFileName;
    }

    /**
     * Gets magic message with the version number.
     *
     * @return the magic message
     */
    public String getMagicMessage() {
        return magicMessage + "-" + formatVersion;
    }

    /**
     * Gets default magic message.
     *
     * @return the default magic message
     */
    public String getDefaultMagicMessage() { return defaultMagicMessage; }

    /**
     * Gets format version.
     *
     * @return the format version
     */
    public int getFormatVersion() {
        return formatVersion;
    }

    /**
     * Save.
     *
     * @throws NullPointerException the null pointer exception
     */
    public void save() throws NullPointerException {
        if(filename != null) {
            FlatDB<AbstractManager> flatDB = new FlatDB<AbstractManager>(context, filename, true);
            flatDB.dump(this);
        } else throw new NullPointerException("filename is not set");
    }

    /**
     * Sets filename to which the object data will be saved.
     *
     * @param filename the filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
