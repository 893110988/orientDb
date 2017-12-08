package com.handle;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.util.PropertiesUtil.p;

/**
 * Created by Administrator on 2017\12\7 0007.
 */
public class ComponentHandle {
    public static void main(String[] args) {
            List<String> n3=Arrays.asList("S PEN","OLED 屏","全视曲面屏");
            List<String> apple=Arrays.asList("SUPER RETINA HD","3D TOUCH","APPLE PENCIL");
            List<String> n2= Arrays.asList( "2GRAM", "AR APP", "AR CORE"/*,
                    "ARCORE", "AR",  "GOOGLE PLAY",
                    "TF卡", "手机卡", "SIM卡", "主卡", "4G卡", "3G卡",
                    "SD卡", "SIM卡", "MICROSD卡", "电信卡", "联通卡",
                    "移动卡", "屏幕", "充电线",
                    "拍照", "摄影", "微距", "色彩", "像素", "光泽度","手机套", "真机"
                    ,"品控","耳机","电流声","贴纸"*/);
            OrientGraph graph=new OrientGraph( p.getProperty("orient.url"), p.getProperty("orient.username"), p.getProperty("orient.password"));
            Iterable<Vertex> vertices=graph.getVertices("@class","MobileSubjectRelevant");
        //Iterable<Vertex> vertices=graph.getVertices("word","NOTE8");

        vertices.forEach(vertex -> {
            n2.forEach(n->{
                Vertex in=graph.getVertices("word",n).iterator().next();
                vertex.addEdge("Component",in);
            });
        });
        // graph.
       /* Iterable<Edge> edges=graph.getEdgesOfClass("Component+");*/
        graph.commit();
    }

}
