package edu.elearning.xml;

import java.util.Optional;

public interface XmlFileReader {

    public Optional<String> readLineFromXmlFile();

    public void close();
}
