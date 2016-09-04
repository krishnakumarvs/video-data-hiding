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
public class TextBitPack {

    private File coverFile;
    private File outputFile;
    private String msg;
    private int compression;
    private String password;
   

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
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
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

    /**
     * @return the coverFile
     */
    public File getCoverFile() {
        return coverFile;
    }

    /**
     * @param coverFile the coverFile to set
     */
    public void setCoverFile(File coverFile) {
        this.coverFile = coverFile;
    }
}
