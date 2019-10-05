package edu.elearning.xml;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class XmlFileHelper {

    private static final String DEFAULT_FILE_EXTENSION = ".xml";

    public static List<File> getAllFilesForFolder(String bashPath) {

        return getAllFilesForFolder(bashPath, DEFAULT_FILE_EXTENSION);
    }

    public static List<File> getAllFilesForFolder(String bashPath, String extension) {

        File bashDir = new File(XmlFileHelper.class.getClassLoader().getResource(bashPath).getFile());

        List<File> fileList = Arrays.stream(bashDir.listFiles((dir, name) -> name.toLowerCase().endsWith(extension)))
                .filter(file -> file.isFile())
                .collect(Collectors.toList());


        return fileList;
    }
}
