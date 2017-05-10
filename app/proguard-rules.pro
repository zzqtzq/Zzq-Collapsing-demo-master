# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidStudio2.0\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontskipnonpubliclibraryclasses #指定不去忽略非公共的库类
-dontpreverify           # 混淆时是否做预校验
-dontwarn #不警告
-verbose                # 混淆时是否记录日志
-obfuscationdictionary dictionary-elder.txt
-dontshrink #不压缩 roboguice 需要设置成不压缩
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法
-ignorewarnings #忽略警告
# 保留签名，解决泛型、类型转换的问题
-keepattributes Signature
# 不混淆带有 annotation 的变量 和 函数
-keepattributes *Annotation*

-keep class * implements java.io.Serializable

# 除了 其他都不混淆
#-keep class !me.ele.** {*;}

# eventbus(只有指定的member不混淆)
-keepclassmembers class * {
    public void onEvent*(**);
}

#scheme
-keep class **$$SchemeDatabase
-keep class me.ele.scheme.SchemeDispatcher

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keep class freemarker.** {*;}

#weixin
-keep class com.tencent.mm.sdk.** {
   *;
}

# WebView
-keepclassmembers class * extends android.webkit.WebChromeClient {
     public void openFileChooser(...);
}