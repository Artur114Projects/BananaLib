package com.artur114.bananalib.math

def maxArgs = Long.MAX_VALUE
def types = ['int','long','float','double','boolean','char','short','byte','Object']
def className = 'Hasher'

new File("${className}.java").withPrintWriter { pw ->
    pw.println()
    pw.println "import java.util.Objects;"
    pw.println()
    pw.println("/**\n" +
            " * This file is generated. Do not edit, or the hash gods will curse you.\n" +
            " */")
    pw.println "public final class ${className} {"

    types.each { type ->
        (1..maxArgs).each { n ->
            def args = (0..<n).collect { "$type v$it" }.join(', ')
            def body = (1..<n).collect {"${it == 1 ? "" : "        " * (it)}31 * ${it == (n - 1) ? "" : "("}${n > 2 ? "\n" : ""}"}.join()
            body += ("        " * (n <= 2 ? 0 : n)) + hashMethod(type, 0)
            body += (1..<n).collect {" + ${hashMethod(type, it)}${it == (n - 1) ? "" : "\n${"        " * (n - (it + 1))})"}"}.join()
            pw.println "    public static int hash($args) {\n        return $body;\n    }"
            pw.println()
        }
    }

    pw.println "}"
}

println "${className}.java generated with ${types.size()} types and up to ${maxArgs} arguments per overload."


def hashMethod(String type, int arg) {
    def typesHashMap = [
            int: "Integer.hashCode",
            long: "Long.hashCode",
            float: "Float.hashCode",
            double: "Double.hashCode",
            char: "Character.hashCode",
            short: "Short.hashCode",
            byte: "Byte.hashCode",
            Object: "Objects.hashCode",
            boolean: "Boolean.hashCode"
    ]

    return "${typesHashMap.get(type)}(v$arg)"
}
