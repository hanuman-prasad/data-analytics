package edu.elearning;

import com.google.common.collect.ImmutableMap;
import edu.elearning.regex.StackExchangeRegex;
import edu.elearning.se.AsteriModel;
import edu.elearning.se.Post;
import edu.elearning.translator.PostTranslator;
import edu.elearning.translator.Translator;
import edu.elearning.translator.UserTranslator;
import edu.elearning.xml.XmlFileHelper;
import edu.elearning.xml.XmlFileReader;
import edu.elearning.xml.XmlFileReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ParserMain {
    public static void main(String[] args) throws FileNotFoundException {
        XmlFileReader xmlFileReader = new XmlFileReaderImpl("D:/stackexchange/beer/Posts.xml");

        List<File> files = XmlFileHelper.getAllFilesForFolder("D:/stackexchange/beer/");

        for(File file : files){

        }

        Optional<String> line = xmlFileReader.readLineFromXmlFile();



        List<Map<String, String>> list = new ArrayList<>();

        while (line.isPresent()) {
            Map<String, String> map = StackExchangeRegex.parseString(line.get());
            if (!map.isEmpty()) {
                list.add(map);
            }
            line = xmlFileReader.readLineFromXmlFile();
        }

        Translator<Post> translator;
        for (Map<String, String> m : list) {
            Set<Map.Entry<String, String>> entries = m.entrySet();
            for (Map.Entry<String, String> e : entries) {

                if (e.getKey().equals("176")) {
                    System.out.println(e.getValue());
                }

//                System.out.println(e.getKey() + e.getValue());
            }
            String id = m.get("id");
            if (id.equals("177")) {
                System.out.println(id);
            }
            translator = new PostTranslator();
            Post post = translator.translate(m);
            System.out.println(post);
        }

    }


}
