package com.linfafa.test;

import com.linfafa.test.antlr4.ArrayInitLexer;
import com.linfafa.test.antlr4.ArrayInitParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

/**
 * @author linmin
 * @date 2021/8/31
 */
public class Test {
    public static void main(String[] args) throws IOException {
        //从标准输入读取数据
        ANTLRInputStream input = new ANTLRInputStream(System.in);

        //新建词法分析器，处理输入的CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input);

        //词法符号缓冲区，用于存储词法分析器将生成的词法符号
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //语法分析器，处理词法符号缓冲区中的内容
        ArrayInitParser parser = new ArrayInitParser(tokens);

        ParseTree tree = parser.init();// 针对init规则，开始语法分析

        System.out.println(tree.toStringTree(parser));//用LISP风格打印生成的树
    }
}
