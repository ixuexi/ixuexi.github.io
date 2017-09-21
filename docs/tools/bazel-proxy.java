/*
                    bazel支持proxy代理版本
                    bazel with proxy brach

目前bazel编译工具还不能在使用proxy的网络环境下下载url依赖包，不知是proxy下载功能未开发完整还是什么原因，
只要在proxy网络环境下，无法下载外部包依赖。从bazel在github的issue上看，好像当前还不支持proxy下载功能。

从bazel本身的代码看，在运行bazel时更是将http_proxy的环境变量给去掉了：
src/main/cpp/blaze.cc ：
*/

static void PrepareEnvironmentForJvm() {
  if (!blaze::GetEnv("http_proxy").empty()) {
    PrintWarning("ignoring http_proxy in environment.");
    blaze::UnsetEnv("http_proxy");
  }
  
/*

由于本人也是工作在proxy网络之下(痛苦)，因此自己简易做了一个代码适配，可以支持proxy的bazel，github地址：
https://github.com/ixuexi/bazel

下载代码后，在原有bazel基础上(建议使用bazel 0.5.3以上版本，没有的下载二进制包安装一个即可)，
重新编译bazel（方法见readme）然后替换现有bazel二进制文件即可。


Kevin 
2017/9/21

*/