package com.lanwq.jvm.parameterTest;

/**
 * @program: javaThinkingDemo->XXtype
 * @description: jvm XX  类型参数
 * @author: lanwenquan
 * @date: 2023-03-19 13:41
 */
public class XXtype {
    public static void main(String[] args) {
        System.out.println("············Hello");
//        while (true) {
            // 让死循环，然后使用命令查看jvm此时的命令，可以使用 jsp，jinfo
            /**
             * 使用输出：
             * E:\project\IdeaProjects\javaDemo>jps -l
             * 14864 com.lanwq.jvm.parameterTest.XXtype
             * 13364 org.jetbrains.jps.cmdline.Launcher
             * 5368 jdk.jcmd/sun.tools.jps.Jps
             * 15484
             *
             * E:\project\IdeaProjects\javaDemo>
             */
//        }

        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("totalMemory(-Xms) = " + totalMemory);
        System.out.println("maxMemory(-Xmx) = " + maxMemory);
    }

}
