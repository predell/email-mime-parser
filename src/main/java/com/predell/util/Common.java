package com.predell.util;

import com.predell.configuration.AppConfig;
import com.predell.disposition.ContentDispositionDecoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.message.MaximalBodyDescriptor;
import org.apache.james.mime4j.stream.BodyDescriptor;

import java.util.Map;
import java.util.Properties;

/**
 *
 */
public class Common {

    /**
     * @param bd Body descriptor
     * @return Attachment name
     */
    public static String getAttachmentNameFromDispositionParameters(BodyDescriptor bd) {
        //Refer RFC : 2047 for mime word decoding
        //Refer RFC : 2183 for Content-Disposition Header Field
        String attachmentName = null;
        if (bd instanceof MaximalBodyDescriptor) {
            attachmentName = getDecodedWord(((MaximalBodyDescriptor) bd).getContentDispositionFilename());
            if (StringUtils.isEmpty(attachmentName)) {
                //Handling case where RFC 2183 is not properly implemented by tech.blueglacier.email creating client.
                attachmentName = getDecodedWord(((MaximalBodyDescriptor) bd).getContentDispositionParameters().get("name"));
            }
            //Added case for supporting RFC 2184 'Parameter Value Character Set and Language Information' 
            // this is currently not supported in mime4j (version 0.7.2)
            if (StringUtils.isEmpty(attachmentName)) {
                attachmentName = getDecodedDispositionFileName(bd);
            }
        }
        return attachmentName;
    }

    /**
     * @param bd Body descriptor
     * @return Decoded disposition file name
     */
    private static String getDecodedDispositionFileName(BodyDescriptor bd) {
        String attachmentName = null;
        try {
            attachmentName = ContentDispositionDecoder.decodeDispositionFileName(((MaximalBodyDescriptor) bd).getContentDispositionParameters());
        } catch (MimeException e) {
            throw new RuntimeException(e);
        }
        return attachmentName;
    }

    /**
     * @param filename File name
     * @return Decoded word
     */
    private static String getDecodedWord(String filename) {
        filename = MimeWordDecoder.decodeEncodedWords(filename, DecodeMonitor.SILENT);
        return filename;
    }

    /**
     * @param bd Body descriptor
     * @return Attachment name
     */
    public static String getAttachmentNameFromContentTypeParemeters(BodyDescriptor bd) {
        String attachmentName = null;
        if (bd instanceof MaximalBodyDescriptor) {
            Map<String, String> contentTypeParameters = ((MaximalBodyDescriptor) bd).getContentTypeParameters();
            String nameKey = null;
            if (contentTypeParameters.containsKey(nameKey = "name") || contentTypeParameters.containsKey(nameKey = "NAME")
                    || contentTypeParameters.containsKey(nameKey = "Name")) {
                attachmentName = contentTypeParameters.get(nameKey);
            }
            attachmentName = getDecodedWord(attachmentName);
        }
        return attachmentName;
    }

    /**
     * @param bd Body descriptor
     * @return Attachment name
     */
    public static String getAttachmentName(BodyDescriptor bd) {
        // Content tech.blueglacier.disposition 'filename' is more standard, so it's taken as default first
        String attachmentName = Common.getAttachmentNameFromDispositionParameters(bd);
        if (attachmentName == null || attachmentName.isEmpty()) {
            // Content type 'name' is other alternative so it's taken as alternative too
            attachmentName = Common.getAttachmentNameFromContentTypeParemeters(bd);
        }
        return attachmentName;
    }

    /**
     *
     * @param charSet Charset
     * @return Fallback charset
     */
    public static String getFallbackCharset(String charSet) {
        Properties charSetMap;
        charSetMap = AppConfig.getInstance().getCharSetMap();
        charSet = charSetMap.getProperty(charSet.toLowerCase(), charSet);
        return charSet;
    }
}
