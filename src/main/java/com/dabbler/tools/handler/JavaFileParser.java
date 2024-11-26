package com.dabbler.tools.handler;

import com.dabbler.tools.ast.java.ClassElement;
import com.dabbler.tools.ast.java.constant.Reserve;
import com.dabbler.tools.utils.FileHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/7/10
 */
public class JavaFileParser {




    public static List<ClassElement> getClassElement(String filePath) throws FileNotFoundException {

        List<String> lines = FileHelper.readFileLines(new File(filePath));
        return Lists.newArrayList();
    }
}
