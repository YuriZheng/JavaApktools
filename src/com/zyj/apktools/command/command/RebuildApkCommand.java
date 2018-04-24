package com.zyj.apktools.command.command;

import com.zyj.apktools.command.Receiver;

import java.io.File;

/**
 * CREATED ON: 2018/4/11 17:34
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 重打包成apk
 */
final class RebuildApkCommand extends AbstractCommand {

    /**
     * 重编译apk命令，两个参数：一个文件夹路径，一个生成的apk路径
     */
    private final String buildCommandString = getDecodJar() + "apktool build %s -o %s";

    private String filePath;

    public RebuildApkCommand(Receiver receiver) {
        super(receiver);
    }

    @Override public void execute() {
        final String separator = File.separator;
        final String parentPath = new File(filePath).getParent();
        final StringBuilder tagPath = new StringBuilder(parentPath);
        final String suffix = "_unsign__out.apk";
        if (parentPath.contains(separator)) {
            tagPath.append(separator);
            tagPath.append(filePath.substring(filePath.lastIndexOf(File.separator) + 1));

        }
        tagPath.append(suffix);
        File outFile = new File(tagPath.toString());
        if (outFile.exists()) {
            outFile.delete();
        }
        getReceiver().doAction(String.format(buildCommandString, filePath, outFile.getAbsolutePath()));
    }

    public RebuildApkCommand setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
}
