package syncinfo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import syncinfo.utils.security.TripleDESUtil;


public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final String ENCRYPTED_PREFIX = "Encrypted:{";
    private static final String ENCRYPTED_SUFFIX = "}";
    private static Pattern encryptedPattern = Pattern.compile("Encrypted:\\{([a-z,A-Z,0-9,/,+]*[=]{0,})\\}");  
    private Logger logger = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);
    
    private Set<String> encryptedProps = Collections.emptySet();

    public void setEncryptedProps(Set<String> encryptedProps) {
        this.encryptedProps = encryptedProps;
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {

        if (encryptedProps.contains(propertyName)) { 
            final Matcher matcher = encryptedPattern.matcher(propertyValue);  
            if (matcher.matches()) {   
                String encryptedString = matcher.group(1);   
                String decryptedPropValue = null;
				try {
					decryptedPropValue = TripleDESUtil.decrypt(propertyName, encryptedString);
				} catch (Exception e) {
					 logger.error(e.getMessage(), e);
				} 

                if (decryptedPropValue != null) {  
                    propertyValue = decryptedPropValue; 
                } else {
                    logger.error("Decrypt " + propertyName + "=" + propertyValue + " error!");
                }
            }
        }

        return super.convertProperty(propertyName, propertyValue); 
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);    

        for (Resource location : locations) {   

            try {
                final File file = location.getFile();
                if (file.isFile()) {  

                    if (file.canWrite()) { 
                        encrypt(file);   
                    } else {
                        if (logger.isWarnEnabled()) {
                            logger.warn("File '" + location + "' can not be write!");
                        }
                    }

                } else {
                    if (logger.isWarnEnabled()) {
                        logger.warn("File '" + location + "' is not a normal file!");
                    }
                }

            } catch (IOException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("File '" + location + "' is not a normal file!");
                }
            }
        }

    }

    /**
     * 属性文件加密方法
     *
     * @param file
     */
    private void encrypt(File file) {

        List<String> outputLine = new ArrayList<String>();  

        boolean doEncrypt = false;   


        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            String line = null;
            do {

                line = bufferedReader.readLine(); 
                if (line != null) { 
                    if (StringUtils.isNotBlank(line)) {   
                        line = line.trim();    
                        if (!line.startsWith("#")) {
                            String[] lineParts = line.split("=", 2);  
                            String key = lineParts[0];      
                            String value = lineParts[1];     
                            if (key != null && value != null) {
                                if (encryptedProps.contains(key)) { 
                                    final Matcher matcher = encryptedPattern.matcher(value);
                                    if (!matcher.matches()) { 

                                        value = ENCRYPTED_PREFIX + TripleDESUtil.encrypt(key, value) + ENCRYPTED_SUFFIX; 

                                        line = key + "=" + value;  

                                        doEncrypt = true;   

                                        if (logger.isDebugEnabled()) {
                                            logger.debug("encrypt property:" + key);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    outputLine.add(line);
                }

            } while (line != null);


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
       
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        if (doEncrypt) {      
            BufferedWriter bufferedWriter = null;
            File tmpFile = null;
            try {
                tmpFile = File.createTempFile(file.getName(), null, file.getParentFile());  

                if (logger.isDebugEnabled()) {
                    logger.debug("Create tmp file '" + tmpFile.getAbsolutePath() + "'.");
                }

                bufferedWriter = new BufferedWriter(new FileWriter(tmpFile));

                final Iterator<String> iterator = outputLine.iterator();
                while (iterator.hasNext()) {                          
                    bufferedWriter.write(iterator.next());
                    if (iterator.hasNext()) {
                        bufferedWriter.newLine();
                    }
                }

                bufferedWriter.flush();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }

            File backupFile = new File(file.getAbsoluteFile() + "_" + System.currentTimeMillis());  

            //以下为备份，异常恢复机制
            if (!file.renameTo(backupFile)) {   
                logger.error("Could not encrypt the file '" + file.getAbsoluteFile() + "'! Backup the file failed!");
                tmpFile.delete(); //删除临时文件
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Backup the file '" + backupFile.getAbsolutePath() + "'.");
                }

                if (!tmpFile.renameTo(file)) {   
                    logger.error("Could not encrypt the file '" + file.getAbsoluteFile() + "'! Rename the tmp file failed!");

                    if (backupFile.renameTo(file)) {   
                        if (logger.isInfoEnabled()) {
                            logger.info("Restore the backup, success.");
                        }
                    } else {
                        logger.error("Restore the backup, failed!");
                    }
                } else {  //（加密文件替换原成功）

                    if (logger.isDebugEnabled()) {
                        logger.debug("Rename the file '" + tmpFile.getAbsolutePath() + "' -> '" + file.getAbsoluteFile() + "'.");
                    }

                    boolean dBackup = backupFile.delete();

                    if (logger.isDebugEnabled()) {
                        logger.debug("Delete the backup '" + backupFile.getAbsolutePath() + "'.(" + dBackup + ")");
                    }
                }
            }


        }

    }

    protected Resource[] locations;

    @Override
    public void setLocations(Resource[] locations) {   
        super.setLocations(locations);
        this.locations = locations;
    }

    @Override
    public void setLocation(Resource location) {   
        super.setLocation(location);
        this.locations = new Resource[]{location};
    }
}
