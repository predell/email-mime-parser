package com.predell.email;

import com.predell.storage.AbstractStorageProvider;
import com.predell.storage.MemoryStorageProvider;
import org.apache.james.mime4j.storage.Storage;
import org.apache.james.mime4j.storage.StorageProvider;
import org.apache.james.mime4j.stream.BodyDescriptor;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public abstract class Attachment {

    /**
     *
     */
    protected BodyDescriptor bd;

    /**
     *
     * @return Attachment name
     */
    public abstract String getAttachmentName();

    private InputStream is;

    /**
     *
     * @return Body descriptor
     */
    public BodyDescriptor getBd() {
        return bd;
    }

    private Storage storage;

    /**
     *
     * @return Input Stream
     */
    public InputStream getIs() {
        try {
            is = storage.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return is;
    }

    private int attachmentSize;

    /**
     *
     * @return Attachment size
     */
    public int getAttachmentSize() {
        return attachmentSize;
    }

    /**
     *
     * @param is Set Input Stream
     */
    public void setIs(InputStream is) {
        StorageProvider storageProvider = new MemoryStorageProvider();
        try {
            storage = storageProvider.store(is);
            attachmentSize = ((AbstractStorageProvider) storageProvider).getTotalBytesTransferred();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param bd Body descriptor
     * @param is Input Stream
     */
    public Attachment(BodyDescriptor bd, InputStream is) {
        this.bd = bd;
        attachmentSize = 0;
        setIs(is);
    }

}