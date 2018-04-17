package com.zyj.apktools.command.command;

import com.zyj.apktools.Utils;
import com.zyj.apktools.command.Receiver;

import java.io.File;

/**
 * CREATED ON: 2018/4/11 17:34
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 命令发出者
 */
final class SignerApkCommand extends AbstractCommand {

    /**
     * jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore itools.jks -storepass *** -keypass *** -signedjar new_signed.apk new.apk itools
     * 开始对apk进行签名
     * 第一个参数：密钥文件路径<br>
     * 第二个参数：密钥文件密码<br>
     * 第三个参数：专用密钥密码（一般都是密钥文件密码一致）<br>
     * 第四个参数：未签名apk路径<br>
     * 第五个参数：输出签名apk路径<br>
     * 第六个参数：密钥别名<br>
     */
    private final String signerCommandString = "jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore %s -storepass %s -keypass %s -signedjar %s %s %s";

    private String storPath;
    private String storpass;
    private String keypass;
    private String unsignApk;
    private String alias;

    public SignerApkCommand(Receiver receiver) {
        super(receiver);
    }

    @Override public void execute() {
        File apk = new File(unsignApk);
        String newName = apk.getName().substring(0, apk.getName().lastIndexOf(".")) + "_signed.apk";
        String outputApk = apk.getParent() + Utils.getFileSeparator() + newName;
        getReceiver().doAction(String.format(signerCommandString, storPath, storpass, keypass, outputApk, unsignApk, alias));
    }

    public SignerApkCommand setStorPath(String storPath) {
        this.storPath = storPath;
        return this;
    }

    public SignerApkCommand setStorpass(String storpass) {
        this.storpass = storpass;
        return this;
    }

    public SignerApkCommand setKeypass(String keypass) {
        this.keypass = keypass;
        return this;
    }

    public SignerApkCommand setUnsignApk(String unsignApk) {
        this.unsignApk = unsignApk;
        return this;
    }

    public SignerApkCommand setAlias(String alias) {
        this.alias = alias;
        return this;
    }
}
