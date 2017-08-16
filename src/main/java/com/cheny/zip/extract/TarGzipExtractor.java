package com.cheny.zip.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;

import com.cheny.zip.spi.Extension;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.cheny.zip.spi.Extractor;

/**
 * <p>Filename: com.qianmi.pc.admin.task.common.zip.extract.TarGzipExtractor.java</p>
 * <p>Date: 2017-08-16 15:00.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
@Extension("tar.gz")
public class TarGzipExtractor implements Extractor {
    @Override
    public String extract(String sourceZipFile, String targetDirectory) {
        File zipFileDirectory = new File(targetDirectory, FilenameUtils.getBaseName(FilenameUtils.getBaseName(sourceZipFile)));
        zipFileDirectory.mkdirs();
        String path = zipFileDirectory.getPath();

        try(FileInputStream fin = new FileInputStream(sourceZipFile);
            GZIPInputStream gzin = new GZIPInputStream(fin);
            ArchiveInputStream ain = new ArchiveStreamFactory().createArchiveInputStream("tar",gzin)){
            TarArchiveEntry entry = null;
            while ((entry = (TarArchiveEntry)ain.getNextEntry()) != null){
                File file = new File(path,entry.getName());
                if(entry.isDirectory()){
                    file.mkdirs();
                    continue;
                }
                File parentFile = file.getParentFile();
                if(null != parentFile && !parentFile.exists()){
                    parentFile.mkdirs();
                }

                FileOutputStream fout = new FileOutputStream(file);
                IOUtils.copy(ain,fout);
                fout.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }
}
