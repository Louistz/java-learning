package com.cheny.zip.extract;

import com.cheny.zip.spi.Extension;
import com.cheny.zip.spi.Extractor;
import com.github.junrar.extract.ExtractArchive;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * <p>Filename: com.cheny.zip.extract.RarExtractor.java</p>
 * <p>Date: 2017-08-16 16:40.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
@Extension("rar")
public class RarExtractor implements Extractor {

    @Override
    public String extract(String sourceZipFile, String targetDirectory) {
        File zipFileDirectory = new File(targetDirectory, FilenameUtils.getBaseName(sourceZipFile));
        zipFileDirectory.mkdirs();
        String path = zipFileDirectory.getPath();
        File rar = new File(sourceZipFile);
        ExtractArchive extractArchive = new ExtractArchive();
        extractArchive.extractArchive(rar,zipFileDirectory);
        return path;
    }
}
