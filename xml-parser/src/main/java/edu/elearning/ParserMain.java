package edu.elearning;

import edu.elearning.regex.StackExchangeRegex;
import edu.elearning.se.AsteriModel;
import edu.elearning.translator.TranslationException;
import edu.elearning.translator.Translator;
import edu.elearning.translator.TranslatorRegistry;
import edu.elearning.xml.XmlFileHelper;
import edu.elearning.xml.XmlFileReader;
import edu.elearning.xml.XmlFileReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

public class ParserMain {

    private static final Logger LOG = Logger.getLogger("ParserMain");

    public static void main(String[] args) throws FileNotFoundException {

        List<File> files = XmlFileHelper.getAllFilesForFolder("D:/stackexchange/beer/");
        Map<String, List<AsteriModel>> modelMap = new HashMap<>();

        for (File file : files) {

            final String website_name = file.getParentFile().getName();

            String name = file.getName();

            name = name.substring(0, name.length() - 4).toLowerCase();

            Translator<? extends AsteriModel> translator = TranslatorRegistry.MODEL_REGISTRY.get(name);

            if(translator == null) {
                continue;
            }

            XmlFileReader xmlFileReader = new XmlFileReaderImpl(file);
            Optional<String> line = xmlFileReader.readLineFromXmlFile();

            List<AsteriModel> models = new ArrayList<>();
            List<String> exceptions = new ArrayList<>();

            while (line.isPresent()) {
                Map<String, String> map = StackExchangeRegex.parseString(line.get());
                line = xmlFileReader.readLineFromXmlFile();

                if (!map.isEmpty()) {
                    map.put("se_website", website_name);
                    AsteriModel asteriModel = null;
                    try {
                        asteriModel = translator.translate(map);
                    } catch (TranslationException e) {
                        exceptions.add(name + " - "+ map.get("id") + " - "  + e.getType().getMessage());
                        continue;
                    }
                    models.add(asteriModel);
                }
            }

            modelMap.put(name, models);

        }

        LOG.info("Number of file parsed - " + modelMap.size());
    }


}
