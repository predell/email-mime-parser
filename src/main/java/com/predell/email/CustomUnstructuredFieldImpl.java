package com.predell.email;

import com.predell.util.MimeWordDecoder;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.UnstructuredField;
import org.apache.james.mime4j.field.AbstractField;
import org.apache.james.mime4j.stream.Field;

/**
 * Simple unstructured field such as <code>Subject</code>.
 */
public class CustomUnstructuredFieldImpl extends AbstractField implements UnstructuredField {
    private boolean parsed = false;

    private String value;

    /**
     *
     * @param rawField Raw field
     * @param monitor Decode monitor
     */
    public CustomUnstructuredFieldImpl(Field rawField, DecodeMonitor monitor) {
        super(rawField, monitor);
    }

    /**
     * @see org.apache.james.mime4j.dom.field.UnstructuredField#getValue()
     */
    public String getValue() {
        if (!parsed)
            parse();

        return value;
    }

    private void parse() {
        String body = getBody();

        value = MimeWordDecoder.decodeEncodedWords(body, monitor);

        parsed = true;
    }

    /**
     *
     */
    public static final FieldParser<UnstructuredField> PARSER = new FieldParser<UnstructuredField>() {

        public UnstructuredField parse(final Field rawField, final DecodeMonitor monitor) {
            return new CustomUnstructuredFieldImpl(rawField, monitor);
        }

    };

}

