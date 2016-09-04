/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;

/**
 *
 * @author kakes
 */
public class FileBitPack {

    private File covelFile;
    private File outputFile;
    private File dataFile;
    private int compression;
    private String password;

    /**
     * @return the covelFile
     */
    public File getCovelFile() {
        return covelFile;
    }

    /**
     * @param covelFile the covelFile to set
     */
    public void setCovelFile(File covelFile) {
        this.covelFile = covelFile;
    }

    /**
     * @return the outputFile
     */
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * @param outputFile the outputFile to set
     */
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * @return the dataFile
     */
    public File getDataFile() {
        return dataFile;
    }

    /**
     * @param dataFile the dataFile to set
     */
    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * @return the compression
     */
    public int getCompression() {
        return compression;
    }

    /**
     * @param compression the compression to set
     */
    public void setCompression(int compression) {
        this.compression = compression;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
