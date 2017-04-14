package com.darkfig.plasmalivewallpaper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Miguel Martrinez on 2/1/2017.
 */

public class MeshFactory {

    //This method creates a quad mesh subdivided into the desired vertices usingtriangle strips
    public static Mesh createQuadMesh(int dimensionX, int dimensionY){
        int i = 0;
        int index = 0;
        int vertTotal = dimensionX*dimensionY;
        vertTotal*=4;
        float[] verts = new float[vertTotal];
        short[] indeces;
        if(dimensionY <= 2)
            indeces = new short[dimensionX *(dimensionY)  + ((dimensionY -2) * 2)];
        else {
            indeces = new short[dimensionX * (dimensionY - 1) * 2 + ((dimensionY - 2) * 2)];
        }
        float height = Gdx.graphics.getHeight()/(float)(dimensionY-1);
        float width = Gdx.graphics.getWidth()/(float)(dimensionX-1);

        // fill vertices
        for(int y = 0; y < dimensionY; y++){
            for(int x = 0; x < dimensionX; x++){
                verts[i] = x * width; // calculates x pposition
                i++;
                verts[i] = y * height; // calculates y position
                i++;
                verts[i] = (float)(x) / (float)(dimensionX-1);// u coord
                i++;
                verts[i] = (float)(dimensionY - y) / (float)(dimensionY - 1);//v coord
                i++;
            }

        }

        //fill index buffer
        int counter = 0;
        for(int y = 0; y < dimensionY-1; y++){

            for(int x=0; x < dimensionX; x++){

                indeces[index] = (short)counter;
                index++;
                indeces[index] = (short) (counter + dimensionX);
                index++;
                if(x == dimensionX-1 && y != dimensionY-2){
                    indeces[index] = (short) (counter + dimensionX-1);
                    index++;
                    indeces[index] = (short) (counter + 1);
                    index++;
                }
                counter++;
            }
        }


        Mesh mesh = new Mesh(true,vertTotal/4,indeces.length, new VertexAttribute(VertexAttributes.Usage.Position,2, ShaderProgram.POSITION_ATTRIBUTE),
                new VertexAttribute( VertexAttributes.Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0" ));
        mesh.setVertices(verts);
        mesh.setIndices(indeces);
        return mesh;
    }

    public static  Mesh testMesh(){
        float[] verts = new float[6];
        int i = 0;

        //Top Left Vertex Triangle 1
        verts[i++] =0;
        verts[i++] = 0;
        verts[i++] = 0;
        verts[i++] = Gdx.graphics.getHeight();
        verts[i++] = Gdx.graphics.getWidth();
        verts[i++] = 0;


        Mesh mesh = new Mesh( true, 3, 0,
                new VertexAttribute( VertexAttributes.Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE ));
        mesh.setVertices(verts);
        //mesh.setIndices(indeces);
        return mesh;
    }

    public static Mesh testMeshStrip(){
        float[] verts = new float[10];
        short[] indeces = new short[]{0,1,2,3,3,1,1,4,3};
        int i = 0;




        //Top Left Vertex Triangle 1
        verts[i++] =0;
        verts[i++] = 0;
        verts[i++] = 0;
        verts[i++] = Gdx.graphics.getHeight()/4;
        verts[i++] = Gdx.graphics.getWidth();
        verts[i++] = 0;
        verts[i++] = Gdx.graphics.getWidth();
        verts[i++] = Gdx.graphics.getHeight()/4;
        verts[i++] = 0;
        verts[i++] = Gdx.graphics.getHeight()/2;


        Mesh mesh = new Mesh( true, 5, indeces.length,
                new VertexAttribute( VertexAttributes.Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE ));
        mesh.setVertices(verts);
        mesh.setIndices(indeces);
        return mesh;
    }

    public static void createCircleMesh(ShaderProgram shader){
        ShapeRenderer shapeRenderer = new ShapeRenderer(10000,shader);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,0,0,1);
        shapeRenderer.circle(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,100f,100);
        shapeRenderer.end();
    }
}
