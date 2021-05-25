package com.zhangxf.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class HelloPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create("dataPlugin",PluginConfig)
        project.tasks.register("hello") {
            doLast {
                println("Hello from plugin")
            }
        }
        project.afterEvaluate {
            println("debug:"+project.dataPlugin.debug)
        }

    }
}