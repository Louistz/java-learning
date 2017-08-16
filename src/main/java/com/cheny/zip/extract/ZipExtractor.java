package com.cheny.zip.extract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.cheny.zip.spi.Extension;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.cheny.zip.spi.Extractor;

/**
 * <p>Filename: com.qianmi.pc.admin.task.common.zip.extract.ZipExtractor.java</p>
 * <p>Date: 2017-08-15 17:25.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
@Extension("zip")
public class ZipExtractor implements Extractor {
    @Override
    public String extract(String sourceZipFile, String targetDirectory) {
        File zipFileDirectory = new File(targetDirectory,FilenameUtils.getBaseName(sourceZipFile));
        zipFileDirectory.mkdirs();
        String path = zipFileDirectory.getPath();

        try(ZipFile zipFile = new ZipFile(new File(sourceZipFile))){
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                File entryFile = new File(path,entry.getName());
                if(entry.isDirectory()){
                    entryFile.mkdirs();
                    continue;
                }
                File parent = entryFile.getParentFile();
                if(parent != null && !parent.exists()){
                    parent.mkdirs();
                }
                InputStream in = zipFile.getInputStream(entry);
                OutputStream out = new FileOutputStream(entryFile);
                IOUtils.copy(in,out);
                IOUtils.closeQuietly(in);
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }
}
