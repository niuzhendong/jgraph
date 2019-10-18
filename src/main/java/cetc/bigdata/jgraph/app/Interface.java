package cetc.bigdata.jgraph.app;

import cetc.bigdata.jgraph.dataSources.JanusTools;
import org.geotools.feature.FeatureCollection;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.janusgraph.core.JanusGraph;

import javax.swing.*;

public class Interface {
    // Create a map content and add our shapefile to it
    private MapContent map = new MapContent();

    public Interface(){

        map.setTitle("shapeTools");
        JMapFrame jMapFrame = new JMapFrame(map);

        jMapFrame.setSize(800,600);

        jMapFrame.enableInputMethods(true);

        // list layers and set them as visible + selected
        jMapFrame.enableLayerTable( true );

        // zoom in, zoom out, pan, show all
        jMapFrame.enableToolBar( true );

        // location of cursor and bounds of current
        jMapFrame.enableStatusBar( true );

        jMapFrame.getToolBar().add("连接",new JButton("连接"));

        // display
        jMapFrame.setVisible( true );

        addShape(new JanusTools().getShapes());
    }


    public void addShape(FeatureCollection featureCollection){
        Style style = SLD.createSimpleStyle(featureCollection.getSchema());
        Layer layer = new FeatureLayer(featureCollection, style);
        map.addLayer(layer);
    }
}
