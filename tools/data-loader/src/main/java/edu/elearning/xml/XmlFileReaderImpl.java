package edu.elearning.xml;

import java.io.*;
import java.util.Optional;
import java.util.logging.Logger;

public class XmlFileReaderImpl implements XmlFileReader {

    private static final Logger LOG = Logger.getLogger("XmlFileReaderImpl");

    private final BufferedReader reader;

    public XmlFileReaderImpl(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public XmlFileReaderImpl(File file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
    }


    @Override
    public Optional<String> readLineFromXmlFile() {
        try {
            return Optional.ofNullable(reader.readLine());
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }

    }
}
