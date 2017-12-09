package com.handle;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import java.util.Arrays;
import java.util.List;

import static com.util.PropertiesUtil.p;

/**
 * Created by Administrator on 2017\12\7 0007.
 */
public class ComponentHandle {
    static List<String> n3 = Arrays.asList("S PEN", "OLED 屏", "全视曲面屏");
    static List<String> apple = Arrays.asList("SUPER RETINA HD", "3D TOUCH", "APPLE PENCIL");
    static List<String> n2 = Arrays.asList("2GRAM", "AR APP", "AR CORE",
            "ARCORE", "AR", "GOOGLE PLAY",
            "TF卡", "手机卡", "SIM卡", "主卡", "4G卡", "3G卡",
            "SD卡", "MICROSD卡", "电信卡", "联通卡",
            "移动卡", "屏幕", "充电线",
            "拍照", "摄影", "微距", "色彩", "像素", "光泽度", "手机套", "真机"
            , "品控", "耳机", "电流声", "贴纸", "电池");
    public static List<String> n_one = Arrays.asList(
            "NOTE8", "GALAXY NOTE 8", "NOTE 8", "S8+",
            "S8", "牛8", "S8 PLUS", "NOTE7", "NOTE 7",
            "GALAXY NOTE 7", "S7 EDGE", "S7EDGE", "S6",
            "S6 EDGE", "NOTE", "GALAXY NOTE", "GALAXY", "牛5"
    );

    public static void main(String[] args) {
        OrientGraph graph = new OrientGraph(p.getProperty("orient.url"), p.getProperty("orient.username"), p.getProperty("orient.password"));
        insertEdgeByClass(graph, "Component", "MobileSubjectRelevant");

        //removeEdgeAllByClass(graph,"Component+");
        //graph.getVertex("").de
        //graph.removeVertex(graph.getVertex("#36:8"));
        //graph.removeEdge(graph.getEdge("#54:0"));

    }

    //手机out->电池in
    private static void insertEdgeByClass(OrientGraph graph, String label, String className) {
        //Iterable<Vertex> vertices = graph.getVertices("@class", className);
        n_one.forEach(n_o -> {
            Vertex out = graph.getVertices("word", n_o).iterator().next();
            n3.forEach(n -> {
                Vertex in = graph.getVertices("word", n).iterator().next();
                out.addEdge(label, in);
            });

        });

        graph.commit();
    }

    private static void removeEdgeAllByClass(OrientGraph graph, Object label) {
        if (label instanceof String) {
            Iterable<Edge> edges = graph.getEdgesOfClass((String) label);
            edges.forEach(graph::removeEdge);

        } else if (label instanceof Iterable) {
            ((Iterable) label).forEach(l -> {
                Iterable<Edge> edges = graph.getEdgesOfClass((String) l);
                edges.forEach(graph::removeEdge);
            });
        }
        graph.commit();
    }

}
