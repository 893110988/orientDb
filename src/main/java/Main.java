import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import java.io.*;

/**
 * Created by hushuiwang on 2017/12/4.
 */
public class Main {
    public static void main(String[] args) {

        OrientGraph graph=new OrientGraph("remote:192.168.0.24/Samsung","root","root");
//MobileComponentRelevant MobileSubjectRelevant
        try {
//            for (Vertex v : graph.getVertices("MobileSubjectRelevant.word", "手机")) {
//                System.out.println("Found vertex: " + v.getProperty("word"));
//            }
            FileReader reader =null;
            BufferedReader br=null;
            try {
                reader = new FileReader("/Users/hushuiwang/工作/知识图谱/二级实体.txt");
                br = new BufferedReader(reader);
                String str=null;
                while((str = br.readLine()) != null) {
                    OrientVertex v = graph.addVertex("class:MobileComponentRelevant");
                    v.setProperty("word",str);
                    graph.commit();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }catch (IOException e){
               e.printStackTrace();
            }finally {
                if (br!=null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }finally {
            graph.rollback();
            graph.shutdown();
        }

    }
}
