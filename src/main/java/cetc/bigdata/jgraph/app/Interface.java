package cetc.bigdata.jgraph.app;

import cetc.bigdata.jgraph.dataSources.JanusTools;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.janusgraph.core.JanusGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Interface {
    // Create a map content and add our shapefile to it
    private MapContent map = new MapContent();

    private JanusTools janusTools = new JanusTools();

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

        JButton jButton = new JButton("连接");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    importShpfile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jMapFrame.getToolBar().add("连接",jButton);

        // display
        jMapFrame.setVisible( true );

        addShape(janusTools.getShapes());
    }


    public void addShape(FeatureCollection featureCollection){
        Style style = SLD.createSimpleStyle(featureCollection.getSchema());
        Layer layer = new FeatureLayer(featureCollection, style);
        map.addLayer(layer);
    }

    public void importShpfile () throws IOException {
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }
        //FileDataStore store = FileDataStoreFinder.getDataStore(file);
        //SimpleFeatureSource featureSource = store.getFeatureSource();

        janusTools.getG().addV("person").property("id", "632127199212200019").property("name", "marko").property("age", 29).next();
    }
}
