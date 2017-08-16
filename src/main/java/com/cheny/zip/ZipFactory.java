package com.cheny.zip;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

import com.cheny.zip.spi.Extension;
import com.cheny.zip.spi.Extractor;

/**
 * <p>Filename: com.qianmi.pc.admin.task.common.zip.ZipFactory.java</p>
 * <p>Date: 2017-08-16 15:39.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class ZipFactory {
    private ZipFactory(){}


    public static Extractor createExtractor(String fileName){
        if(StringUtils.isBlank(fileName)){
            throw new IllegalArgumentException("文件格式错误");
        }
        List<String> fileFormats = new ArrayList<>();
        fileFormats.add(fileName);

        while (true){
            String lastFormat = fileFormats.get(fileFormats.size()-1);
            String format = lastFormat.substring(lastFormat.indexOf(".") + 1);
            if(StringUtils.isBlank(format) || format.equals(lastFormat)){
                break;
            }
            fileFormats.add(format);
        }

        Map<String,Extractor> extractorMap = new HashMap<>();
        ServiceLoader<Extractor> serviceLoader = ServiceLoader.load(Extractor.class);
        Iterator<Extractor> extractors = serviceLoader.iterator();
        while (extractors.hasNext()){
            Extractor extractor = extractors.next();
            Extension extension = extractor.getClass().getAnnotation(Extension.class);
            if(null != extension){
                extractorMap.put(extension.value(),extractor);
            }
        }

        for(String extension : fileFormats){
            if(extractorMap.containsKey(extension)){
                return extractorMap.get(extension);
            }
        }
        throw new IllegalArgumentException("未找到文件["+fileName+"]对应的解压器.");
    }
}
