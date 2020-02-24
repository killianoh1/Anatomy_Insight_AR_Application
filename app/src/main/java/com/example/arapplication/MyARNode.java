package com.example.arapplication;

//references: youtube tutorial: https://www.youtube.com/watch?v=1lu4PenfVWc

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Pose;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;

import java.util.concurrent.CompletableFuture;

public class MyARNode extends AnchorNode {

    private AugmentedImage image;
    private static CompletableFuture<ModelRenderable> modelRenderableCompletableFuture;
















    private float hand_scale = 0.0f;



    public MyARNode(Context context,int modelId)
    {

        if (modelRenderableCompletableFuture == null) {
            modelRenderableCompletableFuture = ModelRenderable.builder()
                    .setRegistryId("my_model")
                    .setSource(context, modelId)
                    .build();

            //MyARNode infoCard = new MyARNode(context,R.id.planetInfoCard);
            //infoCard.setParent(this);
            //infoCard.setLocalPosition(new Vector3(0.15f, 0.1f, 0.0f));

            /*Node infoCard = new Node();
            infoCard.setParent(this);
            infoCard.setEnabled(false);

            infoCard.setLocalPosition(new Vector3(0f, hand_scale * 0.1f, 0.0f));

            ViewRenderable.builder()
                    .setView(context, R.id.planetInfoCard)
                    .build()
                    .thenAccept(renderable -> {
                        infoCard.setRenderable(renderable);
                    });*/


        }






    }

    public void setImage(AugmentedImage image) {
        this.image = image;
        if (!modelRenderableCompletableFuture.isDone())
        {
            CompletableFuture.allOf(modelRenderableCompletableFuture)
                    .thenAccept((Void aVoid)-> {
                        setImage(image);
                    }).exceptionally(throwable -> {
                        return null;
            });
        }

        setAnchor(image.createAnchor(image.getCenterPose()));



        Node node = new Node();
        node.setParent(this);
        node.setRenderable(modelRenderableCompletableFuture.getNow(null));














        final float maze_edge_size = 5f;
        final float max_image_edge = Math.max(image.getExtentX(), image.getExtentZ());
        hand_scale = max_image_edge / maze_edge_size;

        // Scale Y an extra 10 times to lower the maze wall.
        node.setLocalScale(new Vector3(hand_scale, hand_scale * 0.1f, hand_scale));

    }

    public AugmentedImage getImage() {
        return image;
    }
}
