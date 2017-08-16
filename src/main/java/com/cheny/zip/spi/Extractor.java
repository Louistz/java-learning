package com.cheny.zip.spi;

/**
 * 解压器
 * <p>Filename: com.qianmi.pc.admin.task.common.zip.spi.Extractor.java</p>
 * <p>Date: 2017-08-15 17:22.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public interface Extractor {
    String extract(String sourceZipFile, String targetDirectory);
}
