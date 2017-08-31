package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {

    private static float CAMERA_WIDTH = 8f;
    private static float CAMERA_HEIGHT = 5f;

    private World world;
    public OrthographicCamera camera;
    ShapeRenderer renderer;

    public int width;
    public int height;
    public float ppuX;
    public float ppuY;

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = width / CAMERA_WIDTH;
        ppuY = height / CAMERA_HEIGHT;
    }

    public void setCamera(float x, float y) {
        this.camera.position.set(x, y, 0);
        this.camera.update();
    }

    public WorldRenderer(World world) {
        this.world = world;
        this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        setCamera(CAMERA_HEIGHT / 2f, CAMERA_WIDTH / 2f);

    }

    public void render() {
        drawBricks();
        drawPlayer();
    }

    private void drawBricks() {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Brick brick : world.getBricks()) {
            Rectangle rect = brick.getBounds();
            float x1 = brick.getPosition().x + rect.x;
            float y1 = brick.getPosition().y + rect.y;
            renderer.setColor(new Color(0, 0, 0, 1));
            renderer.rect(x1, y1, rect.width, rect.height);
        }
        renderer.end();
    }

    private void drawPlayer() {

        renderer.setProjectionMatrix(camera.combined);
        Player player = world.getPlayer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        Rectangle rect = player.getBounds();
        float x1 = player.getPosition().x + rect.x;
        float y1 = player.getPosition().y + rect.y;
        renderer.setColor(new Color(1, 0, 0, 1));
        renderer.rect(x1, y1, rect.width, rect.height);
        renderer.end();

    }


}
