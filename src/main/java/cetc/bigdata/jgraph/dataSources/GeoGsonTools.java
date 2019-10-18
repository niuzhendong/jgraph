package cetc.bigdata.jgraph.dataSources;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.geotools.geojson.GeoJSON;

import java.util.Map;

public class GeoGsonTools {
        public GeoJSON toGeoGson(GraphTraversal<Vertex, Map<Object, Object>> graphTraversal){
            return new GeoJSON();
        }
}
