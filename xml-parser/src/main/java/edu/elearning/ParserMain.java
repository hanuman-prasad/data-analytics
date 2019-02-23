package edu.elearning;

import edu.elearning.regex.StackExchangeRegex;
import edu.elearning.se.AsteriModel;
import edu.elearning.translator.Translator;
import edu.elearning.translator.TranslatorRegistry;
import edu.elearning.xml.XmlFileHelper;
import edu.elearning.xml.XmlFileReader;
import edu.elearning.xml.XmlFileReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ParserMain {
    public static void main(String[] args) throws FileNotFoundException {

        List<File> files = XmlFileHelper.getAllFilesForFolder("D:/stackexchange/beer/parse/");
        Map<String, List<AsteriModel>> modelMap = new HashMap<>();

        for (File file : files) {

            String name = file.getName();

            name = name.substring(0, name.length() - 4).toLowerCase();

            Translator<? extends AsteriModel> translator = TranslatorRegistry.MODEL_REGISTRY.get(name);

            XmlFileReader xmlFileReader = new XmlFileReaderImpl(file);
            Optional<String> line = xmlFileReader.readLineFromXmlFile();

            List<AsteriModel> list = new ArrayList<>();

            while (line.isPresent()) {
                Map<String, String> map = StackExchangeRegex.parseString(line.get());
                if (!map.isEmpty()) {
                    AsteriModel asteriModel = translator.translate(map);
                    list.add(asteriModel);
                }
                line = xmlFileReader.readLineFromXmlFile();
            }


            modelMap.put(name, list);


        }

    }


}
