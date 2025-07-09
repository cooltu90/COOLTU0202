package com.codingtu.cooltu.processor.tool;

import static java.util.Objects.requireNonNull;

import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;

public class IdTool {
    public static Trees trees;
    public static RScanner rScanner;

    public static class Id {
        public String rPackage;
        public String rType;
        public String rName;
        public String r;

        public Id(String rPackage, String rType, String rName) {
            this.rPackage = rPackage;
            this.rType = rType;
            this.rName = rName;
            this.r = "R." + this.rType + "." + this.rName;
        }

        public Id(int value) {
            this(value, null);
        }

        public Id(int value, @Nullable Symbol rSymbol) {
            if (rSymbol != null) {
                this.rPackage = rSymbol.packge().getQualifiedName().toString();
                this.rType = rSymbol.enclClass().name.toString();
                this.rName = rSymbol.name.toString();
                this.r = "R." + this.rType + "." + this.rName;
            } else {
                this.r = value + "";
            }
        }

        @Override
        public String toString() {
            return rPackage + "." + this.r;
        }
    }

    public static class RScanner extends TreeScanner {
        public Map<Integer, Id> resourceIds = new LinkedHashMap<>();

        @Override
        public void visitSelect(JCTree.JCFieldAccess jcFieldAccess) {
            Symbol symbol = jcFieldAccess.sym;
            if (symbol.getEnclosingElement() != null
                    && symbol.getEnclosingElement().getEnclosingElement() != null
                    && symbol.getEnclosingElement().getEnclosingElement().enclClass() != null) {
                try {
                    int value = (Integer) requireNonNull(((Symbol.VarSymbol) symbol).getConstantValue());
                    resourceIds.put(value, new Id(value, symbol));
                } catch (Exception ignored) {
                }
            }
        }

        @Override
        public void visitLiteral(JCTree.JCLiteral jcLiteral) {
            try {
                int value = (Integer) jcLiteral.value;
                resourceIds.put(value, new Id(value));
            } catch (Exception ignored) {
            }
        }

        public void reset() {
            resourceIds.clear();
        }
    }

    private static AnnotationMirror getMirror(Element element,
                                              Class<? extends Annotation> annotation) {
        for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
            if (annotationMirror.getAnnotationType().toString().equals(annotation.getCanonicalName())) {
                return annotationMirror;
            }
        }
        return null;
    }

    public static Id elementToId(Element element, Class<? extends Annotation> annotation, int value) {
        JCTree tree = (JCTree) trees.getTree(element, getMirror(element, annotation));
        if (tree != null) { // tree can be null if the references are compiled types and not source
            rScanner.reset();
            tree.accept(rScanner);
            if (!rScanner.resourceIds.isEmpty()) {
                return rScanner.resourceIds.get(value);
            }
        }
        return new Id(value);
    }

    public static Map<Integer, Id> elementToIds(Element element, Class<? extends Annotation> annotation,
                                                int[] values) {
        Map<Integer, Id> resourceIds = new LinkedHashMap<>();
        JCTree tree = (JCTree) trees.getTree(element, getMirror(element, annotation));
        if (tree != null) { // tree can be null if the references are compiled types and not source
            rScanner.reset();
            tree.accept(rScanner);
            resourceIds = rScanner.resourceIds;
        }
        // Every ids looked up should have an Id

        Map<Integer, Id> ids = new LinkedHashMap<>();
        for (int value : values) {

            Id id = resourceIds.get(value);
            if (id != null) {
                ids.put(value, id);
            } else {
                ids.put(value, new Id(value));
            }
        }

        return ids;
    }

}
