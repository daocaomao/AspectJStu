package com.zhangxf.plugin

import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

class CollectionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def logger = project.logger
        project.dependencies {
            implementation 'org.aspectj:aspectjrt:1.9.0'
        }
        project.android.applicationVariants.all { variant ->
            JavaCompile javaCompile = variant.javaCompile

            javaCompile.doLast {
                String[] args = [
                        "-showWeaveInfo",
                        "-1.7",
                        "-inpath", javaCompile.destinationDir.toString(),
                        "-aspectpath", javaCompile.classpath.asPath,
                        "-d", javaCompile.destinationDir.toString(),
                        "-classpath", javaCompile.classpath.asPath,
                        "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)
                ]
                MessageHandler handler = new MessageHandler(true);
                new Main().run(args, handler);

                println()
                println("####################################################################")
                println("########                                                    ########")
                println("########                                                    ########")
                println("########         欢迎使用 CollectionPlugin 编译插件        ########")
                println("########          使用过程中碰到任何问题请联系我们          ########")
                println("########                                                    ########")
                println("########                                                    ########")
                println("####################################################################")
                println()

                for (IMessage message : handler.getMessages(null, true)) {
                    switch (message.getKind()) {
                        case IMessage.ABORT:
                        case IMessage.ERROR:
                        case IMessage.FAIL:
                            logger.error message.message, message.thrown
                            break;
                        case IMessage.WARNING:
                            logger.warn message.message, message.thrown
                            break;
                        case IMessage.INFO:
                            logger.info message.message, message.thrown
                            break;
                        case IMessage.DEBUG:
                            logger.debug message.message, message.thrown
                            break;
                    }
                }
            }
        }
    }
}