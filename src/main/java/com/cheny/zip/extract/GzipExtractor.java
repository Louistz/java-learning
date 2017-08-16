package com.cheny.zip.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import com.cheny.zip.spi.Extension;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.cheny.zip.spi.Extractor;

/**
 * <p>Filename: com.qianmi.pc.admin.task.common.zip.extract.GzipExtractor.java</p>
 * <p>Date: 2017-08-16 10:04.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
@Extension("gz")
public class GzipExtractor implements Extractor {
    @Override
    public String extract(String sourceZipFile, String targetDirectory) {
        File zipFileDirectory = new File(targetDirectory,FilenameUtils.getBaseName(sourceZipFile));
        zipFileDirectory.mkdirs();
        String path = zipFileDirectory.getPath();

        try(FileInputStream fin = new FileInputStream(sourceZipFile); GZIPInputStream gzin = new GZIPInputStream(fin)){
            File zipFile = new File(path,FilenameUtils.getBaseName(sourceZipFile));
            FileOutputStream fout = new FileOutputStream(zipFile);
            IOUtils.copy(gzin,fout);
            fout.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }
}
